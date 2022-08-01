package backend.fitness;

import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.HashMap;

public class PenaltySumOfRoutesFitness implements IFitness {
    @Override
    public double computeFitness(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, ArrayList<Customer>> truckRoutes = FitnessHelper.computeTruckRoutes(genotype);
        HashMap<DeliveryTruck, Double> routeLenghts = FitnessHelper.computeRouteLengths(truckRoutes);

        double maxRouteLength = routeLenghts.values().stream().max(Double::compare).get();

        return routeLenghts.values().stream().reduce(0.0, (a, b) -> {
            if (b == 0.0) {
                return a + maxRouteLength * 0.25;
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
