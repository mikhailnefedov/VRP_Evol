package backend.selection;

import backend.helper.Tuple;
import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class RouletteSelection extends BaseSelection {

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> individuals) {
        ArrayList<Double> probabilityRange = computeProbabilityRanges(individuals);
        Random random = new Random();
        ArrayList<VRPIndividual> parents = new ArrayList<>();

        while (parents.size() < selectionCount) {
            double randomProbability = random.nextDouble();
            for (int i = 0; i < individuals.size(); i++) {
                if (isProbabilityInRange(randomProbability, probabilityRange.get(i), probabilityRange.get(i + 1))) {
                    parents.add(individuals.get(i));
                }
            }
        }
        return parents;
    }

    private boolean isProbabilityInRange(double probability, double lowerBound, double upperBound) {
        return probability >= lowerBound && probability <= upperBound;
    }

    private ArrayList<Double> computeProbabilityRanges(ArrayList<VRPIndividual> generation) {
        ArrayList<Double> probabilityRange = new ArrayList<>();
        double sumOfProbabilities = 0.0;
        double totalRouteLength = computeTotalLengthOfRoutes(generation);
        ArrayList<Tuple<Double, Double>> probabilityLinks = getProbabilityLinks(generation);
        probabilityRange.add(0.0);
        for (VRPIndividual individual : generation) {
            double probability = individual.getFitness() / totalRouteLength;
            Tuple<Double, Double> tuple = probabilityLinks.stream().filter(t -> t.getLeft() == probability).findFirst().get();
            double inverseProbability = tuple.getRight();
            probabilityLinks.remove(tuple);

            sumOfProbabilities += inverseProbability;
            probabilityRange.add(sumOfProbabilities);
        }
        probabilityRange.set(probabilityRange.size() - 1, 1.0);

        return probabilityRange;
    }

    private ArrayList<Tuple<Double, Double>> getProbabilityLinks(ArrayList<VRPIndividual> generation) {
        ArrayList<Double> probabilities = new ArrayList<>();
        double totalRouteLength = computeTotalLengthOfRoutes(generation);
        for (VRPIndividual individual : generation) {
            double probability = individual.getFitness() / totalRouteLength;
            probabilities.add(probability);
        }
        Collections.sort(probabilities);
        return createInverseProbabilityLink(probabilities);
    }

    private ArrayList<Tuple<Double, Double>> createInverseProbabilityLink(ArrayList<Double> probabilities) {
        ArrayList<Tuple<Double, Double>> links = new ArrayList<>();
        for (int i = 0; i < probabilities.size(); i++) {
            double probability = probabilities.get(i);
            double inverseProbability = probabilities.get(probabilities.size() - 1 - i);
            links.add(new Tuple<>(probability, inverseProbability));
        }
        return links;
    }

    private double computeTotalLengthOfRoutes(ArrayList<VRPIndividual> generation) {
        return generation.stream().map(VRPIndividual::getFitness).reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {
        return "RouletteSelection";
    }
}
