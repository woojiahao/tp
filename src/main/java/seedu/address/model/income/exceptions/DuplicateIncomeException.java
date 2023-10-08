package seedu.address.model.income.exceptions;

/**
 * Signals that the operation will result in duplicate Incomes (Incomes are considered duplicates if they have the same
 * data fields).
 */
public class DuplicateIncomeException extends RuntimeException {
    public DuplicateIncomeException() {
        super("Operation would result in duplicate incomes");
    }
}
