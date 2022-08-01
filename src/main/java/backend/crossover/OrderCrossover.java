package backend.crossover;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Random;

public class OrderCrossover implements ICrossover {

    @Override
    public VRPIndividual crossover(VRPIndividual father, VRPIndividual mother) {
        Random random = new Random();
        int randomIndex = random.nextInt(father.getGenotype().size());
        ArrayList<Genome> genotype = new ArrayList<>(father.getGenotype().subList(0, randomIndex));
        for (Genome g : mother.getGenotype()) {
            if (!genotype.contains(g)) {
                genotype.add(g);
            }
        }
        return new VRPIndividual(genotype);
    }

    @Override
    public String toString() {
        return "OrderCrossover";
    }
}
