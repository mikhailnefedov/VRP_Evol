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
import backend.mutation.InvertRoutesMutation;
import backend.mutation.SwapMutation;
import backend.orchestration.GeneticAlgorithmOrchestrator;
import backend.orchestration.OrchestrationParameters;
import backend.selection.ISelection;
import backend.selection.RouletteSelection;
import backend.selection.TopXSelection;
import backend.selection.TournamentSelection;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class OrchestrationParameterWindowController {

    @FXML
    private ComboBox<IMutation> mutationPicker;
    @FXML
    private TextField mutationRate;
    @FXML
    private ComboBox<ISelection> selectionPicker;
    @FXML
    private ComboBox<ICrossover> crossoverPicker;
    @FXML
    private TextField crossoverRate;
    @FXML
    private ComboBox<IFitness> fitnessPicker;
    @FXML
    private TextField generationCount;
    @FXML
    private TextField deliveryTruckCount;
    @FXML
    private ComboBox<IData> mapPicker;
    @FXML
    private TextField populationCount;
    @FXML
    private TextField parentsCount;

    public OrchestrationParameterWindowController() {

    }

    @FXML
    protected void initialize() {
        //selection
        selectionPicker.getItems().addAll(new TopXSelection(), new RouletteSelection(), new TournamentSelection());
        selectionPicker.getSelectionModel().select(0);

        //mutation
        mutationPicker.getItems().addAll(new SwapMutation(), new InvertMutation(), new InvertRoutesMutation());
        mutationPicker.getSelectionModel().select(0);

        //crossover
        crossoverPicker.getItems().addAll(new OrderCrossover(), new EdgeCrossover());
        crossoverPicker.getSelectionModel().select(0);

        //fitness
        fitnessPicker.getItems().addAll(new LongestRouteFitness(), new SumOfRoutesFitness(), new PenaltySumOfRoutesFitness());
        fitnessPicker.getSelectionModel().select(0);

        //map
        mapPicker.getItems().addAll(new DebugProblem(), new TestProblem(), new AugeratSetAN32(), new Cluster4());
        mapPicker.getSelectionModel().select(0);
    }

    public void startGeneticAlgorithm() {
        OrchestrationParameters parameters = createOrchestrationParameters();
        GeneticAlgorithmOrchestrator geneticAlgorithmOrchestrator = new GeneticAlgorithmOrchestrator(parameters);

        WindowController.clearElements();
        WindowController.initializeScatterChart(parameters.getMapType(), parameters.getGenerationCount());
        geneticAlgorithmOrchestrator.executeGeneticAlgorithm();
    }

    private OrchestrationParameters createOrchestrationParameters() {
        OrchestrationParameters parameters = new OrchestrationParameters();

        //selection
        ISelection selectionType = selectionPicker.getValue();
        selectionType.setSelectionCount(Integer.parseInt(parentsCount.getText()));
        parameters.setSelectionType(selectionType);

        //crossover
        parameters.setCrossoverType(crossoverPicker.getValue());
        parameters.setCrossoverRate(Double.parseDouble(crossoverRate.getText()));

        //mutation
        IMutation mutationType = mutationPicker.getValue();
        mutationType.setMutationRate(Double.parseDouble(mutationRate.getText()));
        parameters.setMutationType(mutationType);

        parameters.setGenerationCount(Integer.parseInt(generationCount.getText()));
        parameters.setParentsCount(Integer.parseInt(parentsCount.getText()));
        parameters.setDeliveryTruckCount(Integer.parseInt(deliveryTruckCount.getText()));

        //map
        parameters.setMapType(mapPicker.getValue());

        parameters.setPopulationCount(Integer.parseInt(populationCount.getText()));

        parameters.setFitnessType(fitnessPicker.getValue());

        return parameters;
    }
}
