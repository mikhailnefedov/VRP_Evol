package backend.mutation;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.Random;

/**
 * Swaps 2 genomes of a VRPIndividual
 */
public class SwapMutation extends BaseMutation {

    protected VRPIndividual executeMutation(VRPIndividual individual) {
        int size = individual.getGenotype().size();
        Random random = new Random();
        int index1 = random.nextInt(size);
        int index2 = random.nextInt(size);

        Genome a = individual.getGenotype().get(index1);
        Genome b = individual.getGenotype().get(index2);

        individual.getGenotype().set(index1, b);
        individual.getGenotype().set(index2, a);

        return individual;
    }

    @Override
    public String toString() {
        return "SwapMutation";
    }

}
