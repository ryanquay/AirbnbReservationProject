package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a list of all the Airbnb properties that are available
public class Properties implements Writable {
    private List<Airbnb> properties; //A list that stores Airbnb objects
    private String propertiesBooked; //Keeps track of which properties a customer has reservations at


    /*
     * EFFECTS: propertiesBooked set to have an empty String
     */
    public Properties() {
        properties = new ArrayList<>();
        propertiesBooked = "";
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds an airbnb to properties and returns true;
     *          If there already exists one with the same name,
     *          return false
     */
    public boolean addProperties(Airbnb airbnb) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getAirbnbName().equals(airbnb.getAirbnbName())) {
                System.out.println("You already have a property with the same name.\n");
                return false;
            }
        }
        properties.add(airbnb);
        System.out.println("Airbnb added.");
        return true;
    }

    /*
     * REQUIRES: name has a non-zero length
     * MODIFIES: this
     * EFFECTS: If an airbnb name is equals to name
     *          it is removed and returns true. Returns
     *          false otherwise.
     */
    public boolean removeProperties(String name) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getAirbnbName().equalsIgnoreCase(name)) {
                properties.remove(i);
                System.out.println(name + " removed.");
                return true;
            }
        }
        System.out.println("There are no Airbnb with the name " + name);
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: Adds all the airbnb names to property
     *          names and returns it
     */
    public List<String> seeAllProperties() {
        List<String> propertyNames = new ArrayList<>();
        for (int i = 0; i < properties.size(); i++) {
            propertyNames.add(properties.get(i).getAirbnbName());
        }
        return propertyNames;
    }

    /*
     * REQUIRES: name has a non-zero length
     * MODIFIES: this
     * EFFECTS: If an airbnb has reservations that contains
     *          the customer's name, add the airbnb name to
     *          propertiesBooked and return true. Return false
     *          if the customer has no reservations at all
     */
    public boolean returnCustomerReservationLocations(String name) {
        propertiesBooked = name + " has reservations at: \n";
        String propertiesBookedTemp = propertiesBooked;
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getReservations().contains(name)) {
                propertiesBooked = propertiesBooked + properties.get(i).getAirbnbName() + "\n";
            }
        }
        if (propertiesBooked.equalsIgnoreCase(propertiesBookedTemp)) {
            propertiesBooked = name + " has not booked a reservation at any property.\n";
            return false;
        }
        return true;
    }

    public boolean airbnbExists(String name) {
        for (int i = 0; i < properties.size(); i++) {
            if (properties.get(i).getAirbnbName().equals(name)) {
                return true;
            }
        }
        System.out.println("This Airbnb name does not exist.");
        return false;
    }

    public List<Airbnb> getProperties() {
        return properties;
    }

    public String getPropertiesBooked() {
        return propertiesBooked;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("properties", properties);
        return json;
    }
}
