package seedu.address.logic.commands.transaction;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.NUS;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUniCash;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UniCash;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.TransactionBuilder;

public class AddTransactionCommandTest {

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTransactionCommand(null));
    }

    @Test
    public void execute_incomeAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTransactionAdded modelStub = new ModelStubAcceptingTransactionAdded();
        Transaction validTransaction = new TransactionBuilder().build();

        CommandResult commandResult = new AddTransactionCommand(validTransaction).execute(modelStub);

        assertEquals(String.format(AddTransactionCommand.MESSAGE_SUCCESS, Messages.formatTransaction(validTransaction)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTransaction), modelStub.incomesAdded);
    }

    @Test
    public void equals() {
        Transaction nus = new TransactionBuilder().withName("Nus").build();
        Transaction intern = new TransactionBuilder().withName("Intern").build();
        AddTransactionCommand addNusCommand = new AddTransactionCommand(nus);
        AddTransactionCommand addBobCommand = new AddTransactionCommand(intern);

        // same object -> returns true
        assertTrue(addNusCommand.equals(addNusCommand));

        // same values -> returns true
        AddTransactionCommand addNusCommandCopy = new AddTransactionCommand(nus);
        assertTrue(addNusCommand.equals(addNusCommandCopy));

        // different types -> returns false
        assertFalse(addNusCommand.equals(1));

        // null -> returns false
        assertFalse(addNusCommand.equals(null));

        // different Transaction -> returns false
        assertFalse(addNusCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddTransactionCommand addTransactionCommand = new AddTransactionCommand(NUS);
        String expected = AddTransactionCommand.class.getCanonicalName() + "{toAdd=" + NUS + "}";
        assertEquals(expected, addTransactionCommand.toString());
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
        public void setUniCash(ReadOnlyUniCash uniCash) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUniCash getUniCash() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTransaction(Transaction income) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTransaction(Transaction target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTransaction(Transaction income) {
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
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTransaction extends AddTransactionCommandTest.ModelStub {
        private final Transaction income;

        ModelStubWithTransaction(Transaction income) {
            requireNonNull(income);
            this.income = income;
        }

        @Override
        public boolean hasTransaction(Transaction income) {
            requireNonNull(income);
            return this.income.equals(income);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTransactionAdded extends AddTransactionCommandTest.ModelStub {
        final ArrayList<Transaction> incomesAdded = new ArrayList<>();

        @Override
        public boolean hasTransaction(Transaction income) {
            requireNonNull(income);
            return incomesAdded.stream().anyMatch(income::equals);
        }

        @Override
        public void addTransaction(Transaction income) {
            requireNonNull(income);
            incomesAdded.add(income);
        }

        @Override
        public ReadOnlyUniCash getUniCash() {
            return new UniCash();
        }
    }

}
