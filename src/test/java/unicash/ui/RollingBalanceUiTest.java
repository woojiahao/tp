package unicash.ui;

import static unicash.model.util.SampleDataUtil.getSampleUniCash;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
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
import unicash.testutil.TestDataUtil;
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
        Assertions.assertTrue(rollingBalanceNode.isPresent());
        var rollingBalanceNodeLabel = (Label) rollingBalanceNode.get();
        robot.clickOn("#commandBoxPlaceholder");
        robot.write("reset_unicash");
        robot.press(KeyCode.ENTER);

        Double resetUniCashIncome = getSampleUniCash().getTransactionList()
                .stream()
                .filter(x -> x.getTypeString().equalsIgnoreCase("income"))
                .map(Transaction::getAmountAsDouble)
                .reduce(0.0, Double::sum);


        Double resetUniCashExpense = getSampleUniCash().getTransactionList()
                .stream()
                .filter(x -> x.getTypeString().equalsIgnoreCase("expense"))
                .map(Transaction::getAmountAsDouble)
                .reduce(0.0, Double::sum);

        Double resetUniCashSum = resetUniCashIncome - resetUniCashExpense;

        String formattedRollingBalanceLabel;

        if (Double.compare(resetUniCashSum, 0) < 0) {
            formattedRollingBalanceLabel = String.format("Rolling Balance: -$%.2f",
                    Math.abs(resetUniCashSum));
        } else {
            formattedRollingBalanceLabel = String.format("Rolling Balance: $%.2f",
                    resetUniCashSum);
        }

        Assertions.assertEquals(rollingBalanceNodeLabel
                .getText(), formattedRollingBalanceLabel);
    }


    /**
     * Helper method to invoke UserInputBuilder.
     */
    private static String convertTransaction(Transaction transaction) {
        return new UserInputBuilder(transaction)
                .withAllProperties()
                .toString();
    }

}
