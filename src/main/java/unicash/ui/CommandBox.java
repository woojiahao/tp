package unicash.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import unicash.logic.Logic;
import unicash.logic.commands.CommandResult;
import unicash.logic.commands.exceptions.CommandException;
import unicash.logic.parser.exceptions.ParseException;


/**
 * The UI component that is responsible for receiving user command inputs.
 * Each user input, regardless of it's correctness
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    // To keep a history of previous userInputs
    private List<String> userInputHistory = new ArrayList<>();

    // To keep track of which userInput the user is currently on
    private int currentUserInputIndex = -1;

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        /* Iterates through history of userInputs held in the userInputHistory List
         * the arrow "UP" key and arrow "DOWN" key are constants defined by the KeyCode
         * class, and user keyboard input is observed by the KeyEvent class. The user can
         * also input the "ESCAPE" key to blank the field, instead of having to manually
         * backspace through the Command Text Field */
        commandTextField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case UP:
                showPreviousUserInput();
                break;
            case DOWN:
                showNextUserInput();
                break;
            case ESCAPE:
                clearCommandTextField();
                break;
            default:
                break;
            }
        });
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    private void handleCommandEntered() {
        String commandText = commandTextField.getText();
        if (commandText.equals("")) {
            return;
        }

        try {
            addUserInputToHistory(commandText);
            commandExecutor.execute(commandText);
            clearCommandTextField();
        } catch (CommandException | ParseException e) {
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see Logic#execute(String)
         */
        CommandResult execute(String commandText) throws CommandException, ParseException;
    }

    /**
     * Adds the input string to command history list and resets command index.
     */
    private void addUserInputToHistory(String input) {
        userInputHistory.add(input);
        currentUserInputIndex = -1; // reset index as new command is added
    }

    /**
     * Clears the Command Text Field.
     */
    private void clearCommandTextField() {
        commandTextField.setText("");
    }

    /**
     * Iterates through userInput history backwards and sets the commandTextField text
     * stopping at the earliest entered user input.
     */
    private void showPreviousUserInput() {
        if (userInputHistory.isEmpty()) {
            return;
        }

        if (currentUserInputIndex == -1) {
            currentUserInputIndex = userInputHistory.size() - 1;

        } else if (currentUserInputIndex > 0) {
            currentUserInputIndex--;
        }

        commandTextField.setText(userInputHistory.get(currentUserInputIndex));
    }

    /**
     * Iterates through userInput history backwards and sets the commandTextField text
     * stopping at the latest state of the CommandTextField, which is cleared by default.
     */
    private void showNextUserInput() {
        if (userInputHistory.isEmpty() || currentUserInputIndex == -1) {
            return;
        }

        if (currentUserInputIndex < userInputHistory.size() - 1) {
            // If not at the end of the list, move one step forward i.e. down
            currentUserInputIndex++;
            commandTextField.setText(userInputHistory.get(currentUserInputIndex));

        } else {
            // If at the end of the list, reset index to -1 and clear text field
            currentUserInputIndex = -1;
            clearCommandTextField();
        }
    }
}
