package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.income.AddIncomeCommand;
import seedu.address.model.income.Income;

/**
 * A utility class for Income.
 */
public class IncomeUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddIncomeCommand(Income income) {
        return AddIncomeCommand.COMMAND_WORD + " " + getPersonDetails(income);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Income income) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + income.getName().fullName + " ");
        sb.append(PREFIX_AMOUNT + income.getAmount().toString() + " ");
        sb.append(PREFIX_DATETIME + income.getDateTime().originalString() + " ");

        return sb.toString();
    }
}
