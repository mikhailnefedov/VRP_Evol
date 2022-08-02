package backend.fitness;

import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Gives a penalty to an individual if there is at least one truck that doesn't serve customers
 */
public class PenaltySumOfRoutesFitness implements IFitness {

    private final int PENALTY_FACTOR = 15;

    @Override
    public double computeFitness(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, Double> routeLengths = FitnessHelper.computeRouteLengths(genotype);
        double maxRouteLength = routeLengths.values().stream().max(Double::compare).get();

        return routeLengths.values().stream().reduce(0.0, (a, b) -> {
            if (b == 0.0) {
                return a + maxRouteLength * PENALTY_FACTOR;
            } else {
                return a + b;
            }
        });
    }

    @Override
    public String toString() {
        return "PenaltySumOfRoutesFitness";
    }
}
