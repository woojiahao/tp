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
import unicash.logic.commands.ExitCommand;


/**
 * A class to test the ExitCommandParser.
 */
public class ExitCommandParserTest {
    private final ExitCommandParser parser = new ExitCommandParser();

    @Test
    public void parse_withArgsWord_throwsParseException() {

        //clear command with trailing text
        assertParseFailure(parser, " abc", ExitCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withArgsNumber_throwsParseException() {

        //clear command with trailing number
        assertParseFailure(parser, " 1", ExitCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withExcessSpacing_doesNotThrowParseException() {

        //clear command with trailing number
        assertParseSuccess(parser, "     ", new ExitCommand());
    }

    @Test
    public void parse_withAllErrors_throwsParseException() {

        //clear command with trailing numbers, spaces and letters
        assertParseFailure(parser, "   1 a b c ,,  ", ExitCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withNonAsciiSymbols_throwsParseException() {

        //clear command with trailing non-ascii symbols
        assertParseFailure(parser, "∑∆˙©˚©˚˚∆˙©", ExitCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withNullInput_assertionFailure() {
        assertThrows(AssertionError.class, () -> parser.parse(null));

    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new ExitCommandParser().parse(" "));
    }


    @Test
    public void sameExitCommandParser_equalsTrue() {
        ExitCommandParser parser = new ExitCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new ExitCommandParser()));

    }

    @Test
    public void differentCommandTypes_equalsFalse() {
        ExitCommandParser exitCommandParser = new ExitCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(listCommandParser, exitCommandParser);
        assertFalse(exitCommandParser.equals(listCommandParser));
    }

    @Test
    public void nullInput_equalsFalse() {
        assertNotEquals(null, new ExitCommandParser());
    }

    @Test
    public void toStringTest() {
        ExitCommandParser exitCommandParser = new ExitCommandParser();
        String expected = new ToStringBuilder(new ExitCommandParser()).toString();
        assertEquals(expected, exitCommandParser.toString());
    }

}

