package unicash.logic.parser;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.ListCommand;
import unicash.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments for the list command.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        assert userInput != null : "userInput cannot be null";
        // list command must not have any additional arguments
        if (!userInput.trim().isBlank()) {
            throw new ParseException(ListCommand.MESSAGE_FAILURE);
        }
        return new ListCommand();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommandParser)) {
            return false;
        }

        return other instanceof ListCommandParser;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .toString();
    }

}

