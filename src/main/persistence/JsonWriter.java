package persistence;

import model.Properties;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int SPACING = 4;
    private PrintWriter writer;
    private String fileLocation;

    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    public void write(Properties pp) {
        JSONObject json = pp.toJson();
        saveToFile(json.toString(SPACING));
    }

    public void close() {
        writer.close();
    }

    public void saveToFile(String json) {
        writer.print(json);
    }
}
