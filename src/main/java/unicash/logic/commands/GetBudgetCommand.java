package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.function.Function;

import unicash.commons.enums.BudgetInterval;
import unicash.commons.enums.TransactionType;
import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.logic.commands.exceptions.CommandException;
import unicash.model.Model;
import unicash.model.transaction.Transaction;

/**
 * Calculates the total expenditure used relative to the assigned budget for a given interval.
 *
 * <p>Calculation format = {@code Budget - Total Expenses + Total Income} where the total values
 * are accumulated over the given interval.</p>
 *
 * <p>If the interval is a day, the calculation only contains transactions within the same day,
 * the same for both week and month.</p>
 */
public class GetBudgetCommand extends Command {
    public static final String COMMAND_WORD = "get_budget";

    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription(
                    "Retrieves the budget and the usage over the given interval.\n"
                            + "If you are missing transactions, consider using list first."
            )
            .setExample(ExampleGenerator.generate(COMMAND_WORD))
            .build()
            .toString();

    public static final String MESSAGE_SUCCESS = "%s budget of %s\n\nNet amount of $%.2f\n\n";
    public static final String MESSAGE_NO_BUDGET =
            "No budget set. Use set_budget amt/Amount interval/Interval\n\n";

    /**
     * Time to calculate all interval offsets from. Provided to make testing easy.
     */
    private final LocalDateTime from;

    /**
     * Creates {@code GetBudgetCommand} from {@code LocalDateTime}.
     */
    public GetBudgetCommand(LocalDateTime from) {
        this.from = from;
    }

    /**
     * Defaults {@code from} to {@link LocalDateTime#now()} and
     * uses {@link #GetBudgetCommand(LocalDateTime)} to set {@code from}.
     */
    public GetBudgetCommand() {
        this(LocalDateTime.now());
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        var budget = model.getBudget();
        if (budget == null) {
            return new CommandResult(MESSAGE_NO_BUDGET);
        }

        var interval = budget.getInterval().interval;
        Function<LocalDateTime, Integer> filter = getIntervalFilter(interval);
        String intervalString = getIntervalString(interval);
        requireAllNonNull(filter, intervalString);

        var calculatedRemainder = model
                .getFilteredTransactionList()
                .stream()
                .filter(t -> filter.apply(t.getDateTime().getDateTime()).equals(filter.apply(from)))
                .map(this::getAmountByType)
                .reduce(budget.getAmount().amount, Double::sum, Double::sum);

        var roundedRemainder = (calculatedRemainder * 100.0) / 100.0;

        return new CommandResult(String.format(
                MESSAGE_SUCCESS,
                intervalString,
                budget.getAmount().toString(),
                roundedRemainder
        ));
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        // Note that from field is not used for computation as that is unnecessary
        return other instanceof GetBudgetCommand;
    }

    /**
     * Maps an transactions overall amount based on the type of transaction. Expenses cause a deficit,
     * incomes cause an increase.
     */
    private double getAmountByType(Transaction transaction) {
        int sign = 1;
        if (transaction.getType().type.equals(TransactionType.EXPENSE)) {
            sign = -1;
        }
        return sign * transaction.getAmount().amount;
    }

    /**
     * Maps a filter function to a given {@code BudgetInterval}. The filter function is used to compare
     * 2 {@code LocalDateTime} to determine if they are within the same interval.
     */
    private Function<LocalDateTime, Integer> getIntervalFilter(BudgetInterval interval) {
        switch (interval) {
        case DAY:
            return LocalDateTime::getDayOfYear;
        case WEEK:
            return d -> d.get(WeekFields.ISO.weekOfYear());
        case MONTH:
            return LocalDateTime::getMonthValue;
        default:
            return null;
        }
    }

    /**
     * Maps an interval string to a given {@code BudgetInterval}.
     */
    private String getIntervalString(BudgetInterval interval) {
        switch (interval) {
        case DAY:
            return "Daily";
        case WEEK:
            return "Weekly";
        case MONTH:
            return "Monthly";
        default:
            return null;
        }
    }
}
