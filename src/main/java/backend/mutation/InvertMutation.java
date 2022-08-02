package backend.mutation;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.Random;

/**
 * Inverts a genome sublist of a VRPIndividual
 */
public class InvertMutation extends BaseMutation {

    protected VRPIndividual executeMutation(VRPIndividual individual) {
        int size = individual.getGenotype().size();
        Random random = new Random();
        int left = random.nextInt(size);
        int right = random.nextInt(size);

        if (left > right) {
            int tmp = left;
            left = right;
            right = tmp;
        }

        while (left < right) {
            Genome tmp = individual.getGenotype().get(left);
            individual.getGenotype().set(left, individual.getGenotype().get(right));
            individual.getGenotype().set(right, tmp);
            left++;
            right--;
        }

        return individual;
    }

    @Override
    public String toString() {
        return "InvertMutation";
    }

}
