package persistence;

import model.Properties;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {


    @Test
    void TestWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\nillegalName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            e.printStackTrace();
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



}
