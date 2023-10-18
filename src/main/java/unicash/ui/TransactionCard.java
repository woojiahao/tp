package unicash.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import unicash.model.transaction.Amount;
import unicash.model.transaction.Transaction;

/**
 * A UI component that displays information of a {@code Transaction}.
 */
public class TransactionCard extends UiPart<Region> {

    private static final String FXML = "TransactionCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Transaction transaction;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label amount;
    @FXML
    private Label dateTime;

    @FXML
    private Label transactionLocation;

    @FXML
    private Label categories;


    /**
     * Creates a {@code TransactionCard} with the given {@code Transaction} and index to display.
     * The transaction's polarity (i.e. direction) depends on whether it's an expense or an income.
     * All transactions are assumed to be made in dollars.
     */
    public TransactionCard(Transaction transaction, int displayedIndex) {
        super(FXML);
        this.transaction = transaction;
        String dollarTransactionWithDecimal = "$" + Amount.amountToDecimalString(transaction.getAmount());
        String transactionPolarity = transaction.getType().toString().equals("expense") ? "-" : "+";

        id.setText(displayedIndex + ". ");
        name.setText(transaction.getName().toString());
        amount.setText(transactionPolarity + dollarTransactionWithDecimal);
        dateTime.setText(transaction.getDateTime().toString());
        transactionLocation.setText(transaction.getLocation().toString());
        categories.setText(transaction.getCategories().toString());

    }


}
