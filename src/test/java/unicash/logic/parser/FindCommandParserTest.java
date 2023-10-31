package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.commands.FindCommand;
import unicash.model.transaction.predicates.TransactionContainsAnyKeywordsPredicate;

/**
 * A class to test the FindCommandParser.
 */
public class FindCommandParserTest {

    private final FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        CommandParserTestUtil.assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new TransactionContainsAnyKeywordsPredicate(Arrays.asList("Shopping", "Work")));
        CommandParserTestUtil.assertParseSuccess(parser, "Shopping Work", expectedFindCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Shopping \n \t Work  \t", expectedFindCommand);
    }


    @Test
    public void sameFindCommandParser_equalsTrue() {
        FindCommandParser parser = new FindCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new FindCommandParser()));

    }

    @Test
    public void equalsMethod_differentCommandTypes_returnsFalse() {
        FindCommandParser findCommandParser = new FindCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(listCommandParser, findCommandParser);
        assertFalse(findCommandParser.equals(listCommandParser));
    }

    @Test
    public void equalsMethod_nullInput_returnsFalse() {
        assertNotEquals(null, new FindCommandParser());
    }

    @Test
    public void toStringTest() {
        FindCommandParser findCommandParser = new FindCommandParser();
        String expected = new ToStringBuilder(new FindCommandParser()).toString();
        assertEquals(expected, findCommandParser.toString());
    }

}

