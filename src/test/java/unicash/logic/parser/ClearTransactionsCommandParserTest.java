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
import unicash.logic.commands.ClearTransactionsCommand;

public class ClearTransactionsCommandParserTest {
    private final ClearTransactionsCommandParser parser = new ClearTransactionsCommandParser();

    @Test
    public void parse_withArgsWord_throwsParseException() {

        //clear command with trailing text
        assertParseFailure(parser, " abc", ClearTransactionsCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withArgsNumber_throwsParseException() {

        //clear command with trailing number
        assertParseFailure(parser, " 1", ClearTransactionsCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withExcessSpacing_doesNotThrowParseException() {

        //clear command with trailing number
        assertParseSuccess(parser, "     ", new ClearTransactionsCommand());
    }

    @Test
    public void parse_withAllErrors_throwsParseException() {

        //clear command with trailing numbers, spaces and letters
        assertParseFailure(parser, "   1 a b c ,,  ", ClearTransactionsCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withNonAsciiSymbols_throwsParseException() {

        //clear command with trailing non-ascii symbols
        assertParseFailure(parser, "∑∆˙©˚©˚˚∆˙©", ClearTransactionsCommand.MESSAGE_FAILURE);
    }

    @Test
    public void parse_withNullInput_assertionFailure() {
        assertThrows(AssertionError.class, () -> parser.parse(null));

    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new ClearTransactionsCommandParser().parse(" "));
    }

    @Test
    public void equalsMethod_sameClearTransactionsCommandParserInput_returnsTrue() {
        ClearTransactionsCommandParser parser = new ClearTransactionsCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new ClearTransactionsCommandParser()));

    }

    @Test
    public void equalsMethod_differentCommandTypes_returnsFalse() {
        ClearTransactionsCommandParser clearTransactionsCommandParser =
                new ClearTransactionsCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(listCommandParser, clearTransactionsCommandParser);
        assertFalse(clearTransactionsCommandParser.equals(listCommandParser));
    }

    @Test
    public void equalsMethod_nullInput_returnsFalse() {
        assertNotEquals(null, new ClearTransactionsCommandParser());
    }

    @Test
    public void toStringTest() {
        ClearTransactionsCommandParser clearTransactionsCommandParser = new ClearTransactionsCommandParser();
        String expected = new ToStringBuilder(new ClearTransactionsCommandParser()).toString();
        assertEquals(expected, clearTransactionsCommandParser.toString());
    }

}

