package seedu.address.logic.commands.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;

import java.time.Month;

import seedu.address.commons.enums.TransactionType;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Category;

public class GetTotalExpenditureCommand extends Command {
    public static final String COMMAND_WORD = "get_total_expenditure";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the total expenditure by month with optional filters for category.\n"
            + "Parameters: MONTH (must be valid month index) "
            + PREFIX_CATEGORY + "CATEGORY\n";

    public static final String MESSAGE_SUCCESS = "Your total expenditure in %1$s was %2$.2f";
    public static final String MESSAGE_INVALID_MONTH = "Invalid month provided, value must be from 1 to 12.";

    private final int month;
    private final Category categoryFilter;

    public GetTotalExpenditureCommand(int month, Category categoryFilter) {
        this.month = month;
        this.categoryFilter = categoryFilter;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (month < 1 || month > 12) {
            throw new CommandException(Messages.MESSAGE_INVALID_MONTH);
        }

        model.updateFilteredTransactionList(transaction -> {
            boolean isExpense = transaction.getType().type.equals(TransactionType.EXPENSE);
            boolean isSameMonth = transaction.getDateTime().dateTime.getMonthValue() == month;
            if (categoryFilter == null) {
                return isExpense && isSameMonth;
            }
            return isExpense && isSameMonth && transaction.getCategory().equals(categoryFilter);
        });

        var filteredList = model.getFilteredTransactionList();
        double totalExpenditure = filteredList
                .stream()
                .reduce(0.0, (acc, cur) -> acc + cur.getAmount().amount, Double::sum);

        String monthString = Month.of(month).name();

        return new CommandResult(String.format(MESSAGE_SUCCESS, monthString, totalExpenditure));
    }
}
