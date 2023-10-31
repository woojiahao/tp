package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import unicash.commons.enums.CommandType;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.commands.exceptions.CommandException;
import unicash.model.Model;
import unicash.model.budget.Budget;

/**
 * Adds a budget to UniCash.
 */
public class AddBudgetCommand extends Command {
    public static final String COMMAND_WORD = CommandType.ADD_BUDGET.getCommandWords();

    public static final String MESSAGE_USAGE = CommandType.ADD_BUDGET.getMessageUsage();

    public static final String MESSAGE_SUCCESS = CommandType.ADD_BUDGET.getMessageSuccess();

    private final Budget budget;

    /**
     * Creates an AddBudgetCommand to add the specified {@code Budget}
     */
    public AddBudgetCommand(Budget budget) {
        requireNonNull(budget);
        this.budget = budget;
    }
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setBudget(budget);
        return new CommandResult(String.format(MESSAGE_SUCCESS, UniCashMessages.formatBudget(budget)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddBudgetCommand)) {
            return false;
        }

        AddBudgetCommand otherCommand = (AddBudgetCommand) other;
        return budget.equals(otherCommand.budget);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("budget", budget)
                .toString();
    }
}
