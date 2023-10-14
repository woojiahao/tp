package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.transaction.ListCommandParser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

public class ListCommandParserTest {
    private final ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_withArgs_throwsParseException() {
        //list with text
        assertParseFailure(parser, " abc", ListCommand.MESSAGE_FAILURE);

        //list with number
        assertParseFailure(parser, " 1", ListCommand.MESSAGE_FAILURE);
    }
}
