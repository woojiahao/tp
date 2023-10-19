package unicash.logic.parser;

import static unicash.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import unicash.logic.commands.ListCommand;

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
}
