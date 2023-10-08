package seedu.address.model.income;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIncomes.INTERN;
import static seedu.address.testutil.TypicalIncomes.NUS;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.income.exceptions.DuplicateIncomeException;
import seedu.address.model.income.exceptions.IncomeNotFoundException;

public class UniqueIncomeListTest {

    private final UniqueIncomeList uniqueIncomeList = new UniqueIncomeList();

    @Test
    public void contains_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.contains(null));
    }

    @Test
    public void contains_incomeNotInList_returnsFalse() {
        assertFalse(uniqueIncomeList.contains(NUS));
    }

    @Test
    public void contains_incomeInList_returnsTrue() {
        uniqueIncomeList.add(NUS);
        assertTrue(uniqueIncomeList.contains(NUS));
    }

    @Test
    public void add_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.add(null));
    }

    @Test
    public void add_duplicateIncome_throwsDuplicateIncomeException() {
        uniqueIncomeList.add(NUS);
        assertThrows(DuplicateIncomeException.class, () -> uniqueIncomeList.add(NUS));
    }

    @Test
    public void setIncome_nullTargetIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setIncome(null, NUS));
    }

    @Test
    public void setIncome_nullEditedIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setIncome(NUS, null));
    }

    @Test
    public void setIncome_targetIncomeNotInList_throwsIncomeNotFoundException() {
        assertThrows(IncomeNotFoundException.class, () -> uniqueIncomeList.setIncome(NUS, NUS));
    }

    @Test
    public void setIncome_editedIncomeIsSameIncome_success() {
        uniqueIncomeList.add(NUS);
        uniqueIncomeList.setIncome(NUS, NUS);
        UniqueIncomeList expecteduniqueIncomeList = new UniqueIncomeList();
        expecteduniqueIncomeList.add(NUS);
        assertEquals(expecteduniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setIncome_editedIncomeHasDifferentIdentity_success() {
        uniqueIncomeList.add(NUS);
        uniqueIncomeList.setIncome(NUS, INTERN);
        UniqueIncomeList expecteduniqueIncomeList = new UniqueIncomeList();
        expecteduniqueIncomeList.add(INTERN);
        assertEquals(expecteduniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void remove_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.remove(null));
    }

    @Test
    public void remove_incomeDoesNotExist_throwsIncomeNotFoundException() {
        assertThrows(IncomeNotFoundException.class, () -> uniqueIncomeList.remove(NUS));
    }

    @Test
    public void remove_existingIncome_removesIncome() {
        uniqueIncomeList.add(NUS);
        uniqueIncomeList.remove(NUS);
        UniqueIncomeList expecteduniqueIncomeList = new UniqueIncomeList();
        assertEquals(expecteduniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setIncomes_nullUniqueIncomeList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setIncomes((UniqueIncomeList) null));
    }

    @Test
    public void setIncomes_uniqueIncomeList_replacesOwnListWithProvidedUniqueIncomeList() {
        uniqueIncomeList.add(NUS);
        UniqueIncomeList expectedUniqueIncomeList = new UniqueIncomeList();
        expectedUniqueIncomeList.add(INTERN);
        uniqueIncomeList.setIncomes(expectedUniqueIncomeList);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setIncomes_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueIncomeList.setIncomes((List<Income>) null));
    }

    @Test
    public void setIncomes_list_replacesOwnListWithProvidedList() {
        uniqueIncomeList.add(NUS);
        List<Income> incomeList = Collections.singletonList(INTERN);
        uniqueIncomeList.setIncomes(incomeList);
        UniqueIncomeList expectedUniqueIncomeList = new UniqueIncomeList();
        expectedUniqueIncomeList.add(INTERN);
        assertEquals(expectedUniqueIncomeList, uniqueIncomeList);
    }

    @Test
    public void setIncomes_listWithDuplicateIncomes_throwsDuplicateIncomeException() {
        List<Income> listWithDuplicateIncomes = Arrays.asList(NUS, NUS);
        assertThrows(DuplicateIncomeException.class, () -> uniqueIncomeList.setIncomes(listWithDuplicateIncomes));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueIncomeList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueIncomeList.asUnmodifiableObservableList().toString(), uniqueIncomeList.toString());
    }
}
