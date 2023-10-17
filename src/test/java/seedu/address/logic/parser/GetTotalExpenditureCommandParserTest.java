package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GetTotalExpenditureCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class GetTotalExpenditureCommandParserTest {
    private final GetTotalExpenditureCommandParser parser = new GetTotalExpenditureCommandParser();

    @Test
    public void parse_emptyPreamble_throwsParseException() {
        var thrown = assertThrows(ParseException.class, () -> parser.parse("   c/Food"));
        assertEquals(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetTotalExpenditureCommand.MESSAGE_USAGE),
                thrown.getMessage()
        );
    }

    @Test
    public void parse_nonIntegerMonth_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("hi"));
    }
}
