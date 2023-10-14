package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

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
    private final DateTime dateTime;
    private final Location location;
    private final Type type;


    /**
     * Constructs a Transaction with all fields populated.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Transaction(Name name, Type type, Amount amount, Category category, DateTime dateTime,
                       Location location) {
        requireAllNonNull(name, type, amount, category, dateTime, location);
        this.name = name;
        this.type = type;
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

    public DateTime getDateTime() {
        return dateTime;
    }

    public Location getLocation() {
        return location;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, category, dateTime, location, type);
    }

    /**
     * Returns true if both transactions have the same name and dateTime.
     * This defines a weaker notion of equality between two transactions.
     */
    public boolean isSameTransaction(Transaction otherTransaction) {
        if (otherTransaction == this) {
            return true;
        }

        return otherTransaction != null
                && otherTransaction.getName().equals(getName())
                && otherTransaction.getDateTime().equals(getDateTime());
    }

    /**
     * Returns true if both transactions have the same data fields.
     * This defines a stronger notion of equality between two transactions.
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
                && type.equals(otherTransaction.type)
                && amount.equals(otherTransaction.amount)
                && category.equals(otherTransaction.category)
                && dateTime.equals(otherTransaction.dateTime)
                && location.equals(otherTransaction.location);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("type", type)
                .add("amount", amount)
                .add("category", category)
                .add("dateTime", dateTime)
                .add("location", location)
                .toString();
    }
}
