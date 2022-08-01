package backend.selection;

import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The best x individuals will be selected
 */
public class BestXSelection extends BaseSelection {

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> individuals) {
        return individuals.stream()
                .sorted(Comparator.comparingDouble(VRPIndividual::getFitness))
                .limit(selectionCount)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public String toString() {
        return "BestXSelection";
    }
}
