package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static unicash.logic.UniCashMessages.MESSAGE_UNKNOWN_COMMAND;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static unicash.testutil.Assert.assertThrows;
import static unicash.testutil.TypicalBudgets.MONTHLY;
import static unicash.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import unicash.commons.core.index.Index;
import unicash.commons.enums.CommandType;
import unicash.logic.commands.AddTransactionCommand;
import unicash.logic.commands.ClearBudgetCommand;
import unicash.logic.commands.ClearTransactionsCommand;
import unicash.logic.commands.DeleteTransactionCommand;
import unicash.logic.commands.EditTransactionCommand;
import unicash.logic.commands.ExitCommand;
import unicash.logic.commands.FindCommand;
import unicash.logic.commands.GetBudgetCommand;
import unicash.logic.commands.GetCommand;
import unicash.logic.commands.GetTotalExpenditureCommand;
import unicash.logic.commands.HelpCommand;
import unicash.logic.commands.ListCommand;
import unicash.logic.commands.ResetCommand;
import unicash.logic.commands.SetBudgetCommand;
import unicash.logic.commands.SummaryCommand;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.predicates.TransactionContainsAllKeywordsPredicate;
import unicash.testutil.EditTransactionDescriptorBuilder;
import unicash.testutil.TransactionBuilder;
import unicash.testutil.TransactionUtil;

public class UniCashParserTest {

    private final UniCashParser parser = new UniCashParser();

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(CommandType.EXIT.getMainCommandWord()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(CommandType.EXIT.getMainCommandWord() + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(CommandType.HELP.getMainCommandWord()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(CommandType.HELP.getMainCommandWord() + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(CommandType.LIST.getMainCommandWord()) instanceof ListCommand);
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
                        CommandType.DELETE_TRANSACTION.getMainCommandWord() + " "
                                + INDEX_FIRST_TRANSACTION.getOneBased());
        assertEquals(new DeleteTransactionCommand(INDEX_FIRST_TRANSACTION), command);
    }

    @Test
    public void parseCommand_clearTransactions() throws Exception {
        assertTrue(parser.parseCommand(CommandType.CLEAR_TRANSACTIONS.getMainCommandWord())
                instanceof ClearTransactionsCommand);

        String message = ClearTransactionsCommand.MESSAGE_FAILURE;
        assertThrows(ParseException.class, message, () -> parser.parseCommand(
                CommandType.CLEAR_TRANSACTIONS.getMainCommandWord() + " 3"));

    }

    @Test
    public void parseCommand_resetUniCashCommand() throws Exception {
        assertTrue(parser.parseCommand(CommandType.RESET.getMainCommandWord()) instanceof ResetCommand);

        String message = ResetCommand.MESSAGE_FAILURE;
        assertThrows(ParseException.class, message, () -> parser.parseCommand(
                CommandType.RESET.getMainCommandWord() + " 3"));
    }

    @Test
    public void parseCommand_editTransaction() throws Exception {
        Transaction transaction = new TransactionBuilder().build();
        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder(transaction)
                .build();
        String input = CommandType.EDIT_TRANSACTION.getMainCommandWord() + " "
                + INDEX_FIRST_TRANSACTION.getOneBased() + " ";
        input += TransactionUtil.getEditTransactionDescriptorDetails(descriptor);
        EditTransactionCommand command = (EditTransactionCommand) parser.parseCommand(input);
        assertEquals(new EditTransactionCommand(INDEX_FIRST_TRANSACTION, descriptor), command);
    }

    @Test
    public void parseCommand_getTotalExpenditure() throws Exception {
        assertTrue(
                parser.parseCommand(CommandType.GET_TOTAL_EXPENDITURE.getMainCommandWord() + " month/8")
                        instanceof GetTotalExpenditureCommand
        );
        assertTrue(
                parser.parseCommand(CommandType.GET_TOTAL_EXPENDITURE.getMainCommandWord() + " month/8 c/Food")
                        instanceof GetTotalExpenditureCommand
        );
    }

    @Test
    public void parseCommand_helpUniCash() throws Exception {
        assertTrue(parser.parseCommand(
                CommandType.HELP.getMainCommandWord()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(
                CommandType.HELP.getMainCommandWord() + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_exitUniCash() throws Exception {
        assertTrue(parser.parseCommand(
                CommandType.EXIT.getMainCommandWord()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(
                CommandType.EXIT.getMainCommandWord() + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_summary() throws Exception {
        assertTrue(parser.parseCommand(
                CommandType.SUMMARY.getMainCommandWord()) instanceof SummaryCommand);
        assertTrue(parser.parseCommand(
                CommandType.SUMMARY.getMainCommandWord() + " 3") instanceof SummaryCommand);
    }

    @Test
    public void parseCommand_findCommand() throws Exception {
        List<String> keywords = Arrays.asList("n/foo", "l/bar", "c/baz");
        TransactionContainsAllKeywordsPredicate allKeywordsPredicate = getSamplePredicate();

        var joinedKeywords = keywords.stream().collect(Collectors.joining(" "));
        var findCommand = (FindCommand) parser.parseCommand(
                CommandType.FIND.getMainCommandWord() + " " + joinedKeywords
        );

        assertEquals(new FindCommand(allKeywordsPredicate), findCommand);

    }

    private static TransactionContainsAllKeywordsPredicate getSamplePredicate() {
        List<String> parsedKeywords = Arrays.asList("foo", "bar", "baz");
        List<Predicate<Transaction>> predicateList = new ArrayList<>();

        TransactionContainsAllKeywordsPredicate allKeywordsPredicate =
                new TransactionContainsAllKeywordsPredicate(predicateList);

        allKeywordsPredicate.addNameKeyword((parsedKeywords.get(0)));
        allKeywordsPredicate.addCategoryKeyword(parsedKeywords.get(2));
        allKeywordsPredicate.addLocationKeyword(parsedKeywords.get(1));
        return allKeywordsPredicate;
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        var message = String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        assertThrows(ParseException.class, message, () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_setBudget() throws Exception {
        String validCommand = "set_budget " + PREFIX_AMOUNT + MONTHLY.getAmount().toString() + " "
                + PREFIX_INTERVAL + MONTHLY.getInterval().toString();
        SetBudgetCommand command = (SetBudgetCommand) parser.parseCommand(validCommand);
        assertEquals(new SetBudgetCommand(MONTHLY), command);
    }

    @Test
    public void parseCommand_clearBudget() throws Exception {
        String validCommand = "clear_budget";
        var command = (ClearBudgetCommand) parser.parseCommand(validCommand);
        assertEquals(new ClearBudgetCommand(), command);
    }

    @Test
    public void parseCommand_getCommand() throws Exception {
        String validCommand = "get 1";
        var command = (GetCommand) parser.parseCommand(validCommand);
        assertEquals(new GetCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parseCommand_getBudget() throws Exception {
        String validCommand = "get_budget";
        var command = (GetBudgetCommand) parser.parseCommand(validCommand);
        assertEquals(new GetBudgetCommand(), command);
    }
}
