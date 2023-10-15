package seedu.address.logic.parser;

import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.transaction.TransactionNameContainsKeywordsPredicate;

/**
 * A class to test the FindCommandParser.
 */
public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TransactionNameContainsKeywordsPredicate(Arrays.asList("Shopping", "Work")));
        assertParseSuccess(parser, "Shopping Work", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Shopping \n \t Work  \t", expectedFindCommand);
    }

}
