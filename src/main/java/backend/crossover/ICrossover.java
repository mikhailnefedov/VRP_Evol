package backend.crossover;

import backend.models.VRPIndividual;

public interface ICrossover {
    VRPIndividual crossover(VRPIndividual father, VRPIndividual mother);
    String toString();
}
