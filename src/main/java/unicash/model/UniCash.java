package unicash.model;

import static java.util.Objects.requireNonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import unicash.commons.util.ToStringBuilder;
import unicash.model.budget.Budget;
import unicash.model.category.Category;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.TransactionList;

/**
 * Wraps all data in UniCash
 */
public class UniCash implements ReadOnlyUniCash {

    private final TransactionList transactions;
    private Budget budget;

    /**
     * Creates UniCash instance with starting values of {@code TransactionList} and {@code Budget}.
     */
    public UniCash() {
        transactions = new TransactionList();
        // Null budget is equivalent to having no budget
        budget = null;
    }

    /**
     * Creates an UniCash using the Transactions in the {@code toBeCopied}.
     *
     * <p>Sets default values before copying any values over using {@link #UniCash() constructor}.</p>
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
        if (newData.getBudget() != null) {
            setBudget(newData.getBudget());
        }
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

    /**
     * Set budget.
     *
     * @throws NullPointerException if {@code budget} is null.
     */
    public void setBudget(Budget budget) {
        requireNonNull(budget);
        this.budget = budget;
    }

    /**
     * Clears budget by setting to null.
     *
     * <p>Special case of {@link #setBudget(Budget) setBudget} to avoid accidentally allowing users to set
     * null {@code budget}</p>
     */
    public void clearBudget() {
        budget = null;
    }

    /**
     * Returns the amount for each category of expenses.
     * Note: This function ignores all 'income' transactions
     */
    HashMap<String, Double> getSumOfExpensePerCategory() {
        HashMap<String, Double> sumPerCategory = new HashMap<>();
        ObservableList<Transaction> allTransactions = getTransactionList();
        String uncategorizedCategoryName = "Uncategorized";
        for (Transaction t : allTransactions) {
            if (t.getType().toString().equals("income")) {
                continue;
            }
            Double transactionAmount = t.getAmountAsDouble();
            // If t has no categories
            if (t.getCategories().asUnmodifiableObservableList().isEmpty()) {
                if (!sumPerCategory.containsKey(uncategorizedCategoryName)) {
                    sumPerCategory.put(uncategorizedCategoryName, (double) 0);
                }
                Double currAmount = sumPerCategory.get(uncategorizedCategoryName);
                Double newAmount = currAmount + transactionAmount;
                sumPerCategory.put(uncategorizedCategoryName, newAmount);
                continue;
            }
            // If t has at least one category
            for (Category transactionCategory : t.getCategories()) {
                if (!sumPerCategory.containsKey(transactionCategory.category)) {
                    sumPerCategory.put(transactionCategory.category, (double) 0);
                }
                Double currAmount = sumPerCategory.get(transactionCategory.category);
                Double newAmount = currAmount + transactionAmount;
                sumPerCategory.put(transactionCategory.category, newAmount);
            }
        }
        return sumPerCategory;
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("transactions", transactions).toString();
    }

    @Override
    public ObservableList<Transaction> getTransactionList() {
        return transactions.asUnmodifiableObservableList();
    }

    /**
     * Returns an unmodifiable view of the budget.
     *
     * <p>If current budget is null, there is no user imposed budget, so null should be returned as well.</p>
     */
    @Override
    public Budget getBudget() {
        if (budget == null) {
            return null;
        }
        // Create a deep copy of the current budget so that a direct object reference is not passed to callee
        return new Budget(budget);
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
        if (budget == null) {
            // If current budget is null, other budget should also be null
            return transactions.equals(otherUniCash.transactions) && otherUniCash.budget == null;
        }
        return transactions.equals(otherUniCash.transactions) && budget.equals(otherUniCash.budget);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions, budget);
    }
}
