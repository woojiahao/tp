package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Transaction in the finance tracker.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {
    private final Name name;
    private final Amount amount;
    private final Category category;
    private final LocalDateTime dateTime;
    private final Location location;

    /**
     * Every field must be present and not null.
     */
    public Transaction(Name name, Amount amount, Category category, LocalDateTime dateTime, Location location) {
        requireAllNonNull(name, amount, category, dateTime, location);
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.dateTime = dateTime;
        this.location = location;
    }

    public Name getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, category, dateTime, location);
    }

    /**
     * Returns true if both transactions have the same data fields.
     * This defines a stronger notion of equality between two expenses.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Transaction)) {
            return false;
        }

        Transaction otherTransaction = (Transaction) other;
        return name.equals(otherTransaction.name)
                && amount == otherTransaction.amount
                && category.equals(otherTransaction.category)
                && dateTime.equals(otherTransaction.dateTime)
                && location.equals(otherTransaction.location);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("amount", amount)
                .add("category", category)
                .add("dateTime", dateTime)
                .add("location", location)
                .toString();
    }
}
