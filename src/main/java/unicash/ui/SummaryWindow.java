package unicash.ui;

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


    /**
     * Creates a new SummaryWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public SummaryWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new SummaryWindow.
     */
    public SummaryWindow() {
        this(new Stage());
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
//        logger.fine("Showing UniCash help page.");
//        getRoot().show();
//        getRoot().centerOnScreen();

//        getStage(getRoot()).show();

        Stage root = getRoot();
        setPieChart(root);
        root.show();
    }

    public void setPieChart(Stage stage) {
//        Scene scene = new Scene(new Group());
//        stage.setTitle("Imported Fruits");
//        stage.setWidth(500);
//        stage.setHeight(500);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Grapefruit", 13),
                        new PieChart.Data("Oranges", 25),
                        new PieChart.Data("Plums", 10),
                        new PieChart.Data("Pears", 22),
                        new PieChart.Data("Apples", 30));
//        pieChart = new PieChart();

        pieChart.getData().clear();

        pieChart.getData().addAll(pieChartData);
//        pieChart.setTitle("Your Expenses");

//        ((Group) scene.getRoot()).getChildren().add(pieChart);
//        stage.setScene(scene);

//        pieChart.setLabelLineLength(10);
//        pieChart.setLegendSide(Side.LEFT);

//        final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");

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


