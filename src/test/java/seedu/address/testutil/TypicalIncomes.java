package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.IncomeList;
import seedu.address.model.income.DateTime;
import seedu.address.model.income.Income;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Income} objects to be used in tests.
 */
public class TypicalIncomes {
    public static final Income WORK_AT_LIHO = new IncomeBuilder().withName("Work at liho")
            .withAmount(300.0)
            .withDate(new DateTime(LocalDateTime.of(2023, 9, 28, 0, 0)))
            .build();

    public static final Income ALLOWANCE = new IncomeBuilder().withName("Allowance")
            .withAmount(200.0)
            .withDate(new DateTime(LocalDateTime.of(2023, 10, 20, 0, 0)))
            .build();

    public static final Income TUITION = new IncomeBuilder().withName("Tuition")
            .withAmount(450.0)
            .withDate(new DateTime(LocalDateTime.of(2023, 11, 25, 12, 12)))
            .build();

    private TypicalIncomes() {} // prevents instantiation

    /**
     * Returns an {@code IncomeList} with all the typical persons.
     */
    public static IncomeList getTypicalIncomeList() {
        IncomeList ab = new IncomeList();
        for (Income income : getTypicalIncomes()) {
            ab.addIncome(income);
        }
        return ab;
    }

    public static List<Income> getTypicalIncomes() {
        return new ArrayList<>(Arrays.asList(WORK_AT_LIHO, ALLOWANCE, TUITION));
    }
}
