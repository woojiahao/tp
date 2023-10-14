package seedu.address.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.Prefix;

import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Category;
import seedu.address.model.transaction.DateTime;
import seedu.address.model.transaction.Location;
import seedu.address.model.transaction.Name;
import seedu.address.model.transaction.Transaction;
import seedu.address.model.transaction.Type;


public class UniCashMessagesTest {

    @Test
    public void getErrorMessageForDuplicatePrefixes_noPrefixes_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> UniCashMessages.getErrorMessageForDuplicatePrefixes());
    }

    @Test
    public void getErrorMessageForDuplicatePrefixes_singlePrefix_returnsFormattedMessage() {
        String errorMessage = UniCashMessages.getErrorMessageForDuplicatePrefixes(new Prefix("n/"));
        assertEquals("Multiple values specified for the following single-valued field(s): n/", errorMessage);
    }

    @Test
    public void getErrorMessageForDuplicatePrefixes_multiplePrefixes_returnsFormattedMessage() {
        String errorMessage = UniCashMessages.getErrorMessageForDuplicatePrefixes(new Prefix("n/"), new Prefix("p/"));
        String expectedMessage = "Multiple values specified for the following single-valued field(s): n/ p/";
        assertEquals(expectedMessage, errorMessage);
    }

    @Test
    public void formatTransaction_validTransaction_returnsFormattedString() {
        Transaction mockTransaction = new Transaction(
                new Name("TestName"),
                new Type("Expense"),
                new Amount(100.0),
                new Category("Food"),
                new DateTime("14/10/2023"),
                new Location("SampleLocation")
        );

        String formatted = UniCashMessages.formatTransaction(mockTransaction);
        String expectedString = "TestName; Type: Expense; Amount: 100.0; Category: "
                + "Food; Date: 14/10/2023; Location: SampleLocation";
        assertEquals(expectedString, formatted);
    }


}
