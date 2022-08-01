package mutation;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.VRPIndividual;
import backend.mutation.InvertRoutesMutation;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class InvertRoutesMutationTest {

    @Test
    public void getIndicesOfRoutes_TruckAtFirstPosition_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new DeliveryTruck(),
                new Customer(1, 25.0, 100.0),
                new Customer(2, 0.0, 50.0),
                new Customer(3, 100.0, 50.0)
        )));

        ArrayList<Tuple<Integer, Integer>> result = new InvertRoutesMutation().getIndicesOfRoutes(individual);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(3, result.get(0).getRight().intValue());
        Assert.assertEquals(1, result.get(0).getLeft().intValue());
    }

    @Test
    public void getIndicesOfRoutes_TruckAtLastPosition_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 25.0, 100.0),
                new Customer(2, 0.0, 50.0),
                new Customer(3, 100.0, 50.0),
                new DeliveryTruck()
        )));

        ArrayList<Tuple<Integer, Integer>> result = new InvertRoutesMutation().getIndicesOfRoutes(individual);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(2, result.get(0).getRight().intValue());
        Assert.assertEquals(0, result.get(0).getLeft().intValue());
    }
}
