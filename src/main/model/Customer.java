package model;

//Represents a customer that has a name
public class Customer {
    private String name; //The customer name

    /*
     * REQUIRES: name has a non-zero length
     * EFFECTS: Customer name is set to the name passed as a parameter
     */
    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
