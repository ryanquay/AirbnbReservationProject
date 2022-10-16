package model;

import java.util.ArrayList;
import java.util.List;

public class Properties implements Days {
    private List<Airbnb> properties;
    private String propertiesBooked;

    public Properties() {
        properties = new ArrayList<>();
        propertiesBooked = "";
    }

    public void addProperties(Airbnb airbnb) {
        properties.add(airbnb);
    }

    public List<String> seeAllProperties() {
        List<String> propertyNames = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            propertyNames.add(properties.get(i).getAirbnbName());
        }
        return propertyNames;
    }

    public boolean returnCustomerReservationLocations(String name) {
        String propertiesBooked = name + " has reservations at: \n";
        String propertiesBookedTemp = propertiesBooked;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getReservations().contains(name)) {
                propertiesBooked = propertiesBooked + properties.get(i).getAirbnbName() + "\n";
            }
        }
        if (propertiesBooked == propertiesBookedTemp) {
            propertiesBooked = name + "has not booked a reservation at any property.\n";
            return false;
        }
        return true;
    }

    public List<Airbnb> getProperties() {
        return properties;
    }
}
