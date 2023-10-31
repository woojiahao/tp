package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.Month;

import unicash.commons.enums.CommandType;
import unicash.commons.enums.TransactionType;
import unicash.commons.util.StringUtil;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.commands.exceptions.CommandException;
import unicash.model.Model;
import unicash.model.category.Category;

/**
 * Calculates and returns the total expenditure of a user in a given month and (optionally) category and year.
 */
public class GetTotalExpenditureCommand extends Command {
    public static final String COMMAND_WORD = CommandType.GET_TOTAL_EXPENDITURE.getCommandWords();

    public static final String MESSAGE_USAGE = CommandType.GET_TOTAL_EXPENDITURE.getMessageUsage();

    public static final String MESSAGE_SUCCESS = "Your total expenditure in %1$s %2$d was %3$.2f";

    private final int month;
    private final int year;
    private final Category categoryFilter;

    /**
     * Creates GetTotalExpenditureCommand.
     */
    public GetTotalExpenditureCommand(int month, int year, Category categoryFilter) {
        this.year = year;
        this.month = month;
        this.categoryFilter = categoryFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (month < 1 || month > 12) {
            throw new CommandException(UniCashMessages.MESSAGE_INVALID_MONTH);
        }

        if (year < 1920) {
            throw new CommandException(UniCashMessages.MESSAGE_INVALID_YEAR);
        }

        model.updateFilteredTransactionList(transaction -> {
            boolean isExpense = transaction.getType().type.equals(TransactionType.EXPENSE);

            var dateTime = transaction.getDateTime().getDateTime();
            boolean isSameMonth = dateTime.getMonthValue() == month;
            boolean isSameYear = dateTime.getYear() == year;
            boolean isSameDateFields = isSameMonth && isSameYear;

            if (categoryFilter == null) {
                // No category filter so just get all expenses of the month
                return isExpense && isSameDateFields;
            }

            // If category filter exists and expense contains no category, it will not have the category
            // Note: If the stream is empty then false is returned and the predicate is not evaluated.
            // Case insensitivity is handled by the creation of Category objects
            boolean hasCategory = transaction
                    .getCategories()
                    .asUnmodifiableObservableList()
                    .stream()
                    .anyMatch(cat -> cat.equals(categoryFilter));

            return isExpense && isSameDateFields && hasCategory;
        });

        var filteredList = model.getFilteredTransactionList();
        double totalExpenditure = filteredList
                .stream()
                .reduce(0.0, (acc, cur) -> acc + cur.getAmount().amount, Double::sum);

        String monthString = StringUtil.capitalizeString(Month.of(month).name());

        return new CommandResult(String.format(MESSAGE_SUCCESS, monthString, year, totalExpenditure));
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
        if (categoryFilter == null) {
            return month == otherCommand.month
                    && otherCommand.categoryFilter == null
                    && year == otherCommand.year;
        }
        return month == otherCommand.month
                && categoryFilter.equals(otherCommand.categoryFilter)
                && year == otherCommand.year;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("month", month)
                .add("year", year)
                .add("categoryFilter", categoryFilter)
                .toString();
    }
}
