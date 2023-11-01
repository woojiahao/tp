package unicash.logic.commands;

import unicash.commons.enums.CommandType;
import unicash.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class SummaryCommand extends Command {

    public static final String COMMAND_WORD = CommandType.SUMMARY.getCommandWords();

    public static final String MESSAGE_USAGE = CommandType.SUMMARY.getMessageUsage();

    public static final String MESSAGE_SHOWING_SUMMARY_SUCCESS = CommandType.SUMMARY.getMessageSuccess();

    public static final String MESSAGE_NOT_SHOWING_SUMMARY = "You have no expenses to summarize.";

    @Override
    public CommandResult execute(Model model) {
        if (model.hasExpenses()) {
            return new CommandResult(MESSAGE_SHOWING_SUMMARY_SUCCESS, false, false, true);
        }
        return new CommandResult(MESSAGE_NOT_SHOWING_SUMMARY, false, false, false);
    }
}
