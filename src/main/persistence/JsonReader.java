package persistence;

import model.Airbnb;
import model.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

//Represents a reader that reads Properties from JSON data stored in file
public class JsonReader {
    private String fileLocation;

    //EFFECTS: Constructs reader to read from file location
    public JsonReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    //EFFECTS: Reads properties from file and returns it;
    // throws IOException if error occurs reading data from file
    public Properties read() throws IOException {
        String jsonData = readFile(fileLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProperties(jsonObject);
    }

    //EFFECTS: Reads file as a string and returns it
    private String readFile(String fileLocation) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();

    }

    //EFFECTS: Parses properties from JSON object and returns it
    private Properties parseProperties(JSONObject jsonObject) {
        Properties pp = new Properties();
        addProperties(pp, jsonObject);
        return pp;
    }

    //MODIFIES: pp
    //EFFECTS: Parses properties from JSON object and adds them to properties
    private void addProperties(Properties pp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(pp, nextProperty);
        }

    }

    //MODIFIES: pp
    //EFFECTS: Parses airbnb from JSON object and adds it to properties
    private void addProperty(Properties pp, JSONObject jsonObject) {
        String name = jsonObject.getString("airbnbName");
        Airbnb airbnb = new Airbnb(name);
        JSONArray reservations = jsonObject.getJSONArray("reservations");
        List reservationList;
        reservationList = reservations.toList();
        airbnb.setReservations(reservationList);
        pp.addProperties(airbnb);

    }
}
