package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.logic.UniCashMessages.MESSAGE_UNICASH_WELCOME;
import static unicash.logic.UniCashMessages.MESSAGE_UNKNOWN_COMMAND;

import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription("Shows UniCa$h general usage instructions and specific command"
                    + " usage by specifying the command word.")
            .setArgument("Command word specified must be a valid command word present in "
                    + "the help command")
            .setExample(ExampleGenerator.generate(COMMAND_WORD, "add_transaction"))
            .build()
            .toString();

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
        switch (target) {
        case FindCommand.COMMAND_WORD:
            return new CommandResult(FindCommand.MESSAGE_USAGE);

        case ListCommand.COMMAND_WORD:
            return new CommandResult(ListCommand.MESSAGE_USAGE);

        case AddTransactionCommand.COMMAND_WORD:
            return new CommandResult(AddTransactionCommand.MESSAGE_USAGE);

        case EditTransactionCommand.COMMAND_WORD:
            return new CommandResult(EditTransactionCommand.MESSAGE_USAGE);

        case DeleteTransactionCommand.COMMAND_WORD:
            return new CommandResult(DeleteTransactionCommand.MESSAGE_USAGE);

        case GetTotalExpenditureCommand.COMMAND_WORD:
            return new CommandResult(GetTotalExpenditureCommand.MESSAGE_USAGE);

        case ClearTransactionsCommand.COMMAND_WORD:
            return new CommandResult(ClearTransactionsCommand.MESSAGE_USAGE);

        case AddBudgetCommand.COMMAND_WORD:
            return new CommandResult(AddBudgetCommand.MESSAGE_USAGE);

        case ResetCommand.COMMAND_WORD:
            return new CommandResult(ResetCommand.MESSAGE_USAGE);

        case GetCommand.COMMAND_WORD:
            return new CommandResult(GetCommand.MESSAGE_USAGE);

        case HelpCommand.COMMAND_WORD:
            return new CommandResult(HelpCommand.MESSAGE_USAGE);

        case ExitCommand.COMMAND_WORD:
            return new CommandResult(ExitCommand.MESSAGE_USAGE);

        case SummaryCommand.COMMAND_WORD:
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


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("target", target)
                .toString();
    }
}
