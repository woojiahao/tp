package unicash.model.transaction.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import unicash.model.transaction.Transaction;
import unicash.testutil.TransactionBuilder;


public class TransactionContainsAllKeywordsPredicateTest {

    private static TransactionContainsAllKeywordsPredicate predicate =
            new TransactionContainsAllKeywordsPredicate();

    @BeforeEach
    public void resetPredicate() {
        predicate = new TransactionContainsAllKeywordsPredicate();
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TransactionContainsAllKeywordsPredicate firstPredicate =
                new TransactionContainsAllKeywordsPredicate();
        firstPredicate.addNameKeyword(firstPredicateKeywordList.get(0));

        TransactionContainsAllKeywordsPredicate secondPredicate =
                new TransactionContainsAllKeywordsPredicate();

        secondPredicateKeywordList.stream().forEach(keyword -> {
            secondPredicate.addNameKeyword(keyword);
        });

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TransactionContainsAllKeywordsPredicate firstPredicateCopy =
                new TransactionContainsAllKeywordsPredicate();
        firstPredicateCopy.addNameKeyword(firstPredicateKeywordList.get(0));
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        assertFalse(firstPredicate.equals(1));

    }

    @Test
    public void test_transactionContainsKeywords_returnsTrue() {

        // One keyword
        resetPredicate();
        predicate.addNameKeyword("food");
        assertTrue(predicate.test(new TransactionBuilder().withName("Food at mcdonalds").build()));

        // Multiple keywords
        resetPredicate();
        predicate.addNameKeyword("food");
        predicate.addNameKeyword("mcdonalds");
        assertTrue(predicate.test(new TransactionBuilder().withName("Food mcdonalds").build()));

        // Only one matching keyword
        resetPredicate();
        predicate.addNameKeyword("Rice");
        predicate.addNameKeyword("food");
        assertFalse(predicate.test(new TransactionBuilder().withName("Chicken Rice").build()));

        // Mixed-case keywords
        resetPredicate();
        predicate.addNameKeyword("FoOd");
        predicate.addNameKeyword("mCdOnAlDs");
        assertTrue(predicate.test(new TransactionBuilder().withName("Food mcdonalds").build()));
    }

    @Test
    public void test_transactionDoesNotContainKeywords_returnsFalse() {

        // Zero keywords
        resetPredicate();
        predicate.addNameKeyword("chicken");
        assertFalse(predicate.test(new TransactionBuilder().withName("test").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        resetPredicate();
        keywords.stream().forEach(keyword -> {
            predicate.addNameKeyword(keyword);
        });

        List<Predicate<Transaction>> predicateList = new ArrayList<>();
        predicateList.add(new TransactionNameContainsKeywordsPredicate(List.of("keyword1")));
        predicateList.add(new TransactionNameContainsKeywordsPredicate(List.of("keyword2")));

        String expected = TransactionContainsAllKeywordsPredicate
                .class.getCanonicalName() + "{predicateList=" + predicateList.toString() + "}";
        assertEquals(expected, predicate.toString());
    }
}
