package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AirbnbTest {
    private Airbnb airbnb1;
    private Customer customer;
    private Properties properties;


    @BeforeEach
    void setUp() {
        airbnb1 = new Airbnb("House1");
        customer= new Customer ("Ryan");
        properties = new Properties();

    }

    @Test
    void TestConstructor() {
        assertEquals(airbnb1.getReservations().size(),31);
    }

    @Test
    void TestDisplayReservationInfoNoReservations() {
        System.out.println(airbnb1.displayReservationInformation());
    }
    @Test
    void TestSeeAllProperties() {
        List<String> propertyNames= new ArrayList<>();
        Airbnb airbnb2 = new Airbnb("House 2");
        properties.addProperties(airbnb1);
        properties.addProperties(airbnb2);
        Collections.addAll(propertyNames, airbnb1.getAirbnbName(), airbnb2.getAirbnbName());
        assertEquals(properties.seeAllProperties(), propertyNames);
        System.out.println(properties.seeAllProperties());

    }


}


