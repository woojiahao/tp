package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCOME_NAME_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INCOME_NAME_NUS;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.IncomeList;
import seedu.address.model.income.Income;

/**
 * A utility class containing a list of {@code Income} objects to be used in tests.
 */
public class TypicalIncomes {
    public static final Income WORK_AT_LIHO = new IncomeBuilder().withName("Work at liho")
            .withAmount(300.0)
            .withDate(LocalDateTime.of(2023, 9, 28, 0, 0))
            .build();

    public static final Income ALLOWANCE = new IncomeBuilder().withName("Allowance")
            .withAmount(200.0)
            .withDate(LocalDateTime.of(2023, 10, 20, 0, 0))
            .build();

    public static final Income TUITION = new IncomeBuilder().withName("Tuition")
            .withAmount(450.0)
            .withDate(LocalDateTime.of(2023, 11, 25, 12, 12))
            .build();

    // Manually added - Income's details found in {@code CommandTestUtil}
    public static final Income NUS = new IncomeBuilder().withName(VALID_INCOME_NAME_NUS)
            .withAmount(VALID_AMOUNT_NUS)
            .withDate(VALID_DATETIME_NUS)
            .build();
    public static final Income INTERN = new IncomeBuilder().withName(VALID_INCOME_NAME_INTERN)
            .withAmount(VALID_AMOUNT_INTERN)
            .withDate(VALID_DATETIME_INTERN)
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
