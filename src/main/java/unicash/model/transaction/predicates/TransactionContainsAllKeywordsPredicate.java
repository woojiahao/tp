package unicash.model.transaction.predicates;

import unicash.commons.util.ToStringBuilder;
import unicash.model.transaction.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that the {@code Transactions}'s properties matches all the keywords given.
 * Matching in this context either means a partial match of the keyword with the query, or
 * a full match of the keyword, repeated for each keyword present in the list of keywords.
 *
 * </p> The matching depends on the property of the Transaction being matched, and the
 * most appropriate matching is specified within the associated property predicate. This class
 * simulates a composed predicate that represents a short-circuiting logical AND of all property
 * predicates.
 *
 * </p> Encapsulated within is a list of transaction predicates, and this list can be accessed
 * publicly, and modified. The test method returns true only if the input Transaction matches
 * all predicates in this list.
 */
public class TransactionContainsAllKeywordsPredicate implements Predicate<Transaction> {

    private List<Predicate<Transaction>> predicateList;

    public TransactionContainsAllKeywordsPredicate() {
        this.predicateList = new ArrayList<>();
    }

    @Override
    public boolean test(Transaction transaction) {
        if (predicateList.isEmpty()) {
            return false;
        }

        return predicateList.stream()
                .allMatch(predicate -> predicate.test(transaction));
    }
    

    /**
     * Creates a new {@code TransactionAmountContainsValuePredicate} with the
     * given input amount as a string and adds this to the encapsulated list of
     * transaction predicates.
     *
     * @param amount the input amount to be added as a transaction predicate
     */
    public void addAmountKeyword(String amount) {
        TransactionAmountContainsValuePredicate amountPredicate =
                new TransactionAmountContainsValuePredicate(toKeywordList(amount));

        predicateList.add(amountPredicate);
    }

    /**
     * Creates a new {@code TransactionAmountContainsValuePredicate} with the
     * given input amount as a string and adds this to the encapsulated list of
     * transaction predicates.
     *
     * @param name the input amount to be added as a transaction predicate
     */
    public void addNameKeyword(String name) {
        TransactionNameContainsKeywordsPredicate namePredicate =
                new TransactionNameContainsKeywordsPredicate(toKeywordList(name));

        predicateList.add(namePredicate);

    }



    public void setLocationKeyword(String location) {
        BooleanPredicatePair predicatePair = new BooleanPredicatePair(true,
                new TransactionLocationContainsKeywordsPredicate(
                        Collections.singletonList(location)));

        predicatePairMap.put(TransactionProperty.LOCATION, predicatePair);
    }

    public void setDateTimeKeyword(String dateTime) {
        BooleanPredicatePair predicatePair = new BooleanPredicatePair(true,
                new TransactionDateTimeContainsValuePredicate(
                        Collections.singletonList(dateTime)));

        predicatePairMap.put(TransactionProperty.NAME, predicatePair);
    }

    public void setTypeKeyword(String type) {
        BooleanPredicatePair predicatePair = new BooleanPredicatePair(true,
                new TransactionTypeContainsValuePredicate(
                        Collections.singletonList(type)));

        predicatePairMap.put(TransactionProperty.TYPE, predicatePair);
    }

    public void setCategoryKeyword(String category) {
        BooleanPredicatePair predicatePair = new BooleanPredicatePair(true,
                new TransactionTypeContainsValuePredicate(
                        Collections.singletonList(category)));

        predicatePairMap.put(TransactionProperty.CATEGORY, predicatePair);
    }


    /**
     * A helper method that returns the input string keyword as a list
     * with a single item.
     *
     * @param keyword the input keyword String
     * @return the keyword as a single List.
     */
    private static List<String> toKeywordList(String keyword) {
        return Collections.singletonList(keyword);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TransactionContainsAllKeywordsPredicate)) {
            return false;
        }

        TransactionContainsAllKeywordsPredicate otherContainsKeywordsPredicate =
                (TransactionContainsAllKeywordsPredicate) other;
        return predicateList.equals(otherContainsKeywordsPredicate.predicateList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicateList",
                        predicateList).toString();
    }
}
