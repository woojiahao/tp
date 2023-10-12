package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_NAME_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_NAME_NUS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.UniCash;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalTransactions {
    public static final Transaction BUYING_GROCERIES = new TransactionBuilder()
            .withName("Buying groceries")
            .withAmount(16.75)
            .withCategory("Groceries")
            .withLocation("Sheng Shiong (UTown)")
            .build();

    public static final Transaction DINING_WITH_FRIENDS = new TransactionBuilder()
            .withName("End of Semester Celebration")
            .withAmount(234.50)
            .withCategory("Food")
            .withLocation("Poulet")
            .withDateTime("18-08-2001 18:18")
            .build();

    public static final Transaction WORK_AT_LIHO = new TransactionBuilder()
            .withName("Working at liho")
            .withType("income")
            .withAmount(888)
            .withDateTime("18-09-2002 18:17")
            .build();

    // Manually added - Income's details found in {@code CommandTestUtil}
    public static final Transaction NUS = new TransactionBuilder().withName(VALID_TRANSACTION_NAME_NUS)
            .withType("income")
            .withAmount(VALID_AMOUNT_NUS)
            .withDateTime(VALID_DATETIME_NUS)
            .build();
    public static final Transaction INTERN = new TransactionBuilder().withName(VALID_TRANSACTION_NAME_INTERN)
            .withType("income")
            .withAmount(VALID_AMOUNT_INTERN)
            .withDateTime(VALID_DATETIME_INTERN)
            .build();
    /**
     * Returns a {@code UniCash} with all the typical transactions.
     */
    public static UniCash getTypicalUniCash() {
        UniCash ab = new UniCash();
        for (Transaction transaction : getTypicalTransactions()) {
            ab.addTransaction(transaction);
        }
        return ab;
    }

    public static List<Transaction> getTypicalTransactions() {
        return new ArrayList<>(Arrays.asList(BUYING_GROCERIES, DINING_WITH_FRIENDS, WORK_AT_LIHO, NUS, INTERN));
    }
}
