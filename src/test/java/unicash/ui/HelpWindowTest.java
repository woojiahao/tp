package unicash.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.concurrent.TimeoutException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;
import unicash.MainApp;

import javafx.scene.input.KeyCode;

@ExtendWith(ApplicationExtension.class)
public class HelpWindowTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    public void runAppToTests() throws Exception {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(() -> new MainApp(tempDir.resolve("ui_data.json")));
        FxToolkit.showStage();
        WaitForAsyncUtils.waitForFxEvents(100);
    }

    @AfterEach
    public void stopApp() throws TimeoutException {
        FxToolkit.cleanupStages();
    }

    @Test
    public void userInput_help_showHelpWindowAsRoot(FxRobot robot) {
        var beforeHelpContainer = robot.lookup("#helpMessageContainer").tryQuery();
        assertTrue(beforeHelpContainer.isEmpty());
        robot.clickOn("#commandBoxPlaceholder");
        robot.write("help");
        robot.press(KeyCode.ENTER);
        var afterHelp = robot.lookup("#helpMessageContainer").tryQuery();
        assertTrue(afterHelp.isPresent());
    }

}
