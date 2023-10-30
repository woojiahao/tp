package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.commons.core.index.Index;
import unicash.logic.commands.GetCommand;
import unicash.logic.parser.exceptions.ParseException;


public class GetCommandParserTest {

    private final GetCommandParser parser = new GetCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTransactionCommand() throws Exception {
        GetCommand command = parser.parse("1");
        assertEquals(new GetCommand(Index.fromOneBased(1)), command);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // No number provided
        assertThrows(ParseException.class, () -> parser.parse(""));

        // Non-integer input
        assertThrows(ParseException.class, () -> parser.parse("a"));
        assertThrows(ParseException.class, () -> parser.parse("."));

        // Negative number
        assertThrows(ParseException.class, () -> parser.parse("-1"));

        // Zero as input (assuming indices are 1-based)
        assertThrows(ParseException.class, () -> parser.parse("0"));

        // Float as input (assuming indices are 1-based)
        assertThrows(ParseException.class, () -> parser.parse("0.5"));
    }

}

