package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.time.Month;

import seedu.address.commons.enums.TransactionType;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.UniCashMessages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Category;

/**
 * Calculates and returns the total expenditure of a user in a given month and (optionally) category.
 */
public class GetTotalExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "get_total_expenditure";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the total expenditure by month with optional filters for category.\n"
            + "Parameters: MONTH (must be valid month index) "
            + PREFIX_CATEGORY + "CATEGORY\n";

    public static final String MESSAGE_SUCCESS = "Your total expenditure in %1$s was %2$.2f";

    // TODO: Allow users to specify the year as well
    private final int month;
    private final Category categoryFilter;

    /**
     * Creates GetTotalExpenditureCommand.
     */
    public GetTotalExpenditureCommand(int month, Category categoryFilter) {
        this.month = month;
        this.categoryFilter = categoryFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (month < 1 || month > 12) {
            throw new CommandException(UniCashMessages.MESSAGE_INVALID_MONTH);
        }

        model.updateFilteredTransactionList(transaction -> {
            boolean isExpense = transaction.getType().type.equals(TransactionType.EXPENSE);
            boolean isSameMonth = transaction.getDateTime().getDateTime().getMonthValue() == month;
            if (categoryFilter == null || categoryFilter.isEmpty()) {
                return isExpense && isSameMonth;
            }
            return isExpense && isSameMonth && transaction.getCategory().equals(categoryFilter);
        });

        var filteredList = model.getFilteredTransactionList();
        double totalExpenditure = filteredList
                .stream()
                .reduce(0.0, (acc, cur) -> acc + cur.getAmount().amount, Double::sum);

        // TODO: Capitalize maybe?
        String monthString = Month.of(month).name();

        return new CommandResult(String.format(MESSAGE_SUCCESS, monthString, totalExpenditure));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GetTotalExpenditureCommand)) {
            return false;
        }

        GetTotalExpenditureCommand otherCommand = (GetTotalExpenditureCommand) other;
        return month == otherCommand.month && categoryFilter.equals(otherCommand.categoryFilter);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("month", month)
                .add("categoryFilter", categoryFilter)
                .toString();
    }
}
