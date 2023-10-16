package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Category(null));
    }

    @Test
    public void constructor_noCategory_setDefault() {
        assertEquals("-", new Category("").toString());
        assertEquals("-", new Category(" ").toString());
    }

    @Test
    public void isValidCategory() {
        // null name
        assertThrows(NullPointerException.class, () -> Category.isValidCategory(null));

        // invalid name
        assertFalse(Category.isValidCategory("")); // empty string
        assertFalse(Category.isValidCategory(" ")); // spaces only
        assertFalse(Category.isValidCategory("^")); // only non-allowed characters
        assertFalse(Category.isValidCategory("Lots*")); // contains non-allowed characters

        // valid name
        assertTrue(Category.isValidCategory("food")); // alphabets only
        assertTrue(Category.isValidCategory("12345")); // numbers only
        assertTrue(Category.isValidCategory("groceries 2morrow")); // alphanumeric characters
        assertTrue(Category.isValidCategory("Food Drinks")); // with capital letters
        assertTrue(Category.isValidCategory("Food Drinks and everything in between")); // long names
        assertTrue(Category.isValidCategory("Food & Drinks")); // &
        assertTrue(Category.isValidCategory("Food & Drink_s")); // -
        assertTrue(Category.isValidCategory("Food-Drinks")); // -
        assertTrue(Category.isValidCategory("-")); // -
    }

    @Test
    public void equals() {
        Category category = new Category("Valid Category");

        // same values -> returns true
        assertEquals(category, new Category("Valid Category"));

        // same object -> returns true
        assertEquals(category, category);

        // null -> returns false
        assertNotEquals(null, category);

        // different types -> returns false
        assertNotEquals(5.0f, category);

        assertFalse(category.equals(5));

        // different values -> returns false
        assertNotEquals(category, new Category("Other Valid Category"));
    }
}
