package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.model.util.SampleDataUtil.getSampleUniCash;

import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.model.Model;

/**
 * Resets UniCa$h to initial state by replacing the current UniCa$h
 * storage data and populating it with the default UniCa$h containing
 * typical transactions from {@code SampleDataUtil}.
 */
public class ResetCommand extends Command {

    public static final String COMMAND_WORD = "reset_unicash";
    public static final String MESSAGE_SUCCESS =
            "UniCa$h has been successfully restored to its original state!";
    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription("Reset UniCa$h to its original state with pre-existing transactions.")
            .setExample(ExampleGenerator.generate(COMMAND_WORD))
            .build()
            .toString();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUniCash(getSampleUniCash());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
