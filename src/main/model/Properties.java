package model;

import java.util.ArrayList;
import java.util.List;

public class Properties implements Days {
    private List<Airbnb> properties;

    public Properties() {
        properties = new ArrayList<>();
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

    public void printCustomerReservationInfo(String name) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getReservations().contains(name)) {
                for (int)
            }
        }
    }

    public List<Airbnb> getProperties() {
        return properties;
    }
}
