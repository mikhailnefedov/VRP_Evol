package backend.mutation;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.Random;


public class SwapMutation implements IMutation {

    private double mutationRate;

    public SwapMutation() {

    }

    @Override
    public VRPIndividual mutate(VRPIndividual individual) {
        double randomDouble = new Random().nextDouble();

        if (randomDouble < mutationRate) {
            individual = doSwapMutation(individual);
        }

        individual.computeFitness();

        return individual;
    }

    private VRPIndividual doSwapMutation(VRPIndividual individual) {
        int size = individual.getGenotype().size();
        Random random = new Random();
        int randomIndex1 = random.nextInt(size);
        int randomIndex2 = random.nextInt(size);

        Genome a = individual.getGenotype().get(randomIndex1);
        Genome b = individual.getGenotype().get(randomIndex2);

        individual.getGenotype().set(randomIndex1, b);
        individual.getGenotype().set(randomIndex2, a);

        return individual;
    }

    @Override
    public String toString() {
        return "SwapMutation";
    }

    @Override
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
}
