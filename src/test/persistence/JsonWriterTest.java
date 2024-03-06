package persistence;

import model.Database;
import model.Location;
import model.MenuItem;
import model.Restaurant;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    //strategy:
    //write data to a file, use the reader to read it back in and check equals


    @Test
    void testWriterInvalidFile() {
        try {
            Database db = new Database();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Database db = new Database();
            JsonWriter writer = new JsonWriter("./data/testWriterEmpty.json");
            writer.open();
            writer.write(db);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmpty.json");
            db = reader.read();
            assertEquals(0, db.getDataBase().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Database db = new Database();
            Location location1 = new Location("A",0,0);
            db.addRestaurant(new Restaurant("Lulu BBQ", "Asian", 8, location1));
            Location location2 = new Location("B",5,5);
            Restaurant restaurant2 = new Restaurant("McDonald", "American", 2, location2);
            restaurant2.addToMenu(new MenuItem("Burger", 10));
            db.addRestaurant(restaurant2);
            JsonWriter writer = new JsonWriter("./data/testWriterExampleDatabase.json");
            writer.open();
            writer.write(db);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterExampleDatabase.json");
            db = reader.read();
            List<Restaurant> testDatabase = db.getDataBase();
            assertEquals(2, testDatabase.size());

            assertEquals("Lulu BBQ", testDatabase.get(0).getName());
            assertEquals(0, testDatabase.get(0).getMenu().size());
            assertEquals("McDonald", testDatabase.get(1).getName());
            assertEquals(1, testDatabase.get(1).getMenu().size());
            assertEquals("Burger", testDatabase.get(1).getMenu().get(0).getFoodName());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
