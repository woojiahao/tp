package unicash.logic.parser;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.ResetCommand;
import unicash.logic.parser.exceptions.ParseException;

/**
 * Parses arguments for the {@code ResetCommand}
 */
public class ResetCommandParser implements Parser<ResetCommand> {

    /**
     * Parses {@code userInput} into a {@code ResetCommand} and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ResetCommand parse(String userInput) throws ParseException {
        assert userInput != null : "userInput cannot be null";

        // reset unicash command must not have any trailing arguments
        if (!userInput.trim().isBlank()) {
            throw new ParseException(ResetCommand.MESSAGE_FAILURE);
        }
        return new ResetCommand();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ResetCommandParser)) {
            return false;
        }

        return this instanceof ResetCommandParser;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}

