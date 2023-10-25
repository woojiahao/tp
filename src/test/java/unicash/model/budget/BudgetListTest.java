package unicash.model.budget;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.testutil.Assert.assertThrows;
import static unicash.testutil.TypicalBudgets.DAILY;
import static unicash.testutil.TypicalBudgets.MONTHLY;
import static unicash.testutil.TypicalBudgets.WEEKLY;

import org.junit.jupiter.api.Test;

import unicash.model.budget.exceptions.BudgetNotFoundException;
import unicash.testutil.BudgetBuilder;

public class BudgetListTest {
    private final BudgetList budgetList = new BudgetList();

    @Test
    public void contains_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.contains(null));
    }

    @Test
    public void contains_budgetNotInList_returnsFalse() {
        assertFalse(budgetList.contains(MONTHLY));
    }

    @Test
    public void contains_budgetInList_returnsTrue() {
        budgetList.add(DAILY);
        assertTrue(budgetList.contains(DAILY));
    }

    @Test
    public void contains_differentBudget_returnsFalse() {
        budgetList.add(WEEKLY);
        Budget editedBudget = new BudgetBuilder(MONTHLY).withAmount(500).build();
        assertFalse(budgetList.contains(editedBudget));
    }

    @Test
    public void add_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.add(null));
    }

    @Test
    public void add_duplicateBudget_doesNotThrow() {
        budgetList.add(MONTHLY);
        assertDoesNotThrow(() -> budgetList.add(MONTHLY));
    }

    @Test
    public void setBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(null, WEEKLY));
    }

    @Test
    public void setBudget_nullEditedBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(WEEKLY, null));
    }

    @Test
    public void setBudget_budgetNotInList_throwsBudgetNotFoundException() {
        assertThrows(BudgetNotFoundException.class, () -> budgetList.setBudget(WEEKLY, WEEKLY));
    }

}
