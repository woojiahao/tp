package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.jupiter.api.Test;


public class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
        assertThrows(NullPointerException.class, () -> new DateTime("18-08-2001 18:18", null));
        assertThrows(NullPointerException.class, () -> new DateTime(null, null));
    }

    @Test
    public void constructor_noDateTime_setDefault() {
        Clock clock = Clock.fixed(Instant.parse("2014-12-21T10:15:30.00Z"), ZoneId.of("UTC"));
        String empty = "";
        assertEquals("1015, Dec 21 2014", new DateTime(empty, clock).toString());
    }

    @Test
    public void isValidDate() {
        assertFalse(DateTime.isValidDateTime("1-1-2001 18:18")); // wrong day and month format
        assertFalse(DateTime.isValidDateTime("01-1-2001 17:1")); // invalid time
        assertFalse(DateTime.isValidDateTime("01-01-2001")); // missing time
        assertFalse(DateTime.isValidDateTime("18:18")); // missing date
        assertFalse(DateTime.isValidDateTime("20-31-2001 18:18")); // invalid date
        assertFalse(DateTime.isValidDateTime("20-01-2001 25:18")); // invalid time
        assertFalse(DateTime.isValidDateTime("31-02-2001 18:18")); // invalid date
    }

    @Test
    public void originalString() {
        DateTime dateTime = new DateTime("18-12-2023 01:01");
        String stringify = dateTime.originalString();
        assertEquals(stringify, "18-12-2023 01:01");
    }

    @Test
    public void equals() {
        DateTime datetime = new DateTime("01-01-2001 01:01");

        // same values -> returns true
        assertEquals(datetime, new DateTime("01-01-2001 01:01"));

        // same object -> returns true
        assertEquals(datetime, datetime);

        // null -> returns false
        assertNotEquals(null, datetime);

        // different types -> returns false
        assertNotEquals("hi", datetime);

        // different year -> returns false
        assertNotEquals(datetime, new DateTime("01-01-2000 01:01"));

        // different month -> returns false
        assertNotEquals(datetime, new DateTime("01-02-2001 01:01"));

        // different day -> returns false
        assertNotEquals(datetime, new DateTime("02-01-2001 01:01"));

        // different time -> returns false
        assertNotEquals(datetime, new DateTime("01-01-2001 02:02"));
    }

    @Test
    public void toStringMethod() {
        DateTime dateTime = new DateTime("18-08-2023 01:01");
        assertEquals(dateTime.toString(), "0101, Aug 18 2023");
    }

    @Test
    public void hashCode_test() {
        DateTime dateTime1 = new DateTime("01-01-2001 12:12");
        DateTime dateTime2 = new DateTime("01-01-2001 12:12");
        DateTime dateTime3 = new DateTime("01-01-2002 12:12");
        assertEquals(dateTime1.hashCode(), dateTime2.hashCode());
        assertNotEquals(dateTime1.hashCode(), dateTime3.hashCode());
    }
}
