package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTransaction.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTransactions.SHOPPING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Category;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Type;

public class JsonAdaptedTransactionTest {
    private static final String INVALID_NAME = "R@chel";
    private static final double INVALID_AMOUNT = -10;
    private static final String INVALID_CATEGORY = "@@@";
    private static final String INVALID_DATETIME = "hi";
    private static final String INVALID_LOCATION = "@@@@";
    private static final String INVALID_TYPE = "others";

    private static final String VALID_NAME = SHOPPING.getName().toString();
    private static final double VALID_AMOUNT = SHOPPING.getAmount().amount;
    private static final String VALID_CATEGORY = SHOPPING.getCategory().category;
    private static final String VALID_DATETIME = SHOPPING.getDateTime().originalString();
    private static final String VALID_LOCATION = SHOPPING.getLocation().location;
    private static final String VALID_TYPE = SHOPPING.getType().toString();

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        var transaction = new JsonAdaptedTransaction(SHOPPING);
        assertEquals(SHOPPING, transaction.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                INVALID_NAME,
                VALID_AMOUNT,
                VALID_CATEGORY,
                VALID_DATETIME,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                null,
                VALID_AMOUNT,
                VALID_CATEGORY,
                VALID_DATETIME,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidAmount_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                INVALID_AMOUNT,
                VALID_CATEGORY,
                VALID_DATETIME,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = Amount.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidCategory_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                INVALID_CATEGORY,
                VALID_DATETIME,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = Category.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullCategory_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                null,
                VALID_DATETIME,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Category.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidDateTime_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                VALID_CATEGORY,
                INVALID_DATETIME,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = DateTime.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullDateTime_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                VALID_CATEGORY,
                null,
                VALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidLocation_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                VALID_CATEGORY,
                VALID_DATETIME,
                INVALID_LOCATION,
                VALID_TYPE
        );
        String expectedMessage = Location.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                VALID_CATEGORY,
                VALID_DATETIME,
                VALID_LOCATION,
                INVALID_TYPE
        );
        String expectedMessage = Type.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        var transaction = new JsonAdaptedTransaction(
                VALID_NAME,
                VALID_AMOUNT,
                VALID_CATEGORY,
                VALID_DATETIME,
                VALID_LOCATION,
                null
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Type.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, transaction::toModelType);
    }
}
