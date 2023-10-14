package seedu.address.logic.parser.transaction;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments for the list command.
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    @Override
    public ListCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        //list command must not have any additional arguments
        if (!userInput.trim().isBlank()) {
            throw new ParseException(ListCommand.MESSAGE_FAILURE);
        }
        return new ListCommand();
    }
}
