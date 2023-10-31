package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.testutil.TypicalBudgets.DAILY;
import static unicash.testutil.TypicalBudgets.MONTHLY;
import static unicash.testutil.TypicalBudgets.WEEKLY;
import static unicash.testutil.TypicalTransactions.INTERN;
import static unicash.testutil.TypicalTransactions.NUS;
import static unicash.testutil.TypicalTransactions.SHOPPING;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;
import unicash.model.budget.Budget;
import unicash.model.transaction.DateTime;
import unicash.testutil.TransactionBuilder;

// TODO: Must include unit tests to ensure overflows don't happen
public class GetBudgetCommandTest {
    @Test
    public void execute_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GetBudgetCommand().execute(null));
    }

    @Test
    public void execute_noBudget_returnsNoBudgetMessage() {
        var uniCash = new UniCash();
        var model = new ModelManager(uniCash, new UserPrefs());
        var command = new GetBudgetCommand();
        assertCommandSuccess(command, model, GetBudgetCommand.MESSAGE_NO_BUDGET, model);
    }

    @Test
    public void execute_noTransactionWithinInterval_calculatesFullBudgetRemainder() {
        var uniCash = new UniCash();
        var model = new ModelManager(uniCash, new UserPrefs());
        model.setBudget(new Budget(DAILY));
        var before = localDateTimeToString(LocalDateTime.now().minusDays(2));
        model.addTransaction(new TransactionBuilder(NUS).withDateTime(before).build());
        model.addTransaction(new TransactionBuilder(INTERN).withDateTime(before).build());
        var command = new GetBudgetCommand();
        assertCommandSuccess(
                command,
                model,
                String.format(
                        GetBudgetCommand.MESSAGE_SUCCESS,
                        "Daily",
                        DAILY.getAmount().toString(),
                        DAILY.getAmount().amount
                ),
                model
        );
    }

    @Test
    public void execute_withinDayInterval_calculatesRemainder() {
        var uniCash = new UniCash();
        var model = new ModelManager(uniCash, new UserPrefs());
        model.setBudget(new Budget(DAILY));

        var before = localDateTimeToString(LocalDateTime.now().minusDays(2));
        var today = localDateTimeToString(LocalDateTime.now());

        model.addTransaction(new TransactionBuilder(NUS).withDateTime(before).build());
        model.addTransaction(new TransactionBuilder(INTERN).withDateTime(before).build());
        model.addTransaction(new TransactionBuilder(NUS).withDateTime(today).build());
        model.addTransaction(new TransactionBuilder(INTERN).withDateTime(today).build());
        model.addTransaction(new TransactionBuilder(SHOPPING).withDateTime(today).build());

        var command = new GetBudgetCommand();
        var expectedRemainder =
                DAILY.getAmount().amount - NUS.getAmount().amount
                        - INTERN.getAmount().amount + SHOPPING.getAmount().amount;
        assertCommandSuccess(
                command,
                model,
                String.format(
                        GetBudgetCommand.MESSAGE_SUCCESS,
                        "Daily",
                        DAILY.getAmount().toString(),
                        expectedRemainder
                ),
                model
        );
    }

    @Test
    public void execute_withinWeekInterval_calculatesRemainder() {
        var uniCash = new UniCash();
        var model = new ModelManager(uniCash, new UserPrefs());
        model.setBudget(new Budget(WEEKLY));

        // Hardcoded to be a Monday
        Clock clock = Clock.fixed(Instant.parse("2023-10-30T10:15:30.00Z"), ZoneId.of("UTC"));
        var today = LocalDateTime.now(clock);

        var beforeDates = Stream
                .iterate(-4, x -> x + 1)
                .limit(12)
                .map(i -> localDateTimeToString(today.plusDays(i)))
                .collect(Collectors.toList());
        for (String beforeDate : beforeDates) {
            model.addTransaction(new TransactionBuilder(NUS).withDateTime(beforeDate).build());
        }

        var command = new GetBudgetCommand(today);
        var expectedRemainder = WEEKLY.getAmount().amount - (7 * NUS.getAmount().amount);
        assertCommandSuccess(
                command,
                model,
                String.format(
                        GetBudgetCommand.MESSAGE_SUCCESS,
                        "Weekly",
                        WEEKLY.getAmount().toString(),
                        expectedRemainder
                ),
                model
        );
    }

    @Test
    public void execute_withinMonthInterval_calculateRemainder() {
        var uniCash = new UniCash();
        var model = new ModelManager(uniCash, new UserPrefs());
        model.setBudget(new Budget(MONTHLY));

        // Hardcoded to be the start of a month
        Clock clock = Clock.fixed(Instant.parse("2023-11-01T10:15:30.00Z"), ZoneId.of("UTC"));
        var today = LocalDateTime.now(clock);

        // Previous month
        model.addTransaction(
                new TransactionBuilder(NUS)
                        .withDateTime(localDateTimeToString(today.minusDays(2)))
                        .build()
        );
        // Next month
        model.addTransaction(
                new TransactionBuilder(INTERN)
                        .withDateTime(localDateTimeToString(today.plusMonths(1)))
                        .build()
        );
        // Within month
        model.addTransaction(
                new TransactionBuilder(NUS)
                        .withDateTime(localDateTimeToString(today.plusDays(3)))
                        .build()
        );
        model.addTransaction(
                new TransactionBuilder(INTERN)
                        .withDateTime(localDateTimeToString(today.plusDays(7)))
                        .build()
        );
        model.addTransaction(
                new TransactionBuilder(SHOPPING)
                        .withDateTime(localDateTimeToString(today.plusDays(21)))
                        .build()
        );

        var command = new GetBudgetCommand(today);
        var expectedRemainder =
                MONTHLY.getAmount().amount - NUS.getAmount().amount
                        - INTERN.getAmount().amount + SHOPPING.getAmount().amount;
        assertCommandSuccess(
                command,
                model,
                String.format(
                        GetBudgetCommand.MESSAGE_SUCCESS,
                        "Monthly",
                        MONTHLY.getAmount().toString(),
                        expectedRemainder
                ),
                model
        );
    }

    @Test
    public void equals_nullOther_returnsFalse() {
        assertNotEquals(new GetBudgetCommand(), null);
    }

    @Test
    public void equals_otherDifferentType_returnsFalse() {
        assertFalse(new GetBudgetCommand().equals(5));
    }

    @Test
    public void equals_anyOtherGetBudgetCommand_returnsTrue() {
        assertEquals(new GetBudgetCommand(), new GetBudgetCommand());
    }

    private String localDateTimeToString(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern(DateTime.DATETIME_PATTERN_ONE));
    }
}
