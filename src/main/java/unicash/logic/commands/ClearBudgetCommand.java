package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import unicash.model.Model;

/**
 * Clears the current set budget in UniCa$h.
 */
public class ClearBudgetCommand extends Command {

    public static final String COMMAND_WORD = "clear_budget";
    public static final String MESSAGE_SUCCESS = "Budget cleared.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.clearBudget();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        // All instances of ClearBudgetCommand is the same
        return other instanceof ClearBudgetCommand;
    }
}
