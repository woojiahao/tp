package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class GetTotalExpenditureCommandParserTest {
    private final GetTotalExpenditureCommandParser parser = new GetTotalExpenditureCommandParser();

    @Test
    public void parse_emptyPreamble_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("get_total_expenditure"));
    }

    @Test
    public void parse_nonIntegerMonth_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("get_total_expenditure hi"));
    }
}
