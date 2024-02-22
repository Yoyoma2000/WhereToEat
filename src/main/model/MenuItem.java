package model;

import java.lang.Math;
import static java.lang.Math.sqrt;

// This class represents an individual dish that a restaurant has in its menu
public class MenuItem {
    private String foodName;
    private double price;

    public MenuItem(String foodName, double price) {
        this.foodName = foodName;
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Dish Name = " + foodName + " | Price ($): " + price;
    }
}
