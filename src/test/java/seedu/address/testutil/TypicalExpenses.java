package seedu.address.testutil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import seedu.address.model.expense.Expense;

public class TypicalExpenses {
    public static final Expense BUYING_GROCERIES = new ExpenseBuilder()
            .withName("Buying groceries")
            .withAmount(16.75)
            .withCategory("Groceries")
            .withLocation("Sheng Shiong (UTown)")
            .build();

    public static final Expense DINING_WITH_FRIENDS = new ExpenseBuilder()
            .withName("End of Semester Celebration")
            .withAmount(234.50)
            .withCategory("Food")
            .withLocation("Poulet")
            .withDateTime(
                    LocalDateTime.of(
                            LocalDate.of(2022, 12, 12), LocalTime.now()
                    )
            )
            .build();
}
