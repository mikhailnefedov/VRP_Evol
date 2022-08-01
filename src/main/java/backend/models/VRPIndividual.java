package backend.models;

import backend.fitness.IFitness;
import lombok.Data;

import java.util.ArrayList;

@Data
public class VRPIndividual {
    private static IFitness fitnessComputation;
    private ArrayList<Genome> genotype;
    private double fitness;

    public VRPIndividual() {

    }

    public VRPIndividual(ArrayList<Genome> genotype) {
        this.genotype = genotype;
        computeFitness();
    }

    public void computeFitness() {
        fitness = fitnessComputation.computeFitness(genotype);
    }

    public static void setFitness(IFitness fitnessType) {
        fitnessComputation = fitnessType;
    }

    @Override
    public String toString() {
        return "VRPIndividual{" +
                "genotype=" + genotype.toString() +
                ", fitness=" + fitness +
                '}';
    }
}
