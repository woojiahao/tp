package seedu.address.logic.parser.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteTransactionCommand;
import seedu.address.logic.parser.DeleteTransactionCommandParser;
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

}

