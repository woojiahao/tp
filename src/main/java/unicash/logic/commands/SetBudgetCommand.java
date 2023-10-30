package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static unicash.logic.parser.CliSyntax.PREFIX_MONTH;

import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.commands.exceptions.CommandException;
import unicash.model.Model;
import unicash.model.budget.Budget;

/**
 * Sets budget for UniCa$h.
 */
public class SetBudgetCommand extends Command {
    public static final String COMMAND_WORD = "set_budget";

    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription("Sets the user's budget on UniCa$h.")
            .addParameter(PREFIX_AMOUNT, "Amount")
            .addParameter(PREFIX_INTERVAL, "Interval")
            .setExample(ExampleGenerator.generate(COMMAND_WORD, PREFIX_MONTH, PREFIX_INTERVAL))
            .build()
            .toString();

    public static final String MESSAGE_SUCCESS = "New budget added: \n\n%1$s";

    private final Budget budget;

    /**
     * Creates an SetBudgetCommand.
     */
    public SetBudgetCommand(Budget budget) {
        requireNonNull(budget);
        this.budget = budget;
    }

    /**
     * Sets the user's budget.
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

        if (!(other instanceof SetBudgetCommand)) {
            return false;
        }

        SetBudgetCommand otherCommand = (SetBudgetCommand) other;
        return budget.equals(otherCommand.budget);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("budget", budget)
                .toString();
    }
}
