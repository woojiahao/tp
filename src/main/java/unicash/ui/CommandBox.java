package unicash.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import unicash.logic.Logic;
import unicash.logic.commands.CommandResult;
import unicash.logic.commands.exceptions.CommandException;
import unicash.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";

    private final CommandExecutor commandExecutor;

    @FXML
    private TextField commandTextField;

    private List<String> commandHistory = new ArrayList<>();
    private int currentCommandIndex = -1; // this is to keep track of which command the user is currently on

    /**
     * Creates a {@code CommandBox} with the given {@code CommandExecutor}.
     */
    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());

        // Iterates through history of commands held in the commandHistory List
        commandTextField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case UP:
                showPreviousCommand();
                break;
            case DOWN:
                showNextCommand();
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
            addCommandToHistory(commandText);
            commandExecutor.execute(commandText);
            commandTextField.setText("");
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

    /* Adds the input string to command history list and resets command index */
    private void addCommandToHistory(String input) {
        commandHistory.add(input);
        currentCommandIndex = -1; // reset index as new command is added
    }

    /* Iterates through command history backwards and sets the commandTextField text */
    private void showPreviousCommand() {
        if (commandHistory.isEmpty()) {
            return;
        }

        if (currentCommandIndex == -1) {
            currentCommandIndex = commandHistory.size() - 1;
        } else if (currentCommandIndex > 0) {
            currentCommandIndex--;
        }
        commandTextField.setText(commandHistory.get(currentCommandIndex));
    }

    /* Iterates through command history forwards and sets the commandTextField text */
    private void showNextCommand() {
        if (commandHistory.isEmpty() || currentCommandIndex == -1) {
            return;
        }

        if (currentCommandIndex < commandHistory.size() - 1) {
            // If not at the end of the list, move one step forward i.e. down
            currentCommandIndex++;
            commandTextField.setText(commandHistory.get(currentCommandIndex));
        } else {
            // If at the end of the list, reset to -1 and clear the input
            currentCommandIndex = -1;
            commandTextField.setText("");
        }
    }


}
