package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TRANSACTIONS;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all transactions";

    private static final Logger logger = Logger.getLogger("ListCommandLogger");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all transactions in UniCash. "
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_FAILURE = "Command not recognised. Try using the command " + COMMAND_WORD
            + " without any parameters instead.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.log(Level.INFO, "Executing list command");
        model.updateFilteredTransactionList(PREDICATE_SHOW_ALL_TRANSACTIONS);
        logger.log(Level.INFO, "List command executed successfully");
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
