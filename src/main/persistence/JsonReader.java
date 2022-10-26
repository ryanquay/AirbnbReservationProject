package persistence;

import model.Airbnb;
import model.Properties;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class JsonReader {
    private String fileLocation;

    public JsonReader(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public Properties read() throws IOException {
        String jsonData = readFile(fileLocation);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProperties(jsonObject);
    }

    private String readFile(String fileLocation) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(fileLocation), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();

    }

    private Properties parseProperties(JSONObject jsonObject) {
        Properties pp = new Properties();
        addProperties(pp, jsonObject);
        return pp;
    }

    private void addProperties(Properties pp, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(pp, nextProperty);
        }

    }

    private void addProperty(Properties pp, JSONObject jsonObject) {
        String name = jsonObject.getString("airbnbName");
        Airbnb airbnb = new Airbnb(name);
        JSONArray reservations = jsonObject.getJSONArray("reservations");
        List reservationList = airbnb.getReservations();
        reservationList = reservations.toList();
        airbnb.setReservations(reservationList);
        pp.addProperties(airbnb);

    }
}
