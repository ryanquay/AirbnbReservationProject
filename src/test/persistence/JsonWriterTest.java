package persistence;

import model.Airbnb;
import model.Properties;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{


    @Test
    void TestWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\nillegalName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Test passed");
        }
    }

    @Test
    void TestWriterEmptyPropertyList() {
        try {
            Properties pp = new Properties();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPropertyList.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPropertyList.json");
            pp=reader.read();
            assertEquals(0,pp.getProperties().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void TestWriterWithProperties() {
        try {
            Properties pp = new Properties();
            Airbnb house1= new Airbnb("House1");
            Airbnb house2 = new Airbnb("House2");
            pp.addProperties(house1);
            pp.addProperties(house2);
            JsonWriter writer = new JsonWriter("./data/testWriterWithProperties.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithProperties.json");
            pp=reader.read();
            List<Airbnb> houses = pp.getProperties();
            assertEquals(2,houses.size());
            checkProperties(house1.getAirbnbName(),house1.getReservations(),houses.get(0));
            checkProperties(house2.getAirbnbName(),house2.getReservations(),houses.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void TestWriterWithPropertiesWithReservations() {
        try {
            Properties pp = new Properties();
            Airbnb house1= new Airbnb("House1");
            house1.makeReservation("Ryan", 2, 5);
            pp.addProperties(house1);
            JsonWriter writer = new JsonWriter("./data/testWriterWithPropertiesWithReservations.json");
            writer.open();
            writer.write(pp);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterWithPropertiesWithReservations.json");
            pp=reader.read();
            List<Airbnb> houses = pp.getProperties();
            assertEquals(1,houses.size());
            checkProperties(house1.getAirbnbName(),house1.getReservations(),houses.get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




}
