package ui;


import model.Airbnb;
import model.Properties;

import java.util.Locale;
import java.util.Scanner;

public class AirbnbApp {
    private Scanner input;
    private String customerName;
    private String airbnbName;
    private Properties propertyList;
    //private Airbnb airbnb;


    public AirbnbApp() {
        runAirbnbApp();
    }

    private void runAirbnbApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayLoginMenu();
            command = input.next();


            if (command.equalsIgnoreCase("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    private void initialSetUp() {
        propertyList = new Properties();
        propertyList.addProperties(new Airbnb("House1"));
    }

    private void displayLoginMenu() {
        System.out.println("Welcome! Please login as: ");
        System.out.println("a -> Admin");
        System.out.println("c -> Customer");
        System.out.println("q -> quit");
    }

    private void processCommand(String command) {

    }
}
