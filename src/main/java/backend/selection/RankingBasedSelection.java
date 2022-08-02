package backend.selection;

import backend.helper.Tuple;
import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class RankingBasedSelection extends BaseSelection {

    @Override
    public ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> individuals) {
        individuals.sort(Comparator.comparingDouble(VRPIndividual::getFitness));
        ArrayList<Double> probabilityRange = computeProbabilityRanges(individuals);

        Random random = new Random();
        ArrayList<VRPIndividual> parents = new ArrayList<>();

        while (parents.size() < selectionCount) {
            double randomProbability = random.nextDouble();
            for (int i = 0; i < individuals.size(); i++) {
                if (isProbabilityInRange(randomProbability, probabilityRange.get(i), probabilityRange.get(i + 1))) {
                    parents.add(individuals.get(i));
                }
            }
        }
        return parents;
    }

    private boolean isProbabilityInRange(double probability, double lowerBound, double upperBound) {
        return probability >= lowerBound && probability <= upperBound;
    }

    private ArrayList<Double> computeProbabilityRanges(ArrayList<VRPIndividual> generation) {
        ArrayList<Double> probabilityRange = new ArrayList<>();
        double sumOfProbabilities = 0.0;
        probabilityRange.add(0.0);
        for (int i = 0; i < generation.size(); i++) {
            double probability = computeProbability(i, generation.size());
            sumOfProbabilities += probability;
            probabilityRange.add(sumOfProbabilities);
        }
        probabilityRange.set(probabilityRange.size() - 1, 1.0);
        return probabilityRange;
    }

    private double computeProbability(int index, int generationSize) {
        return (2.0/generationSize) * (1 - (double) index/(generationSize - 1));
    }

    @Override
    public String toString() {
        return "RankingBasedSelection";
    }
}
