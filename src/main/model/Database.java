package model;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

// This class stores a list of restaurants
public class Database {
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
    }

    //REQUIRES: name and cityName must not match another name and cityName
    //MODIFIES: this
    //EFFECTS: adds a new restaurant to the database
    public void addRestaurant(String name, String genre, int rating, Location location) {
        Restaurant restaurant = new Restaurant(name, genre, rating, location);
        dataBase.add(restaurant);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: finds position of restaurant in processedDataBase, -1 if unable to find it.
    public int searchDatabase(String name, String cityName) {
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
        int max = dataBase.size();
        int randomPos = (int) Math.floor(Math.random() * (max - 0) + 0);
        return (Restaurant) dataBase.get(randomPos);
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: sorts the list by specified information
    public void sortDatabase(String sortByThis) {

    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: filters the list by specified information
    public void filterDatabase(String category, String specific) {

    }


}
