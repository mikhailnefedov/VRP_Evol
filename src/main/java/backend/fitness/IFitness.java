package backend.fitness;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.ArrayList;

public interface IFitness {
    double computeFitness(ArrayList<Genome> genotype);
    String toString();
}
