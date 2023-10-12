package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_invalidType_throwsIllegalArgumentException() {
        String[] invalidTypes = new String[] {"", "1", "Wrong"};
        for (String invalidType: invalidTypes) {
            assertThrows(IllegalArgumentException.class, () -> new Type(invalidType));
        }
    }

    @Test
    public void isValidType() {
        // null name
        assertFalse(Type.isValidType(null));

        // invalid name
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" ")); // spaces only
        assertFalse(Type.isValidType("^")); // wrong type
        assertFalse(Type.isValidType("peter*")); // wrong type
        assertFalse(Type.isValidType("transfers")); // wrong type

        // valid name
        assertTrue(Type.isValidType("income")); // income
        assertTrue(Type.isValidType("expense")); // expense
    }

    @Test
    public void equals() {
        Type name = new Type("income");

        // same values -> returns true
        assertTrue(name.equals(new Type("income")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new Type("expense")));
    }
}
