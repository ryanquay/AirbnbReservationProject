package persistence;

import model.Airbnb;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {

    //EFFECTS: Checks to see if this read from a file is as expected
    protected void checkProperties(String name, List<String> reservations, Airbnb airbnb) {
        assertEquals(name,airbnb.getAirbnbName());
        assertEquals(reservations,airbnb.getReservations());
    }
}
