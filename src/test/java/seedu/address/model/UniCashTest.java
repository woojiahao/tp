package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_INTERN;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.BUYING_GROCERIES;
import static seedu.address.testutil.TypicalTransactions.INTERN;
import static seedu.address.testutil.TypicalTransactions.NUS;
import static seedu.address.testutil.TypicalTransactions.getTypicalUniCash;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.exceptions.TransactionNotFoundException;
import seedu.address.testutil.TransactionBuilder;
import seedu.address.testutil.UniCashBuilder;

public class UniCashTest {

    private final UniCash uniCash = new UniCash();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), uniCash.getTransactionList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniCash.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyUniCash_replacesData() {
        UniCash newData = getTypicalUniCash();
        uniCash.resetData(newData);
        assertEquals(newData, uniCash);
    }

    @Test
    public void resetData_withDuplicateTransactions_success() {
        // Two persons with the same identity fields
        Transaction editedNus = new TransactionBuilder(NUS).withAmount(VALID_AMOUNT_INTERN).build();
        List<Transaction> newTransactions = Arrays.asList(NUS, editedNus);
        UniCashTest.UniCashStub newData = new UniCashTest.UniCashStub(newTransactions);

        assertDoesNotThrow(() -> uniCash.resetData(newData));
    }

    @Test
    public void hasTransaction_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniCash.hasTransaction(null));
    }

    @Test
    public void hasTransaction_transactionNotInUniCash_returnsFalse() {
        assertFalse(uniCash.hasTransaction(NUS));
    }

    @Test
    public void hasTransaction_personInUniCash_returnsTrue() {
        uniCash.addTransaction(NUS);
        assertTrue(uniCash.hasTransaction(NUS));
    }

    @Test
    public void setTransaction_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniCash.setTransaction(NUS, null));
    }

    @Test
    public void setTransaction_transactionNotInUniCash_throwsTransactionNotFoundException() {
        assertThrows(TransactionNotFoundException.class, () -> uniCash.setTransaction(NUS, BUYING_GROCERIES));
    }

    @Test
    public void setTransaction_transactionInUniCash_success() {
        uniCash.addTransaction(NUS);
        assertDoesNotThrow(() -> uniCash.setTransaction(NUS, BUYING_GROCERIES));
    }

    @Test
    public void removeTransaction_personInUniCash_returnsTrue() {
        UniCash transactionList = new UniCashBuilder().withTransaction(NUS).build();
        assertTrue(transactionList.hasTransaction(NUS));
        transactionList.removeTransaction(NUS);
        assertFalse(transactionList.hasTransaction(NUS));
    }

    @Test
    public void getUniCash_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniCash.getTransactionList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = UniCash.class.getCanonicalName() + "{transactions=" + uniCash.getTransactionList() + "}";
        assertEquals(expected, uniCash.toString());
    }

    @Test
    public void equals() {
        // same object -> returns true
        UniCash transactionList = new UniCashBuilder().build();
        assertEquals(transactionList, transactionList);

        // same lists
        transactionList.addTransaction(NUS);
        UniCash anotherList = new UniCashBuilder().withTransaction(NUS).build();
        assertEquals(transactionList, anotherList);

        // different lists
        anotherList = new UniCashBuilder().withTransaction(INTERN).build();
        assertNotEquals(transactionList, anotherList);

        // null -> returns false
        assertNotEquals(null, transactionList);
    }

    /**
     * A stub ReadOnlyUniCash whose persons list can violate interface constraints.
     */
    private static class UniCashStub implements ReadOnlyUniCash {
        private final ObservableList<Transaction> transactions = FXCollections.observableArrayList();

        UniCashStub(Collection<Transaction> transactions) {
            this.transactions.setAll(transactions);
        }

        @Override
        public ObservableList<Transaction> getTransactionList() {
            return transactions;
        }
    }

}
