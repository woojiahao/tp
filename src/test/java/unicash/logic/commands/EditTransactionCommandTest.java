package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import unicash.commons.core.index.Index;
import unicash.logic.UniCashMessages;
import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UserPrefs;
import unicash.model.transaction.Transaction;
import unicash.testutil.EditTransactionDescriptorBuilder;
import unicash.testutil.TransactionBuilder;
import unicash.testutil.TypicalIndexes;
import unicash.testutil.TypicalTransactions;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditTransactionCommand.
 */
public class EditTransactionCommandTest {

    private final Model model = new ModelManager(TypicalTransactions.getTypicalUniCash(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Transaction editedTransaction = new TransactionBuilder().build();
        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder(
                editedTransaction).build();
        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION, descriptor);

        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(editedTransaction));

        Model expectedModel = new ModelManager(
                TypicalTransactions.getTypicalUniCash(), new UserPrefs());
        expectedModel.setTransaction(model.getFilteredTransactionList().get(0), editedTransaction);

        CommandTestUtil.assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastTransaction = Index.fromOneBased(model.getFilteredTransactionList().size());
        Transaction lastTransaction = model.getFilteredTransactionList().get(indexLastTransaction.getZeroBased());

        TransactionBuilder transactionInList = new TransactionBuilder(lastTransaction);
        Transaction editedTransaction = transactionInList
                .withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS)
                .withAmount(CommandTestUtil.VALID_AMOUNT_NUS)
                .withDateTime(CommandTestUtil.VALID_DATETIME_NUS).build();

        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS)
                .withAmount(CommandTestUtil.VALID_AMOUNT_NUS)
                .withDateTime(CommandTestUtil.VALID_DATETIME_NUS).build();
        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(indexLastTransaction, descriptor);

        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(editedTransaction));

        Model expectedModel = new ModelManager(
                TypicalTransactions.getTypicalUniCash(), new UserPrefs());
        expectedModel.setTransaction(lastTransaction, editedTransaction);

        CommandTestUtil.assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION,
                new EditTransactionCommand.EditTransactionDescriptor());
        Transaction editedTransaction = model.getFilteredTransactionList().get(TypicalIndexes.INDEX_FIRST_TRANSACTION.getZeroBased());

        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(editedTransaction));

        Model expectedModel = new ModelManager(
                TypicalTransactions.getTypicalUniCash(), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showTransactionAtIndex(model, TypicalIndexes.INDEX_FIRST_TRANSACTION);

        Transaction transactionInFilteredList = model.getFilteredTransactionList()
                .get(TypicalIndexes.INDEX_FIRST_TRANSACTION.getZeroBased());
        Transaction editedTransaction = new TransactionBuilder(transactionInFilteredList)
                .withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS).build();
        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION,
                new EditTransactionDescriptorBuilder().withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS).build());

        String expectedMessage = String.format(EditTransactionCommand.MESSAGE_EDIT_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(editedTransaction));

        Model expectedModel = new ModelManager(
                TypicalTransactions.getTypicalUniCash(), new UserPrefs());
        expectedModel.setTransaction(model.getFilteredTransactionList().get(0), editedTransaction);

        CommandTestUtil.assertCommandSuccess(editTransactionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidTransactionIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTransactionList().size() + 1);
        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS).build();
        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(outOfBoundIndex, descriptor);

        CommandTestUtil.assertCommandFailure(editTransactionCommand, model,
                UniCashMessages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTransactionIndexFilteredList_failure() {
        CommandTestUtil.showTransactionAtIndex(model, TypicalIndexes.INDEX_FIRST_TRANSACTION);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_TRANSACTION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getUniCash().getTransactionList().size());

        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(outOfBoundIndex,
                new EditTransactionDescriptorBuilder().withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS).build());

        CommandTestUtil.assertCommandFailure(editTransactionCommand, model,
                UniCashMessages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditTransactionCommand standardCommand = new EditTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION, CommandTestUtil.DESC_NUS);

        // same values -> returns true
        EditTransactionCommand.EditTransactionDescriptor copyDescriptor = new EditTransactionCommand.EditTransactionDescriptor(CommandTestUtil.DESC_NUS);
        EditTransactionCommand commandWithSameValues = new EditTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION,
                copyDescriptor);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(copyDescriptor, copyDescriptor);
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(copyDescriptor, standardCommand);
        assertNotEquals(standardCommand, new ClearTransactionsCommand());

        assertFalse(standardCommand.equals(copyDescriptor));

        // different index -> returns false
        assertNotEquals(standardCommand, new EditTransactionCommand(TypicalIndexes.INDEX_SECOND_TRANSACTION, CommandTestUtil.DESC_NUS));

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new EditTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION, CommandTestUtil.DESC_SHOPPING));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditTransactionCommand.EditTransactionDescriptor editTransactionDescriptor = new EditTransactionCommand.EditTransactionDescriptor();
        EditTransactionCommand editTransactionCommand = new EditTransactionCommand(index, editTransactionDescriptor);
        String expected = EditTransactionCommand.class.getCanonicalName()
                + "{index=" + index + ", editTransactionDescriptor=" + editTransactionDescriptor + "}";
        assertEquals(expected, editTransactionCommand.toString());
    }

}

