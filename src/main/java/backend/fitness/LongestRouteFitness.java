package backend.fitness;

import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.HashMap;

public class LongestRouteFitness implements IFitness {

    @Override
    public double computeFitness(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, ArrayList<Customer>> truckRoutes = FitnessHelper.computeTruckRoutes(genotype);
        HashMap<DeliveryTruck, Double> routeLenghts = FitnessHelper.computeRouteLengths(truckRoutes);

        double maxRouteLength = routeLenghts.values().stream().max(Double::compare).get();
        return maxRouteLength;
    }

    @Override
    public String toString() {
        return "LongestRouteFitness";
    }
}
