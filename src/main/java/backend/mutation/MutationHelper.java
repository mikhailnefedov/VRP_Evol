package backend.mutation;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.Random;

public class MutationHelper {

    public static VRPIndividual doInvertMutationOnWholeGenotype(VRPIndividual individual) {
        int size = individual.getGenotype().size();
        Random random = new Random();
        int randomIndex1 = random.nextInt(size);
        int randomIndex2 = random.nextInt(size);

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
}
