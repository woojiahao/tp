package unicash.logic.parser;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.ExitCommand;
import unicash.logic.parser.exceptions.ParseException;

/**
 * Parses arguments for the exit command
 */
public class ExitCommandParser implements Parser<ExitCommand> {

    /**
     * Parses {@code userInput} into an {@code ExitCommand}and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ExitCommand parse(String userInput) throws ParseException {
        assert userInput != null : "userInput cannot be null";

        //exit command must not have any trailing arguments
        if (!userInput.trim().isBlank()) {
            throw new ParseException(ExitCommand.MESSAGE_FAILURE);
        }
        return new ExitCommand();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ExitCommandParser)) {
            return false;
        }

        return this instanceof ExitCommandParser;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}

