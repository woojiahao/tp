package unicash.model;

import javafx.collections.ObservableList;
import unicash.model.transaction.Transaction;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyUniCash {

    /**
     * Returns an unmodifiable view of the transaction list.
     */
    ObservableList<Transaction> getTransactionList();

}
