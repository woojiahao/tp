package unicash.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.model.util.SampleDataUtil.getSampleUniCash;
import static unicash.testutil.TestDataUtil.getSumOfTestExpenses;
import static unicash.testutil.TestDataUtil.getTestTransactions;
import static unicash.testutil.TestDataUtil.getTestTransactionsAsUserInputs;

import java.util.Collections;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;

import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import unicash.MainApp;
import unicash.commons.enums.CommandType;
import unicash.model.transaction.Transaction;
import unicash.model.transaction.predicates.TransactionNameContainsKeywordsPredicate;
import unicash.testutil.TransactionBuilder;
import unicash.testutil.UserInputBuilder;

@ExtendWith(ApplicationExtension.class)
public class RollingBalanceUiTest {

    @BeforeEach
    public void setUp() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> new MainApp());
        FxToolkit.showStage();
        WaitForAsyncUtils.waitForFxEvents(20);
    }

    @AfterEach
    public void stopApp() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @Test
    public void rollingBalance_unicashResetState_showsDefaultBalance(FxRobot robot) {
        var rollingBalanceNode = robot.lookup("#balanceIndicator").tryQuery();
        assertTrue(rollingBalanceNode.isPresent());
        var rollingBalanceNodeLabel = (Label) rollingBalanceNode.get();
        robot.clickOn("#commandBoxPlaceholder");
        robot.write(CommandType.RESET.getMainCommandWord());
        robot.type(KeyCode.ENTER);

        Double resetUniCashSumOfIncomes = getSampleUniCash().getTransactionList()
                .stream()
                .filter(x -> x.getTypeString().equalsIgnoreCase("income"))
                .map(Transaction::getAmountAsDouble)
                .reduce(0.0, Double::sum);


        Double resetUniCashSumOfExpenses = getSampleUniCash().getTransactionList()
                .stream()
                .filter(x -> x.getTypeString().equalsIgnoreCase("expense"))
                .map(Transaction::getAmountAsDouble)
                .reduce(0.0, Double::sum);

        Double resetUniCashNetSum = resetUniCashSumOfIncomes - resetUniCashSumOfExpenses;

        String formattedRollingBalanceLabel;
        if (Double.compare(resetUniCashNetSum, 0) < 0) {
            formattedRollingBalanceLabel = String.format("Rolling Balance: -$%.2f",
                    Math.abs(resetUniCashNetSum));
        } else {
            formattedRollingBalanceLabel = String.format("Rolling Balance: $%.2f",
                    resetUniCashNetSum);
        }

        assertEquals(rollingBalanceNodeLabel.getText(), formattedRollingBalanceLabel);
    }


    @Test
    public void rollingBalance_filteredTransactionList_showsCorrectBalance(FxRobot robot) {

        /* All transactions in UniCash are cleared at first */
        robot.clickOn("#commandBoxPlaceholder");
        robot.write(CommandType.CLEAR_TRANSACTIONS.getMainCommandWord());
        robot.type(KeyCode.ENTER);

        /* Each test transaction is manually input into the command box */
        Transaction[] transactionList = getTestTransactions();
        for (String userInput : getTestTransactionsAsUserInputs()) {
            robot.clickOn("#commandBoxPlaceholder");
            robot.write(userInput);
            robot.type(KeyCode.ENTER);

        }

        /* The sum of expenses is tabulated from the test transactions list internally */
        Double lunchExpensesSum = getSumOfTestExpenses(
                new TransactionNameContainsKeywordsPredicate(
                        Collections.singletonList("lunch")));

        /* User input is constructed */
        String userInputString = new UserInputBuilder(
                new TransactionBuilder().withName("lunch"))
                .addName()
                .addCommand(CommandType.FIND)
                .toString();


        /* Find command is input by the FxRobot */
        robot.clickOn("#commandBoxPlaceholder");
        robot.write(userInputString);
        robot.type(KeyCode.ENTER);

        var rollingBalanceNode = robot.lookup("#balanceIndicator").tryQuery();
        assertTrue(rollingBalanceNode.isPresent());
        var rollingBalanceNodeLabel = (Label) rollingBalanceNode.get();

        String formattedRollingBalanceLabel =
                String.format("Rolling Balance: -$%.2f", lunchExpensesSum);

        assertEquals(rollingBalanceNodeLabel.getText(),
                formattedRollingBalanceLabel);

    }


}
