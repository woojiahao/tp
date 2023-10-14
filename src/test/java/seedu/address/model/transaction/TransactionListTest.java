package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_INTERN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.BUYING_GROCERIES;
import static seedu.address.testutil.TypicalTransactions.NUS;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.transaction.exceptions.TransactionNotFoundException;
import seedu.address.testutil.TransactionBuilder;

public class TransactionListTest {

    private final TransactionList transactionList = new TransactionList();

    @Test
    public void contains_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.contains(null));
    }

    @Test
    public void contains_transactionNotInList_returnsFalse() {
        assertFalse(transactionList.contains(NUS));
    }

    @Test
    public void contains_transactionInList_returnsTrue() {
        transactionList.add(NUS);
        assertTrue(transactionList.contains(NUS));
    }

    @Test
    public void contains_differentTransaction_returnsFalse() {
        transactionList.add(NUS);
        Transaction editedNus = new TransactionBuilder(NUS).withAmount(VALID_AMOUNT_INTERN)
                .build();
        assertFalse(transactionList.contains(editedNus));
    }

    @Test
    public void add_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.add(null));
    }

    @Test
    public void add_duplicateTransaction_throwsDuplicateTransactionException() {
        transactionList.add(NUS);
        assertDoesNotThrow(() -> transactionList.add(NUS));
    }

    @Test
    public void setTransaction_nullTargetTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.setTransaction(null, NUS));
    }

    @Test
    public void setTransaction_nullEditedTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.setTransaction(NUS, null));
    }

    @Test
    public void setTransaction_targetTransactionNotInList_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> transactionList.setTransaction(NUS, NUS));
    }

    @Test
    public void setTransaction_editedTransactionIsSameTransaction_success() {
        transactionList.add(NUS);
        transactionList.setTransaction(NUS, NUS);
        TransactionList expectedUniqueTransactionList = new TransactionList();
        expectedUniqueTransactionList.add(NUS);
        assertEquals(expectedUniqueTransactionList, transactionList);
    }

    @Test
    public void setTransaction_editedTransactionHasDifferentIdentity_success() {
        transactionList.add(NUS);
        transactionList.setTransaction(NUS, BUYING_GROCERIES);
        TransactionList expectedTransactionList = new TransactionList();
        expectedTransactionList.add(BUYING_GROCERIES);
        assertEquals(expectedTransactionList, transactionList);
    }

    @Test
    public void remove_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.remove(null));
    }

    @Test
    public void remove_transactionDoesNotExist_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> transactionList.remove(NUS));
    }

    @Test
    public void remove_existingTransaction_removesTransaction() {
        transactionList.add(NUS);
        transactionList.remove(NUS);
        TransactionList expectedTransactionList = new TransactionList();
        assertEquals(expectedTransactionList, transactionList);
    }

    @Test
    public void setTransactions_nullTransactionList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.setTransactions((TransactionList) null));
    }

    @Test
    public void setTransactions_transactionList_replacesOwnListWithProvidedTransactionList() {
        transactionList.add(NUS);
        TransactionList expectedTransactionList = new TransactionList();
        expectedTransactionList.add(BUYING_GROCERIES);
        transactionList.setTransactions(expectedTransactionList);
        assertEquals(expectedTransactionList, transactionList);
    }

    @Test
    public void setTransactions_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> transactionList.setTransactions((List<Transaction>) null));
    }

    @Test
    public void setTransactions_uniqueTransactionList_replacesOwnListWithProvidedUniqueTransactionList() {
        transactionList.add(NUS);
        TransactionList expectedTransactionList = new TransactionList();
        expectedTransactionList.add(BUYING_GROCERIES);
        transactionList.setTransactions(expectedTransactionList);
        assertEquals(expectedTransactionList, transactionList);
    }

    @Test
    public void setTransactions_listWithDuplicateTransactions_success() {
        List<Transaction> listWithDuplicateTransactions = Arrays.asList(NUS, NUS);
        assertDoesNotThrow(() -> transactionList.setTransactions(listWithDuplicateTransactions));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> transactionList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(transactionList.asUnmodifiableObservableList().toString(), transactionList.toString());
    }

    @Test
    public void equals() {
        // same object -> returns true
        TransactionList transactionList = new TransactionList();
        assertTrue(transactionList.equals(transactionList));

        // same lists
        transactionList.add(NUS);
        TransactionList anotherList = new TransactionList();
        anotherList.add(NUS);
        assertTrue(transactionList.equals(anotherList));

        // different lists
        anotherList = new TransactionList();
        anotherList.add(BUYING_GROCERIES);
        assertFalse(transactionList.equals(anotherList));

        // null -> returns false
        assertFalse(transactionList.equals(null));
    }

    @Test
    public void hashCode_test() {
        TransactionList transactionList1 = new TransactionList();
        TransactionList transactionList2 = new TransactionList();
        TransactionList transactionList3 = new TransactionList();
        transactionList3.add(NUS);
        assertEquals(transactionList1.hashCode(), transactionList2.hashCode());
        assertNotEquals(transactionList1.hashCode(), transactionList3.hashCode());
    }
}