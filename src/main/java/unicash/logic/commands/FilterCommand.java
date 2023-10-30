package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static unicash.logic.parser.CliSyntax.PREFIX_DATETIME;
import static unicash.logic.parser.CliSyntax.PREFIX_LOCATION;
import static unicash.logic.parser.CliSyntax.PREFIX_NAME;
import static unicash.logic.parser.CliSyntax.PREFIX_TYPE;

import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.commands.exceptions.CommandException;
import unicash.model.Model;
import unicash.model.transaction.predicates.TransactionContainsAllKeywordsPredicate;

/**
 * Filters the displayed Transactions according to certain parameters.
 */
public class FilterCommand extends Command {
    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription(
                    "Filters the transactions in UniCa$h "
                            + "according to the specified properties. "
                            + "Only transactions matching all specified properties. will be displayed. "
                            + "\n\nMultiple keywords for the same property is allowed, except for Transaction "
                            + "Type for which there can only be one keyword. A minimum of one keyword "
                            + "must be provided."
            )
            .addParameter(PREFIX_NAME, "Name", true, true)
            .addParameter(PREFIX_TYPE, "Type", true, false)
            .addParameter(PREFIX_AMOUNT, "Amount", true, true)
            .addParameter(PREFIX_DATETIME, "DateTime", true, true)
            .addParameter(PREFIX_LOCATION, "Location", true, true)
            .addParameter(PREFIX_CATEGORY, "Category", true, true)
            .setExample(
                    ExampleGenerator.generate(
                            COMMAND_WORD,
                            PREFIX_NAME,
                            PREFIX_TYPE,
                            PREFIX_AMOUNT,
                            PREFIX_DATETIME,
                            PREFIX_LOCATION,
                            PREFIX_CATEGORY
                    )
            )
            .build()
            .toString();

    public static final String MESSAGE_SUCCESS = "Filtered %1$s Transactions!";

    private final TransactionContainsAllKeywordsPredicate predicate;

    /**
     * Creates an FilterCommand to filter the transactions list according to
     * the given predicate.
     */
    public FilterCommand(TransactionContainsAllKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        assert predicate != null : "predicate cannot be null";
        model.updateFilteredTransactionList(predicate);
        int listSize = model.getFilteredTransactionList().size();
        return new CommandResult(String.format(
                UniCashMessages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, listSize));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand otherCommand = (FilterCommand) other;
        return predicate.equals(otherCommand.predicate);

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

