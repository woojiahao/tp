package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.income.Amount;
import seedu.address.model.income.DateTime;
import seedu.address.model.income.Income;
import seedu.address.model.income.Name;

/**
 * A utility class to help with building Income objects.
 */
public class IncomeBuilder {

    public static final String DEFAULT_NAME = "Work at liho";
    public static final Double DEFAULT_AMOUNT = 3.0;
    public static final DateTime DEFAULT_DATE = new DateTime(LocalDateTime.now());

    private Name name;
    private Amount amount;
    private DateTime date;

    /**
     * Creates a {@code IncomeBuilder} with the default details.
     */
    public IncomeBuilder() {
        name = new Name(DEFAULT_NAME);
        amount = new Amount(DEFAULT_AMOUNT);
        date = DEFAULT_DATE;
    }

    /**
     * Initializes the IncomeBuilder with the data of {@code incomeToCopy}.
     */
    public IncomeBuilder(Income incomeToCopy) {
        name = incomeToCopy.getName();
        amount = incomeToCopy.getAmount();
        date = incomeToCopy.getDateTime();
    }

    /**
     * Sets the {@code Name} of the {@code Income} that we are building.
     */
    public IncomeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Amount} of the {@code Income} that we are building.
     */
    public IncomeBuilder withAmount(Double amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code LocalDate} of the {@code Income} that we are building.
     */
    public IncomeBuilder withDate(LocalDateTime date) {
        this.date = new DateTime(date);
        return this;
    }


    public Income build() {
        return new Income(name, amount, date);
    }

}
