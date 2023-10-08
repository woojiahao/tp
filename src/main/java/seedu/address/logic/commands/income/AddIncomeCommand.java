package seedu.address.logic.commands.income;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.income.Income;

/**
 * Adds an income to the income list.
 */
public class AddIncomeCommand extends Command {
    public static final String COMMAND_WORD = "add_income";

    public static final String MESSAGE_SUCCESS = "New income added: %1$s";
    public static final String MESSAGE_DUPLICATE_INCOME = "This person already exists in the address book";

    private final Income toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddIncomeCommand(Income income) {
        requireNonNull(income);
        toAdd = income;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasIncome(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_INCOME);
        }

        model.addIncome(toAdd);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
