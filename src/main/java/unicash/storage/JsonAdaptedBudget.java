package unicash.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import unicash.commons.exceptions.IllegalValueException;
import unicash.model.budget.Budget;
import unicash.model.budget.Interval;
import unicash.model.commons.Amount;

public class JsonAdaptedBudget {
    public static final String MISSING_FIELD_FORMAT = "Budget's %s field is missing!";

    private final double amount;
    private final String interval;

    @JsonCreator
    public JsonAdaptedBudget(
            @JsonProperty("amount") double amount,
            @JsonProperty("interval") String interval
    ) {
        this.amount = amount;
        this.interval = interval;
    }

    public JsonAdaptedBudget(Budget budget) {
        amount = budget.getAmount().amount;
        interval = budget.getInterval().interval.getOriginalString();
    }

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
