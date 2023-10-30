package unicash.storage;

import static java.util.Objects.requireNonNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import unicash.commons.exceptions.IllegalValueException;
import unicash.model.budget.Budget;
import unicash.model.budget.Interval;
import unicash.model.commons.Amount;

/**
 * {@code Budget} adapted to be exported to JSON.
 */
public class JsonAdaptedBudget {
    public static final String MISSING_FIELD_FORMAT = "Budget's %s field is missing!";

    private final double amount;
    private final String interval;

    /**
     * Creates budget.
     */
    @JsonCreator
    public JsonAdaptedBudget(
            @JsonProperty("amount") double amount,
            @JsonProperty("interval") String interval
    ) {
        this.amount = amount;
        this.interval = interval;
    }

    /**
     * Creates budget based on another {@code budget}.
     *
     * @throws NullPointerException if {@code budget is null}.
     */
    public JsonAdaptedBudget(Budget budget) {
        requireNonNull(budget);
        amount = budget.getAmount().amount;
        interval = budget.getInterval().interval.getOriginalString();
    }

    /**
     * Converts {@code JsonAdaptedBudget} to {@code Budget} while performing validation.
     *
     * @throws IllegalValueException if there were any data constraint violations.
     */
    public Budget toModelType() throws IllegalValueException {
        if (!Amount.isValidAmount(amount)) {
            throw new IllegalValueException(Amount.MESSAGE_CONSTRAINTS);
        }
        final Amount modelAmount = new Amount(amount);

        if (interval == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_FORMAT, Interval.class.getSimpleName()));
        }
        if (!Interval.isValidInterval(interval)) {
            throw new IllegalValueException(Interval.MESSAGE_CONSTRAINTS);
        }
        final Interval modelInterval = new Interval(interval);

        return new Budget(modelAmount, modelInterval);
    }
}
