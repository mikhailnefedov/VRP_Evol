package backend.crossover;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Random;

/**
 * Models the order crossover on a VRPIndividual
 */
public class OrderCrossover implements ICrossover {

    @Override
    public VRPIndividual crossover(VRPIndividual father, VRPIndividual mother) {
        int index = getRandomIndex(father);
        ArrayList<Genome> childGenotype = startChildGenotypeCreation(index, father);
        childGenotype = finishChildGenotypeCreation(childGenotype, mother);
        return new VRPIndividual(childGenotype);
    }

    /**
     * Takes the genomes (0 to index) from the father
     */
    private ArrayList<Genome> startChildGenotypeCreation(int index, VRPIndividual father) {
        return new ArrayList<>(father.getGenotype().subList(0, index));
    }

    /**
     * Takes the remaining genomes that aren't in the child yet from the mother (in order of appearance in mother)
     */
    private ArrayList<Genome> finishChildGenotypeCreation(ArrayList<Genome> childGenotype, VRPIndividual mother) {
        for (Genome g : mother.getGenotype()) {
            if (!childGenotype.contains(g)) {
                childGenotype.add(g);
            }
        }
        return childGenotype;
    }

    private int getRandomIndex(VRPIndividual individual) {
        Random random = new Random();
        return random.nextInt(individual.getGenotype().size());
    }

    @Override
    public String toString() {
        return "OrderCrossover";
    }
}
