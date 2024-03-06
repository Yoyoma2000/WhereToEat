package model;

import org.json.JSONObject;
import persistence.Writable;

// This class represents the location name and coordinate (not actual) of a restaurant
public class Location implements Writable {
    private String cityName;
    private double coordX;
    private double coordY;

    public Location(String cityName, double x, double y) {
        this.cityName = cityName;
        this.coordX = x;
        this.coordY = y;
    }

    public String getCityName() {
        return cityName;
    }

    public double getX() {
        return coordX;
    }

    public double getY() {
        return coordY;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setX(double x) {
        this.coordX = x;
    }

    public void setY(double y) {
        this.coordY = y;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("cityName", cityName);
        json.put("coordX", coordX);
        json.put("coordY", coordY);

        return json;
    }
}
