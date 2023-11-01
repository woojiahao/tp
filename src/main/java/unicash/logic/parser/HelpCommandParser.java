package unicash.logic.parser;

import static java.util.Objects.requireNonNull;

import unicash.commons.util.ToStringBuilder;
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
        return new HelpCommand(trimmed.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        return other instanceof HelpCommandParser;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }
}
