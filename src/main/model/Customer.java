package model;

public class Customer {
    private String name;
    private int customerID;

    public Customer(String name, int customerID) {
        this.name = name;
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public int getCustomerID() {
        return customerID;
    }
}
