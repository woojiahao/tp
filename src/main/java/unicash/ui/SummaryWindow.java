package unicash.ui;

import java.time.YearMonth;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import unicash.commons.core.LogsCenter;

/**
 * Controller for the pop-up summary window.
 */
public class SummaryWindow extends UiPart<Stage> {

    public static final String USER_GUIDE_URL = "https://ay2324s1-cs2103-t16-3.github.io/tp/";
    public static final String SUMMARY_MESSAGE = "Expense Summary:";
    public static final String SUMMARY_MESSAGE_WHEN_NO_TRANSACTIONS = "There are no transactions available";

    private static final Logger logger = LogsCenter.getLogger(SummaryWindow.class);
    private static final String FXML = "SummaryWindow.fxml";
    private static final int MAX_DISPLAYED_CATEGORIES = 10;
    private static final int MAX_DISPLAYED_YEAR_MONTHS = 12;
    private static final YearMonth EARLIEST_YEAR_MONTH = YearMonth.now().minusMonths(MAX_DISPLAYED_YEAR_MONTHS);

    @FXML
    private Label summaryMessage;

    @FXML
    private PieChart pieChart;

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private VBox summaryMessageContainer;


    /**
     * Creates a new SummaryWindow.
     *
     * @param root Stage to use as the root of the HelpWindow.
     */
    public SummaryWindow(Stage root) {
        super(FXML, root);
        summaryMessage.setText(SUMMARY_MESSAGE);
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
    public void show(HashMap<String, Double> expenseSummary) {
        setPieChart(expenseSummary);
        if (!expenseSummary.isEmpty()) {
            Stage root = getRoot();
            root.show();
        }
    }

    /**
     * Updates the pie chart with expense data
     * Note that only MAX_DISPLAYED_CATEGORIES number of categories will be displayed on the pie chart
     */
    public void setPieChart(HashMap<String, Double> expenseSummary) {
        pieChart.getData().clear();

        if (expenseSummary.isEmpty()) {
            summaryMessage.setText(SUMMARY_MESSAGE_WHEN_NO_TRANSACTIONS);
        } else {
            summaryMessage.setText(SUMMARY_MESSAGE);
            ObservableList<PieChart.Data> pieChartData = expenseSummary.entrySet().stream()
                    .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue()))
                    .sorted(Comparator.comparing(PieChart.Data::getPieValue).reversed())
                    .limit(MAX_DISPLAYED_CATEGORIES)
                    .collect(Collectors.toCollection(FXCollections::observableArrayList));

            pieChart.getData().setAll(pieChartData);
            summaryMessageContainer.layout();
        }
    }

    /**
     * Updates the line chart with expense data
     * Note that only MAX_DISPLAYED_YEAR_MONTHS number of year-months will be displayed on the line chart
     */
    public void setLineGraph(HashMap<YearMonth, Double> expenseSummary) {
        lineChart.getData().clear();

        fillExpenseSummary(expenseSummary);

        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<String, Double>> lineChartData = expenseSummary.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getKey().toString()))
                .filter(entry -> isValidYearMonth(entry.getKey()))
                .map(entry -> new XYChart.Data<>(entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        series.getData().addAll(lineChartData);

        lineChart.getData().add(series);
        summaryMessageContainer.layout();
    }

    /**
     * Returns true if yearMonth is between now and EARLIEST_YEAR_MONTH, and false otherwise.
     * Note: This function also returns true if yearMonth is the same as the year-month of now
     * or EARLIEST_YEAR_MONTH
     */
    private static boolean isValidYearMonth(YearMonth yearMonth) {
        boolean afterMostRecentYearMonth = yearMonth.compareTo(EARLIEST_YEAR_MONTH) >= 0;
        boolean beforeCurrentYearMonth = yearMonth.compareTo(YearMonth.now()) <= 0;
        return afterMostRecentYearMonth & beforeCurrentYearMonth;
    }

    private void fillExpenseSummary(HashMap<YearMonth, Double> expenseSummary) {
        YearMonth endYearMonth = YearMonth.now();

        YearMonth currentYearMonth = EARLIEST_YEAR_MONTH;
        while (currentYearMonth.isBefore(endYearMonth)) {
            if (!expenseSummary.containsKey(currentYearMonth)) {
                expenseSummary.put(currentYearMonth, 0.0);
            }
            currentYearMonth = currentYearMonth.plusMonths(1);
        }
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



}


