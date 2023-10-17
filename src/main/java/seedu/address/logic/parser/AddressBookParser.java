package seedu.address.logic.parser;

import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.UniCashMessages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.commands.ClearTransactionsCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteTransactionCommand;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.logic.commands.ExitCommandUniCash;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GetTotalExpenditureCommand;
import seedu.address.logic.commands.HelpCommandUniCash;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommandUniCash.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case AddTransactionCommand.COMMAND_WORD:
            return new AddTransactionCommandParser().parse(arguments);

        case EditTransactionCommand.COMMAND_WORD:
            return new EditTransactionCommandParser().parse(arguments);

        case DeleteTransactionCommand.COMMAND_WORD:
            return new DeleteTransactionCommandParser().parse(arguments);

        case GetTotalExpenditureCommand.COMMAND_WORD:
            return new GetTotalExpenditureCommandParser().parse(arguments);

        case ClearTransactionsCommand.COMMAND_WORD:
            return new ClearTransactionsCommand();

        case HelpCommandUniCash.COMMAND_WORD:
            return new HelpCommandUniCash();

        case ExitCommandUniCash.COMMAND_WORD:
            return new ExitCommandUniCash();


        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
