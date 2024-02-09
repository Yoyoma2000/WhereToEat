package model;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Database {
    private ArrayList<Restaurant> dataBase;
    private ArrayList<Restaurant> processedDataBase; //Database after sort and filter

    public Database() {
        dataBase = new ArrayList<Restaurant>();
        processedDataBase = dataBase;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: makes both databases same again
    public void resetProcessedDataBase() {
        processedDataBase = dataBase;
    }

    public ArrayList<Restaurant> getDataBase() {
        return dataBase;
    }

    public ArrayList<Restaurant> getProcessedDataBase() {
        return processedDataBase;
    }

    //REQUIRES: nothing
    //MODIFIES: this
    //EFFECTS: adds a new restaurant to the database
    public void addRestaurant(String name) {
        Restaurant restaurant = new Restaurant(name);
        dataBase.add(restaurant);
    }

    //REQUIRES: name and cityName must not match another name and cityName
    //MODIFIES: this
    //EFFECTS: adds a new restaurant to the database
    public void addRestaurant(String name, String genre, int rating, String cityName, double x, double y) {
        Restaurant restaurant = new Restaurant(name, genre, rating, cityName, x, y);
        dataBase.add(restaurant);
    }

    //REQUIRES: nothing
    //MODIFIES: nothing
    //EFFECTS: finds position of restaurant in processedDataBase, -1 if unable to find it.
    public int searchDatabase(String name, String cityName) {
        for (Restaurant res: processedDataBase) {
            if (name.equals(res.getName()) && cityName.equals(res.getLocation().getCityName())) {
                return dataBase.indexOf(res);
            }
        }
        return -1;
    }
}
