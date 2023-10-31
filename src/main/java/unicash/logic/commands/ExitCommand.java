package unicash.logic.commands;

import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.model.Model;

/**
 * Terminates UniCa$h.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting UniCa$h as requested ...";
    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription("Exit from UniCa$h.")
            .setExample(ExampleGenerator.generate(COMMAND_WORD))
            .build()
            .toString();

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
