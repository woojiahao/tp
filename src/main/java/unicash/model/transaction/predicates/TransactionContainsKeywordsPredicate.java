package unicash.model.transaction.predicates;

import java.util.List;
import java.util.function.Predicate;

import unicash.commons.util.ToStringBuilder;
import unicash.model.transaction.Transaction;

/**
 * Tests that any of a {@code Transactions}'s properties matches any of the keywords given.
 */
public class TransactionContainsKeywordsPredicate
        implements Predicate<Transaction> {
    private final List<String> keywords;

    public TransactionContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Transaction transaction) {
        List<Predicate<Transaction>> allPredicates = List.of(
                new TransactionAmountContainsKeywordsPredicate(keywords),
                new TransactionCategoryContainsKeywordsPredicate(keywords),
                new TransactionDateTimeContainsKeywordsPredicate(keywords),
                new TransactionLocationContainsKeywordsPredicate(keywords),
                new TransactionNameContainsKeywordsPredicate(keywords),
                new TransactionTypeContainsKeywordsPredicate(keywords)
        );

        return allPredicates.stream()
                .anyMatch(predicate -> predicate.test(transaction));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionContainsKeywordsPredicate)) {
            return false;
        }

        TransactionContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (TransactionContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
