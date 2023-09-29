package seedu.address.model.expense;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void constructor_negativeAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Amount(-10));
    }

    @Test
    public void isValidAmount() {
        assertFalse(Amount.isValidAmount(-1));
        assertFalse(Amount.isValidAmount(-0.000001));
        assertTrue(Amount.isValidAmount(0.000));
        assertTrue(Amount.isValidAmount(10));
        assertTrue(Amount.isValidAmount(12.13));
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
}