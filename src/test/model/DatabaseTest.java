package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    private Database testData;
    private Restaurant restTest1;
    private Restaurant restTest2;
    private Location location1;
    private Location location2;
    private String name;
    private String genre;
    private int rating;
    private Location location3;

    private MenuItem food1;
    private MenuItem food2;
    private MenuItem food3;
    private MenuItem food4;

    @BeforeEach
    void setup() {
        testData = new Database();
        location1 = new Location("Burnaby", 100,100);
        restTest1 = new Restaurant("Pizza", "Italian", 8, location1);
        location2 = new Location("Langley", 100, 50);
        restTest2 = new Restaurant("Stirfry", "Asian", 5, location2);

        food1 = new MenuItem("Hawaii", 50);
        food2 = new MenuItem("Cheese", 60);
        food3 = new MenuItem("Chicken", 10);
        food4 = new MenuItem("Beef", 20);

        restTest1.addToMenu(food1);
        restTest1.addToMenu(food2);
        restTest2.addToMenu(food3);
        restTest2.addToMenu(food4);


        name = "BBQ";
        genre = "American";
        rating = 4;
        location3 = new Location("Surrey", 50, 50);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testData.getDataBase().size());
        assertEquals(0, testData.getProcessedDataBase().size());
    }

    @Test
    void testAddRestaurant() {
        testData.addRestaurant(restTest1);
        assertEquals(1, testData.getDataBase().size());
        assertEquals(restTest1, testData.getDataBase().get(0));
    }

    @Test
    void testAddMultipleRestaurant() {
        testData.addRestaurant(restTest1);
        assertEquals(1, testData.getDataBase().size());
        assertEquals(restTest1, testData.getDataBase().get(0));
        testData.addRestaurant(restTest2);
        assertEquals(2, testData.getDataBase().size());
        assertEquals(restTest2, testData.getDataBase().get(1));
        testData.addRestaurant(name, genre, rating, location3);
        assertEquals(3, testData.getDataBase().size());
        assertEquals(name, testData.getDataBase().get(2).getName());
        assertEquals(genre, testData.getDataBase().get(2).getGenre());
    }
    @Test
    void testResetProcessedDataBase() {
        testData.addRestaurant(restTest1);
        testData.addRestaurant(restTest2);
        assertEquals(2, testData.getDataBase().size());
        assertEquals(0, testData.getProcessedDataBase().size());
        testData.resetProcessedDataBase();
        assertEquals(2, testData.getDataBase().size());
        assertEquals(2, testData.getProcessedDataBase().size());
    }


    @Test
    void testSearchDataBase() {
        testData.addRestaurant(restTest1);
        testData.addRestaurant(restTest2);
        testData.addRestaurant(name, genre, rating, location3);
        testData.resetProcessedDataBase();

        assertEquals(0, testData.searchDatabase("pizza", "burnaby"));
        assertEquals(1, testData.searchDatabase("stirfry", "langley"));
        assertEquals(2, testData.searchDatabase("bbq", "surrey"));

        assertEquals(0, testData.searchDatabase("pizza", "burnaby"));
        assertEquals(1, testData.searchDatabase("stirfry", "langley"));
        assertEquals(2, testData.searchDatabase("bbq", "surrey"));

        assertEquals(-1, testData.searchDatabase("wrong name", "burnaby"));
        assertEquals(-1, testData.searchDatabase("pizza", "wrong name"));
        assertEquals(-1, testData.searchDatabase("wrong name", "wrong name"));
    }

    @Test
    void testRandomizeRestaurant() {
        testData.addRestaurant(restTest1);
        testData.addRestaurant(restTest2);
        testData.addRestaurant(name, genre, rating, location3);
        testData.resetProcessedDataBase();

        assertTrue(testData.getDataBase().contains(testData.randomRestaurant(testData.getDataBase())));
        assertTrue(testData.getDataBase().contains(testData.randomRestaurant(testData.getProcessedDataBase())));
    }

    @Test
    void testSortDatabaseAscending() {
        testData.addRestaurant(restTest1);
        testData.addRestaurant(restTest2);
        testData.resetProcessedDataBase();

        testData.sortDatabaseAscending("rating");
        assertEquals(restTest2, testData.getProcessedDataBase().get(0));
        assertEquals(restTest1, testData.getProcessedDataBase().get(1));

        testData.sortDatabaseAscending("name");
        assertEquals(restTest1, testData.getProcessedDataBase().get(0));
        assertEquals(restTest2, testData.getProcessedDataBase().get(1));

        testData.sortDatabaseAscending("price");
        assertEquals(restTest2, testData.getProcessedDataBase().get(0));
        assertEquals(restTest1, testData.getProcessedDataBase().get(1));
    }

    @Test
    void testSortDatabaseDescending() {
        testData.addRestaurant(restTest2);
        testData.addRestaurant(restTest1);
        testData.resetProcessedDataBase();

        testData.sortDatabaseDescending("rating");
        assertEquals(restTest1, testData.getProcessedDataBase().get(0));
        assertEquals(restTest2, testData.getProcessedDataBase().get(1));

        testData.sortDatabaseDescending("name");
        assertEquals(restTest2, testData.getProcessedDataBase().get(0));
        assertEquals(restTest1, testData.getProcessedDataBase().get(1));

        testData.sortDatabaseDescending("price");
        assertEquals(restTest1, testData.getProcessedDataBase().get(0));
        assertEquals(restTest2, testData.getProcessedDataBase().get(1));
    }


}
