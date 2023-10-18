//package seedu.address.logic.commands;

//import static commands.logic.unicash.CommandTestUtil.assertCommandFailure;
//import static commands.logic.unicash.CommandTestUtil.assertCommandSuccess;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import logic.unicash.UniCashMessages;
//import model.unicash.Model;
//import model.unicash.ModelManager;
//import model.unicash.UniCash;
//import model.unicash.UserPrefs;
//import seedu.address.model.person.Person;
//import seedu.address.testutil.PersonBuilder;

// TODO: Add an integration test for the AddTransactionCommand
///**
// * Contains integration tests (interaction with the Model) for {@code AddCommand}.
// */
//public class AddCommandIntegrationTest {
//
//    private Model model;
//
//    @BeforeEach
//    public void setUp() {
//        model = new ModelManager(new UniCash(), new UserPrefs());
//    }
//
//    @Test
//    public void execute_newPerson_success() {
//        Person validPerson = new PersonBuilder().build();
//
//        Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());
//        expectedModel.addPerson(validPerson);
//
//        assertCommandSuccess(new AddCommand(validPerson), model,
//                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
//                expectedModel);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person personInList = model.getAddressBook().getPersonList().get(0);
//        assertCommandFailure(new AddCommand(personInList), model,
//                AddCommand.MESSAGE_DUPLICATE_PERSON);
//    }
//
//}
