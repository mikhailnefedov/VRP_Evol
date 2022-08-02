package backend.mutation;

import backend.models.VRPIndividual;

import java.util.Random;

public abstract class BaseMutation implements IMutation {

    protected double mutationRate;

    @Override
    public VRPIndividual mutate(VRPIndividual individual) {
        double randomDouble = new Random().nextDouble();
        if (randomDouble < mutationRate) {
            individual = executeMutation(individual);
            individual.computeFitness();
        }
        return individual;
    }

    protected abstract VRPIndividual executeMutation(VRPIndividual individual);

    @Override
    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }
}
