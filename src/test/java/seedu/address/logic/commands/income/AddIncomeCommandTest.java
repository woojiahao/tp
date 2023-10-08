package seedu.address.logic.commands.income;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIncomes.NUS;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommandTest;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.*;
import seedu.address.model.income.Income;
import seedu.address.model.person.Person;
import seedu.address.testutil.IncomeBuilder;

public class AddIncomeCommandTest {

    @Test
    public void constructor_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddIncomeCommand(null));
    }

    @Test
    public void execute_IncomeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingIncomeAdded modelStub = new ModelStubAcceptingIncomeAdded();
        Income validIncome = new IncomeBuilder().build();

        CommandResult commandResult = new AddIncomeCommand(validIncome).execute(modelStub);

        assertEquals(String.format(AddIncomeCommand.MESSAGE_SUCCESS, Messages.formatIncome(validIncome)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validIncome), modelStub.incomesAdded);
    }

    @Test
    public void execute_duplicateIncome_throwsCommandException() {
        Income validIncome = new IncomeBuilder().build();
        AddIncomeCommand AddIncomeCommand = new AddIncomeCommand(validIncome);
        ModelStub modelStub = new ModelStubWithIncome(validIncome);

        assertThrows(CommandException.class, AddIncomeCommand.MESSAGE_DUPLICATE_INCOME,
                () -> AddIncomeCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Income nus = new IncomeBuilder().withName("Nus").build();
        Income intern = new IncomeBuilder().withName("Intern").build();
        AddIncomeCommand addNusCommand = new AddIncomeCommand(nus);
        AddIncomeCommand addBobCommand = new AddIncomeCommand(intern);

        // same object -> returns true
        assertTrue(addNusCommand.equals(addNusCommand));

        // same values -> returns true
        AddIncomeCommand addNusCommandCopy = new AddIncomeCommand(nus);
        assertTrue(addNusCommand.equals(addNusCommandCopy));

        // different types -> returns false
        assertFalse(addNusCommand.equals(1));

        // null -> returns false
        assertFalse(addNusCommand.equals(null));

        // different Income -> returns false
        assertFalse(addNusCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddIncomeCommand AddIncomeCommand = new AddIncomeCommand(NUS);
        String expected = AddIncomeCommand.class.getCanonicalName() + "{toAdd=" + NUS + "}";
        assertEquals(expected, AddIncomeCommand.toString());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
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
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setIncomeList(ReadOnlyIncomeList incomeList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyIncomeList getIncomeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasIncome(Income income) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteIncome(Income target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addIncome(Income income) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Income> getFilteredIncomeList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredIncomeList(Predicate<Income> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithIncome extends AddIncomeCommandTest.ModelStub {
        private final Income income;

        ModelStubWithIncome(Income income) {
            requireNonNull(income);
            this.income = income;
        }

        @Override
        public boolean hasIncome(Income income) {
            requireNonNull(income);
            return this.income.equals(income);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingIncomeAdded extends AddIncomeCommandTest.ModelStub {
        final ArrayList<Income> incomesAdded = new ArrayList<>();

        @Override
        public boolean hasIncome(Income income) {
            requireNonNull(income);
            return incomesAdded.stream().anyMatch(income::equals);
        }

        @Override
        public void addIncome(Income income) {
            requireNonNull(income);
            incomesAdded.add(income);
        }

        @Override
        public ReadOnlyIncomeList getIncomeList() {
            return new IncomeList();
        }
    }

}
