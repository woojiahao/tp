package unicash.model.transaction;

import static unicash.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction's amount.
 */
public class Amount {

    public static final String MESSAGE_CONSTRAINTS =
            "Amounts must be positive.";

    public final double amount;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid amount.
     */
    public Amount(double amount) {
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    /**
     * Returns true if a given amount is a non-negative value.
     */
    public static boolean isValidAmount(double amount) {
        return amount >= 0.00;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(amount);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Amount)) {
            return false;
        }

        return amount == ((Amount) other).amount;
    }

    @Override
    public String toString() {
        return Double.toString(amount);
    }
}
