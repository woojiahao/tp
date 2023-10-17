package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyUniCash;
import seedu.address.model.UniCash;
import seedu.address.model.category.Category;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;

/**
 * Contains utility methods for populating {@code UniCash} with sample data.
 */
public class SampleDataUtil {

    private SampleDataUtil() {

    }

    public static Transaction[] getSampleTransactions() {
        return new Transaction[]{
            new Transaction(
                    new Name("Valid transaction"),
                    new Type("expense"),
                    new Amount(17.0),
                    new DateTime("15-09-2023 00:00"),
                    new Location(""),
                    getCategorySet("Food")
            ),
            new Transaction(
                    new Name("Valid transaction 2"),
                    new Type("expense"),
                    new Amount(123),
                    new DateTime("15-07-2023 00:00"),
                    new Location("Jurong"),
                    getCategorySet("Others")
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

    /**
     * Returns a category set containing the list of strings given.
     */
    public static Set<Category> getCategorySet(String... strings) {
        return Arrays.stream(strings)
                .map(Category::new)
                .collect(Collectors.toSet());
    }
}
