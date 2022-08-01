package fitness;

import backend.data.IData;
import backend.fitness.FitnessHelper;
import backend.fitness.IFitness;
import backend.fitness.LongestRouteFitness;
import backend.fitness.SumOfRoutesFitness;
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
public class SumOfRoutesFitnessTest {

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
        IFitness fitnessComputation = new SumOfRoutesFitness();

        double fitness = fitnessComputation.computeFitness(individual.getGenotype());

        Assert.assertEquals(14.0, fitness, 0.0);
    }

    @Test
    public void computeFitness_ComplexExample_CorrectComputation() {
        VRPIndividual individual = new VRPIndividual();
        individual.setGenotype(new ArrayList<>(List.of(
                new Customer(1, 25.0,100.0),
                new Customer(2, 0.0,50.0),
                new Customer(3, 100.0, 50.0),
                new Customer(4, 50.0, 100.0),
                new Customer(5, 100.0, 25.0),
                new Customer(6, 50.0, 0.0),
                new Customer(7, 50.0, 75.0),
                new Customer(8, 0.0, 100.0),
                new Customer(9, 100.0, 0.0),
                new Customer(10, 75.0, 0.0),
                new Customer(11, 0.0, 75.0)
        )));
        when(dataMock.getFleetHQCoordinates()).thenReturn(new Tuple<>(50.0, 50.0));
        FitnessHelper.setData(dataMock);
        IFitness fitnessComputation = new SumOfRoutesFitness();

        double fitness = fitnessComputation.computeFitness(individual.getGenotype());

        Assert.assertEquals(887.73, fitness, 0.4);
    }
}

