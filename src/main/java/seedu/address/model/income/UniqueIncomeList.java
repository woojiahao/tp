package seedu.address.model.income;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.income.exceptions.DuplicateIncomeException;
import seedu.address.model.income.exceptions.IncomeNotFoundException;

/**
 * A list of Incomes that enforces uniqueness between its elements and does not allow nulls.
 * An Income is considered unique by comparing using {@code Income#equals(Object)}. As such, adding and updating of
 * Incomes uses Income#isSameIncome(Income) for equality to ensure that the Income being added or updated is
 * unique in terms of identity in the UniqueIncomeList. However, the removal of an Income uses Income#equals(Object)
 * to ensure that the Income with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Income#equals(Object)
 */
public class UniqueIncomeList implements Iterable<Income> {

    private final ObservableList<Income> internalList = FXCollections.observableArrayList();
    private final ObservableList<Income> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Income as the given argument.
     */
    public boolean contains(Income toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Income to the list.
     * The Income must not already exist in the list.
     */
    public void add(Income toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateIncomeException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Income {@code target} in the list with {@code editedIncome}.
     * {@code target} must exist in the list.
     * The Income identity of {@code editedIncome} must not be the same as another existing Income in the list.
     */
    public void setIncome(Income target, Income editedIncome) {
        requireAllNonNull(target, editedIncome);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new IncomeNotFoundException();
        }

        if (!target.equals(editedIncome) && contains(editedIncome)) {
            throw new DuplicateIncomeException();
        }

        internalList.set(index, editedIncome);
    }

    /**
     * Removes the equivalent Income from the list.
     * The Income must exist in the list.
     */
    public void remove(Income toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new IncomeNotFoundException();
        }
    }

    public void setIncomes(UniqueIncomeList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Incomes}.
     * {@code Incomes} must not contain duplicate Incomes.
     */
    public void setIncomes(List<Income> incomes) {
        requireAllNonNull(incomes);
        if (!incomesAreUnique(incomes)) {
            throw new DuplicateIncomeException();
        }

        internalList.setAll(incomes);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Income> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Income> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueIncomeList)) {
            return false;
        }

        UniqueIncomeList otherUniqueIncomeList = (UniqueIncomeList) other;
        return internalList.equals(otherUniqueIncomeList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code Incomes} contains only unique Incomes.
     */
    private boolean incomesAreUnique(List<Income> incomes) {
        for (int i = 0; i < incomes.size() - 1; i++) {
            for (int j = i + 1; j < incomes.size(); j++) {
                if (incomes.get(i).equals(incomes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
