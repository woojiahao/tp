package seedu.address.testutil;

import seedu.address.model.IncomeList;
import seedu.address.model.income.Income;

/**
 * A utility class to help with building IncomeList objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class IncomeListBuilder {

    private IncomeList incomeList;

    public IncomeListBuilder() {
        incomeList = new IncomeList();
    }

    public IncomeListBuilder(IncomeList incomeList) {
        this.incomeList = incomeList;
    }

    /**
     * Adds a new {@code Income} to the {@code IncomeList} that we are building.
     */
    public IncomeListBuilder withIncome(Income income) {
        incomeList.addIncome(income);
        return this;
    }

    public IncomeList build() {
        return incomeList;
    }
}
