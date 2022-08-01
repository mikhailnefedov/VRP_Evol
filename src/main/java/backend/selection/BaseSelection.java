package backend.selection;

import backend.models.VRPIndividual;

import java.util.ArrayList;

public abstract class BaseSelection implements ISelection {

    protected int selectionCount;

    @Override
    public abstract ArrayList<VRPIndividual> select(ArrayList<VRPIndividual> individuals);

    @Override
    public void setSelectionCount(int selectionCount) {
        this.selectionCount = selectionCount;
    }

    @Override
    public abstract String toString();
}
