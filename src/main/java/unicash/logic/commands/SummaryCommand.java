package unicash.logic.commands;

import unicash.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = "summary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays summary statistics of user's spending\n"
            + "\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_SUMMARY_MESSAGE = "Opened UniCa$h summary window.";

    @Override
    public CommandResult execute(Model model) {
        model.updateExpenseSummary();
        return new CommandResult(SHOWING_SUMMARY_MESSAGE, false, false, true);
    }
}
