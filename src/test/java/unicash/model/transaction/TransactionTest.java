package unicash.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import unicash.testutil.TransactionBuilder;
import unicash.testutil.TypicalTransactions;

public class TransactionTest {

    @Test
    public void equals() {
        // same values -> returns true
        Transaction groceriesCopy = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES).build();
        Assertions.assertEquals(TypicalTransactions.BUYING_GROCERIES, groceriesCopy);

        // same object -> returns true
        Assertions.assertEquals(TypicalTransactions.BUYING_GROCERIES, TypicalTransactions.BUYING_GROCERIES);

        // null -> returns false
        assertNotEquals(null, groceriesCopy);

        // null -> returns false
        Assertions.assertNotEquals(null, TypicalTransactions.BUYING_GROCERIES);

        // different type -> returns false
        Assertions.assertNotEquals(5, TypicalTransactions.BUYING_GROCERIES);

        Assertions.assertFalse(TypicalTransactions.BUYING_GROCERIES.equals(5));

        // different person -> returns false
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES, TypicalTransactions.DINING_WITH_FRIENDS);

        // different name -> returns false
        Transaction editedGroceries = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES).withName("Another thing").build();
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES, editedGroceries);

        // different amount -> returns false
        editedGroceries = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES).withAmount(10.15).build();
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES, editedGroceries);

        // different date -> returns false
        editedGroceries = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES)
                .withDateTime("01-01-1999 18:18")
                .build();
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES, editedGroceries);

        // different location -> returns false
        editedGroceries = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES).withLocation("UTown").build();
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES, editedGroceries);

        // different transaction type -> return false
        editedGroceries = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES).withType("expense").build();
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES, editedGroceries);

        // different categories -> returns false
        editedGroceries = new TransactionBuilder(TypicalTransactions.BUYING_GROCERIES).withCategories("TEST").build();
        Assertions.assertFalse(TypicalTransactions.BUYING_GROCERIES.equals(editedGroceries));
    }

    @Test
    public void toStringMethod() {
        String expected =
                Transaction.class.getCanonicalName()
                        + "{name=" + TypicalTransactions.BUYING_GROCERIES.getName()
                        + ", type=" + TypicalTransactions.BUYING_GROCERIES.getType()
                        + ", amount=" + TypicalTransactions.BUYING_GROCERIES.getAmount()
                        + ", dateTime=" + TypicalTransactions.BUYING_GROCERIES.getDateTime()
                        + ", location=" + TypicalTransactions.BUYING_GROCERIES.getLocation()
                        + ", categories=" + TypicalTransactions.BUYING_GROCERIES.getCategories()
                        + "}";
        Assertions.assertEquals(expected, TypicalTransactions.BUYING_GROCERIES.toString());
    }

    @Test
    public void hashCode_test() {
        Assertions.assertEquals(TypicalTransactions.BUYING_GROCERIES.hashCode(), TypicalTransactions.BUYING_GROCERIES.hashCode());
        Assertions.assertNotEquals(TypicalTransactions.BUYING_GROCERIES.hashCode(), TypicalTransactions.WORK_AT_LIHO.hashCode());
    }
}
