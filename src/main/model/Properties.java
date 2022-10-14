package model;

import java.util.ArrayList;
import java.util.List;

public class Properties {
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


}
