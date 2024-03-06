package persistence;

import model.Database;
import model.Restaurant;
import model.Location;
import model.MenuItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Database from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Database read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDatabase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Database from JSON object and returns it
    private Database parseDatabase(JSONObject jsonObject) {
        //String name = jsonObject.getString("name");
        Database db = new Database();
        addDatabase(db, jsonObject);
        return db;
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // MODIFIES: database
    // EFFECTS: parses restaurants from JSON object and adds them to database
    private void addDatabase(Database db, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dataBase");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(db, nextRestaurant);
        }
    }

    // MODIFIES: database
    // EFFECTS: parses menu item from JSON object and adds it to restaurant
    private void addMenuItem(Restaurant r, JSONObject jsonObject) {
        String foodName = jsonObject.getString("foodName");
        Double price = jsonObject.getDouble("price");
        MenuItem item = new MenuItem(foodName, price);
        r.addToMenu(item);
    }

    // MODIFIES: database
    // EFFECTS: parses Location from JSON object and returns it
    private Location parseLocation(JSONObject jsonObject) {
        String cityName = jsonObject.getString("cityName");
        Double coordX = jsonObject.getDouble("coordX");
        Double coordY = jsonObject.getDouble("coordY");
        Location location = new Location(cityName, coordX, coordY);
        return location;
    }

    // MODIFIES: database
    // EFFECTS: parses restaurant from JSON object and adds it to database
    private void addRestaurant(Database db, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String genre = jsonObject.getString("genre");
        int rating = jsonObject.getInt("rating");
        Location location = parseLocation((JSONObject) jsonObject.get("location"));

        Restaurant restaurant = new Restaurant(name, genre, rating, location);

        boolean favourite = jsonObject.getBoolean("favourite");
        if (favourite) {
            restaurant.toggleFavourite();
        }

        JSONArray jsonArray = jsonObject.getJSONArray("menu");
        for (Object json : jsonArray) {
            JSONObject nextMenuItem = (JSONObject) json;
            addMenuItem(restaurant, nextMenuItem);
        }

        db.addRestaurant(restaurant);
    }

}
