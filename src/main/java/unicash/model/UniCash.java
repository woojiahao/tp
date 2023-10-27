package unicash.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import unicash.commons.util.ToStringBuilder;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.TransactionList;

/**
 * Wraps all data in UniCash
 */
public class UniCash implements ReadOnlyUniCash {

    private final TransactionList transactions;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        transactions = new TransactionList();
    }

    public UniCash() {}

    /**
     * Creates an UniCash using the Transactions in the {@code toBeCopied}
     */
    public UniCash(ReadOnlyUniCash toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Transaction list with {@code transactions}.
     * {@code transactions} must not contain any null.
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions.setTransactions(transactions);
    }

    /**
     * Resets the existing data of this {@code UniCash} with {@code newData}.
     */
    public void resetData(ReadOnlyUniCash newData) {
        requireNonNull(newData);

        setTransactions(newData.getTransactionList());
    }

    //// Transaction-level operations

    /**
     * Returns true if a Transaction with the same identity as {@code Transaction} exists in UniCash.
     */
    public boolean hasTransaction(Transaction transaction) {
        requireNonNull(transaction);
        return transactions.contains(transaction);
    }

    /**
     * Adds a transaction to UniCash
     */
    public void addTransaction(Transaction p) {
        transactions.add(p);
    }

    /**
     * Replaces the given Transaction {@code target} in the list with {@code editedTransaction}.
     */
    public void setTransaction(Transaction target, Transaction editedTransaction) {
        requireNonNull(editedTransaction);
        transactions.setTransaction(target, editedTransaction);
    }

    /**
     * Removes {@code key} from this {@code UniCash}.
     * {@code key} must exist in UniCash.
     */
    public void removeTransaction(Transaction key) {
        transactions.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("transactions", transactions)
                .toString();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniCash)) {
            return false;
        }

        UniCash otherUniCash = (UniCash) other;
        return transactions.equals(otherUniCash.transactions);
    }

    @Override
    public int hashCode() {
        return transactions.hashCode();
    }
}
