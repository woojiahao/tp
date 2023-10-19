package unicash.ui;

import static unicash.ui.StyleSheet.FONT_STYLE_BOLD;
import static unicash.ui.StyleSheet.TEXT_FILL_BLACK;
import static unicash.ui.StyleSheet.TEXT_FILL_GREEN;
import static unicash.ui.StyleSheet.TEXT_FILL_RED;

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
        // TODO: Split class into multiple methods for easier styling of each element
        super(FXML);
        this.transaction = transaction;
        id.setText(displayedIndex + ". ");
        name.setText(transaction.getName().toString());
        dateTime.setText(transaction.getDateTime().toString());
        transactionLocation.setText(transaction.getLocation().toString());

        /*
         * For better presentation of the transaction amounts, instead of using the
         * toString method of the transaction amount directly, a dollar symbol is prepended
         * to the transaction, along with the polarity of the transaction depending on whether
         * it is an income or expense type, Accordingly, text fill styles are also used to
         * set the color of the amount Label text based on their type.
         */
        String dollarTransactionWithDecimal = "$" + Amount.amountToDecimalString(transaction.getAmount());
        String transactionType = transaction.getType().toString().toLowerCase();
        String transactionPolarity = transactionType.equals("expense") ? "-" : "+";
        String transactionCardString = transactionPolarity + dollarTransactionWithDecimal;
        amount.setText(transactionCardString);

        if (transactionPolarity.equals("-")) { // Set color to red or green depending on amt
            amount.setStyle(TEXT_FILL_RED);
        } else if (transactionPolarity.equals("+")) {
            amount.setStyle(TEXT_FILL_GREEN);
        } else {
            amount.setStyle(TEXT_FILL_BLACK);
        }

        /*
         * For better presentation of the transaction category, instead of using the
         * toString method of the UniqueCategoryList directly, the leading and trailing
         * square brackets are trimmed, and prepended with a "hashtag".
         */
        String categoriesToString = transaction.getCategories().toString();
        int categoriesToStringLength = categoriesToString.length();
        String trimmedCategoriesToString = categoriesToString
                .substring(1, categoriesToStringLength - 1);
        String categoriesToStringWithHashTag = "#" + trimmedCategoriesToString;
        categories.setText(categoriesToStringWithHashTag);
        categories.setStyle(FONT_STYLE_BOLD);

    }
}

