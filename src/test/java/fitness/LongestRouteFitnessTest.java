package fitness;

import backend.data.IData;
import backend.fitness.FitnessHelper;
import backend.fitness.IFitness;
import backend.fitness.LongestRouteFitness;
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
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class LongestRouteFitnessTest {

    @Mock
    private IData dataMock;

    @Test
    public void computeFitness_SimpleExample_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 3.0,0.0),
                new Customer(2, 3.0,4.0),
                new DeliveryTruck(),
                new Customer(3, 0.0, 1.0)
        )));
        when(dataMock.getFleetHQCoordinates()).thenReturn(new Tuple<>(0.0, 0.0));
        FitnessHelper.setData(dataMock);
        IFitness fitnessComputation = new LongestRouteFitness();

        double fitness = fitnessComputation.computeFitness(individual.getGenotype());

        Assert.assertEquals(12.0, fitness, 0.0);
    }

    @Test
    public void computeFitness_SimpleExample1_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 0.0,10.0),
                new DeliveryTruck(),
                new Customer(2, 0.0,30.0),
                new Customer(3, 0.0, 40.0),
                new Customer(4, 0.0, 20.0),
                new Customer(5, 0.0, 50.0)
        )));
        when(dataMock.getFleetHQCoordinates()).thenReturn(new Tuple<>(0.0, 0.0));
        FitnessHelper.setData(dataMock);
        IFitness fitnessComputation = new LongestRouteFitness();

        double fitness = fitnessComputation.computeFitness(individual.getGenotype());

        Assert.assertEquals(140, fitness, 0.0);
    }
}
