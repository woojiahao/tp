package unicash.logic.parser;

import static java.util.Objects.requireNonNull;

import unicash.logic.commands.HelpCommand;

/**
 * Parses input arguments for the list command.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     */
    @Override
    public HelpCommand parse(String userInput) {
        requireNonNull(userInput);
        String trimmed = userInput.trim();
        return new HelpCommand(trimmed);
    }
}
