package frontend;

import backend.data.IData;
import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.Genome;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WindowController {

    @FXML
    private LineChart<Integer, Double> fitnessGraph;
    private static XYChart.Series<Integer, Double> fitnessDataSeries;
    @FXML
    private ScatterChart<Double, Double> scatterChart;
    private static XYChart.Series<Double, Double> scatterCustomerSeries;
    private static XYChart.Series<Double, Double> hqSeries;
    @FXML
    private Label bestFitnessValue;
    private static SimpleStringProperty bestFitnessValueProperty;
    @FXML
    private Label sumOfRouteLengthsValue;
    private static SimpleStringProperty sumOfRouteLengthsProperty;

    private static ArrayList<XYChart.Series<Double, Double>> routesSeries;
    private final int MAX_DELIVERYTRUCKS = 50;

    public WindowController() {

    }

    @FXML
    protected void initialize() {
        fitnessDataSeries = new XYChart.Series<>();
        fitnessGraph.getData().add(fitnessDataSeries);

        scatterCustomerSeries = new XYChart.Series<>();
        hqSeries = new XYChart.Series<>();
        scatterChart.getData().addAll(scatterCustomerSeries, hqSeries);

        bestFitnessValueProperty = new SimpleStringProperty();
        bestFitnessValue.textProperty().bind(bestFitnessValueProperty);

        sumOfRouteLengthsProperty = new SimpleStringProperty();
        sumOfRouteLengthsValue.textProperty().bind(sumOfRouteLengthsProperty);

        routesSeries = new ArrayList<>();
        for (int i = 0; i < MAX_DELIVERYTRUCKS; i++) {
            XYChart.Series<Double, Double> routeSeries = new XYChart.Series<>();
            routesSeries.add(routeSeries);
        }
        scatterChart.getData().addAll(routesSeries);
    }

    public static void addDataPointToFitnessGraph(int generationNumber, double fitnessValue, double sumOfRouteLengths) {
        fitnessDataSeries.getData().add(new XYChart.Data<>(generationNumber, fitnessValue));
        bestFitnessValueProperty.set(String.valueOf(fitnessValue));
        sumOfRouteLengthsProperty.set(String.valueOf(sumOfRouteLengths));
    }

    public static void addRoutesToScatterChart(ArrayList<Genome> genotype) {
        clearRoutes();

        int routeNumber = 0;
        for (int i = 0; i < genotype.size(); i++) {
            if (genotype.get(i).getClass().equals(Customer.class)) {
                Customer c = (Customer) genotype.get(i);
                routesSeries.get(routeNumber).getData().add(new XYChart.Data<>(c.getX(), c.getY()));
            } else {
                routeNumber++;
            }
        }
    }

    public static void clearElements() {
        clearFitnessGraph();
        clearScatterChart();
    }

    public static void clearFitnessGraph() {
        fitnessDataSeries.getData().clear();
    }

    public static void initializeScatterChart(IData data, int generationCount) {
        ArrayList<Genome> genomes = data.getCustomers();
        List<XYChart.Data<Double, Double>> points = genomes.stream()
                .map(g -> (Customer) g)
                .map(c -> new XYChart.Data<>(c.getX(), c.getY()))
                .collect(Collectors.toList());
        scatterCustomerSeries.getData().addAll(points);

        Tuple<Double, Double> hqCoordinates = data.getFleetHQCoordinates();
        hqSeries.getData().add(new XYChart.Data<>(hqCoordinates.getLeft(), hqCoordinates.getRight()));
    }

    public static void clearScatterChart() {
        scatterCustomerSeries.getData().clear();
        hqSeries.getData().clear();
        clearRoutes();
    }

    private static void clearRoutes() {
        routesSeries.forEach(s -> s.getData().clear());
    }
}
