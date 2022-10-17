package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirbnbTest {
    private Airbnb airbnb1;
    private Airbnb airbnb2;
    private Customer customer1;
    private Customer customer2;
    private Properties properties;
    private List<String> propertyNames;
    private int checkIn;
    private int checkOut;


    @BeforeEach
    void setUp() {
        airbnb1 = new Airbnb("House1");
        airbnb2 = new Airbnb("House2");
        customer1 = new Customer("Ryan Quay");
        customer2 = new Customer("Bobby Bob");
        properties = new Properties();
        propertyNames = new ArrayList<>();

    }

    @Test
    void TestConstructor() {
        assertEquals(airbnb1.getReservations().size(), 31);
    }

    @Test
    void TestDisplayReservationInfoNoReservations() {
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestAddAndSeeAllProperties() {
        assertTrue(properties.addProperties(airbnb1));
        assertTrue(properties.addProperties(airbnb2));
        Collections.addAll(propertyNames, airbnb1.getAirbnbName(), airbnb2.getAirbnbName());
        assertEquals(properties.seeAllProperties(), propertyNames);
        System.out.println(properties.seeAllProperties());
    }

    @Test
    void TestRemoveProperties() {
        assertTrue(properties.addProperties(airbnb1));
        assertTrue(properties.addProperties(airbnb2));
        assertTrue(properties.removeProperties("House1"));
        System.out.println(properties.seeAllProperties());
    }

    @Test
    void TestMakeReservation() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestMakeReservationAllDays() {
        checkIn = 1;
        checkOut = 31;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestMakeMultipleReservations() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        checkIn = 18;
        checkOut = 22;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestMakeReservationsInDifferentAirbnb() {
        properties.addProperties(airbnb1);
        properties.addProperties(airbnb2);
        checkIn = 2;
        checkOut = 5;
        assertTrue(properties.getProperties().get(0).makeReservation(customer1.getName(), checkIn, checkOut));
        assertTrue(properties.getProperties().get(1).makeReservation(customer1.getName(), checkIn, checkOut));
        System.out.println(properties.getProperties().get(0).displayReservationInformation());
        System.out.println(properties.getProperties().get(1).displayReservationInformation());
    }

    @Test
    void TestMakeReservationWithDifferentCustomers() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        checkIn = 12;
        checkOut = 15;
        assertTrue(airbnb1.makeReservation(customer2.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestViewCustomerReservationLocations() {
        properties.addProperties(airbnb1);
        properties.addProperties(airbnb2);
        checkIn = 2;
        checkOut = 5;
        assertTrue(properties.getProperties().get(0).makeReservation(customer1.getName(), checkIn, checkOut));
        assertTrue(properties.getProperties().get(1).makeReservation(customer1.getName(), checkIn, checkOut));
        assertTrue(properties.returnCustomerReservationLocations(customer1.getName()));
        System.out.println(properties.getPropertiesBooked());
    }

    @Test
    void TestNoReservationsViewCustomerLocations() {
        assertFalse(properties.returnCustomerReservationLocations(customer1.getName()));
        System.out.println(properties.getPropertiesBooked());
    }

    @Test
    void TestCancelReservations() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        checkIn = 11;
        checkOut = 15;
        assertTrue(airbnb1.makeReservation(customer2.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
        assertTrue(airbnb1.cancelReservation(customer1.getName()));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestCancelMultipleReservations() {
        checkIn = 1;
        checkOut = 4;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        checkIn = 28;
        checkOut = 31;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
        assertTrue(airbnb1.cancelReservation(customer1.getName()));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestUnableToCancelReservations() {
        assertFalse(airbnb1.cancelReservation(customer1.getName()));
    }


    @Test
    void TestInappropriateDaysReserve() {
        checkIn = 0;
        checkOut = 5;
        assertFalse(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        checkIn = 22;
        checkOut = 32;
        assertFalse(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        checkIn = 7;
        checkOut = 4;
        assertFalse(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
    }

    @Test
    void TestDatesAlreadyReserved() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        assertFalse(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
    }

    @Test
    void TestDatesAlreadyReservedByDifferentCustomer() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer1.getName(), checkIn, checkOut));
        assertFalse(airbnb1.makeReservation(customer2.getName(), checkIn, checkOut));
    }


}


