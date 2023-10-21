package unicash.model.commons;

import static unicash.commons.util.AppUtil.checkArgument;

/**
 * Represents a Transaction's amount.
 */
public class Amount {

    public static final String MESSAGE_SIGN_WARNING =
            "Amounts must be positive.";

    public static final String MESSAGE_RANGE_LIMITS =
            "Amounts must be between -2^31 and 2^31.";

    public static final String MESSAGE_PRECISION_WARNING =
            "Amounts must not have more than 2 decimal places!";

    public static final String MESSAGE_CONSTRAINTS = MESSAGE_SIGN_WARNING
            + MESSAGE_RANGE_LIMITS
            + MESSAGE_PRECISION_WARNING;

    public static final String CURRENCY_INDICATOR = "$";

    public final double amount;

    /**
     * Constructs a {@code Amount}.
     *
     * @param amount A valid amount.
     */
    public Amount(double amount) {
        checkArgument(isValidAmount(amount), MESSAGE_SIGN_WARNING);
        this.amount = Math.round(amount * 100.0) / 100.0;
    }

    /**
     * Returns true if a given amount is a non-negative value.
     */
    public static boolean isValidAmount(double amount) {
        return amount >= 0.00;
    }

    /**
     * Returns true if a given amount is a non-negative value.
     */
    public static boolean isPositiveAmount(double amount) {
        return amount >= 0.00;
    }

    /**
     * Returns true if a given amount is a non-negative value.
     */
    public static boolean isWithinRange(double amount) {
        return (amount > Integer.MIN_VALUE) && (amount < Integer.MAX_VALUE);
    }

    /**
     * Returns true if a given amount has less than or equal to two decimal places.
     */
    public static boolean hasNoMoreThanTwoDecimalPlaces(double amount) {
        String stringValue = Double.toString(amount);
        int decimalIndex = stringValue.indexOf(".");

        // If there's no decimal point, then it has no decimal places
        if (decimalIndex == -1) {
            return false;
        }
        int decimalCount = stringValue.length() - decimalIndex - 1;

        return decimalCount > 2;
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


    public String originalString() {
        return Double.toString(amount);
    }

    @Override
    public String toString() {
        return CURRENCY_INDICATOR + String.format("%.2f", this.amount);
    }
}
