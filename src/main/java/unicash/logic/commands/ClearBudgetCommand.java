package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import unicash.commons.enums.CommandType;
import unicash.model.Model;

/**
 * Clears the current set budget in UniCa$h.
 */
public class ClearBudgetCommand extends Command {

    public static final String COMMAND_WORD = CommandType.CLEAR_BUDGET.getCommandWords();
    public static final String MESSAGE_SUCCESS = CommandType.CLEAR_BUDGET.getMessageSuccess();
    public static final String MESSAGE_USAGE = CommandType.CLEAR_BUDGET.getMessageUsage();
    //alternative success case
    public static final String MESSAGE_NO_BUDGET = "No budget to clear."
            + "\n\nConsider using set_budget amt/Amount interval/Interval first!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        if (model.getBudget() == null) {
            return new CommandResult(MESSAGE_NO_BUDGET);
        }
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
