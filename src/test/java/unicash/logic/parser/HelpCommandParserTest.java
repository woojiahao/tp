package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.HelpCommand;

public class HelpCommandParserTest {
    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_withExcessSpacing_success() {
        assertParseSuccess(parser, "     ", new HelpCommand(""));
    }

    @Test
    public void parse_withExcessSpacingAfterWords_success() {
        assertParseSuccess(parser, "test     ", new HelpCommand("test"));
    }

    @Test
    public void parse_withNullInput_assertionFailure() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));

    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new ListCommandParser().parse(" "));
    }

    @Test
    public void parse_withMixedCase_success() {
        assertParseSuccess(parser, "ReSeT_uniCaSh", new HelpCommand("reset_unicash"));
    }

    @Test
    public void equals_nullInput_returnsFalse() {
        assertFalse(new HelpCommandParser().equals(null));
    }

    @Test
    public void equals_helpCommandParserObject_returnsTrue() {
        assertTrue(new HelpCommandParser().equals(new HelpCommandParser()));
    }

    @Test
    public void toStringMethod() {
        HelpCommandParser helpCommandParser = new HelpCommandParser();

        String expected = new ToStringBuilder(new HelpCommandParser()).toString();
        assertEquals(expected, helpCommandParser.toString());
    }
}
