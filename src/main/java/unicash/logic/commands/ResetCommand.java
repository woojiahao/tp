package unicash.logic.commands;

import static java.util.Objects.requireNonNull;
import static unicash.model.util.SampleDataUtil.getSampleUniCash;

import unicash.commons.util.ToStringBuilder;
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


    public static final String MESSAGE_FAILURE = String.format(
            "Reset command cannot have trailing arguments. "
                    + "Use the command %s without any trailing arguments.", COMMAND_WORD);


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUniCash(getSampleUniCash());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResetCommand)) {
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
