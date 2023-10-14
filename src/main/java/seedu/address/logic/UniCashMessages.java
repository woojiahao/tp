package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.transaction.Transaction;

/**
 * Container for user visible messages.
 */
public class UniCashMessages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX = "The transaction index provided is invalid";
    public static final String MESSAGE_TRANSACTIONS_LISTED_OVERVIEW = "%1$d transactions listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code transaction} for display to the user.
     */
    public static String formatTransaction(Transaction transaction) {
        final StringBuilder builder = new StringBuilder();
        builder.append(transaction.getName())
                .append("; Type: ")
                .append(transaction.getType())
                .append("; Amount: ")
                .append(transaction.getAmount())
                .append("; Category: ")
                .append(transaction.getCategory())
                .append("; Date: ")
                .append(transaction.getDateTime())
                .append("; Location: ")
                .append(transaction.getLocation());
        return builder.toString();
    }
}
