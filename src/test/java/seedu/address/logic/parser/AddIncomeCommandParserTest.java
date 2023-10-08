package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIncomes.NUS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.income.AddIncomeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.income.Amount;
import seedu.address.model.income.DateTime;
import seedu.address.model.income.Income;
import seedu.address.model.person.Email;
import seedu.address.testutil.IncomeBuilder;

import java.time.LocalDateTime;

public class AddIncomeCommandParserTest {
    private AddIncomeCommandParser parser = new AddIncomeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Income expectedIncome = new IncomeBuilder(NUS).build();

        // whitespace only preamble
        assertParseSuccess(parser, INCOME_NAME_DESC_NUS + AMOUNT_DESC_NUS
                + DATETIME_DESC_NUS, new AddIncomeCommand(expectedIncome));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedIncomeString = INCOME_NAME_DESC_NUS + AMOUNT_DESC_NUS
                + DATETIME_DESC_NUS;

        // multiple names
        assertParseFailure(parser, INCOME_NAME_DESC_INTERN + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple amount
        assertParseFailure(parser, AMOUNT_DESC_INTERN + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // multiple datetime
        assertParseFailure(parser, DATETIME_DESC_INTERN + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_INCOME_NAME_DESC + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid amount
        assertParseFailure(parser, INVALID_AMOUNT_DESC + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // invalid datetime
        assertParseFailure(parser, INVALID_DATETIME_DESC + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedIncomeString + INVALID_INCOME_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid amount
        assertParseFailure(parser, validExpectedIncomeString + INVALID_AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // invalid datetime
        assertParseFailure(parser, validExpectedIncomeString + INVALID_DATETIME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIncomeCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_INCOME_NAME_NUS + AMOUNT_DESC_NUS + DATETIME_DESC_NUS,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, INCOME_NAME_DESC_NUS + VALID_AMOUNT_NUS + DATETIME_DESC_NUS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_INCOME_NAME_DESC + AMOUNT_DESC_NUS + DATETIME_DESC_NUS,
                seedu.address.model.income.Name.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, INCOME_NAME_DESC_NUS + INVALID_AMOUNT_DESC + DATETIME_DESC_NUS,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, INCOME_NAME_DESC_NUS + AMOUNT_DESC_NUS + INVALID_DATETIME_DESC,
                DateTime.MESSAGE_CONSTRAINTS);
    }
}
