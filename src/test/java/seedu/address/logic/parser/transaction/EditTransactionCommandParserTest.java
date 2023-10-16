package seedu.address.logic.parser.transaction;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRANSACTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TRANSACTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TRANSACTION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.UniCashMessages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.logic.commands.EditTransactionCommand;
import seedu.address.logic.parser.EditTransactionCommandParser;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Category;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Type;
import seedu.address.testutil.EditTransactionDescriptorBuilder;

public class EditTransactionCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTransactionCommand.MESSAGE_USAGE);

    private EditTransactionCommandParser parser = new EditTransactionCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, CommandTestUtil.VALID_TRANSACTION_NAME_NUS, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditTransactionCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.TRANSACTION_NAME_DESC_NUS, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CommandTestUtil.TRANSACTION_NAME_DESC_NUS, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_TRANSACTION_NAME_DESC,
                Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_AMOUNT_DESC,
                Amount.MESSAGE_CONSTRAINTS); // invalid amount
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_CATEGORY_DESC,
                Category.MESSAGE_CONSTRAINTS); // invalid category
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_DATETIME_DESC,
                DateTime.MESSAGE_CONSTRAINTS); // invalid datetime
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_LOCATION_DESC,
                Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_TYPE_DESC,
                Type.MESSAGE_CONSTRAINTS); // invalid type

        // invalid amount followed by valid category
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_AMOUNT_DESC + CommandTestUtil.CATEGORY_DESC_ENTERTAINMENT,
                Amount.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser,
                "1" + CommandTestUtil.INVALID_TRANSACTION_NAME_DESC + CommandTestUtil.INVALID_CATEGORY_DESC
                        + CommandTestUtil.VALID_AMOUNT_INTERN + CommandTestUtil.VALID_DATETIME_INTERN,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TRANSACTION;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.AMOUNT_DESC_NUS
                + CommandTestUtil.LOCATION_DESC_NUS + CommandTestUtil.TYPE_DESC_INCOME
                + CommandTestUtil.CATEGORY_DESC_NUS + CommandTestUtil.DATETIME_DESC_NUS
                + CommandTestUtil.TRANSACTION_NAME_DESC_NUS;

        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS)
                .withAmount(CommandTestUtil.VALID_AMOUNT_NUS).withCategory(CommandTestUtil.VALID_CATEGORY_NUS)
                .withLocation(CommandTestUtil.VALID_LOCATION_NUS).withDateTime(CommandTestUtil.VALID_DATETIME_NUS)
                .withType(CommandTestUtil.VALID_TYPE_INCOME).build();
        EditTransactionCommand expectedTransactionCommand = new EditTransactionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedTransactionCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TRANSACTION;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.AMOUNT_DESC_INTERN
                + CommandTestUtil.CATEGORY_DESC_NUS;

        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withAmount(CommandTestUtil.VALID_AMOUNT_INTERN)
                .withCategory(CommandTestUtil.VALID_CATEGORY_NUS).build();
        EditTransactionCommand expectedTransactionCommand = new EditTransactionCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedTransactionCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TRANSACTION;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.TRANSACTION_NAME_DESC_NUS;
        EditTransactionCommand.EditTransactionDescriptor descriptor = new EditTransactionDescriptorBuilder()
                .withName(CommandTestUtil.VALID_TRANSACTION_NAME_NUS).build();
        EditTransactionCommand expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // amount
        userInput = targetIndex.getOneBased() + CommandTestUtil.AMOUNT_DESC_NUS;
        descriptor = new EditTransactionDescriptorBuilder().withAmount(CommandTestUtil.VALID_AMOUNT_NUS).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // category
        userInput = targetIndex.getOneBased() + CommandTestUtil.CATEGORY_DESC_NUS;
        descriptor = new EditTransactionDescriptorBuilder().withCategory(CommandTestUtil.VALID_CATEGORY_NUS).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // datetime
        userInput = targetIndex.getOneBased() + CommandTestUtil.DATETIME_DESC_NUS;
        descriptor = new EditTransactionDescriptorBuilder().withDateTime(CommandTestUtil.VALID_DATETIME_NUS).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + CommandTestUtil.LOCATION_DESC_NUS;
        descriptor = new EditTransactionDescriptorBuilder().withLocation(CommandTestUtil.VALID_LOCATION_NUS).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // type
        userInput = targetIndex.getOneBased() + CommandTestUtil.TYPE_DESC_INCOME;
        descriptor = new EditTransactionDescriptorBuilder().withType(CommandTestUtil.VALID_TYPE_INCOME).build();
        expectedCommand = new EditTransactionCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddTransactionCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_TRANSACTION;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.LOCATION_DESC_NUS
                + CommandTestUtil.INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput, UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_LOCATION_DESC
                + CommandTestUtil.LOCATION_DESC_NUS;

        assertParseFailure(parser, userInput, UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + CommandTestUtil.LOCATION_DESC_NUS + CommandTestUtil.TYPE_DESC_INCOME
                + CommandTestUtil.AMOUNT_DESC_NUS + CommandTestUtil.CATEGORY_DESC_NUS
                + CommandTestUtil.DATETIME_DESC_NUS + CommandTestUtil.LOCATION_DESC_NUS
                + CommandTestUtil.TYPE_DESC_INCOME + CommandTestUtil.AMOUNT_DESC_NUS
                + CommandTestUtil.CATEGORY_DESC_NUS + CommandTestUtil.DATETIME_DESC_NUS
                + CommandTestUtil.LOCATION_DESC_ORCHARD + CommandTestUtil.TYPE_DESC_EXPENSE
                + CommandTestUtil.AMOUNT_DESC_INTERN + CommandTestUtil.CATEGORY_DESC_ENTERTAINMENT
                + CommandTestUtil.DATETIME_DESC_INTERN;

        assertParseFailure(parser, userInput,
                UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION, PREFIX_TYPE, PREFIX_AMOUNT,
                        PREFIX_DATETIME, PREFIX_CATEGORY));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_LOCATION_DESC
                + CommandTestUtil.INVALID_CATEGORY_DESC + CommandTestUtil.INVALID_DATETIME_DESC
                + CommandTestUtil.INVALID_LOCATION_DESC + CommandTestUtil.INVALID_CATEGORY_DESC
                + CommandTestUtil.INVALID_DATETIME_DESC;

        assertParseFailure(parser, userInput,
                UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION, PREFIX_CATEGORY, PREFIX_DATETIME));
    }

}
