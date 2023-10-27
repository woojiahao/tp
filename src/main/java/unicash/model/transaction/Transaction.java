package unicash.model.transaction;

import static unicash.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import unicash.commons.util.ToStringBuilder;
import unicash.model.category.UniqueCategoryList;
import unicash.model.commons.Amount;

/**
 * Represents a Transaction in UniCash.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Transaction {
    private final Name name;
    private final Amount amount;
    private final DateTime dateTime;
    private final Location location;
    private final Type type;
    private final UniqueCategoryList categories = new UniqueCategoryList();


    /**
     * Constructs a Transaction with all fields populated.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Transaction(
        Name name,
        Type type,
        Amount amount,
        DateTime dateTime,
        Location location,
        UniqueCategoryList categories
    ) {
        requireAllNonNull(name, type, amount, categories, dateTime, location);
        this.name = name;
        this.type = type;
        this.amount = amount;
        this.dateTime = dateTime;
        this.location = location;
        this.categories.setCategories(categories);
    }

    public Name getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public Double getAmountAsDouble() {
        return amount.amount;
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

    /**
     * Returns the string value associated with this Type
     *
     * @return string value of type
     */
    public String getTypeString() {
        return type.type.getOriginalString();
    }

    public UniqueCategoryList getCategories() {
        return categories;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, dateTime, location, type, categories);
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
        boolean x1 = name.equals(otherTransaction.name);
        boolean x2 =  type.equals(otherTransaction.type);
        boolean x3 = amount.equals(otherTransaction.amount);
        boolean x4 = categories.equals(otherTransaction.categories);
        boolean x5 = dateTime.equals(otherTransaction.dateTime);
        boolean x6 = location.equals(otherTransaction.location);
        System.out.println("*" + dateTime.getDateTime());
        System.out.println("**" + otherTransaction.getDateTime().getDateTime());
        return x1 && x2 && x3 && x4 && x5 && x6;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("type", type)
                .add("amount", amount)
                .add("dateTime", dateTime)
                .add("location", location)
                .add("categories", categories)
                .toString();
    }
}
