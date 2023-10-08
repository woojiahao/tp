package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.income.Income;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyIncomeList {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Income> getIncomeList();

}
