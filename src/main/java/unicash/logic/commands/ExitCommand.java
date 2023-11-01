package unicash.logic.commands;

import unicash.commons.enums.CommandType;
import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;

/**
 * Terminates UniCash.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = CommandType.EXIT.getCommandWords();

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = CommandType.EXIT.getMessageSuccess();

    public static final String MESSAGE_USAGE = CommandType.EXIT.getMessageUsage();


    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        return other instanceof ExitCommand;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}
