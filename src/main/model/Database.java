package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import model.event.EventLog;
import model.event.Event;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

// This class stores a list of restaurants
public class Database implements Writable {
    private ArrayList<Restaurant> dataBase;
    private ArrayList<Restaurant> processedDataBase; //Database after sort and filter

    public Database() {
        dataBase = new ArrayList<Restaurant>();
        processedDataBase = new ArrayList<Restaurant>();
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: makes both databases same again
    public void resetProcessedDataBase() {
        processedDataBase.clear();
        processedDataBase.addAll(dataBase);
        EventLog.getInstance().logEvent(new Event("Reset database to original state"));
    }

    public ArrayList<Restaurant> getDataBase() {
        return dataBase;
    }

    public ArrayList<Restaurant> getProcessedDataBase() {
        return processedDataBase;
    }

    //REQUIRES: no repeat restaurant objects
    //MODIFIES: this
    //EFFECTS: adds a new restaurant to the database
    public void addRestaurant(Restaurant restaurant) {
        dataBase.add(restaurant);
        EventLog.getInstance().logEvent(new Event("Added new restaurant: " + restaurant.getName()));
    }

    //REQUIRES: name and cityName must not match another name and cityName
    //MODIFIES: this
    //EFFECTS: adds a new restaurant to the database
    public void addRestaurant(String name, String genre, int rating, Location location) {
        Restaurant restaurant = new Restaurant(name, genre, rating, location);
        dataBase.add(restaurant);
        EventLog.getInstance().logEvent(new Event("Added new restaurant: " + name));
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: finds position of restaurant in processedDataBase, -1 if unable to find it.
    public int searchDatabase(String name, String cityName) {
        EventLog.getInstance().logEvent(new Event("Attempted search on database"));
        for (Restaurant res: processedDataBase) {
            if (name.equals(res.getName().toLowerCase())
                    && cityName.equals(res.getLocation().getCityName().toLowerCase())) {
                return dataBase.indexOf(res);
            }
        }
        return -1;
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: Chooses a random restaurant from the given dataBase (processed or not)
    public Restaurant randomRestaurant(ArrayList dataBase) {
        EventLog.getInstance().logEvent(new Event("Selected a restaurant at random"));
        int max = dataBase.size();
        int randomPos = (int) Math.floor(Math.random() * (max - 0) + 0);
        return (Restaurant) dataBase.get(randomPos);
    }

    //REQUIRES: string must match one of the comparable fields of restaurant
    //MODIFIES: this
    //EFFECTS: sorts the list by specified information in ascending order
    public void sortDatabaseAscending(String sortBy) {
        EventLog.getInstance().logEvent(new Event("Sorted database in ascending order by: " + sortBy));
        if (sortBy.equals("name")) {
            Collections.sort(processedDataBase, Comparator.comparing(Restaurant::getName));
        } else if (sortBy.equals("rating")) {
            Collections.sort(processedDataBase, Comparator.comparing(Restaurant::getRating));
        } else if (sortBy.equals("price")) {
            Collections.sort(processedDataBase, Comparator.comparing(Restaurant::getAvgPrice));
        }
    }

    //REQUIRES: string must match one of the comparable fields of restaurant
    //MODIFIES: this
    //EFFECTS: sorts the list by specified information
    public void sortDatabaseDescending(String sortBy) {
        EventLog.getInstance().logEvent(new Event("Sorted database in descending order by: " + sortBy));
        if (sortBy.equals("name")) {
            Collections.sort(processedDataBase, Comparator.comparing(Restaurant::getName));
            Collections.reverse(processedDataBase);
        } else if (sortBy.equals("rating")) {
            Collections.sort(processedDataBase, Comparator.comparing(Restaurant::getRating));
            Collections.reverse(processedDataBase);
        } else if (sortBy.equals("price")) {
            Collections.sort(processedDataBase, Comparator.comparing(Restaurant::getAvgPrice));
            Collections.reverse(processedDataBase);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        //json.put("name", name);
        json.put("dataBase", dataBaseToJson());
        return json;
    }

    // EFFECTS: returns restaraunts in this database as a JSON array
    private JSONArray dataBaseToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant t : dataBase) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
