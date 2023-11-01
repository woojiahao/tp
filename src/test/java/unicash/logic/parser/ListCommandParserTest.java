package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.ListCommand;

/**
 * A class to test the ListCommandParser
 */
public class ListCommandParserTest {
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_withArgsWord_throwsParseException() {
        //list with text
        assertParseFailure(parser, " abc", ListCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withArgsNumber_throwsParseException() {
        //list with number
        assertParseFailure(parser, " 1", ListCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withExcessSpacing_throwsParseException() {
        //list with number
        assertParseSuccess(parser, "     ", new ListCommand());
    }

    @Test
    public void parse_withNullInput_assertionFailure() {
        assertThrows(AssertionError.class, () -> parser.parse(null));

    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new ListCommandParser().parse(" "));
    }


    @Test
    public void equalsMethod_sameListCommandParserInput_returnsTrue() {
        ListCommandParser parser = new ListCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new ListCommandParser()));

    }

    @Test
    public void equalsMethod_differentCommandTypes_returnsFalse() {
        ResetCommandParser resetCommandParser = new ResetCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(resetCommandParser, listCommandParser);
        assertFalse(listCommandParser.equals(resetCommandParser));
    }

    @Test
    public void equalsMethod_nullInput_returnsFalse() {
        assertNotEquals(null, new ListCommandParser());
    }

    @Test
    public void toStringMethod() {
        ListCommandParser listCommandParser = new ListCommandParser();
        String expected = new ToStringBuilder(new ListCommandParser()).toString();
        assertEquals(expected, listCommandParser.toString());
    }

}

