package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.lang.Math.*;

public class LocationTest {
    private Location testLocation;

    @BeforeEach
    void setup() {
        testLocation = new Location("Van", 0, 0);
    }

    @Test
    void testConstructor() {
        assertEquals("Van", testLocation.getCityName());
        assertEquals(0, testLocation.getX());
        assertEquals(0, testLocation.getY());
    }

    @Test
    void testSetCityName() {
        testLocation.setCityName("Burn");
        assertEquals("Burn", testLocation.getCityName());
    }

    @Test
    void testSetX() {
        testLocation.setX(20);
        assertEquals(20, testLocation.getX());
    }

    @Test
    void testSetY() {
        testLocation.setY(20);
        assertEquals(20, testLocation.getY());
    }
}
