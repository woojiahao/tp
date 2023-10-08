package seedu.address.model.income;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents an Income.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Income {
    private final Name name;
    private final Amount amount;
    private final DateTime datetime;

    /**
     * Every field must be present and not null.
     */
    public Income(Name name, Amount amount, DateTime datetime) {
        requireAllNonNull(name, amount, datetime);
        this.name = name;
        this.amount = amount;
        this.datetime = datetime;
    }

    public Name getName() {
        return name;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public DateTime getDateTime() {
        return this.datetime;
    }

    /**
     * Returns true if both income have the same data fields.
     * This defines a stronger notion of equality between two incomes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Income)) {
            return false;
        }

        Income otherIncome = (Income) other;
        return name.equals(otherIncome.name)
                && amount.equals(otherIncome.amount)
                && datetime.equals(otherIncome.datetime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, amount, datetime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("amount", amount)
                .add("datetime", datetime)
                .toString();
    }
}
