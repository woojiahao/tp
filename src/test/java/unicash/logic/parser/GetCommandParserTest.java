package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import unicash.commons.core.index.Index;
import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.GetCommand;
import unicash.logic.parser.exceptions.ParseException;

/**
 * A class to test the GetCommandParser
 */
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


    @Test
    public void equals_sameGetCommandParserObject_returnsTrue() {
        GetCommandParser parser = new GetCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new GetCommandParser()));

    }

    @Test
    public void equals_differentCommandTypes_returnsFalse() {
        GetCommandParser getCommandParser = new GetCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(listCommandParser, getCommandParser);
        assertFalse(getCommandParser.equals(listCommandParser));
    }

    @Test
    public void equals_nullInput_returnsFalse() {
        assertNotEquals(null, new GetCommandParser());
    }

    @Test
    public void toStringMethod() {
        GetCommandParser getCommandParser = new GetCommandParser();
        String expected = new ToStringBuilder(new GetCommandParser()).toString();
        assertEquals(expected, getCommandParser.toString());
    }

}

