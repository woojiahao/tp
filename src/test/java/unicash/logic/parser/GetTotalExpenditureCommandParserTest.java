package unicash.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unicash.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import unicash.logic.commands.GetTotalExpenditureCommand;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.category.Category;

public class GetTotalExpenditureCommandParserTest {
    private final GetTotalExpenditureCommandParser parser = new GetTotalExpenditureCommandParser();

    @Test
    public void parse_noCategory_returnsGetTotalExpenditureCommandWithoutCategoryFilter() throws ParseException {
        var result = parser.parse(" month/1");
        var expected = new GetTotalExpenditureCommand(1, null);
        assertEquals(expected, result);
    }

    @Test
    public void parse_withCategory_returnsGetTotalExpenditureCommandWithCategoryFilter() throws ParseException {
        var result = parser.parse(" month/1 c/Food");
        var expected = new GetTotalExpenditureCommand(1, new Category("Food"));
        assertEquals(expected, result);
    }

    @Test
    public void parse_withUnknownArgument_throwsParseException() throws ParseException {
        // Parser will treat the month as 1 amt/500 and as such throw an exception
        assertThrows(ParseException.class, () -> parser.parse(" month/1 amt/500"));
    }

    @Test
    public void parse_missingMonth_throwsParseException() {
        var thrown = assertThrows(ParseException.class, () -> parser.parse(" c/Food"));
        assertEquals(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetTotalExpenditureCommand.MESSAGE_USAGE),
                thrown.getMessage()
        );
    }

    @Test
    public void parse_nonIntegerMonth_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(" month/hi"));
    }
}
