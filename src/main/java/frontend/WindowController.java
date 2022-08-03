package frontend;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.Genome;
import backend.orchestration.OrchestrationParameters;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.ArrayList;

public class WindowController {

    @FXML
    private LineChart<Integer, Double> fitnessGraph;
    private static XYChart.Series<Integer, Double> fitnessData;
    @FXML
    private ScatterChart<Double, Double> scatterChart;
    private static XYChart.Series<Double, Double> headquarters;
    @FXML
    private Label bestFitnessValue;
    private static SimpleStringProperty bestFitnessValueProperty;
    @FXML
    private Label sumOfRouteLengthsValue;
    private static SimpleStringProperty sumOfRouteLengthsProperty;

    private static ArrayList<XYChart.Series<Double, Double>> routes;
    private final int MAX_DELIVERYTRUCKS = 50;   // bad design choice, can only show 50 delivery routes

    public WindowController() {

    }

    @FXML
    protected void initialize() {
        initializeFitnessGraph();
        initializeFitnessProperties();
        initializeScatterChart();
    }

    private void initializeFitnessGraph() {
        fitnessData = new XYChart.Series<>();
        fitnessGraph.getData().add(fitnessData);
    }

    private void initializeFitnessProperties() {
        bestFitnessValueProperty = new SimpleStringProperty();
        bestFitnessValue.textProperty().bind(bestFitnessValueProperty);
        sumOfRouteLengthsProperty = new SimpleStringProperty();
        sumOfRouteLengthsValue.textProperty().bind(sumOfRouteLengthsProperty);
    }

    private void initializeScatterChart() {
        headquarters = new XYChart.Series<>();
        scatterChart.getData().addAll(headquarters);
        routes = new ArrayList<>();
        for (int i = 0; i < MAX_DELIVERYTRUCKS; i++) {
            XYChart.Series<Double, Double> route = new XYChart.Series<>();
            routes.add(route);
        }
        scatterChart.getData().addAll(routes);
    }

    public static void initializeGUIWithData(OrchestrationParameters parameters) {
        Tuple<Double, Double> hqCoordinates = parameters.getData().getFleetHQCoordinates();
        headquarters.getData().add(new XYChart.Data<>(hqCoordinates.getLeft(), hqCoordinates.getRight()));
    }

    public static void addDataPointToFitnessGraph(int generationNumber, double fitnessValue, double sumOfRouteLengths) {
        fitnessData.getData().add(new XYChart.Data<>(generationNumber, fitnessValue));
        bestFitnessValueProperty.set(String.valueOf(fitnessValue));
        sumOfRouteLengthsProperty.set(String.valueOf(sumOfRouteLengths));
    }

    public static void addRoutesToScatterChart(ArrayList<Genome> genotype) {
        clearRoutes();

        int routeNumber = 0;
        for (int i = 0; i < genotype.size(); i++) {
            if (genotype.get(i).getClass().equals(Customer.class)) {
                Customer c = (Customer) genotype.get(i);
                routes.get(routeNumber).getData().add(new XYChart.Data<>(c.getX(), c.getY()));
            } else {
                routeNumber++;
            }
        }
    }

    public static void clearGUI() {
        clearFitnessGraph();
        clearScatterChart();
    }

    private static void clearFitnessGraph() {
        fitnessData.getData().clear();
    }

    private static void clearScatterChart() {
        headquarters.getData().clear();
        clearRoutes();
    }

    private static void clearRoutes() {
        routes.forEach(s -> s.getData().clear());
    }
}
