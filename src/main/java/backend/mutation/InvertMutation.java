package backend.mutation;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.Random;

public class InvertMutation implements IMutation {

    private double mutationRate;

    @Override
    public VRPIndividual mutate(VRPIndividual individual) {
        double randomDouble = new Random().nextDouble();
        if (randomDouble < mutationRate) {
            individual = MutationHelper.doInvertMutationOnWholeGenotype(individual);
        }

        individual.computeFitness();

        return individual;
    }

    @Override
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public String toString() {
        return "InvertMutation";
    }
}
