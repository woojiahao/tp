package unicash.logic.commands;

import static java.util.Objects.requireNonNull;

import unicash.commons.util.ToStringBuilder;
import unicash.commons.util.CommandUsage;
import unicash.commons.util.ExampleGenerator;
import unicash.model.Model;
import unicash.model.UniCash;

/**
 * Clears all transactions in UniCash.
 */
public class ClearTransactionsCommand extends Command {

    public static final String COMMAND_WORD = "clear_transactions";

    public static final String MESSAGE_SUCCESS = "All transactions have been cleared!";
    public static final String MESSAGE_USAGE = new CommandUsage.Builder()
            .setCommandWord(COMMAND_WORD)
            .setDescription("Clears all existing transactions.")
            .setExample(ExampleGenerator.generate(COMMAND_WORD))
            .build()
            .toString();

    public static final String MESSAGE_FAILURE = String.format(
            "Clear transactions command cannot have trailing arguments. "
                    + "Use the command %s without any trailing arguments.", COMMAND_WORD);


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setUniCash(new UniCash());
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearTransactionsCommand)) {
            return false;
        }

        return other instanceof ClearTransactionsCommand;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
