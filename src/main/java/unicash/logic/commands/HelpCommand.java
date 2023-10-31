package unicash.logic.commands;

import static unicash.logic.UniCashMessages.MESSAGE_UNICASH_WELCOME;

import unicash.commons.enums.CommandType;
import unicash.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = CommandType.HELP.getCommandWords();

    public static final String MESSAGE_USAGE = CommandType.HELP.getMessageUsage();

    public static final String SHOWING_HELP_MESSAGE = MESSAGE_UNICASH_WELCOME;

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }
}
