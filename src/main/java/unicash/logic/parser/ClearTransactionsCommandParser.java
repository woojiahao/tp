package unicash.logic.parser;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.ClearTransactionsCommand;
import unicash.logic.parser.exceptions.ParseException;

/**
 * Parses arguments for the {@code ClearTransactionsCommand}
 */
public class ClearTransactionsCommandParser implements Parser<ClearTransactionsCommand> {

    /**
     * Parses {@code userInput} into a {@code ClearTransactionsCommand} and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ClearTransactionsCommand parse(String userInput) throws ParseException {
        assert userInput != null : "userInput cannot be null";

        // clear transactions command must not have any trailing arguments
        if (!userInput.trim().isBlank()) {
            throw new ParseException(ClearTransactionsCommand.MESSAGE_FAILURE);
        }
        return new ClearTransactionsCommand();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClearTransactionsCommandParser)) {
            return false;
        }

        return other instanceof ClearTransactionsCommandParser;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}

