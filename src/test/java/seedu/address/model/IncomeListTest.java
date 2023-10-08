package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_NUS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIncomes.INTERN;
import static seedu.address.testutil.TypicalIncomes.NUS;
import static seedu.address.testutil.TypicalIncomes.getTypicalIncomeList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.income.Income;
import seedu.address.model.income.exceptions.DuplicateIncomeException;
import seedu.address.testutil.IncomeBuilder;
import seedu.address.testutil.IncomeListBuilder;

public class IncomeListTest {

    private final IncomeList incomeList = new IncomeList();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), incomeList.getIncomeList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyIncomeList_replacesData() {
        IncomeList newData = getTypicalIncomeList();
        incomeList.resetData(newData);
        assertEquals(newData, incomeList);
    }

    @Test
    public void resetData_withDuplicateIncomes_throwsDuplicatePersonException() {
        Income editedNus = new IncomeBuilder(NUS).withAmount(VALID_AMOUNT_NUS)
                .build();
        List<Income> newIncomes = Arrays.asList(NUS, editedNus);
        IncomeListTest.IncomeListStub newData = new IncomeListTest.IncomeListStub(newIncomes);

        assertThrows(DuplicateIncomeException.class, () -> incomeList.resetData(newData));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.hasIncome(null));
    }

    @Test
    public void hasIncome_incomeNotInIncomeList_returnsFalse() {
        assertFalse(incomeList.hasIncome(NUS));
    }

    @Test
    public void hasIncome_personInIncomeList_returnsTrue() {
        incomeList.addIncome(NUS);
        assertTrue(incomeList.hasIncome(NUS));
    }

    @Test
    public void removeIncome_personInIncomeList_returnsTrue() {
        IncomeList incomeList = new IncomeListBuilder().withIncome(NUS).build();
        assertTrue(incomeList.hasIncome(NUS));
        incomeList.removeIncome(NUS);
        assertFalse(incomeList.hasIncome(NUS));
    }

    @Test
    public void getIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> incomeList.getIncomeList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = IncomeList.class.getCanonicalName() + "{incomes=" + incomeList.getIncomeList() + "}";
        assertEquals(expected, incomeList.toString());
    }

    @Test
    public void equals() {
        // same object -> returns true
        IncomeList incomeList = new IncomeListBuilder().build();
        assertTrue(incomeList.equals(incomeList));

        // same lists
        incomeList.addIncome(NUS);
        IncomeList anotherList = new IncomeListBuilder().withIncome(NUS).build();
        assertTrue(incomeList.equals(anotherList));

        // different lists
        anotherList = new IncomeListBuilder().withIncome(INTERN).build();
        assertFalse(incomeList.equals(anotherList));

        // null -> returns false
        assertFalse(incomeList.equals(null));
    }

    /**
     * A stub ReadOnlyIncomeList whose persons list can violate interface constraints.
     */
    private static class IncomeListStub implements ReadOnlyIncomeList {
        private final ObservableList<Income> incomes = FXCollections.observableArrayList();

        IncomeListStub(Collection<Income> incomes) {
            this.incomes.setAll(incomes);
        }

        @Override
        public ObservableList<Income> getIncomeList() {
            return incomes;
        }
    }

}
