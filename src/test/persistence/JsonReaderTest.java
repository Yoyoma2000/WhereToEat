package persistence;

import model.Database;
import model.Location;
import model.MenuItem;
import model.Restaurant;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchDatabase.json");
        try {
            Database db = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/EmptyDatabase.json");
        try {
            Database db = reader.read();
            assertEquals(0, db.getDataBase().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderRegularDatabase() {
        JsonReader reader = new JsonReader("./data/ExampleDatabase.json");
        try {
            Database db = reader.read();
            List<Restaurant> database = db.getDataBase();
            assertEquals(4, database.size());
            assertEquals("Chipotle", database.get(0).getName());
            assertEquals("Lamb Hotpot", database.get(1).getName());
            assertEquals("Pho86", database.get(2).getName());
            assertEquals("Lulu Kitchen", database.get(3).getName());

            assertEquals(2, database.get(0).getMenu().size());
            assertEquals("Bowl", database.get(0).getMenu().get(0).getFoodName());
            assertEquals(2, database.get(1).getMenu().size());
            assertEquals("yinyang", database.get(1).getMenu().get(1).getFoodName());
            assertEquals(2, database.get(2).getMenu().size());
            assertEquals("House Special", database.get(2).getMenu().get(0).getFoodName());
            assertEquals(2, database.get(3).getMenu().size());
            assertEquals("Lamb", database.get(3).getMenu().get(1).getFoodName());

            assertEquals(false, database.get(0).isFavourite());
            assertEquals(true, database.get(1).isFavourite());
            assertEquals(false, database.get(2).isFavourite());
            assertEquals(true, database.get(3).isFavourite());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
