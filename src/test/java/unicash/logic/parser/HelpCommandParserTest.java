package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.logic.commands.HelpCommand;

public class HelpCommandParserTest {
    private final HelpCommandParser parser = new HelpCommandParser();

    @Test
    public void parse_withExcessSpacing_success() {
        assertParseSuccess(parser, "     ", new HelpCommand(""));
    }

    @Test
    public void parse_withNullInput_assertionFailure() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));

    }

    @Test
    public void execute_predicateNotNull_assertion() {
        assertDoesNotThrow(() -> new ListCommandParser().parse(" "));
    }
}
