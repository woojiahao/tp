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
import unicash.logic.commands.ResetCommand;

/**
 * A class to test the ResetCommandParser.
 */
public class ResetCommandParserTest {
    private final ResetCommandParser parser = new ResetCommandParser();

    @Test
    public void parse_withArgsWord_throwsParseException() {

        //clear command with trailing text
        assertParseFailure(parser, " abc", ResetCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withArgsNumber_throwsParseException() {

        //clear command with trailing number
        assertParseFailure(parser, " 1", ResetCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withExcessSpacing_doesNotThrowParseException() {

        //clear command with trailing number
        assertParseSuccess(parser, "     ", new ResetCommand());
    }

    @Test
    public void parse_withAllErrors_throwsParseException() {

        //clear command with trailing numbers, spaces and letters
        assertParseFailure(parser, "   1 a b c ,,  ", ResetCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withNonAsciiSymbols_throwsParseException() {

        //clear command with trailing non-ascii symbols
        assertParseFailure(parser, "∑∆˙©˚©˚˚∆˙©", ResetCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withNullInput_assertionFailure() {
        assertThrows(AssertionError.class, () -> parser.parse(null));

    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new ResetCommandParser().parse(" "));
    }

    @Test
    public void sameResetCommandParser_equalsTrue() {
        ResetCommandParser parser = new ResetCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new ResetCommandParser()));

    }

    @Test
    public void equals_differentCommandTypes_returnsFalse() {
        ResetCommandParser resetCommandParser = new ResetCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(listCommandParser, resetCommandParser);
        assertFalse(resetCommandParser.equals(listCommandParser));
    }

    @Test
    public void equals_nullInput_returnsFalse() {
        assertNotEquals(null, new ResetCommandParser());
    }

    @Test
    public void toStringMethod() {
        ResetCommandParser resetCommandParser = new ResetCommandParser();
        String expected = new ToStringBuilder(new ResetCommandParser()).toString();
        assertEquals(expected, resetCommandParser.toString());
    }

}

