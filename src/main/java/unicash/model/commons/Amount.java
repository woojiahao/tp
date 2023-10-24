package unicash.model.commons;

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

    /**
     * Returns a rounded two-decimal precision String version of an {@code Amount}.
     */
    public static String amountToDecimalString(Amount amt) {
        double roundedAmount = Math.round(amt.amount * 100.0) / 100.0;
        String formattedNumberString = String.format("%.2f", roundedAmount);

        return formattedNumberString;
    }

    @Override
    public String toString() {
        return Double.toString(amount);
    }
}
