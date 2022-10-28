package persistence;

import model.Properties;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest{

    @Test
    void TestReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/fileDNE");
        try {
            Properties pp = reader.read();
            fail ("IOException expected");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Test passed");
        }
    }

    @Test
    void TestReaderEmptyPropertyList() {
        JsonReader reader = new JsonReader("./data/test");
    }
}
