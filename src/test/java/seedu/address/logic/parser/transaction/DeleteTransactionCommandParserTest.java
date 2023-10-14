package seedu.address.logic.parser.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.transaction.DeleteTransactionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteTransactionCommandParserTest {

    private DeleteTransactionCommandParser parser = new DeleteTransactionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTransactionCommand() throws Exception {
        DeleteTransactionCommand command = parser.parse("1");
        assertEquals(new DeleteTransactionCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // No number provided
        assertThrows(ParseException.class, () -> parser.parse(""));

        // Non-integer input
        assertThrows(ParseException.class, () -> parser.parse("a"));

        // Negative number
        assertThrows(ParseException.class, () -> parser.parse("-1"));

        // Zero as input (assuming indices are 1-based)
        assertThrows(ParseException.class, () -> parser.parse("0"));
    }

    @Test
    public void parse_invalidFormat_throwsParseException() {
        // More than one space-separated argument
        assertThrows(ParseException.class, () -> parser.parse("1 2"));

        // Leading whitespace
        assertThrows(ParseException.class, () -> parser.parse(" 1"));

        // Trailing whitespace
        assertThrows(ParseException.class, () -> parser.parse("1 "));
    }

    @Test
    public void parse_customErrorMessage() {
        // Ensuring the custom error message is set
        ParseException exception = assertThrows(ParseException.class, () -> parser.parse("a"));
        assertTrue(exception.getMessage().contains("MESSAGE_USAGE"));  // Assuming DeleteCommand.MESSAGE_USAGE is a static final string
    }
}
