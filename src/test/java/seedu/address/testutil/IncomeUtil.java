package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.transaction.AddTransactionCommand;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class for Income.
 */
public class IncomeUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddIncomeCommand(Transaction transaction) {
        return AddTransactionCommand.COMMAND_WORD + " " + getTransactionDetails(transaction);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTransactionDetails(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + transaction.getName().fullName + " ");
        sb.append(PREFIX_AMOUNT + transaction.getAmount().toString() + " ");
        sb.append(PREFIX_DATETIME + transaction.getDateTime().originalString() + " ");

        return sb.toString();
    }
}
