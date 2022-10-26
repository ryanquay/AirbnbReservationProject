package ui;


import model.Airbnb;
import model.Customer;
import model.Properties;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//ISSUES WITH QUITTING AFTER PROCESSING SOME INPUTS WITH SCANNER. ASK TA.


//Airbnb application
public class AirbnbApp {
    private Scanner input; //Read inputs
    private String customerName; //Store customer name
    private String airbnbName; //Store Airbnb name
    private Properties propertyList; //Store Airbnbs
    private boolean menuKeepGoing; //Used to keep application running
    private Airbnb customerAirbnb; //The airbnb a customer chooses to make reservations or cancellations
    private int checkIn; //Store check in day
    private int checkOut; //Store check out day

    private static final String JSON_STORE = "./data/properties.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Runs initial set up and Airbnb application
    public AirbnbApp() {
        initialSetUp();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runAirbnbApp();
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input
     */
    private void runAirbnbApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayLoginMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                System.out.println("Goodbye!");
                input.close();
                System.exit(0);

            } else {
                processMenuCommand(command);
            }
        }

    }

    /*
     * MODIFIES: this
     * EFFECTS: Add an initial Airbnb, so it does not start with zero properties
     */
    private void initialSetUp() {
        propertyList = new Properties();
        propertyList.addProperties(new Airbnb("House1"));
        input = new Scanner(System.in);
        input.useDelimiter("\n");


    }

    // EFFECTS: Displays the login menu options to user
    private void displayLoginMenu() {
        System.out.println("\nWelcome! Select from: ");
        System.out.println("a -> Admin");
        System.out.println("c -> Customer");
        System.out.println("s -> save properties to file");
        System.out.println("l -> load properties from file");
        System.out.println("q -> quit");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input for login menu options
     */
    private void processMenuCommand(String command) {
        menuKeepGoing = true;
        if (command.equals("a")) {
            while (menuKeepGoing) {
                displayAdminMenu();
                command = input.next();
                command = command.toLowerCase();
                processAdminCommands(command);
            }
        } else if (command.equals("c")) {
            System.out.println("Please enter your first and last name: ");
            customerName = input.next();
            while (menuKeepGoing) {
                displayCustomerMenu();
                command = input.next();
                command = command.toLowerCase();
                processCustomerCommands(command);
            }

        } else if (command.equals("s")) {
            saveProperties();
        } else if (command.equals("l")) {
            loadProperties();
        }
    }

    // EFFECTS: Displays menu options for admin only
    private void displayAdminMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("s -> See all properties");
        System.out.println("a -> Add Airbnb");
        System.out.println("r -> Remove Airbnb");
        System.out.println("b -> Back");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input for admin menu options
     */
    private void processAdminCommands(String command) {
        if (command.equals("s")) {
            System.out.println(propertyList.seeAllProperties());
        } else if (command.equals("a")) {
            System.out.println("Enter a name for the new Airbnb: ");
            airbnbName = input.next();
            propertyList.addProperties(new Airbnb(airbnbName));
        } else if (command.equals("r")) {
            System.out.println("Enter the name of the Airbnb you want to remove: ");
            airbnbName = input.next();
            propertyList.removeProperties(airbnbName);
        } else if (command.equals("b")) {
            menuKeepGoing = false;
            runAirbnbApp();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: Displays menu options for customers only
    private void displayCustomerMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("s -> See all available properties");
        System.out.println("m -> Make reservations");
        System.out.println("c -> Cancel reservations");
        System.out.println("v -> View properties that you have reservations for");
        System.out.println("b -> Back");
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input for customer menu options
     */
    private void processCustomerCommands(String command) {
        if (command.equals("s")) {
            System.out.println(propertyList.seeAllProperties());
        } else if (command.equals("m")) {
            makeReservation();
        } else if (command.equals("c")) {
            cancelReservations();
        } else if (command.equals("v")) {
            propertyList.returnCustomerReservationLocations(customerName);
            System.out.println(propertyList.getPropertiesBooked());
        } else if (command.equals("b")) {
            menuKeepGoing = false;
            runAirbnbApp();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input to determine if they can make a reservation
     */
    private void makeReservation() {
        System.out.println("Choose the Airbnb name you want to make reservations at: ");
        airbnbName = input.next();
        if (propertyList.airbnbExists(airbnbName)) {
            for (int i = 0; i < propertyList.getProperties().size(); i++) {
                if (propertyList.getProperties().get(i).getAirbnbName().equals(airbnbName)) {
                    customerAirbnb = propertyList.getProperties().get(i);
                    break;
                }
            }
            reserveDates(customerAirbnb);
        } else {
            System.out.println("List of Airbnb names: ");
            System.out.println(propertyList.seeAllProperties());

        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: Reserves dates for customers
     */
    private void reserveDates(Airbnb chosenAirbnb) {
        System.out.println(chosenAirbnb.displayReservationInformation());
        System.out.println("Choose your check in date: ");
        checkIn = input.nextInt();
        System.out.println("Choose your check out date: ");
        checkOut = input.nextInt();
        chosenAirbnb.makeReservation(customerName, checkIn, checkOut);
    }

    /*
     * MODIFIES: this
     * EFFECTS: Processes user input and determines if they can cancel reservations
     */
    private void cancelReservations() {
        System.out.println("Choose the Airbnb name you want to cancel your reservations at: ");
        airbnbName = input.next();
        for (int i = 0; i < propertyList.getProperties().size(); i++) {
            if (propertyList.getProperties().get(i).getAirbnbName().equals(airbnbName)) {
                customerAirbnb = propertyList.getProperties().get(i);
                break;
            }
        }
        customerAirbnb.cancelReservation(customerName);
    }

    private void saveProperties() {
        try {
            jsonWriter.open();
            jsonWriter.write(propertyList);
            jsonWriter.close();
            System.out.println("Saved properties to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    private void loadProperties() {
        try {
            propertyList = jsonReader.read();
            System.out.println("Loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
