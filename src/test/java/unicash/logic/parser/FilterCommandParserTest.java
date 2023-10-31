package unicash.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.commands.CommandTestUtil.AMOUNT_DESC_NUS;
import static unicash.logic.commands.CommandTestUtil.DATETIME_DESC_NUS;
import static unicash.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static unicash.logic.commands.CommandTestUtil.INVALID_DATETIME_DESC;
import static unicash.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static unicash.logic.commands.CommandTestUtil.INVALID_TRANSACTION_NAME_DESC;
import static unicash.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static unicash.logic.commands.CommandTestUtil.TRANSACTION_NAME_DESC_NUS;
import static unicash.logic.commands.CommandTestUtil.TYPE_DESC_EXPENSE;
import static unicash.logic.commands.CommandTestUtil.TYPE_DESC_INCOME;
import static unicash.logic.parser.CliSyntax.PREFIX_TYPE;
import static unicash.logic.parser.CommandParserTestUtil.assertParseFailure;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.logic.UniCashMessages;
import unicash.logic.parser.exceptions.ParseException;
import unicash.model.commons.Amount;
import unicash.model.transaction.DateTime;
import unicash.model.transaction.Location;
import unicash.model.transaction.Type;
import unicash.model.transaction.predicates.TransactionContainsAllKeywordsPredicate;


/**
 * A class to test the FilterCommandParser.
 */
public class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        String emptyArgument = "";
        assertThrows(ParseException.class, () -> parser.parse(emptyArgument));

    }

    @Test
    public void parse_repeatedType_failure() {
        assertParseFailure(parser, TYPE_DESC_INCOME + TYPE_DESC_EXPENSE,
                UniCashMessages.getErrorMessageForDuplicatePrefixes(PREFIX_TYPE));
    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_TRANSACTION_NAME_DESC + AMOUNT_DESC_NUS + DATETIME_DESC_NUS
                + TYPE_DESC_EXPENSE, unicash.model.transaction.Name.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + INVALID_AMOUNT_DESC
                + DATETIME_DESC_NUS + TYPE_DESC_EXPENSE, Amount.MESSAGE_CONSTRAINTS);

        // invalid type
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS + DATETIME_DESC_NUS
                + INVALID_TYPE_DESC, Type.MESSAGE_CONSTRAINTS);

        // invalid location
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS + DATETIME_DESC_NUS
                + TYPE_DESC_EXPENSE + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS);

        // invalid datetime
        assertParseFailure(parser, TRANSACTION_NAME_DESC_NUS + AMOUNT_DESC_NUS + INVALID_DATETIME_DESC
                + TYPE_DESC_EXPENSE, DateTime.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void sameFilterCommandParser_equalsTrue() {
        FilterCommandParser parser = new FilterCommandParser();
        assertTrue(parser.equals(parser));
        assertTrue(parser.equals(new FilterCommandParser()));

    }

    @Test
    public void equalsMethod_differentCommandTypes_returnsFalse() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        ListCommandParser listCommandParser = new ListCommandParser();
        assertNotEquals(listCommandParser, filterCommandParser);
        assertFalse(filterCommandParser.equals(listCommandParser));
    }

    @Test
    public void equalsMethod_nullInput_returnsFalse() {
        assertNotEquals(null, new FilterCommandParser());
    }

    @Test
    public void toStringTest() {
        FilterCommandParser filterCommandParser = new FilterCommandParser();
        TransactionContainsAllKeywordsPredicate filterPredicate =
                new TransactionContainsAllKeywordsPredicate();

        String expected = new ToStringBuilder(new FilterCommandParser())
                .add("filterPredicate", filterPredicate).toString();
        assertEquals(expected, filterCommandParser.toString());
    }

}
