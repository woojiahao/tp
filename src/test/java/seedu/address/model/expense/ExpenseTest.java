package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.TypicalExpenses.BUYING_GROCERIES;
import static seedu.address.testutil.TypicalExpenses.DINING_WITH_FRIENDS;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ExpenseBuilder;

public class ExpenseTest {

    @Test
    public void equals() {
        // same values -> returns true
        Expense groceriesCopy = new ExpenseBuilder(BUYING_GROCERIES).build();
        assertEquals(BUYING_GROCERIES, groceriesCopy);

        // same object -> returns true
        assertEquals(BUYING_GROCERIES, BUYING_GROCERIES);

        // null -> returns false
        assertNotEquals(null, BUYING_GROCERIES);

        // different type -> returns false
        assertNotEquals(5, BUYING_GROCERIES);

        // different person -> returns false
        assertNotEquals(BUYING_GROCERIES, DINING_WITH_FRIENDS);

        // different name -> returns false
        Expense editedGroceries = new ExpenseBuilder(BUYING_GROCERIES).withName("Another thing").build();
        assertNotEquals(BUYING_GROCERIES, editedGroceries);

        // different phone -> returns false
        editedGroceries = new ExpenseBuilder(BUYING_GROCERIES).withAmount(10.15).build();
        assertNotEquals(BUYING_GROCERIES, editedGroceries);

        // different email -> returns false
        editedGroceries = new ExpenseBuilder(BUYING_GROCERIES).withCategory("Food").build();
        assertNotEquals(BUYING_GROCERIES, editedGroceries);

        // different address -> returns false
        editedGroceries = new ExpenseBuilder(BUYING_GROCERIES).withDateTime(LocalDateTime.MIN).build();
        assertNotEquals(BUYING_GROCERIES, editedGroceries);

        // different tags -> returns false
        editedGroceries = new ExpenseBuilder(BUYING_GROCERIES).withLocation("UTown").build();
        assertNotEquals(BUYING_GROCERIES, editedGroceries);
    }

    @Test
    public void toStringMethod() {
        String expected =
                Expense.class.getCanonicalName()
                        + "{name=" + BUYING_GROCERIES.getName()
                        + ", amount=" + BUYING_GROCERIES.getAmount()
                        + ", category=" + BUYING_GROCERIES.getCategory()
                        + ", dateTime=" + BUYING_GROCERIES.getDateTime()
                        + ", location=" + BUYING_GROCERIES.getLocation()
                        + "}";
        assertEquals(expected, BUYING_GROCERIES.toString());
    }
}
