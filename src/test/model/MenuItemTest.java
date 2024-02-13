package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.*;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {
    private MenuItem testItem;

    @BeforeEach
    void setup() {
        testItem = new MenuItem("Dish", 10);
    }

    @Test
    void testConstructor() {
        assertEquals("Dish", testItem.getFoodName());
        assertEquals(10, testItem.getPrice());
    }

    @Test
    void testSetName() {
        testItem.setFoodName("Dash");
        assertEquals("Dash", testItem.getFoodName());
    }

    @Test
    void testSetPrice() {
        testItem.setPrice(20);
        assertEquals(20, testItem.getPrice());
    }

    @Test
    void testToString() {
        assertEquals("Dish Name = Dish | Price ($): 10.0", testItem.toString());
    }
}
