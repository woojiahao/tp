package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.TransactionNameContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditTransactionDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TRANSACTION_NAME_NUS = "Work at NUS";
    public static final String VALID_TRANSACTION_NAME_INTERN = "Internship";
    public static final String VALID_TRANSACTION_NAME_SHOPPING = "Shopping";
    public static final String VALID_TYPE_EXPENSE = "expense";
    public static final String VALID_TYPE_INCOME = "income";
    public static final Double VALID_AMOUNT_NUS = 888.8;
    public static final Double VALID_AMOUNT_INTERN = 8.8;
    public static final Double VALID_AMOUNT_SHOPPING = 899.8;
    public static final String VALID_DATETIME_NUS = "12-12-2021 12:12";
    public static final String VALID_DATETIME_INTERN = "08-08-2008 08:08";
    public static final String VALID_DATETIME_SHOPPING = "01-02-2008 11:08";
    public static final String VALID_CATEGORY_ENTERTAINMENT = "entertainment";
    public static final String VALID_CATEGORY_NUS = "Teaching Assistant";
    public static final String VALID_LOCATION_ORCHARD = "orchard";
    public static final String VALID_LOCATION_NUS = "NUS";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TRANSACTION_NAME_DESC_NUS = " " + PREFIX_NAME + VALID_TRANSACTION_NAME_NUS;
    public static final String TRANSACTION_NAME_DESC_INTERN = " " + PREFIX_NAME + VALID_TRANSACTION_NAME_INTERN;
    public static final String AMOUNT_DESC_NUS = " " + PREFIX_AMOUNT + VALID_AMOUNT_NUS;
    public static final String AMOUNT_DESC_INTERN = " " + PREFIX_AMOUNT + VALID_AMOUNT_INTERN;
    public static final String DATETIME_DESC_NUS = " " + PREFIX_DATETIME + VALID_DATETIME_NUS;
    public static final String DATETIME_DESC_INTERN = " " + PREFIX_DATETIME + VALID_DATETIME_INTERN;
    public static final String TYPE_DESC_EXPENSE = " " + PREFIX_TYPE + VALID_TYPE_EXPENSE;
    public static final String TYPE_DESC_INCOME = " " + PREFIX_TYPE + VALID_TYPE_INCOME;
    public static final String CATEGORY_DESC_ENTERTAINMENT = " " + PREFIX_CATEGORY + VALID_CATEGORY_ENTERTAINMENT;
    public static final String CATEGORY_DESC_NUS = " " + PREFIX_CATEGORY + VALID_CATEGORY_NUS;
    public static final String LOCATION_DESC_ORCHARD = " " + PREFIX_LOCATION + VALID_LOCATION_ORCHARD;
    public static final String LOCATION_DESC_NUS = " " + PREFIX_LOCATION + VALID_LOCATION_NUS;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_TRANSACTION_NAME_DESC = " " + PREFIX_NAME + "NUS&"; // '&' not allowed in names
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "-3.0"; // negative amounts not allowed
    public static final String INVALID_DATETIME_DESC = " " + PREFIX_DATETIME + "19/13/2001 19:30"; // invalid date
    public static final String INVALID_TYPE_DESC = " " + PREFIX_TYPE + "afaf"; // invalid type
    public static final String INVALID_CATEGORY_DESC = " " + PREFIX_CATEGORY + "afraf*&"; // invalid type
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION + "a214faf*&"; // invalid type

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;
    public static final EditTransactionCommand.EditTransactionDescriptor DESC_NUS;
    public static final EditTransactionCommand.EditTransactionDescriptor DESC_SHOPPING;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_NUS = new EditTransactionDescriptorBuilder().withName(VALID_TRANSACTION_NAME_NUS)
                .withAmount(VALID_AMOUNT_NUS).withType(VALID_TYPE_INCOME).withCategory(VALID_CATEGORY_NUS)
                .withDateTime(VALID_DATETIME_NUS).withLocation(VALID_LOCATION_NUS).build();
        DESC_SHOPPING = new EditTransactionDescriptorBuilder().withName(VALID_TRANSACTION_NAME_SHOPPING)
                .withCategory(VALID_CATEGORY_ENTERTAINMENT).withAmount(VALID_AMOUNT_SHOPPING)
                .withType(VALID_TYPE_EXPENSE).withDateTime(VALID_DATETIME_SHOPPING).withLocation(VALID_LOCATION_ORCHARD)
                .build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the transaction at the
     * given {@code targetIndex} in the {@code model}'s database.
     */
    public static void showTransactionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTransactionList().size());

        Transaction transaction = model.getFilteredTransactionList().get(targetIndex.getZeroBased());
        final String[] splitName = transaction.getName().fullName.split("\\s+");
        model.updateFilteredTransactionList(
                new TransactionNameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredTransactionList().size());
    }

}
