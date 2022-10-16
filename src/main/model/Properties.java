package model;

import java.util.ArrayList;
import java.util.List;

//Represents a list of all the Airbnb properties that are available
public class Properties {
    private List<Airbnb> properties;
    private String propertiesBooked;

    public Properties() {
        properties = new ArrayList<>();
        propertiesBooked = "";
    }

    public void addProperties(Airbnb airbnb) {
        properties.add(airbnb);
    }

    public boolean removeProperties(String name) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getAirbnbName().equals(name)) {
                properties.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<String> seeAllProperties() {
        List<String> propertyNames = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            propertyNames.add(properties.get(i).getAirbnbName());
        }
        return propertyNames;
    }

    public boolean returnCustomerReservationLocations(String name) {
        propertiesBooked = name + " has reservations at: \n";
        String propertiesBookedTemp = propertiesBooked;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getReservations().contains(name)) {
                propertiesBooked = propertiesBooked + properties.get(i).getAirbnbName() + "\n";
            }
        }
        if (propertiesBooked == propertiesBookedTemp) {
            propertiesBooked = name + " has not booked a reservation at any property.\n";
            return false;
        }
        return true;
    }

    public List<Airbnb> getProperties() {
        return properties;
    }

    public String getPropertiesBooked() {
        return propertiesBooked;
    }
}
