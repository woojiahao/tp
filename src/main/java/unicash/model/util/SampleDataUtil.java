package unicash.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import unicash.model.ReadOnlyUniCash;
import unicash.model.UniCash;
import unicash.model.category.Category;
import unicash.model.transaction.Amount;
import unicash.model.transaction.DateTime;
import unicash.model.transaction.Location;
import unicash.model.transaction.Name;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.Type;

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
