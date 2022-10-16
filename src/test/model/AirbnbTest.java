package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirbnbTest {
    private Airbnb airbnb1;
    private Customer customer;
    private Properties properties;
    private List<String> propertyNames;
    private int checkIn;
    private int checkOut;


    @BeforeEach
    void setUp() {
        airbnb1 = new Airbnb("House1");
        customer = new Customer("Ryan Quay");
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
    void TestSeeAllProperties() {
        List<String> propertyNames = new ArrayList<>();
        Airbnb airbnb2 = new Airbnb("House 2");
        properties.addProperties(airbnb1);
        properties.addProperties(airbnb2);
        Collections.addAll(propertyNames, airbnb1.getAirbnbName(), airbnb2.getAirbnbName());
        assertEquals(properties.seeAllProperties(), propertyNames);
        System.out.println(properties.seeAllProperties());

    }

    @Test
    void TestMakeReservation() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test
    void TestMakeMultipleReservations() {
        checkIn = 2;
        checkOut = 5;
        assertTrue(airbnb1.makeReservation(customer.getName(), checkIn, checkOut));
        checkIn = 18;
        checkOut = 22;
        assertTrue(airbnb1.makeReservation(customer.getName(), checkIn, checkOut));
        System.out.println(airbnb1.displayReservationInformation());
    }

    @Test

    @Test
    void TestInappropriateDaysReserve() {
        checkIn = 0;
        checkOut = 5;
        assertFalse(airbnb1.makeReservation(customer.getName(), checkIn, checkOut));
        checkIn = 22;
        checkOut = 32;
        assertFalse(airbnb1.makeReservation(customer.getName(), checkIn, checkOut));
        checkIn = 7;
        checkOut = 4;
        assertFalse(airbnb1.makeReservation(customer.getName(), checkIn, checkOut));
    }

    @Test
    void TestDatesAlreadyReserved() {
        checkIn = 4;
        checkOut = 8;
    }


}


