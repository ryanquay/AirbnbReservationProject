package persistence;

import model.Airbnb;
import model.Properties;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void TestReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDNE");
        try {
            Properties pp = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Test passed");
        }
    }

    @Test
    void TestReaderEmptyPropertyList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPropertyList.json");
        try {
            Properties pp = reader.read();
            assertEquals(0, pp.getProperties().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void TestReaderWithProperties() {
        JsonReader reader = new JsonReader("./data/testReaderWithProperties.json");

        try {
            Airbnb testHouse = new Airbnb("Test");
            List<String> blankReservations = testHouse.getReservations();

            Properties pp= reader.read();
            List<Airbnb> houses = pp.getProperties();
            assertEquals(2, houses.size());
            checkProperties("House1",blankReservations,houses.get(0));
            checkProperties("House2", blankReservations, houses.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void TestReaderWithPropertiesWithReservations() {
        JsonReader reader = new JsonReader("./data/testReaderWithPropertiesWithReservations.json");

        try {
            Airbnb testHouse = new Airbnb("Test");
            testHouse.makeReservation("Ryan",2,5);
            List<String> reservations = testHouse.getReservations();

            Properties pp= reader.read();
            List<Airbnb> houses = pp.getProperties();
            assertEquals(1,houses.size());
            checkProperties("House1",reservations,houses.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
