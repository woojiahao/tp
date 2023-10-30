package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static unicash.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.logic.commands.ExitCommand;


/**
 * A class to test the FilterCommandParser.
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

}

