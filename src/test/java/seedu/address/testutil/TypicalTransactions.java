package seedu.address.testutil;

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
            .withType("expense")
            .withAmount(888)
            .withDateTime("18-09-2002 18:17")
            .build();
}
