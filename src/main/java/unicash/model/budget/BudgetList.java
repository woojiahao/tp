package unicash.model.budget;

import static java.util.Objects.requireNonNull;
import static unicash.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import unicash.model.budget.exceptions.BudgetNotFoundException;


/**
 * A list of budgets that enforces uniqueness between its elements and does not allow nulls.
 */
public class BudgetList implements Iterable<Budget> {

    private final ObservableList<Budget> internalList = FXCollections.observableArrayList();
    private final ObservableList<Budget> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent budget as the given argument.
     */
    public boolean contains(Budget budget) {
        requireNonNull(budget);
        return internalList.stream().anyMatch(budget::equals);
    }

    /**
     * Adds a budget to the list.
     */
    public void add(Budget budget) {
        requireNonNull(budget);
        internalList.add(budget);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Budget> iterator() {
        return internalList.iterator();
    }

    public void setBudget(Budget target, Budget editedBudget) {
        requireAllNonNull(target, editedBudget);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new BudgetNotFoundException();
        }

        internalList.set(index, editedBudget);
    }

    public ObservableList<Budget> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }
}
