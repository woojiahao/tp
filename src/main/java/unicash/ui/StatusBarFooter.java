package unicash.ui;

import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import unicash.model.transaction.Transaction;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";

    @FXML
    private Label saveLocationStatus;

    @FXML
    private Label balanceIndicator;

    private ObservableList<Transaction> transactions;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}
     * indicating the current Data Source.
     */
    public StatusBarFooter(Path saveLocation,
                           ObservableList<Transaction> transactionList) {
        super(FXML);
        saveLocationStatus.setText("Data source -> " + Paths.get(".").resolve(saveLocation));
        this.transactions = transactionList;
        updateBalance(transactions);

        this.transactions.addListener((
                ListChangeListener.Change<? extends Transaction> c) -> {
            updateBalance(transactions);
        });
    }

    /**
     * Handles the updating of balanceIndicator in the status bar
     * @param transactions the input transactions list to be monitored.
     */
    @FXML
    public void updateBalance(ObservableList<Transaction> transactions) {
        double balance = 0.0;

        for (Transaction t : transactions) {
            if (t.getType().type.getOriginalString().equalsIgnoreCase("expense")) {
                balance -= t.getAmount().amount;
            } else {
                balance += t.getAmount().amount;
            }
        }

        balanceIndicator.setText(String.format("Balance: $%.2f", balance));

        if (balance < 0) {
            balanceIndicator.setStyle(unicash.ui.StyleSheet.TEXT_FILL_RED);
        } else {
            balanceIndicator.setStyle(unicash.ui.StyleSheet.TEXT_FILL_GREEN);
        }
    }

}
