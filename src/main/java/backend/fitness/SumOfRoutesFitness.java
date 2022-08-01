package backend.fitness;

import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.HashMap;

public class SumOfRoutesFitness implements IFitness {

    @Override
    public double computeFitness(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, ArrayList<Customer>> truckRoutes = FitnessHelper.computeTruckRoutes(genotype);
        HashMap<DeliveryTruck, Double> routeLenghts = FitnessHelper.computeRouteLengths(truckRoutes);

        return routeLenghts.values().stream().reduce(0.0, Double::sum);
    }

    @Override
    public String toString() {
        return "SumOfRoutesFitness";
    }
}
