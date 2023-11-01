package unicash.model.transaction.predicates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import unicash.commons.util.ToStringBuilder;
import unicash.model.transaction.Transaction;


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

    public TransactionContainsAllKeywordsPredicate(List<Predicate<Transaction>> predicateList) {
        this.predicateList = predicateList;
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
     * Creates a new {@code TransactionNameContainsKeywordsPredicate} with the
     * given name as a string and adds this to the encapsulated list of
     * transaction predicates.
     *
     * @param name the name to be added as a transaction predicate keyword
     */
    public void addNameKeyword(String name) {
        TransactionNameContainsKeywordsPredicate namePredicate =
                new TransactionNameContainsKeywordsPredicate(toKeywordList(name));

        predicateList.add(namePredicate);

    }

    /**
     * Creates a new {@code TransactionLocationContainsKeywordsPredicate} with the
     * given location as a string and adds this to the encapsulated list of
     * transaction predicates.
     *
     * @param location the location to be added as a transaction predicate keyword
     */
    public void addLocationKeyword(String location) {
        TransactionLocationContainsKeywordsPredicate locationPredicate =
                new TransactionLocationContainsKeywordsPredicate(toKeywordList(location));

        predicateList.add(locationPredicate);

    }


    /**
     * Creates a new {@code TransactionCategoryContainsValuePredicate} with the
     * given {@code Category} as a string and adds this to the encapsulated list of
     * transaction predicates.
     *
     * @param category the {@code Category} to be added as a transaction predicate keyword
     */
    public void addCategoryKeyword(String category) {
        TransactionCategoryContainsKeywordsPredicate categoryPredicate =
                new TransactionCategoryContainsKeywordsPredicate(toKeywordList(category));

        predicateList.add(categoryPredicate);

    }


    /**
     * A helper method that returns the input string keyword as a list
     * with a single item.
     *
     * @param keyword the input keyword String
     * @return the keyword as a single List.
     */
    private static List<String> toKeywordList(String keyword) {
        return Collections.singletonList(keyword.toLowerCase());
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
