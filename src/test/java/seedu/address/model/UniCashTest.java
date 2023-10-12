package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.INTERN;
import static seedu.address.testutil.TypicalTransactions.NUS;
import static seedu.address.testutil.TypicalTransactions.getTypicalUniCash;

import java.util.Collection;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.transaction.Transaction;
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
    public void hasTransaction_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniCash.hasTransaction(null));
    }

    @Test
    public void hasTransaction_incomeNotInUniCash_returnsFalse() {
        assertFalse(uniCash.hasTransaction(NUS));
    }

    @Test
    public void hasTransaction_personInUniCash_returnsTrue() {
        uniCash.addTransaction(NUS);
        assertTrue(uniCash.hasTransaction(NUS));
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
        assertTrue(transactionList.equals(transactionList));

        // same lists
        transactionList.addTransaction(NUS);
        UniCash anotherList = new UniCashBuilder().withTransaction(NUS).build();
        assertTrue(transactionList.equals(anotherList));

        // different lists
        anotherList = new UniCashBuilder().withTransaction(INTERN).build();
        assertFalse(transactionList.equals(anotherList));

        // null -> returns false
        assertFalse(transactionList.equals(null));
    }

    /**
     * A stub ReadOnlyUniCash whose persons list can violate interface constraints.
     */
    private static class UniCashStub implements ReadOnlyUniCash {
        private final ObservableList<Transaction> incomes = FXCollections.observableArrayList();

        UniCashStub(Collection<Transaction> incomes) {
            this.incomes.setAll(incomes);
        }

        @Override
        public ObservableList<Transaction> getTransactionList() {
            return incomes;
        }
    }

}
