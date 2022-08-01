package backend.selection;

import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Random;

/**
 * Best of Selection. There are q tournaments for each selection in which always the best individual of these
 * q tournaments will be selected
 */
public class TournamentSelection extends BaseSelection{

    private final int COUNT_OF_TOURNAMENTS = 5; //q

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> individuals) {
        Random random = new Random();
        ArrayList<VRPIndividual> parents = new ArrayList<>();

        for (int i = 0; i < selectionCount; i++) {
            int index = random.nextInt(individuals.size());
            VRPIndividual currentWinner = individuals.get(index);
            for (int j = 0; j < COUNT_OF_TOURNAMENTS; j++) {
                int u = random.nextInt(individuals.size());
                VRPIndividual challenger = individuals.get(u);
                if (challenger.getFitness() < currentWinner.getFitness()) {
                    currentWinner = challenger;
                }
            }
            parents.add(currentWinner);
        }

        return parents;
    }

    @Override
    public String toString() {
        return "TournamentSelection (1vs5)";
    }
}
