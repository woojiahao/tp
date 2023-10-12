package seedu.address.logic.commands.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

/**
 * Adds an income to the income list.
 */
public class AddTransactionCommand extends Command {
    public static final String COMMAND_WORD = "add_income";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an income to the income list. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_AMOUNT + "VALUE "
            + PREFIX_DATETIME + "DATE \n "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "work at liho "
            + PREFIX_AMOUNT + "300 "
            + PREFIX_DATETIME + "18/08/2001 19:30";

    public static final String MESSAGE_SUCCESS = "New transaction added: %1$s";
    public static final String MESSAGE_DUPLICATE_INCOME = "This income already exists in the income list";

    private final Transaction toAdd;

    /**
     * Creates an AddTransactionCommand to add the specified {@code Transaction}
     */
    public AddTransactionCommand(Transaction income) {
        requireNonNull(income);
        toAdd = income;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTransaction(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INCOME);
        }

        model.addTransaction(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.formatTransaction(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTransactionCommand)) {
            return false;
        }

        AddTransactionCommand otherAddTransactionCommand = (AddTransactionCommand) other;
        return toAdd.equals(otherAddTransactionCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
