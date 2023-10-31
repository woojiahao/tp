package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.logic.UniCashMessages.MESSAGE_UNICASH_WELCOME;
import static unicash.logic.UniCashMessages.MESSAGE_UNKNOWN_COMMAND;

import unicash.commons.enums.CommandType;
import unicash.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = CommandType.HELP.getCommandWords();

    public static final String MESSAGE_USAGE = CommandType.HELP.getMessageUsage();

    public static final String SHOWING_HELP_MESSAGE = MESSAGE_UNICASH_WELCOME;
    private final String target;

    /**
     * Creates a HelpCommand to show help for the specified {@code target} command.
     *
     * @param target The command word to show help for.
     */
    public HelpCommand(String target) {
        requireNonNull(target);
        this.target = target;
    }

    @Override
    public CommandResult execute(Model model) {
        if (target.isBlank()) {
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        }
        CommandType commandType = CommandType.parseCommandType(target);
        switch (commandType) {
        case FIND:
            return new CommandResult(FindCommand.MESSAGE_USAGE);

        case LIST:
            return new CommandResult(ListCommand.MESSAGE_USAGE);

        case ADD_TRANSACTION:
            return new CommandResult(AddTransactionCommand.MESSAGE_USAGE);

        case EDIT_TRANSACTION:
            return new CommandResult(EditTransactionCommand.MESSAGE_USAGE);

        case DELETE_TRANSACTION:
            return new CommandResult(DeleteTransactionCommand.MESSAGE_USAGE);

        case GET_TOTAL_EXPENDITURE:
            return new CommandResult(GetTotalExpenditureCommand.MESSAGE_USAGE);

        case CLEAR_TRANSACTIONS:
            return new CommandResult(ClearTransactionsCommand.MESSAGE_USAGE);

        case ADD_BUDGET:
            return new CommandResult(AddBudgetCommand.MESSAGE_USAGE);

        case RESET:
            return new CommandResult(ResetCommand.MESSAGE_USAGE);

        case GET:
            return new CommandResult(GetCommand.MESSAGE_USAGE);

        case HELP:
            return new CommandResult(HelpCommand.MESSAGE_USAGE);

        case EXIT:
            return new CommandResult(ExitCommand.MESSAGE_USAGE);

        case SUMMARY:
            return new CommandResult(SummaryCommand.MESSAGE_USAGE);

        default:
            return new CommandResult(String.format("%s\n\n%s",
                    MESSAGE_UNKNOWN_COMMAND, MESSAGE_USAGE));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        HelpCommand otherCommand = (HelpCommand) other;
        return target.equals(otherCommand.target);
    }
}
