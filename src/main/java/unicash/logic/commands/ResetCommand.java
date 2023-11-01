package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.model.util.SampleDataUtil.getSampleUniCash;

import unicash.commons.enums.CommandType;
import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;

/**
 * Resets UniCa$h to initial state by replacing the current UniCa$h
 * storage data and populating it with the default UniCa$h containing
 * typical transactions from {@code SampleDataUtil}.
 */
public class ResetCommand extends Command {

    public static final String COMMAND_WORD = CommandType.RESET.getCommandWords();
    public static final String MESSAGE_SUCCESS = CommandType.RESET.getMessageSuccess();
    public static final String MESSAGE_USAGE = CommandType.RESET.getMessageUsage();

    public static final String MESSAGE_FAILURE = CommandType.RESET.getMessageFailure();


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUniCash(getSampleUniCash());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        return other instanceof ResetCommand;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
