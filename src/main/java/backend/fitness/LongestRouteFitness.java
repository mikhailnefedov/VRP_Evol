package backend.fitness;

import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The longest route of a truck is the fitness of the individual
 */
public class LongestRouteFitness implements IFitness {

    @Override
    public double computeFitness(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, Double> routeLengths = FitnessHelper.computeRouteLengths(genotype);

        return routeLengths.values().stream().max(Double::compare).get();
    }

    @Override
    public String toString() {
        return "LongestRouteFitness";
    }
}
