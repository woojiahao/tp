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
                    new Name("Lunch at McDonalds"),
                    new Type("expense"),
                    new Amount(17.40),
                    new DateTime("15-09-2023 11:00"),
                    new Location("Clementi Mall"),
                    getCategorySet("food")
            ),
            new Transaction(
                    new Name("Buy clothes"),
                    new Type("expense"),
                    new Amount(109.00),
                    new DateTime("17-09-2023 18:30"),
                    new Location("Uniqlo Bugis"),
                    getCategorySet("shopping")
            ),

            new Transaction(
                    new Name("Intern allowance september"),
                    new Type("income"),
                    new Amount(1800.00),
                    new DateTime("17-09-2023 00:00"),
                    new Location(""),
                    getCategorySet("salary")
            ),

            new Transaction(
                    new Name("Evening with friends"),
                    new Type("expense"),
                    new Amount(49.50),
                    new DateTime("17-09-2023 00:00"),
                    new Location("Clarke Quay"),
                    getCategorySet("social")
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
