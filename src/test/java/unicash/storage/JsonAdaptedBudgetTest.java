package unicash.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static unicash.testutil.Assert.assertThrows;
import static unicash.testutil.TypicalBudgets.MONTHLY;

import org.junit.jupiter.api.Test;

import unicash.commons.exceptions.IllegalValueException;
import unicash.model.budget.Budget;
import unicash.model.budget.Interval;
import unicash.model.commons.Amount;

public class JsonAdaptedBudgetTest {
    @Test
    public void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedBudget(null));
    }

    @Test
    public void constructor_fromOtherBudget_formsSameBudget() throws IllegalValueException {
        var budget = new JsonAdaptedBudget(MONTHLY);
        assertEquals(MONTHLY, budget.toModelType());
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        var budget = new JsonAdaptedBudget(-15.12, "day");
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    public void toModelType_nullInterval_throwsIllegalValueException() {
        var budget = new JsonAdaptedBudget(15.12, null);
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    public void toModelType_invalidInterval_throwsIllegalValueException() {
        var budget = new JsonAdaptedBudget(15.12, "hi");
        assertThrows(IllegalValueException.class, budget::toModelType);
    }

    @Test
    public void toModelType_valid_returnsBudget() throws IllegalValueException {
        var expected = new Budget(new Amount(15.17), new Interval("month"));
        var budget = new JsonAdaptedBudget(15.17, "month");
        assertEquals(expected, budget.toModelType());
    }
}
