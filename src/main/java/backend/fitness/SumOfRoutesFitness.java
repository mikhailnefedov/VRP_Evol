package backend.fitness;

import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The sum of the truck routes is the fitness of the indiviudal
 */
public class SumOfRoutesFitness implements IFitness {

    @Override
    public double computeFitness(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, Double> routeLengths = FitnessHelper.computeRouteLengths(genotype);
        return routeLengths.values().stream().reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {
        return "SumOfRoutesFitness";
    }
}
