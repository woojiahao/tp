package seedu.address.model.util;

import seedu.address.model.ReadOnlyUniCash;
import seedu.address.model.UniCash;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Category;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;

/**
 * Contains utility methods for populating {@code UniCash} with sample data.
 */
public class SampleDataUtil {

    public static Transaction[] getSampleTransactions() {
        return new Transaction[]{
            new Transaction(
                    new seedu.address.model.transaction.Name("Valid transaction"),
                    new Type("expense"),
                    new Amount(17.0),
                    new Category("Food"),
                    new DateTime("15-09-2023 00:00"),
                    new Location("")
            ),
            new Transaction(
                    new seedu.address.model.transaction.Name("Valid transaction 2"),
                    new Type("expense"),
                    new Amount(123),
                    new Category("Others"),
                    new DateTime("15-07-2023 00:00"),
                    new Location("Jurong")
            )
        };
    }

    public static ReadOnlyUniCash getSampleUniCash() {
        UniCash sample = new UniCash();
        for (var transaction : getSampleTransactions()) {
            sample.addTransaction(transaction);
        }
        return sample;
    }

}
