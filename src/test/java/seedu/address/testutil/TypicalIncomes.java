package seedu.address.testutil;

import java.time.LocalDateTime;

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

    private TypicalIncomes() {} // prevents instantiation
}
