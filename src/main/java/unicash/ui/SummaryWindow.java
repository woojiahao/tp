package unicash.ui;

import java.util.HashMap;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import unicash.commons.core.LogsCenter;

public class SummaryWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2324s1-cs2103-t16-3.github.io/tp/";
    public static final String SUMMARY_MESSAGE = "Here is your summary:";

    private static final Logger logger = LogsCenter.getLogger(SummaryWindow.class);
    private static final String FXML = "SummaryWindow.fxml";

    @FXML
    private Label summaryMessage;

    @FXML
    private PieChart pieChart;

    @FXML
    private VBox summaryMessageContainer;

    private final HashMap<String, Double> expenseSummary;

    /**
     * Creates a new SummaryWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public SummaryWindow(Stage root, HashMap<String, Double> expenseSummary) {
        super(FXML, root);
        this.expenseSummary = expenseSummary;
    }

    /**
     * Creates a new SummaryWindow.
     */
    public SummaryWindow(HashMap<String, Double> expenseSummary) {
        this(new Stage(), expenseSummary);
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        Stage root = getRoot();
        setPieChart();
        root.show();
    }

    public void setPieChart() {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (HashMap.Entry<String, Double> entry : expenseSummary.entrySet()) {
            String category = entry.getKey();
            Double totalAmount = entry.getValue();
            pieChartData.add(new PieChart.Data(category, totalAmount));
        }

        pieChart.getData().clear();

        pieChart.getData().addAll(pieChartData);
        summaryMessageContainer.layout();

    }

    /**
     * Returns true if the summary window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the summary window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the summary window.
     */
    public void focus() {
        getRoot().requestFocus();
    }

    /**
     * Copies the URL to the user guide to the clipboard.
     */
    @FXML
    private void copyUrl() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(USER_GUIDE_URL);
        clipboard.setContent(url);
    }

}


