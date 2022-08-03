package frontend;

import backend.crossover.EdgeCrossover;
import backend.crossover.ICrossover;
import backend.crossover.OrderCrossover;
import backend.data.*;
import backend.fitness.IFitness;
import backend.fitness.LongestRouteFitness;
import backend.fitness.PenaltySumOfRoutesFitness;
import backend.fitness.SumOfRoutesFitness;
import backend.mutation.IMutation;
import backend.mutation.InvertMutation;
import backend.mutation.SwapMutation;
import backend.orchestration.GeneticAlgorithmOrchestrator;
import backend.orchestration.OrchestrationParameters;
import backend.selection.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class OrchestrationParameterWindowController {

    @FXML
    private ComboBox<IMutation> mutationPicker;
    @FXML
    private TextField mutationRate;
    @FXML
    private ComboBox<ISelection> parentSelectionPicker;
    @FXML
    private ComboBox<ISelection> environmentSelectionPicker;
    @FXML
    private ComboBox<ICrossover> crossoverPicker;
    @FXML
    private ComboBox<IFitness> fitnessPicker;
    @FXML
    private TextField generationCount;
    @FXML
    private TextField deliveryTruckCount;
    @FXML
    private TextField populationSize;
    @FXML
    private TextField parentsCount;
    @FXML
    private TextField childrenCount;

    public OrchestrationParameterWindowController() {
    }

    @FXML
    protected void initialize() {
        ArrayList<ComboBox> comboBoxes = new ArrayList<>(
                List.of(parentSelectionPicker, environmentSelectionPicker, mutationPicker, crossoverPicker, fitnessPicker));

        parentSelectionPicker.getItems().addAll(new RankingBasedSelection(), new TournamentSelection());

        environmentSelectionPicker.getItems().addAll(new QRound2FoldTournamentSelection(), new BestXSelection());

        mutationPicker.getItems().addAll(new SwapMutation(), new InvertMutation());

        crossoverPicker.getItems().addAll(new OrderCrossover(), new EdgeCrossover());

        fitnessPicker.getItems().addAll(new LongestRouteFitness(), new SumOfRoutesFitness(), new PenaltySumOfRoutesFitness());

        comboBoxes.forEach(c -> c.getSelectionModel().select(0));
    }

    public void startGeneticAlgorithm() {
        OrchestrationParameters parameters = createOrchestrationParameters();
        GeneticAlgorithmOrchestrator geneticAlgorithmOrchestrator = new GeneticAlgorithmOrchestrator(parameters);

        WindowController.clearElements();
        WindowController.initializeScatterChart(parameters.getData(), parameters.getGenerationCount());
        geneticAlgorithmOrchestrator.executeGeneticAlgorithm();
    }

    private OrchestrationParameters createOrchestrationParameters() {
        OrchestrationParameters parameters = new OrchestrationParameters();

        ISelection parentSelection = parentSelectionPicker.getValue();
        parentSelection.setSelectionCount(Integer.parseInt(parentsCount.getText()));
        parameters.setParentSelection(parentSelection);

        ISelection environmentSelection = environmentSelectionPicker.getValue();
        environmentSelection.setSelectionCount(Integer.parseInt(populationSize.getText()));
        parameters.setEnvironmentSelection(parentSelection);

        parameters.setCrossover(crossoverPicker.getValue());

        IMutation mutationType = mutationPicker.getValue();
        mutationType.setMutationRate(Double.parseDouble(mutationRate.getText()));
        parameters.setMutation(mutationType);

        parameters.setFitness(fitnessPicker.getValue());

        parameters.setGenerationCount(Integer.parseInt(generationCount.getText()));
        parameters.setParentsCount(Integer.parseInt(parentsCount.getText()));
        parameters.setChildrenCount(Integer.parseInt(childrenCount.getText()));
        parameters.setDeliveryTruckCount(Integer.parseInt(deliveryTruckCount.getText()));
        parameters.setPopulationSize(Integer.parseInt(populationSize.getText()));

        parameters.setData(new Cluster4());

        return parameters;
    }
}
