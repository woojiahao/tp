package seedu.address.logic.parser;

import static seedu.address.logic.UniCashMessages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_NUS;
import static seedu.address.logic.commands.CommandTestUtil.CATEGORY_DESC_ENTERTAINMENT;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.DATETIME_DESC_NUS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CATEGORY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TRANSACTION_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_ORCHARD;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_NAME_DESC_INTERN;
import static seedu.address.logic.commands.CommandTestUtil.TRANSACTION_NAME_DESC_NUS;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_EXPENSE;
import static seedu.address.logic.commands.CommandTestUtil.TYPE_DESC_INCOME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TRANSACTION_NAME_NUS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TYPE_EXPENSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalTransactions.NUS;

import org.junit.jupiter.api.Test;

import seedu.address.logic.UniCashMessages;
import seedu.address.logic.commands.AddTransactionCommand;
import seedu.address.logic.parser.AddTransactionCommandParser;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;
import seedu.address.testutil.TransactionBuilder;


public class AddTransactionCommandParserTest {
    private final AddTransactionCommandParser parser = new AddTransactionCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Transaction expectedTransaction = new TransactionBuilder(NUS).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TRANSACTION_NAME_DESC_NUS + TYPE_DESC_EXPENSE
                + DATETIME_DESC_NUS + AMOUNT_DESC_NUS + CATEGORY_DESC_ENTERTAINMENT
                + LOCATION_DESC_ORCHARD, new AddTransactionCommand(expectedTransaction));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTransactionString = TRANSACTION_NAME_DESC_NUS + TYPE_DESC_EXPENSE
                + AMOUNT_DESC_NUS + DATETIME_DESC_NUS + LOCATION_DESC_ORCHARD + CATEGORY_DESC_ENTERTAINMENT;

        // multiple names
        assertParseFailure(parser, TRANSACTION_NAME_DESC_INTERN + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple amount
        assertParseFailure(parser, AMOUNT_DESC_INTERN + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // multiple datetime
        assertParseFailure(parser, DATETIME_DESC_INTERN + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // multiple type
        assertParseFailure(parser, TYPE_DESC_INCOME + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TYPE));

        // multiple location
        assertParseFailure(parser, LOCATION_DESC_ORCHARD + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // multiple category
        assertParseFailure(parser, CATEGORY_DESC_ENTERTAINMENT + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CATEGORY));

        // invalid value followed by valid value

        // invalid name
        assertParseFailure(parser, INVALID_TRANSACTION_NAME_DESC + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid amount
        assertParseFailure(parser, INVALID_AMOUNT_DESC + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // invalid datetime
        assertParseFailure(parser, INVALID_DATETIME_DESC + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // invalid type
        assertParseFailure(parser, INVALID_TYPE_DESC + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TYPE));

        // invalid category
        assertParseFailure(parser, INVALID_CATEGORY_DESC + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CATEGORY));

        // invalid location
        assertParseFailure(parser, INVALID_LOCATION_DESC + validExpectedTransactionString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // valid value followed by invalid value

        // invalid name
        assertParseFailure(parser, validExpectedTransactionString + INVALID_TRANSACTION_NAME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // invalid amount
        assertParseFailure(parser, validExpectedTransactionString + INVALID_AMOUNT_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_AMOUNT));

        // invalid datetime
        assertParseFailure(parser, validExpectedTransactionString + INVALID_DATETIME_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATETIME));

        // invalid type
        assertParseFailure(parser, validExpectedTransactionString + INVALID_TYPE_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TYPE));

        // invalid category
        assertParseFailure(parser, validExpectedTransactionString + INVALID_CATEGORY_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_CATEGORY));

        // invalid location
        assertParseFailure(parser, validExpectedTransactionString + INVALID_LOCATION_DESC,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTransactionCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_TRANSACTION_NAME_NUS + AMOUNT_DESC_NUS + TYPE_DESC_EXPENSE,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + VALID_AMOUNT_NUS + TYPE_DESC_EXPENSE,
                expectedMessage);

        // missing type prefix
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + VALID_AMOUNT_NUS + VALID_TYPE_EXPENSE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TRANSACTION_NAME_DESC + AMOUNT_DESC_NUS + DATETIME_DESC_NUS
                + TYPE_DESC_EXPENSE, seedu.address.model.transaction.Name.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + INVALID_AMOUNT_DESC
                + DATETIME_DESC_NUS + TYPE_DESC_EXPENSE, Amount.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS + DATETIME_DESC_NUS
                + INVALID_TYPE_DESC, Type.MESSAGE_CONSTRAINTS);
    }
}
