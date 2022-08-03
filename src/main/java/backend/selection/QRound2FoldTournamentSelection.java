package backend.selection;

import backend.models.VRPIndividual;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Each individual has q fights against challengers. The individuals will be selected by their winning count in
 * decreasing order
 */
public class QRound2FoldTournamentSelection extends BaseSelection {

    private final int COUNT_OF_TOURNAMENTS = 5; //q

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> individuals) {

        HashMap<VRPIndividual, Integer> winCount = executeTournaments(individuals);
        return winCount.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .limit(selectionCount)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private HashMap<VRPIndividual, Integer> executeTournaments(ArrayList<VRPIndividual> individuals) {
        HashMap<VRPIndividual, Integer> winCount = new HashMap<>();
        Random random = new Random();

        for (VRPIndividual individual : individuals) {
            winCount.put(individual, 0);
            for (int i = 0; i < COUNT_OF_TOURNAMENTS; i++) {
                VRPIndividual challenger = individuals.get(random.nextInt(individuals.size()));
                if (individual.getFitness() > challenger.getFitness()) {
                    winCount.put(individual, winCount.get(individual) + 1);
                }
            }
        }

        return winCount;
    }

    @Override
    public String toString() {
        return "QRound2FoldTournamentSelection";
    }
}
