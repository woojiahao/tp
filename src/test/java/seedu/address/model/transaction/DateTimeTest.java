package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_emptyString_success() {
        assertDoesNotThrow(() -> new DateTime(""));
        assertDoesNotThrow(() -> new DateTime("01-01-2001 18:18"));
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
        assertTrue(datetime.equals(new DateTime("01-01-2001 01:01")));

        // same object -> returns true
        assertTrue(datetime.equals(datetime));

        // null -> returns false
        assertFalse(datetime.equals(null));

        // different types -> returns false
        assertFalse(datetime.equals("hi"));

        // different year -> returns false
        assertFalse(datetime.equals(new DateTime("01-01-2000 01:01")));

        // different month -> returns false
        assertFalse(datetime.equals(new DateTime("01-02-2001 01:01")));

        // different day -> returns false
        assertFalse(datetime.equals(new DateTime("02-01-2001 01:01")));

        // different time -> returns false
        assertFalse(datetime.equals(new DateTime("01-01-2001 02:02")));
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
