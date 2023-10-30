package unicash.logic.parser;

import static unicash.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static unicash.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static unicash.logic.parser.CliSyntax.PREFIX_INTERVAL;
import static unicash.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unicash.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static unicash.testutil.TypicalBudgets.MONTHLY;

import org.junit.jupiter.api.Test;

import unicash.logic.UniCashMessages;
import unicash.logic.commands.SetBudgetCommand;
public class SetBudgetCommandParserTest {
    private final SetBudgetCommandParser parser = new SetBudgetCommandParser();

    @Test
    public void parse_noFields_throwsParseException() {
        var expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "", expected);
        assertParseFailure(parser, "   ", expected);
    }

    @Test
    public void parse_missingAmountField_throwsParseException() {
        var expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "interval/day", expected);
    }

    @Test
    public void parse_missingIntervalField_throwsParseException() {
        var expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SetBudgetCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "amt/1000", expected);
    }

    @Test
    public void parse_duplicates_failure() {
        String validCommandStr = " " + PREFIX_AMOUNT + MONTHLY.getAmount().toString() + " "
                + PREFIX_INTERVAL + MONTHLY.getInterval().toString();
        String amount = " " + PREFIX_AMOUNT + MONTHLY.getAmount().toString();
        String interval = " " + PREFIX_INTERVAL + MONTHLY.getInterval().toString();

        // multiple names
        assertParseFailure(parser, amount + validCommandStr,
                UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));
        assertParseFailure(parser, validCommandStr + interval,
                UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVAL));

    }

    @Test
    public void parse_missingPreamble_failure() {
        String invalidCommand = PREFIX_AMOUNT + MONTHLY.getAmount().toString() + " "
                + PREFIX_INTERVAL + MONTHLY.getInterval().toString();
        assertParseFailure(parser, invalidCommand, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                SetBudgetCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validInput_returnsSetBudgetCommand() {
        var validCommandStr =
                " " + PREFIX_AMOUNT + MONTHLY.getAmount() + " " + PREFIX_INTERVAL + MONTHLY.getInterval().toString();
        var expectedCommand = new SetBudgetCommand(MONTHLY);
        assertParseSuccess(parser, validCommandStr, expectedCommand);
    }
}
