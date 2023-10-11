package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.transaction.DateTime;
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
            .withDateTime(new DateTime(LocalDateTime.of(18, 8, 18, 1, 1)))
            .build();
}
