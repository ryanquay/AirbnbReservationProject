package model;

import java.util.ArrayList;
import java.util.List;

//Represents an Airbnb that has dates for reservation
public class Airbnb {
    private static final int DAY_COUNT = 31;
    private List<String> reservations;
    private String airbnbName;

    public Airbnb(String airbnbName) {
        reservations = new ArrayList<>(DAY_COUNT);
        for (int i = 0; i < DAY_COUNT; i++) {
            reservations.add(null);
        }
        this.airbnbName = airbnbName;
    }

    public String displayReservationInformation() {
        String displayInformation = "Airbnb Reservation Info\n_______________________\n\n";
        for (int i = 0; i < DAY_COUNT; i++) {
            displayInformation = displayInformation + (i + 1) + ": ";
            if (reservations.get(i) == null) {
                displayInformation = displayInformation + "Available\n";
            } else {
                displayInformation = displayInformation + reservations.get(i) + "\n";
            }
        }
        return displayInformation;
    }

    public boolean makeReservation(String name, int checkInDate, int checkOutDate) {
        if (checkInDate < 1 || checkOutDate > DAY_COUNT || checkOutDate < checkInDate) {
            System.out.println("Please choose appropriate check in and/or check out dates.\n");
            return false;
        }
        for (int i = checkInDate; i <= checkOutDate; i++) {
            if (reservations.get(i) == name) {
                System.out.println("You have already reserved at least one of your chosen dates. \n"
                        + "Please choose a timeframe of available days.\n");
                return false;
            } else if (reservations.get(i) != null) {
                System.out.println("At least one of your chosen days have been reserved by another person. \n"
                        + "Please choose a timeframe of available days.\n");
                return false;
            }
        }
        return reserveDays(name, checkInDate, checkOutDate, reservations);
    }

    public boolean reserveDays(String name, int checkInDate, int checkOutDate, List<String> reservations) {
        for (int i = checkInDate - 1; i <= checkOutDate - 1; i++) {
            reservations.set(i, name);
        }
        return true;
    }


    public List<String> getReservations() {
        return reservations;
    }

    public String getAirbnbName() {
        return airbnbName;
    }
}
