package ui;


import model.Airbnb;
import model.Properties;

import java.util.Scanner;

public class AirbnbApp {
    private Scanner input;
    private String customerName;
    private String airbnbName;
    private Properties propertyList;
    //private Airbnb airbnb;


    public AirbnbApp() {
        initialSetUp();
        runAirbnbApp();
    }

    private void runAirbnbApp() {
        boolean keepGoing = true;
        String command = null;



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
    }

    private void displayLoginMenu() {
        System.out.println("\nWelcome! Please login as: ");
        System.out.println("a -> Admin");
        System.out.println("c -> Customer");
        System.out.println("q -> quit");
    }

    private void processMenuCommand(String command) {
        if (command.equals("a")) {
            displayAdminMenu();
            command = input.next();
            command = command.toLowerCase();
        }
    }

    private void displayAdminMenu() {
        System.out.println("\nSelect from: ");
        System.out.println("s -> See all properties");
        System.out.println("a -> Add Airbnb");
        System.out.println("r -> Remove Airbnb");
        System.out.println("b -> Back");
    }
}
