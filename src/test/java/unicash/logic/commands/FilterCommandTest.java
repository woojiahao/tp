package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static unicash.logic.UniCashMessages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.testutil.Assert.assertThrows;
import static unicash.testutil.TypicalTransactions.INTERN;
import static unicash.testutil.TypicalTransactions.NUS;
import static unicash.testutil.TypicalTransactions.getTypicalUniCash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.predicates.TransactionContainsAllKeywordsPredicate;
import unicash.model.transaction.predicates.TransactionNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private final Model model = new ModelManager(new UniCash(), new UserPrefs());
    private final Model modelWithTransactions = new ModelManager(getTypicalUniCash(), new UserPrefs());
    private final Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());

    @Test
    public void equals() {
        TransactionContainsAllKeywordsPredicate firstPredicate =
                new TransactionContainsAllKeywordsPredicate();
        TransactionContainsAllKeywordsPredicate secondPredicate =
                new TransactionContainsAllKeywordsPredicate();

        FilterCommand findFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand findSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // same predicate -> returns true
        assertEquals(findFirstCommand, findSecondCommand);

        assertFalse(findFirstCommand.equals(3));
    }

    @Test
    public void execute_zeroKeywords_noTransactionsFound() {
        String expectedMessage = String.format(MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, 0);
        TransactionContainsAllKeywordsPredicate predicate = preparePredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredTransactionList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTransactionList());
    }

    @Test
    public void execute_oneKeyword_multipleTransactionsFound() {
        String expectedMessage = String.format(MESSAGE_TRANSACTIONS_LISTED_OVERVIEW, 3);
        TransactionContainsAllKeywordsPredicate predicate = preparePredicate("work");
        FilterCommand command = new FilterCommand(predicate);

        Model expectedModel = new ModelManager(getTypicalUniCash(), new UserPrefs());
        expectedModel.updateFilteredTransactionList(predicate);

        assertCommandSuccess(command, modelWithTransactions, expectedMessage, expectedModel);

        var filteredResult = modelWithTransactions.getFilteredTransactionList();
        assertEquals(filteredResult.get(1), NUS);
        assertEquals(filteredResult.get(2), INTERN);
    }

    @Test
    public void execute_predicateNull_assertionFailure() {
        assertThrows(NullPointerException.class, () -> new FilterCommand(null));
    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new FilterCommand(preparePredicate()).execute(model));
    }

    @Test
    public void toStringMethod() {
        TransactionContainsAllKeywordsPredicate predicate =
                new TransactionContainsAllKeywordsPredicate();

        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    /**
     * Generates a {@code TransactionContainsAllKeywordsPredicate} object
     */
    private TransactionContainsAllKeywordsPredicate preparePredicate() {

        List<String> parsedKeywords = Arrays.asList("foo", "bar", "baz");
        List<Predicate<Transaction>> predicateList = new ArrayList<>();

        parsedKeywords.stream().forEach(keyword -> {
            TransactionNameContainsKeywordsPredicate namePredicate =
                    new TransactionNameContainsKeywordsPredicate(List.of(keyword));
            predicateList.add(namePredicate);
        });

        return new TransactionContainsAllKeywordsPredicate(predicateList);
    }

    /**
     * Generates a {@code TransactionContainsAllKeywordsPredicate} object with an input string
     */
    private TransactionContainsAllKeywordsPredicate preparePredicate(String string) {

        List<String> parsedKeywords = Arrays.asList(string);
        List<Predicate<Transaction>> predicateList = new ArrayList<>();

        parsedKeywords.stream().forEach(keyword -> {
            TransactionNameContainsKeywordsPredicate namePredicate =
                    new TransactionNameContainsKeywordsPredicate(List.of(keyword));
            predicateList.add(namePredicate);
        });

        return new TransactionContainsAllKeywordsPredicate(predicateList);
    }
}
