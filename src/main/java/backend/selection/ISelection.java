package backend.selection;

import backend.models.VRPIndividual;

import java.util.ArrayList;

public interface ISelection {
    ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> generation);
    void setSelectionCount(int selectionCount);
    String toString();
}
