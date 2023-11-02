package unicash.ui;

import java.nio.file.Paths;
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
import unicash.model.UserPrefs;

@ExtendWith(ApplicationExtension.class)
public class MainAppUiTest {

    @BeforeEach
    public void runAppToTests() throws TimeoutException {
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
    public void constructor_noCustomStoragePath_usesDefaultStoragePath(FxRobot robot) {
        var locationStatusNode = robot.lookup("#saveLocationStatus").tryQuery();
        Assertions.assertTrue(locationStatusNode.isPresent());
        var locationStatusNodeLabel = (Label) locationStatusNode.get();
        var userPrefs = new UserPrefs();
        Assertions.assertEquals(
                "Data source -> " + Paths.get(".").resolve(userPrefs.getUniCashFilePath()),
                locationStatusNodeLabel.getText()
        );
    }

    @Test
    public void statusBar_unicashResetState_showsDefaultBalance(FxRobot robot) {
        var rollingBalanceNode = robot.lookup("#balanceIndicator").tryQuery();
        Assertions.assertTrue(rollingBalanceNode.isPresent());
        var rollingBalanceNodeLabel = (Label) rollingBalanceNode.get();
        var userPrefs = new UserPrefs();
        robot.clickOn("#commandBoxPlaceholder");
        robot.write("reset_unicash");
        robot.press(KeyCode.ENTER);
        Assertions.assertEquals(rollingBalanceNodeLabel.getText(), "Rolling Balance: $1593.20");
    }

}
