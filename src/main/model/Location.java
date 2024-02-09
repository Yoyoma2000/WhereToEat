package model;

public class Location {
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
}
