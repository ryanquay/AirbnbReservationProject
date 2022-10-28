package persistence;

import model.Properties;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

//Represents a writer that writes JSON representation of properties to file
public class JsonWriter {
    private static final int SPACING = 4;
    private PrintWriter writer;
    private String fileLocation;

    //EFFECTS: Constructs writer to write to file location
    public JsonWriter(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    //MODIFIES: this
    //EFFECTS: Opens writer and throws FileNotFoundException if file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(fileLocation));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON representation of workroom to file
    public void write(Properties pp) {
        JSONObject json = pp.toJson();
        saveToFile(json.toString(SPACING));
    }

    //MODIFIES: this
    //EFFECTS: Closes writer
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: Writes string to file
    public void saveToFile(String json) {
        writer.print(json);
    }
}
