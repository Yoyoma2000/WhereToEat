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
    void setup() {
        testLocation = new Location("Burnaby", 46, 67);
        testLocation1 = new Location("Metrotown", 40, 60);
        testRestaurant = new Restaurant("Joe's BBQ");
        testRestaurant1 = new Restaurant("Chicken Palace", "American", 5, testLocation);
        testItem1 = new MenuItem("Chicken", 10.00);
        testItem2 = new MenuItem("Pork", 20.00);
        testItem3 = new MenuItem("Beef", 30.00);
    }

    @Test
    void testConstructor() {
        assertEquals("Joe's BBQ", testRestaurant.getName());
        assertEquals("Chicken Palace", testRestaurant1.getName());
        assertEquals("American", testRestaurant1.getGenre());
        assertEquals(5, testRestaurant1.getRating());
        assertEquals("Burnaby", testRestaurant1.getLocation().getCityName());
        assertEquals(46, testRestaurant1.getLocation().getX());
        assertEquals(67, testRestaurant1.getLocation().getY());
    }

    @Test
    void testSetName() {
        testRestaurant.setName("ChongQing");
        assertEquals("ChongQing", testRestaurant.getName());
    }

    @Test
    void testSetGenre() {
        testRestaurant.setGenre("Chinese");
        assertEquals("Chinese", testRestaurant.getGenre());
    }

    @Test
    void testSetRating() {
        testRestaurant.setRating(10);
        assertEquals(10, testRestaurant.getRating());
    }

    @Test
    void testToggleFavourite() {
        assertFalse(testRestaurant.isFavourite());
        testRestaurant.toggleFavourite();
        assertTrue(testRestaurant.isFavourite());
        testRestaurant.toggleFavourite();
        assertFalse(testRestaurant.isFavourite());
    }

    @Test
    void testSetLocation() {
        assertEquals("", testRestaurant.getLocation().getCityName());
        testRestaurant.setLocation("Surrey", -40, -50);
        assertEquals("Surrey", testRestaurant.getLocation().getCityName());
        assertEquals(-40, testRestaurant.getLocation().getX());
        assertEquals(-50, testRestaurant.getLocation().getY());
    }

    @Test
    void testAddToMenu() {
        assertEquals(0, testRestaurant.getMenu().size());
        testRestaurant.addToMenu(testItem1);
        assertEquals(1, testRestaurant.getMenu().size());
        assertEquals(testItem1, testRestaurant.getMenu().get(0));
        assertEquals("Chicken", testRestaurant.getMenu().get(0).getFoodName());
    }

    @Test
    void testMultipleAddToMenu() {
        testRestaurant.addToMenu(testItem1);
        testRestaurant.addToMenu(testItem2);
        testRestaurant.addToMenu(testItem3);

        assertEquals(testItem1, testRestaurant.getMenu().get(0));
        assertEquals(10.00, testRestaurant.getMenu().get(0).getPrice());
        assertEquals(testItem2, testRestaurant.getMenu().get(1));
        assertEquals(20.00, testRestaurant.getMenu().get(1).getPrice());
        assertEquals(testItem3, testRestaurant.getMenu().get(2));
        assertEquals(30.00, testRestaurant.getMenu().get(2).getPrice());
    }

    @Test
    void testGetDistance(){
        assertEquals(sqrt(pow(6,2) + pow(7,2)), testRestaurant1.getDistance(testLocation1));
    }

    @Test
    void testGetAvgPrice() {
        testRestaurant.addToMenu(testItem1);
        assertEquals(10, testRestaurant.getAvgPrice());
        testRestaurant.addToMenu(testItem2);
        assertEquals(15, testRestaurant.getAvgPrice());
        testRestaurant.addToMenu(testItem3);
        assertEquals(20, testRestaurant.getAvgPrice());
    }

    @Test
    void testToString() {
        assertEquals("[name =Joe's BBQ, ]", testRestaurant.toString());
        assertEquals("[name =Chicken Palace, ]", testRestaurant1.toString());
    }
}