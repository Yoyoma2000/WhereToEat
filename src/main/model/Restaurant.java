package model;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Restaurant {
    private String name;
    private String genre;
    private int rating;
    private ArrayList<MenuItem> menu;
    private Location location;
    private boolean favourite;

    public Restaurant(String name) {
        this.name = name;
        this.genre = "";
        this.rating = 0;
        this.favourite = false;
        this.menu = new ArrayList<>();
        this.location = new Location("", 0, 0);
    }

    public Restaurant(String name, String genre, int rating, String cityName, double x, double y) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.favourite = false;
        this.menu = new ArrayList<>();
        this.location = new Location(cityName, x, y);
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getRating() {
        return rating;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(String cityName, double x, double y) {
        this.location.setCityName(cityName);
        this.location.setX(x);
        this.location.setY(y);
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public void addToMenu(String name, double price) {
        MenuItem item = new MenuItem(name, price);
        menu.add(item);
    }

    //REQUIRES: X and Y values should be within [-100,100] (For the sake of simplicity)
    //MODIFIES: nothing
    //EFFECTS: Returns distance of restaurant to current location
    public double getDistance(double x, double y) {
        double targetX = location.getX();
        double targetY = location.getY();
        double distance = sqrt(pow((targetX - x),2) + pow((targetY - y),2));
        return distance;
    }

    //REQUIRES: menu.size() > 0
    //MODIFIES: nothing
    //EFFECTS:  Calculate average using a menus item, which has prices
    public double getAvgPrice() {
        double sum = 0;
        for (MenuItem item: menu) {
            sum += item.getPrice();
        }
        return sum / menu.size();
    }

    @Override
    public String toString() {
        return "[name =" + name + ", ]";
    }
}
