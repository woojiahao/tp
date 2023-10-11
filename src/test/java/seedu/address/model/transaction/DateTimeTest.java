package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void equals() {
        DateTime datetime = new DateTime(LocalDateTime.of(2001, 1, 1, 1, 1));

        // same values -> returns true
        assertTrue(datetime.equals(new DateTime(LocalDateTime.of(2001, 1, 1, 1, 1))));

        // same object -> returns true
        assertTrue(datetime.equals(datetime));

        // null -> returns false
        assertFalse(datetime.equals(null));

        // different types -> returns false
        assertFalse(datetime.equals("hi"));

        // different year -> returns false
        assertFalse(datetime.equals(new DateTime(LocalDateTime.of(2000, 1, 1, 1, 1))));

        // different month -> returns false
        assertFalse(datetime.equals(new DateTime(LocalDateTime.of(2001, 12, 1, 1, 1))));

        // different day -> returns false
        assertFalse(datetime.equals(new DateTime(LocalDateTime.of(2001, 1, 12, 1, 1))));

        // different time -> returns false
        assertFalse(datetime.equals(new DateTime(LocalDateTime.of(2001, 1, 1, 12, 12))));
    }
}
