package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.income.Income;

/**
 * Panel containing the list of persons.
 */
public class IncomeListPanel extends UiPart<Region> {
    private static final String FXML = "PersonListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(IncomeListPanel.class);

    @FXML
    private ListView<Income> personListView;

    /**
     * Creates a {@code IncomeListPanel} with the given {@code ObservableList}.
     */
    public IncomeListPanel(ObservableList<Income> incomeList) {
        super(FXML);
        personListView.setItems(incomeList);
        personListView.setCellFactory(listView -> new IncomeListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Income} using a {@code IncomeCard}.
     */
    class IncomeListViewCell extends ListCell<Income> {
        @Override
        protected void updateItem(Income income, boolean empty) {
            super.updateItem(income, empty);

            if (empty || income == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new IncomeCard(income, getIndex() + 1).getRoot());
            }
        }
    }

}
