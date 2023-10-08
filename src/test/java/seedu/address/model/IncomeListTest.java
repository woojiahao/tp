package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_NUS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIncomes.NUS;
import static seedu.address.testutil.TypicalIncomes.WORK_AT_LIHO;
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
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> incomeList.hasIncome(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(incomeList.hasIncome(WORK_AT_LIHO));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        incomeList.addIncome(WORK_AT_LIHO);
        assertTrue(incomeList.hasIncome(WORK_AT_LIHO));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> incomeList.getIncomeList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = IncomeList.class.getCanonicalName() + "{incomes=" + incomeList.getIncomeList() + "}";
        assertEquals(expected, incomeList.toString());
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
