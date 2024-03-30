package model.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Event class
 */
public class EventTest {
	private Event e;
	private Date d;

    private Event e1;
    private Date d1;

    private Event eNull;
	
	//NOTE: these tests might fail if time at which line (2) below is executed
	//is different from time that line (1) is executed.  Lines (1) and (2) must
	//run in same millisecond for this test to make sense and pass.
	
	@BeforeEach
	public void runBefore() {
		e = new Event("Added new restaurant");   // (1)
		d = Calendar.getInstance().getTime();   // (2)

        e1 = new Event("Searched restaurant");
        d1 = Calendar.getInstance().getTime();

        eNull = null;
	}
	
	@Test
	public void testEvent() {
		assertEquals("Added new restaurant", e.getDescription());
		assertEquals(d, e.getDate());
	}

	@Test
	public void testToString() {
		assertEquals(d.toString() + "\n" + "Added new restaurant", e.toString());
	}

    @Test
    public void testEquals() {
        assertFalse(e.equals(e1));
        assertFalse(e.equals(d1));
        assertFalse(e.equals(eNull));
        assertTrue(e.equals(e));
    }

    @Test
    public void testHashCode() {
        assertEquals(e.hashCode(), e.hashCode());
        assertNotEquals(e.hashCode(), e1.hashCode());
    }
}
