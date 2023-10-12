package seedu.address.logic.parser.transaction;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_NUS;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_NUS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRANSACTION_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_NAME_DESC_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_NAME_DESC_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_NAME_NUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTransactions.NUS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.transaction.AddTransactionCommand;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Transaction;
import seedu.address.testutil.TransactionBuilder;


public class AddTransactionCommandParserTest {
    private AddTransactionCommandParser parser = new AddTransactionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Transaction expectedIncome = new TransactionBuilder(NUS).build();

        // whitespace only preamble
        assertParseSuccess(parser, TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS
                + DATETIME_DESC_NUS, new AddTransactionCommand(expectedIncome));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedIncomeString = TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS
                + DATETIME_DESC_NUS;

        // multiple names
        assertParseFailure(parser, TRANSACTION_NAME_DESC_INTERN + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple amount
        assertParseFailure(parser, AMOUNT_DESC_INTERN + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // multiple datetime
        assertParseFailure(parser, DATETIME_DESC_INTERN + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_TRANSACTION_NAME_DESC + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid amount
        assertParseFailure(parser, INVALID_AMOUNT_DESC + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // invalid datetime
        assertParseFailure(parser, INVALID_DATETIME_DESC + validExpectedIncomeString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedIncomeString + INVALID_TRANSACTION_NAME_DESC,
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
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransactionCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TRANSACTION_NAME_NUS + AMOUNT_DESC_NUS + DATETIME_DESC_NUS,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + VALID_AMOUNT_NUS + DATETIME_DESC_NUS,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TRANSACTION_NAME_DESC + AMOUNT_DESC_NUS + DATETIME_DESC_NUS,
                seedu.address.model.transaction.Name.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + INVALID_AMOUNT_DESC + DATETIME_DESC_NUS,
                Amount.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS + INVALID_DATETIME_DESC,
                DateTime.MESSAGE_CONSTRAINTS);
    }
}
