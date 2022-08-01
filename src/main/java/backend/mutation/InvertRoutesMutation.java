package backend.mutation;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;
import backend.models.VRPIndividual;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class InvertRoutesMutation implements IMutation {

    private double mutationRate;

    @Override
    public VRPIndividual mutate(VRPIndividual individual) {
        double randomDouble = new Random().nextDouble();
        if (randomDouble < mutationRate) {
            individual = MutationHelper.doInvertMutationOnWholeGenotype(individual);
        }
        if (new Random().nextDouble() < mutationRate) {
            individual = doInvertMutationOnRoute(individual);
        }

        individual.computeFitness();

        return individual;
    }

    private VRPIndividual doInvertMutationOnRoute(VRPIndividual individual) {
        Random random = new Random();
        ArrayList<Tuple<Integer, Integer>> routesIndices = getIndicesOfRoutes(individual);
        int randomRoute = random.nextInt(routesIndices.size());
        Tuple<Integer, Integer> routeIndices = routesIndices.get(randomRoute);

        int randomIndex1 = random.nextInt(routeIndices.getRight() - routeIndices.getLeft()) + routeIndices.getLeft();
        int randomIndex2 = random.nextInt(routeIndices.getRight() - routeIndices.getLeft()) + routeIndices.getLeft();

        if (randomIndex1 > randomIndex2) {
            int tmp = randomIndex1;
            randomIndex1 = randomIndex2;
            randomIndex2 = tmp;
        }

        while (randomIndex1 < randomIndex2) {
            Genome tmp = individual.getGenotype().get(randomIndex1);
            individual.getGenotype().set(randomIndex1, individual.getGenotype().get(randomIndex2));
            individual.getGenotype().set(randomIndex2, tmp);
            randomIndex1++;
            randomIndex2--;
        }

        return individual;
    }

    public ArrayList<Tuple<Integer, Integer>> getIndicesOfRoutes(VRPIndividual individual) {
        ArrayList<Genome> genotype = individual.getGenotype();

        ArrayList<Integer> deliveryTruckIndices = new ArrayList<>();
        for (int i = 0; i < genotype.size(); i++) {
            if (genotype.get(i).getClass().equals(DeliveryTruck.class)) {
                deliveryTruckIndices.add(i);
            }
        }
        deliveryTruckIndices.add(genotype.size());

        ArrayList<Tuple<Integer, Integer>> routesIndices = new ArrayList<>();
        int startIndex = 0;
        for (int deliveryTruckIndex : deliveryTruckIndices) {
            routesIndices.add(new Tuple<>(startIndex, deliveryTruckIndex - 1));
            startIndex = deliveryTruckIndex + 1;
        }

         return routesIndices.stream()
                .filter(t -> t.getRight() > t.getLeft())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public String toString() {
        return "InvertRoutesMutation";
    }
}
