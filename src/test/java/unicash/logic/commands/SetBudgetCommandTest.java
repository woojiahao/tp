package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static unicash.testutil.Assert.assertThrows;
import static unicash.testutil.TypicalBudgets.DAILY;
import static unicash.testutil.TypicalBudgets.MONTHLY;
import static unicash.testutil.TypicalBudgets.WEEKLY;

import java.nio.file.Path;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import unicash.commons.core.GuiSettings;
import unicash.logic.UniCashMessages;
import unicash.model.Model;
import unicash.model.ReadOnlyUniCash;
import unicash.model.ReadOnlyUserPrefs;
import unicash.model.UniCash;
import unicash.model.budget.Budget;
import unicash.model.transaction.Transaction;

public class SetBudgetCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SetBudgetCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        var command = new SetBudgetCommand(DAILY);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void execute_budgetAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBudgetAdded modelStub = new ModelStubAcceptingBudgetAdded();
        Budget validBudget = DAILY;

        assertNull(modelStub.budget);

        CommandResult commandResult = new SetBudgetCommand(validBudget).execute(modelStub);

        var expected = String.format(
                SetBudgetCommand.MESSAGE_SUCCESS,
                UniCashMessages.formatBudget(validBudget)
        );

        assertEquals(expected, commandResult.getFeedbackToUser());
        assertEquals(validBudget, modelStub.budget);
    }

    @Test
    public void equals() {
        SetBudgetCommand setBudgetCommand = new SetBudgetCommand(DAILY);
        SetBudgetCommand setBudgetCommandCopy = new SetBudgetCommand(DAILY);
        SetBudgetCommand addDifferentBudgetCommand = new SetBudgetCommand(WEEKLY);

        //same values -> returns true
        assertEquals(setBudgetCommand, setBudgetCommandCopy);

        //same budget command -> returns true
        assertEquals(setBudgetCommand, setBudgetCommand);

        //null -> returns false
        assertNotEquals(null, setBudgetCommand);

        //different type -> returns false
        assertNotEquals(addDifferentBudgetCommand, setBudgetCommand);

        assertFalse(setBudgetCommand.equals(5));
    }

    @Test
    public void toStringMethod() {
        SetBudgetCommand setBudgetCommand = new SetBudgetCommand(DAILY);
        String expected =
                SetBudgetCommand.class.getCanonicalName() + "{budget=" + DAILY + "}";
        assertEquals(expected, setBudgetCommand.toString());
    }

    @Test
    public void formatBudget() {
        String expectedDaily = "Amount: " + DAILY.getAmount().toString()
                + "; \nInterval: " + DAILY.getInterval().toString();
        String expectedWeekly = "Amount: " + WEEKLY.getAmount().toString()
                + "; \nInterval: " + WEEKLY.getInterval().toString();
        String expectedMonthly = "Amount: " + MONTHLY.getAmount().toString()
                + "; \nInterval: " + MONTHLY.getInterval().toString();
        assertEquals(expectedDaily, UniCashMessages.formatBudget(DAILY));
        assertEquals(expectedWeekly, UniCashMessages.formatBudget(WEEKLY));
        assertEquals(expectedMonthly, UniCashMessages.formatBudget(MONTHLY));
    }

    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getUniCashFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniCashFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTransaction(Transaction target, Transaction editedTransaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUniCash(ReadOnlyUniCash uniCash) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUniCash getUniCash() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTransaction(Transaction transaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isMax() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTransaction(Transaction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTransaction(Transaction transaction) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Transaction> getFilteredTransactionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTransactionList(Predicate<Transaction> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBudget(Budget budget) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void clearBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Budget getBudget() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public HashMap<String, Double> getExpenseSummaryPerCategory() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public HashMap<YearMonth, Double> getExpenseSummaryPerYearMonth() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasExpenses() {
            throw new AssertionError("This method should not be called.");
        }
    }

    private class ModelStubAcceptingBudgetAdded extends SetBudgetCommandTest.ModelStub {
        private Budget budget;

        public boolean hasBudget(Budget budget) {
            requireNonNull(budget);
            return this.budget.equals(budget);
        }

        @Override
        public void setBudget(Budget budget) {
            requireNonNull(budget);
            this.budget = budget;
        }

        @Override
        public ReadOnlyUniCash getUniCash() {
            return new UniCash();
        }
    }
}


