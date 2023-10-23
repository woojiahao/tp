package unicash.model.transaction.predicates;

import unicash.commons.util.StringUtil;
import unicash.commons.util.ToStringBuilder;
import unicash.model.transaction.Transaction;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Transactions}'s {@code Amount} matches any of the keywords given.
 */
public class TransactionAmountContainsKeywordsPredicate
        implements Predicate<Transaction> {
    private final List<String> keywords;

    public TransactionAmountContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Transaction transaction) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsSubstringIgnoreCase(
                        transaction.getAmount().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionAmountContainsKeywordsPredicate)) {
            return false;
        }

        TransactionAmountContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (TransactionAmountContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
