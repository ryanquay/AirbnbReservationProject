package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirbnbTest {
    private Airbnb airbnb;

    @BeforeEach
    void setUp() {
        airbnb = new Airbnb();
    }

    @Test
    void TestConstructor() {
        assertEquals(airbnb.getReservations().size(),31);
    }

    @Test
    void TestDisplayReservationInfoNoReservations() {
        System.out.println(airbnb.displayReservationInformation());
    }
}