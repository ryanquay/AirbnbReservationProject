package model;

import java.util.ArrayList;
import java.util.List;

//Represents an Airbnb that has a name and a schedule that represents one month
public class Airbnb implements Days {

    private List<String> reservations; //A list that will contain all the days that can be reserved
    private String airbnbName; //The name of the Airbnb

    /*
     * REQUIRES: airbnbName has a non-zero length
     * EFFECTS: reservations is set to have a size of DAY_COUNT;
     *          Set each index in reservations to null; airbnbName
     *          is set to airbnbName passed in as a parameter
     */
    public Airbnb(String airbnbName) {
        reservations = new ArrayList<>(DAY_COUNT);
        for (int i = 0; i < DAY_COUNT; i++) {
            reservations.add(null);
        }
        this.airbnbName = airbnbName;
    }

    /*
     * MODIFIES: this
     * EFFECTS: The available and reserved dates are added to
     *          displayInformation, which is returned
     */
    public String displayReservationInformation() {
        String displayInformation = this.airbnbName + " Reservation Info\n_______________________\n\n";
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

    /*
     * REQUIRES: name has a non-zero length; checkInDate >=1
     *           and checkInDate <= DAY_COUNT; checkOutDate >= 1
     *           and checkOutDate <= DAY_COUNT; checkInDate <= checkOutDate
     * MODIFIES: this
     * EFFECTS: Returns false if you cannot make a reservation
     *          and true if you can
     */
    public boolean makeReservation(String name, int checkInDate, int checkOutDate) {
        if (checkInDate < 1 || checkOutDate > DAY_COUNT || checkOutDate < checkInDate) {
            System.out.println("Please choose appropriate check in and/or check out dates.\n");
            return false;
        }
        for (int i = checkInDate; i < checkOutDate; i++) {
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

    /*
     * *The REQUIRES method is not necessary I think because the
     * makeReservation method already checks them before calling this method*
     * MODIFIES: this
     * EFFECTS: Adds the customer's name to their chosen reservation dates and returns true
     */
    public boolean reserveDays(String name, int checkInDate, int checkOutDate, List<String> reservations) {
        for (int i = checkInDate - 1; i <= checkOutDate - 1; i++) {
            reservations.set(i, name);
        }
        return true;
    }

    /*
     * REQUIRES: name has a non-zero length
     * MODIFIES: this
     * EFFECTS: Removes all reservations make by customer name and returns true.
     *          Returns false if customer name has no reservations to cancel
     */
    public boolean cancelReservation(String name) {
        if (!reservations.contains(name)) {
            System.out.println("You have no reservations to cancel.\n");
            return false;
        }
        for (int i = 0; i < DAY_COUNT; i++) {
            if (name.equals(reservations.get(i))) {
                reservations.set(i, null);
            }
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
