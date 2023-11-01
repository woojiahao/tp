package unicash.logic.commands;

import unicash.commons.enums.CommandType;
import unicash.model.Model;

/**
 * Terminates UniCa$h.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = CommandType.EXIT.getCommandWords();

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = CommandType.EXIT.getMessageSuccess();

    public static final String MESSAGE_USAGE = CommandType.EXIT.getMessageUsage();

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

}
