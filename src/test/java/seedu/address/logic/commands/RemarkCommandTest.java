package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.testutil.PersonBuilder;

class RemarkCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRemarkUnfilteredList_success() {
        var firstPersonIndex = INDEX_FIRST_PERSON;
        Person person = model.getFilteredPersonList().get(firstPersonIndex.getZeroBased());
        Person editedPerson = new PersonBuilder(person).withRemark("Hi this is a remark").build();
        RemarkCommand remarkCommand = new RemarkCommand(firstPersonIndex, new Remark(editedPerson.getRemark().value));

        String expected = String.format(RemarkCommand.MESSAGE_ADD_REMARK_SUCCESS, editedPerson);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(person, editedPerson);
        assertCommandSuccess(remarkCommand, model, expected, expectedModel);
    }
}