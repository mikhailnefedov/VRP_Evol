package backend.selection;

import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Random;

public class TournamentSelection implements ISelection {

    private int selectionCount;
    private final int ENEMY_COUNT = 5;

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> generation) {
        Random random = new Random();
        ArrayList<VRPIndividual> parents = new ArrayList<>();

        for (int i = 0; i < selectionCount; i++) {
            int index = random.nextInt(generation.size());
            for (int j = 0; j < ENEMY_COUNT; j++) {
                int u = random.nextInt(generation.size());
                if (generation.get(u).getFitness() < generation.get(index).getFitness()) {
                    index = u;
                }
            }
            parents.add(generation.get(index));
        }

        return parents;
    }

    @Override
    public void setSelectionCount(int selectionCount) {
        this.selectionCount = selectionCount;
    }

    @Override
    public String toString() {
        return "TournamentSelection (1vs5)";
    }
}
