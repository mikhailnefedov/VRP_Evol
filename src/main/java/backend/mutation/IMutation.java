package backend.mutation;

import backend.models.VRPIndividual;

public interface IMutation {
    VRPIndividual mutate(VRPIndividual individual);
    String toString();
    void setMutationRate(double mutationRate);
}
