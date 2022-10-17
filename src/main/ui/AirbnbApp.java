package ui;


import model.Airbnb;
import model.Properties;

import java.util.Scanner;

public class AirbnbApp {
    private Scanner input;
    private String customerName;
    private String airbnbName;
    private Properties propertyList;
    private boolean menuKeepGoing;
    private Airbnb customerAirbnb;
    private int checkIn;
    private int checkOut;


    public AirbnbApp() {
        initialSetUp();
        runAirbnbApp();
    }

    private void runAirbnbApp() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            displayLoginMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMenuCommand(command);
            }
        }
    }

    private void initialSetUp() {
        propertyList = new Properties();
        propertyList.addProperties(new Airbnb("House1"));
        input = new Scanner(System.in);

    }

    private void displayLoginMenu() {
        System.out.println("\nWelcome! Please login as: ");
        System.out.println("a -> Admin");
        System.out.println("c -> Customer");
        System.out.println("q -> quit");
    }

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
            customerName = input.nextLine();
            while (menuKeepGoing) {
                displayCustomerMenu();
                command = input.next();
                command = command.toLowerCase();
                processCustomerCommands(command);
            }

        }
    }

    private void displayAdminMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("s -> See all properties");
        System.out.println("a -> Add Airbnb");
        System.out.println("r -> Remove Airbnb");
        System.out.println("b -> Back");
    }

    private void processAdminCommands(String command) {
        if (command.equals("s")) {
            System.out.println(propertyList.seeAllProperties());
        } else if (command.equals("a")) {
            System.out.println("Enter a name for the new Airbnb: ");
            input.nextLine();
            airbnbName = input.nextLine();
            propertyList.addProperties(new Airbnb(airbnbName));
        } else if (command.equals("r")) {
            System.out.println("Enter the name of the Airbnb you want to remove: ");
            input.nextLine();
            airbnbName = input.nextLine();
            propertyList.removeProperties(airbnbName);
        } else if (command.equals("b")) {
            menuKeepGoing = false;
            runAirbnbApp();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayCustomerMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("s -> See all available properties");
        System.out.println("m -> Make reservations");
        System.out.println("c -> Cancel reservations");
        System.out.println("v -> View properties that you have reservations for");
        System.out.println("b -> Back");
    }

    private void processCustomerCommands(String command) {
        if (command.equals("s")) {
            System.out.println(propertyList.seeAllProperties());
        } else if (command.equals("m")) {
            makeReservation();
        } else if (command.equals("c")) {
            cancelReservations();
        } else if (command.equals("v")) {
            propertyList.returnCustomerReservationLocations(customerName);
        } else if (command.equals("b")) {
            menuKeepGoing = false;
            runAirbnbApp();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void makeReservation() {
        System.out.println("Choose the Airbnb name you want to make reservations at: ");
        input.nextLine();
        airbnbName = input.nextLine();
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
            makeReservation();
        }


    }

    private void reserveDates(Airbnb chosenAirbnb) {
        System.out.println(chosenAirbnb.displayReservationInformation());
        System.out.println("Choose your check in date: ");
        checkIn = input.nextInt();
        System.out.println("Choose your check out date: ");
        checkOut = input.nextInt();
        chosenAirbnb.makeReservation(customerName, checkIn, checkOut);
    }

    private void cancelReservations() {
        System.out.println("Choose the Airbnb name you want to cancel your reservations at: ");
        airbnbName = input.nextLine();
        for (int i = 0; i < propertyList.getProperties().size(); i++) {
            if (propertyList.getProperties().get(i).getAirbnbName().equals(airbnbName)) {
                customerAirbnb = propertyList.getProperties().get(i);
                break;
            }
        }
        customerAirbnb.cancelReservation(airbnbName);
    }
}
