package model;

import java.util.ArrayList;
import java.util.List;

public class Airbnb {
    private static final int DAY_COUNT = 31;
    private List<String> reservations;

    public Airbnb() {
        reservations = new ArrayList<>(DAY_COUNT);
        for (int i = 0; i < DAY_COUNT; i++) {
            reservations.add(null);
        }
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

    public List<String> getReservations() {
        return reservations;
    }
}
