package unicash.logic.parser;

import unicash.logic.commands.HelpCommand;
import unicash.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for the list command.
 */
public class HelpCommandParser implements Parser<HelpCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public HelpCommand parse(String userInput) {
        assert userInput != null : "userInput cannot be null";
        String trimmed = userInput.trim();
        return new HelpCommand(trimmed);
    }
}
