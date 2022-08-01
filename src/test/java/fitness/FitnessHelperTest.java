package fitness;

import backend.data.IData;
import backend.fitness.FitnessHelper;
import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.VRPIndividual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class FitnessHelperTest {

    @Mock
    private IData dataMock;

    @Test
    public void computeTruckRoutes_Simple_Route_correct_computation() {
        VRPIndividual individual = new VRPIndividual();
        DeliveryTruck firstDeliveryTruck = new DeliveryTruck();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 0.0,0.0),
                new Customer(2, 1.0,0.0),
                new Customer(3, 0.0,1.0),
                firstDeliveryTruck,
                new Customer(4, 0.0, 0.0)
        )));

        HashMap<DeliveryTruck, ArrayList<Customer>> result = FitnessHelper.computeTruckRoutes(individual.getGenotype());

        Assert.assertEquals(2, result.keySet().size());
        Assert.assertEquals(3, result.get(firstDeliveryTruck).size());

        int customerCount = 0;
        for (DeliveryTruck truck : result.keySet()) {
            customerCount += result.get(truck).size();
        }
        Assert.assertEquals(4, customerCount);
    }

    @Test
    public void computeTruckRoutes_DeliveryTruckWithoutCustomers_ThrowsNoErrors() {
        VRPIndividual individual = new VRPIndividual();
        DeliveryTruck firstDeliveryTruck = new DeliveryTruck();
        individual.setGenotype(new ArrayList<>(List.of(
                firstDeliveryTruck,
                new Customer(4, 0.0, 0.0)
        )));

        HashMap<DeliveryTruck, ArrayList<Customer>> result = FitnessHelper.computeTruckRoutes(individual.getGenotype());

        Assert.assertEquals(2, result.keySet().size());
        Assert.assertEquals(0, result.get(firstDeliveryTruck).size());

        int customerCount = 0;
        for (DeliveryTruck truck : result.keySet()) {
            customerCount += result.get(truck).size();
        }
        Assert.assertEquals(1, customerCount);
    }

    @Test
    public void computeRouteLenghts_PythagoreanTheorem_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 3.0,0.0),
                new Customer(2, 3.0,4.0)
        )));
        when(dataMock.getFleetHQCoordinates()).thenReturn(new Tuple<>(0.0, 0.0));
        FitnessHelper.setData(dataMock);

        HashMap<DeliveryTruck, ArrayList<Customer>> truckRoutes = FitnessHelper.computeTruckRoutes(individual.getGenotype());
        HashMap<DeliveryTruck, Double> routeLengths = FitnessHelper.computeRouteLengths(truckRoutes);
        DeliveryTruck deliveryTruck = routeLengths.keySet().stream().findFirst().get();

        //Pythagorean Theorem: 3 + 4 + 5 = 12
        Assert.assertEquals(12.0, routeLengths.get(deliveryTruck), 0.0);
    }

    @Test
    public void computeRouteLenghts_MultipleDeliveryTruck_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 3.0,0.0),
                new Customer(2, 3.0,4.0),
                new DeliveryTruck(),
                new Customer(3, 0.0, 1.0)
        )));
        when(dataMock.getFleetHQCoordinates()).thenReturn(new Tuple<>(0.0, 0.0));
        FitnessHelper.setData(dataMock);

        HashMap<DeliveryTruck, ArrayList<Customer>> truckRoutes = FitnessHelper.computeTruckRoutes(individual.getGenotype());
        HashMap<DeliveryTruck, Double> routeLengths = FitnessHelper.computeRouteLengths(truckRoutes);
        ArrayList<Double> routes = new ArrayList<>(routeLengths.values());

        Assert.assertTrue(routes.contains(2.0));
        Assert.assertTrue(routes.contains(12.0));
    }

}
