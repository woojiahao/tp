package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static unicash.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.logic.commands.ResetCommand;

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

}

