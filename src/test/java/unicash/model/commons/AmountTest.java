package unicash.model.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void constructor_invalidDoubleAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Amount(-10));
        assertThrows(IllegalArgumentException.class, () -> new Amount((double) Long.MAX_VALUE));
    }

    @Test
    public void constructor_invalidStringAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Amount(String.valueOf(-10)));
        assertThrows(IllegalArgumentException.class, () -> new Amount(String.valueOf((double) Long.MAX_VALUE)));
        assertThrows(IllegalArgumentException.class, () -> new Amount("$" + -10));
        assertThrows(IllegalArgumentException.class, () -> new Amount("$" + (double) Long.MAX_VALUE));
        assertThrows(IllegalArgumentException.class, () -> new Amount("#13.22"));
        assertThrows(IllegalArgumentException.class, () -> new Amount("$hi.22"));
    }

    @Test
    public void constructor_stringAmountWithWhitespace_returnsAmountWithTrimmedWhitespace() {
        assertEquals(new Amount(14.47), new Amount("  $14.47"));
        assertEquals(new Amount(14.47), new Amount("$14.47  "));
        assertEquals(new Amount(14.47), new Amount("   $14.47  "));
    }

    @Test
    public void constructor_stringAmountWithoutLeadingIndicator_returnsValidAmount() {
        assertEquals(new Amount(14.47), new Amount("14.47"));
    }

    @Test
    public void constructor_stringAmountRoundsAmountTo2DP() {
        Amount amt = new Amount("$1234.5678");
        assertEquals("1234.57", amt.amountString());
        Amount otherAmt = new Amount("1234.5678");
        assertEquals("1234.57", otherAmt.amountString());
    }

    @Test
    public void constructor_doubleAmountRoundsAmountTo2DP() {
        Amount amt = new Amount(1234.5678);
        assertEquals("1234.57", amt.amountString());
    }

    @Test
    public void isValidAmount_doubleAmountWithinRange_returnsTrue() {
        assertTrue(Amount.isValidAmount(0.000));
        assertTrue(Amount.isValidAmount(10));
        assertTrue(Amount.isValidAmount(12.13));
        assertTrue(Amount.isValidAmount(Integer.MAX_VALUE));
        assertTrue(Amount.isValidAmount(Integer.MAX_VALUE - 1));
    }

    @Test
    public void isValidAmount_doubleAmountOutOfRange_returnsFalse() {
        assertFalse(Amount.isValidAmount(-1));
        assertFalse(Amount.isValidAmount(-0.000001));
        assertFalse(Amount.isValidAmount(Integer.MIN_VALUE));
        assertFalse(Amount.isValidAmount((double) (Integer.MAX_VALUE + 1)));
        assertFalse(Amount.isValidAmount((double) (Integer.MAX_VALUE + 0.002)));
        assertFalse(Amount.isValidAmount((double) Long.MAX_VALUE));
    }

    @Test
    public void isValidAmount_stringAmountWithLeadingIndicatorWithinRange_returnsTrue() {
        assertTrue(Amount.isValidAmount("$" + 0.000));
        assertTrue(Amount.isValidAmount("$" + 10));
        assertTrue(Amount.isValidAmount("$" + 12.13));
        assertTrue(Amount.isValidAmount("$" + Integer.MAX_VALUE));
        assertTrue(Amount.isValidAmount("$" + (Integer.MAX_VALUE - 1)));
    }

    @Test
    public void isValidAmount_stringAmountWithoutLeadingIndicatorWithinRange_returnsTrue() {
        assertTrue(Amount.isValidAmount(String.valueOf(0.000)));
        assertTrue(Amount.isValidAmount(String.valueOf(10)));
        assertTrue(Amount.isValidAmount(String.valueOf(12.13)));
        assertTrue(Amount.isValidAmount(String.valueOf(Integer.MAX_VALUE)));
        assertTrue(Amount.isValidAmount(String.valueOf((Integer.MAX_VALUE - 1))));
    }

    @Test
    public void isValidAmount_stringAmountWithLeadingIndicatorOutOfRange_returnsFalse() {
        assertFalse(Amount.isValidAmount("$" + -1));
        assertFalse(Amount.isValidAmount("$" + -0.000001));
        assertFalse(Amount.isValidAmount("$" + Integer.MIN_VALUE));
        assertFalse(Amount.isValidAmount("$" + (double) (Integer.MAX_VALUE + 1)));
        assertFalse(Amount.isValidAmount("$" + (double) Long.MAX_VALUE));
    }

    @Test
    public void isValidAmount_stringAmountWithoutLeadingIndicatorOutOfRange_returnsFalse() {
        assertFalse(Amount.isValidAmount(String.valueOf(-1)));
        assertFalse(Amount.isValidAmount(String.valueOf(-0.000001)));
        assertFalse(Amount.isValidAmount(String.valueOf(Integer.MIN_VALUE)));
        assertFalse(Amount.isValidAmount(String.valueOf((double) (Integer.MAX_VALUE + 1))));
        assertFalse(Amount.isValidAmount(String.valueOf((double) Long.MAX_VALUE)));
    }

    @Test
    public void isValidAmount_stringAmountInvalidCharacters_returnsFalse() {
        assertFalse(Amount.isValidAmount("#12.14"));
        assertFalse(Amount.isValidAmount("hi"));
        assertFalse(Amount.isValidAmount("12  .14"));
    }

    @Test
    public void isValidAmount_stringAmountValidAmount_trimsWhitespaceAndReturnsFalse() {
        assertTrue(Amount.isValidAmount(" $12.14"));
        assertTrue(Amount.isValidAmount("10  "));
        assertTrue(Amount.isValidAmount(" $12.14   "));
    }

    @Test
    public void equals() {
        Amount amount = new Amount(12.13);
        assertEquals(12.13, amount.amount);
        assertEquals(amount, amount);
        assertEquals(amount, new Amount(12.13));
        assertNotEquals(amount, null);
        assertNotEquals(amount, new Amount(12.16));
    }

    @Test
    public void amountToDecimalString_roundingRequired_returnsRoundedString() {
        Amount amt = new Amount(45.678);
        String result = Amount.amountToDecimalString(amt);
        assertEquals("45.68", result);
    }

    @Test
    public void amountString() {
        var amount = new Amount(14.47);
        assertEquals("14.47", amount.amountString());
    }

    @Test
    public void toStringMethod() {
        var amount = new Amount(14.47);
        assertEquals("$14.47", amount.toString());
    }

    @Test
    public void hashCodeMethod() {
        var firstAmount = new Amount(14.47);
        var secondAmount = new Amount("$14.47");
        assertEquals(firstAmount.hashCode(), secondAmount.hashCode());
    }
}
