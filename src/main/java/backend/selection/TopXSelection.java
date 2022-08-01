package backend.selection;

import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class TopXSelection implements ISelection {

    private int selectionCount;

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> generation) {
        return generation.stream()
                .sorted(Comparator.comparingDouble(VRPIndividual::getFitness))
                .limit(selectionCount)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void setSelectionCount(int selectionCount) {
        this.selectionCount = selectionCount;
    }

    @Override
    public String toString() {
        return "TopXSelection";
    }
}
