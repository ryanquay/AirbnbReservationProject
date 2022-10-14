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
}
