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

    @BeforeEach
    void setup(){
        testData = new Database();
        location1 = new Location("Burnaby", 100,100);
        restTest1 = new Restaurant("Pizza", "Italian", 5, location1);
        location2 = new Location("Langley", 100, 50);
        restTest2 = new Restaurant("Stirfry", "Asian", 8, location2);

        name = "BBQ";
        genre = "American";
        rating = 4;
        location3 = new Location("Surrey", 50, 50);
    }

    @Test
    void testConstructor(){
        assertEquals(0, testData.getDataBase().size());
        assertEquals(0, testData.getProcessedDataBase().size());
    }

    @Test
    void testAddRestaurant(){
        testData.addRestaurant(restTest1);
        assertEquals(1, testData.getDataBase().size());
        assertEquals(restTest1, testData.getDataBase().get(0));
    }

    @Test
    void testAddMultipleRestaurant(){
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
    void testResetProcessedDataBase(){
        testData.addRestaurant(restTest1);
        testData.addRestaurant(restTest2);
        assertEquals(2, testData.getDataBase().size());
        assertEquals(0, testData.getProcessedDataBase().size());
        testData.resetProcessedDataBase();
        assertEquals(2, testData.getDataBase().size());
        assertEquals(2, testData.getProcessedDataBase().size());
    }


    @Test
    void testSearchDataBase(){
        testData.addRestaurant(restTest1);
        testData.addRestaurant(restTest2);
        testData.addRestaurant(name, genre, rating, location3);
        testData.resetProcessedDataBase();

        assertEquals(0, testData.searchDatabase("Pizza", "Burnaby"));
        assertEquals(1, testData.searchDatabase("Stirfry", "Langley"));
        assertEquals(2, testData.searchDatabase("BBQ", "Surrey"));

        assertEquals(-1, testData.searchDatabase("Wrong Name", "Burnaby"));
        assertEquals(-1, testData.searchDatabase("Pizza", "Wrong Name"));
        assertEquals(-1, testData.searchDatabase("Wrong Name", "Wrong Name"));
    }

}
