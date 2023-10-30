package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.model.Model;
import unicash.model.transaction.predicates.TransactionContainsAnyKeywordsPredicate;

/**
 * Finds and lists all transactions in UniCa$h whose name contains any of the argument keywords.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription(
                    "Finds any transactions whose name, amount, type, date-time, location, and categories "
                            + "contain any of the specified keywords (case-insensitive). At least one keyword "
                            + "must be specified. "
                            + "\n\n"
                            + "Any number of keywords can be specified and each keyword will be searched "
                            + "individually for all properties except date-time, amount, and type, for which "
                            + "the entire input will be searched as a single substring of these properties. "
            )
            .setArgument("Keyword [More keywords]...")
            .setExample(ExampleGenerator.generate(COMMAND_WORD, "chicken rice"))
            .build()
            .toString();

    private final TransactionContainsAnyKeywordsPredicate predicate;

    /**
     * Creates a {@code TransactionContainsAnyKeywordsPredicate} with a non-null
     * predicate.
     */
    public FindCommand(TransactionContainsAnyKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        assert predicate != null : "predicate cannot be null";
        model.updateFilteredTransactionList(predicate);
        return new CommandResult(
                String.format(UniCashMessages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                        model.getFilteredTransactionList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindCommand)) {
            return false;
        }

        FindCommand otherFindCommand = (FindCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
