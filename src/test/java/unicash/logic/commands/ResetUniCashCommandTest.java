package unicash.logic.commands;

import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.model.util.SampleDataUtil.getSampleUniCash;

import org.junit.jupiter.api.Test;

import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;


public class ResetUniCashCommandTest {

    @Test
    public void execute_emptyUniCash_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setUniCash(getSampleUniCash());

        assertCommandSuccess(new ResetUniCashCommand(), model,
                ResetUniCashCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniCash_success() {
        Model model = new ModelManager(getSampleUniCash(), new UserPrefs());
        Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());
        expectedModel.setUniCash(getSampleUniCash());

        assertCommandSuccess(new ResetUniCashCommand(), model,
                ResetUniCashCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
