package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.income.Income;
import seedu.address.model.income.UniqueIncomeList;

/**
 * Wraps all data at the income-book level
 * Duplicates are not allowed (by .equals comparison)
 */
public class IncomeList implements ReadOnlyIncomeList {

    private final UniqueIncomeList incomes;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        incomes = new UniqueIncomeList();
    }

    public IncomeList() {}

    /**
     * Creates an AddressBook using the Incomes in the {@code toBeCopied}
     */
    public IncomeList(ReadOnlyIncomeList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Income list with {@code Incomes}.
     * {@code Incomes} must not contain duplicate Incomes.
     */
    public void setIncomes(List<Income> income) {
        this.incomes.setIncomes(income);
    }

    /**
     * Resets the existing data of this {@code IncomeBook} with {@code newData}.
     */
    public void resetData(ReadOnlyIncomeList newData) {
        requireNonNull(newData);

        setIncomes(newData.getIncomeList());
    }

    //// Income-level operations

    /**
     * Returns true if an Income with the same identity as {@code Income} exists in the address book.
     */
    public boolean hasIncome(Income income) {
        requireNonNull(income);
        return incomes.contains(income);
    }

    /**
     * Adds an Income to the address book.
     * The Income must not already exist in the address book.
     */
    public void addIncome(Income p) {
        incomes.add(p);
    }

    /**
     * Replaces the given Income {@code target} in the list with {@code editedIncome}.
     * {@code target} must exist in the address book.
     * The Income identity of {@code editedIncome} must not be the same as another existing Income in the address book.
     */
    public void setIncome(Income target, Income editedIncome) {
        requireNonNull(editedIncome);

        incomes.setIncome(target, editedIncome);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeIncome(Income key) {
        incomes.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("incomes", incomes)
                .toString();
    }

    @Override
    public ObservableList<Income> getIncomeList() {
        return incomes.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IncomeList)) {
            return false;
        }

        IncomeList otherIncomeList = (IncomeList) other;
        return incomes.equals(otherIncomeList.incomes);
    }

    @Override
    public int hashCode() {
        return incomes.hashCode();
    }
}
