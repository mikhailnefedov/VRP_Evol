package backend.orchestration;

import backend.crossover.ICrossover;
import backend.data.IData;
import backend.fitness.FitnessHelper;
import backend.fitness.SumOfRoutesFitness;
import backend.models.DeliveryTruck;
import backend.models.Genome;
import backend.models.VRPIndividual;
import backend.mutation.IMutation;
import backend.selection.ISelection;
import frontend.WindowController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class GeneticAlgorithmOrchestrator {

    private ISelection selection;
    private ICrossover crossover;
    private IMutation mutation;
    private final int generationCount;
    private final int deliveryTruckCount;
    private final IData data;
    private final int populationCount;
    private final int parentsCount;
    private final int childrenCount;

    private Random random;
    private Comparator<VRPIndividual> compareByFitness;

    public GeneticAlgorithmOrchestrator(OrchestrationParameters parameters) {
        this.selection = parameters.getParentSelectionType();
        this.crossover = parameters.getCrossoverType();
        this.mutation = parameters.getMutationType();
        this.generationCount = parameters.getGenerationCount();
        this.deliveryTruckCount = parameters.getDeliveryTruckCount();
        this.data = parameters.getMapType();

        this.populationCount = parameters.getPopulationSize();
        this.parentsCount = parameters.getParentsCount();
        this.childrenCount = populationCount - parentsCount;

        FitnessHelper.setData(data);
        VRPIndividual.setFitness(parameters.getFitnessType());

        random = new Random();
        compareByFitness = Comparator.comparingDouble(VRPIndividual::getFitness);
    }

    public void executeGeneticAlgorithm() {

        ArrayList<VRPIndividual> generation = createStartGeneration();
        addBestFitnessToGUI(0, generation);
        int t = 1;
        while (t < generationCount) {
            ArrayList<VRPIndividual> parents = selection.select(generation);
            generation = new ArrayList<>();
            for (int i = 0; i < childrenCount; i++) {
                int index1 = random.nextInt(parentsCount);
                int index2 = random.nextInt(parentsCount);
                VRPIndividual child = crossover.crossover(parents.get(index1), parents.get(index2));
                child = mutation.mutate(child);
                generation.add(child);
            }
            addBestFitnessToGUI(t, generation);
            t++;
        }
        addRoutesToGUI(generation);
        printBestIndividual(generation);
    }

    private void printBestIndividual(ArrayList<VRPIndividual> generation) {
        System.out.println("######RESULT:");
        VRPIndividual bestIndividual = getBestIndividual(generation);
        bestIndividual.getGenotype().forEach(g -> System.out.println(g.toString() + ", "));
        System.out.println(bestIndividual.getFitness());
        System.out.println();
        System.out.println("-------------");
    }

    private void addBestFitnessToGUI(int generationNumber, ArrayList<VRPIndividual> generation) {
        VRPIndividual bestIndividual = getBestIndividual(generation);
        double sumOfRouteFitness = new SumOfRoutesFitness().computeFitness(bestIndividual.getGenotype());
        WindowController.addDataPointToFitnessGraph(generationNumber, bestIndividual.getFitness(), sumOfRouteFitness);
    }

    private void addRoutesToGUI(ArrayList<VRPIndividual> generation) {
        VRPIndividual bestIndividual = getBestIndividual(generation);
        WindowController.addRoutesToScatterChart(bestIndividual.getGenotype());
    }

    private VRPIndividual getBestIndividual(ArrayList<VRPIndividual> generation) {
        return generation.stream().min(compareByFitness).get();
    }

    private ArrayList<VRPIndividual> createStartGeneration() {
        ArrayList<VRPIndividual> startGeneration = new ArrayList<>();
        VRPIndividual bluePrintIndividual = createRandomIndividual();
        for (int i = 0; i < populationCount; i++) {
            startGeneration.add(createRandomCopyOfIndividual(bluePrintIndividual));
        }
        return startGeneration;
    }

    private VRPIndividual createRandomCopyOfIndividual(VRPIndividual individual) {
        ArrayList<Genome> genotypeToCopy = individual.getGenotype();

        ArrayList<Genome> genotype = new ArrayList<>(genotypeToCopy);
        Collections.shuffle(genotype);

        return new VRPIndividual(genotype);
    }

    private VRPIndividual createRandomIndividual() {
        ArrayList<Genome> customers = data.getCustomers();
        int customerCount = customers.size();

        ArrayList<Genome> deliveryTrucks = new ArrayList<>();
        for (int i = 0; i < deliveryTruckCount - 1; i++) {
            DeliveryTruck truck = new DeliveryTruck(customerCount, false);
            deliveryTrucks.add(truck);
            customerCount++;
        }

        customers.addAll(deliveryTrucks);

        Collections.shuffle(customers);

        return new VRPIndividual(customers);
    }
}
