package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static unicash.logic.parser.CliSyntax.PREFIX_DATETIME;
import static unicash.logic.parser.CliSyntax.PREFIX_LOCATION;
import static unicash.logic.parser.CliSyntax.PREFIX_NAME;
import static unicash.logic.parser.CliSyntax.PREFIX_TYPE;


import java.time.Month;

import unicash.commons.enums.TransactionType;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.commands.exceptions.CommandException;
import unicash.logic.parser.CliSyntax;
import unicash.model.Model;
import unicash.model.category.Category;

/**
 * Handles filtering of transaction according to specified filter specifiers.
 */
public class FilterTransactionsCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Filters transactions according to specified transaction property filters.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_TYPE + "TYPE "
            + PREFIX_AMOUNT + "AMOUNT "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_LOCATION + "LOCATION "
            + "[" + PREFIX_CATEGORY + "CATEGORY]...\n"
            + "\n";


    public static final String MESSAGE_SUCCESS = "Filtered transactions displayed.";

    // TODO: Allow users to specify the year as well
    private final int month;
    private final Category categoryFilter;

    /**
     * Creates GetTotalExpenditureCommand.
     */
    public FilterTransactionsCommand(int month, Category categoryFilter) {
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
            if (categoryFilter == null) {
                // No category filter so just get all expenses of the month
                return isExpense && isSameMonth;
            }

            // If category filter exists and expense contains no category, it will not have the category
            // Note: If the stream is empty then false is returned and the predicate is not evaluated.
            boolean hasCategory = transaction.getCategories().asUnmodifiableObservableList()
                    .stream().anyMatch(cat -> cat.equals(categoryFilter));
            return isExpense && isSameMonth && hasCategory;
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

        FilterTransactionsCommand otherCommand = (FilterTransactionsCommand) other;
        if (categoryFilter == null) {
            return month == otherCommand.month && otherCommand.categoryFilter == null;
        }
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
