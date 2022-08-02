package selection;

import backend.models.VRPIndividual;
import backend.selection.ISelection;
import backend.selection.RankingBasedSelection;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RankingBasedSelectionTest {

    private ISelection selection;

    @Before
    public void Initialize() {
        selection = new RankingBasedSelection();
        selection.setSelectionCount(1);
    }

    @Test
    public void select_GenerationWithOneIndividual_ThrowsNoError() {
        VRPIndividual individual = new VRPIndividual();
        ArrayList<VRPIndividual> generation = new ArrayList<>(List.of(individual));

        selection.select(generation);
    }
}
