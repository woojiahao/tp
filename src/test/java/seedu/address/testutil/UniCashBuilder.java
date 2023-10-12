package seedu.address.testutil;

import seedu.address.model.UniCash;
import seedu.address.model.transaction.Transaction;

/**
 * A utility class to help with building IncomeList objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class UniCashBuilder {

    private final UniCash uniCash;

    public UniCashBuilder() {
        uniCash = new UniCash();
    }

    public UniCashBuilder(UniCash transactionList) {
        this.uniCash = transactionList;
    }

    /**
     * Adds a new {@code Transaction} to the {@code UniCash} that we are building.
     */
    public UniCashBuilder withTransaction(Transaction transaction) {
        uniCash.addTransaction(transaction);
        return this;
    }

    public UniCash build() {
        return uniCash;
    }
}
