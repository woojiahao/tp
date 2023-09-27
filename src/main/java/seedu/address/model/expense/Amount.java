package seedu.address.model.expense;

import static seedu.address.commons.util.AppUtil.checkArgument;

public class Amount {

    public static final String MESSAGE_CONSTRAINTS =
            "Amounts must be positive.";

    public final double amount;

    public Amount(double amount) {
        checkArgument(isValidAmount(amount), MESSAGE_CONSTRAINTS);
        this.amount = amount;
    }

    public static boolean isValidAmount(double amount) {
        return amount > 0.00;
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
