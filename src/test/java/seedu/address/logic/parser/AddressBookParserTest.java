package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.UniCashMessages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.commands.ClearTransactionsCommand;
import seedu.address.logic.commands.DeleteTransactionCommand;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.logic.commands.ExitCommandUniCash;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GetTotalExpenditureCommand;
import seedu.address.logic.commands.HelpCommandUniCash;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionNameContainsKeywordsPredicate;
import seedu.address.testutil.EditTransactionDescriptorBuilder;
import seedu.address.testutil.TransactionBuilder;
import seedu.address.testutil.TransactionUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommandUniCash.COMMAND_WORD) instanceof ExitCommandUniCash);
        assertTrue(parser.parseCommand(ExitCommandUniCash.COMMAND_WORD + " 3") instanceof ExitCommandUniCash);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new TransactionNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommandUniCash.COMMAND_WORD) instanceof HelpCommandUniCash);
        assertTrue(parser.parseCommand(HelpCommandUniCash.COMMAND_WORD + " 3") instanceof HelpCommandUniCash);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
    }

    @Test
    public void parseCommand_addTransaction() throws Exception {
        Transaction transaction = new TransactionBuilder().build();
        String s = TransactionUtil.getAddTransactionCommand(transaction);
        AddTransactionCommand command = (AddTransactionCommand) parser.parseCommand(s);
        assertEquals(new AddTransactionCommand(transaction), command);
    }

    @Test
    public void parseCommand_deleteTransaction() throws Exception {
        DeleteTransactionCommand command = (DeleteTransactionCommand)
                parser.parseCommand(
                        DeleteTransactionCommand.COMMAND_WORD + " "
                                + INDEX_FIRST_TRANSACTION.getOneBased());
        assertEquals(new DeleteTransactionCommand(INDEX_FIRST_TRANSACTION), command);
    }

    @Test
    public void parseCommand_clearTransaction() throws Exception {
        assertTrue(parser.parseCommand(ClearTransactionsCommand.COMMAND_WORD) instanceof ClearTransactionsCommand);
        assertTrue(parser.parseCommand(ClearTransactionsCommand.COMMAND_WORD + " 3")
                instanceof ClearTransactionsCommand);
    }

    @Test
    public void parseCommand_editTransaction() throws Exception {
        Transaction transaction = new TransactionBuilder().build();
        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder(transaction)
                .build();
        String input = EditTransactionCommand.COMMAND_WORD + " " + INDEX_FIRST_TRANSACTION.getOneBased() + " ";
        input += TransactionUtil.getEditTransactionDescriptorDetails(descriptor);
        EditTransactionCommand command = (EditTransactionCommand) parser.parseCommand(input);
        assertEquals(new EditTransactionCommand(INDEX_FIRST_TRANSACTION, descriptor), command);
    }

    @Test
    public void parseCommand_getTotalExpenditure() throws Exception {
        assertTrue(
                parser.parseCommand(GetTotalExpenditureCommand.COMMAND_WORD + " 8")
                        instanceof GetTotalExpenditureCommand
        );
        assertTrue(
                parser.parseCommand(ClearTransactionsCommand.COMMAND_WORD + " 8 c/Food")
                        instanceof ClearTransactionsCommand
        );
    }

    @Test
    public void parseCommand_helpUniCash() throws Exception {
        assertTrue(parser.parseCommand(
                HelpCommandUniCash.COMMAND_WORD) instanceof HelpCommandUniCash);
        assertTrue(parser.parseCommand(
                HelpCommandUniCash.COMMAND_WORD + " 3") instanceof HelpCommandUniCash);
    }

    @Test
    public void parseCommand_exitUniCash() throws Exception {
        assertTrue(parser.parseCommand(
                ExitCommandUniCash.COMMAND_WORD) instanceof ExitCommandUniCash);
        assertTrue(parser.parseCommand(
                ExitCommandUniCash.COMMAND_WORD + " 3") instanceof ExitCommandUniCash);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(
                ParseException.class,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommandUniCash.MESSAGE_USAGE),
                () -> parser.parseCommand("")
        );
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
