package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static java.lang.Math.*;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    private Restaurant testRestaurant;
    private Restaurant testRestaurant1;
    private Location testLocation;
    private Location testLocation1;
    private MenuItem testItem1;
    private MenuItem testItem2;
    private MenuItem testItem3;

    @BeforeEach
    void setup(){
        testLocation = new Location("Burnaby", 46, 67);
        testLocation1 = new Location("Metrotown", 40, 60);
        testRestaurant = new Restaurant("Joe's BBQ");
        testRestaurant1 = new Restaurant("Chicken Palace", "American", 5, testLocation);
        testItem1 = new MenuItem("Chicken", 10.00);
        testItem2 = new MenuItem("Pork", 20.00);
        testItem3 = new MenuItem("Beef", 30.00);
    }

    @Test
    void testConstructor(){
        assertEquals(testRestaurant.getName(), "Joe's BBQ");
        assertEquals(testRestaurant1.getName(), "Chicken Palace");
        assertEquals(testRestaurant1.getGenre(), "American");
        assertEquals(testRestaurant1.getRating(), 5);
        assertEquals(testRestaurant1.getLocation().getCityName(), "Burnaby");
        assertEquals(testRestaurant1.getLocation().getX(), 46);
        assertEquals(testRestaurant1.getLocation().getY(), 67);
    }

    @Test
    void testLocation(){
        assertEquals(testRestaurant.getLocation().getCityName() , "");
        testRestaurant.setLocation("Surrey", -40, -50);
        assertEquals(testRestaurant.getLocation().getCityName() , "");
        assertEquals(testRestaurant.getLocation().getX() , -40);
        assertEquals(testRestaurant.getLocation().getY() , -50);
    }

    @Test
    void testAddToMenu(){
        assertEquals(testRestaurant.getMenu().size(), 0);
        testRestaurant.addToMenu(testItem1);
        assertEquals(testRestaurant.getMenu().size(), 1);
        assertEquals(testRestaurant.getMenu().get(0), testItem1);
        assertEquals(testRestaurant.getMenu().get(0).getFoodName(), "Chicken");
    }

    @Test
    void testMultipleAddToMenu(){
        testRestaurant.addToMenu(testItem1);
        testRestaurant.addToMenu(testItem2);
        testRestaurant.addToMenu(testItem3);

        assertEquals(testRestaurant.getMenu().get(0), testItem1);
        assertEquals(testRestaurant.getMenu().get(0).getPrice(), 10.00);
        assertEquals(testRestaurant.getMenu().get(0), testItem2);
        assertEquals(testRestaurant.getMenu().get(0).getPrice(), 20.00);
        assertEquals(testRestaurant.getMenu().get(0), testItem3);
        assertEquals(testRestaurant.getMenu().get(0).getPrice(), 30.00);
    }

    @Test
    void testGetDistance(){
        assertEquals(testRestaurant.getDistance(testLocation1), sqrt(pow((46 - 40),2) + pow((67 - 60),2)));
    }

    @Test
    void testGetAvgPrice(){
        testRestaurant.addToMenu(testItem1);
        assertEquals(testRestaurant.getAvgPrice(), 10);
        testRestaurant.addToMenu(testItem2);
        assertEquals(testRestaurant.getAvgPrice(), 15);
        testRestaurant.addToMenu(testItem3);
        assertEquals(testRestaurant.getAvgPrice(), 20);
    }

    @Test
    void testToString(){
        assertEquals(testRestaurant.toString(), "[name =Joe's BBQ, ]");
        assertEquals(testRestaurant.toString(), "[name =Chicken Palace, ]");
    }
}