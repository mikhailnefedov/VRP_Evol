package crossover;

import backend.crossover.EdgeCrossover;
import backend.data.IData;
import backend.fitness.FitnessHelper;
import backend.fitness.IFitness;
import backend.fitness.LongestRouteFitness;
import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;
import backend.models.VRPIndividual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class EdgeCrossoverTest {

    private VRPIndividual father;
    private VRPIndividual mother;
    @Mock
    private IData dataMock;

    @Before
    public void createFatherAndMother() {
        when(dataMock.getFleetHQCoordinates()).thenReturn(new Tuple<>(0.0, 0.0));
        FitnessHelper.setData(dataMock);
        IFitness fitnessComputation = new LongestRouteFitness();
        VRPIndividual.setFitness(fitnessComputation);

        Customer firstCustomer = new Customer(1, 0.0, 0.0);
        Customer secondCustomer = new Customer(2, 0.0, 0.0);
        Customer thirdCustomer = new Customer(3, 0.0, 0.0);
        Customer fourthCustomer = new Customer(4, 0.0, 0.0);
        DeliveryTruck deliveryTruck = new DeliveryTruck(5, false);

        ArrayList<Genome> fatherGenotype = new ArrayList<>(
                List.of(firstCustomer, secondCustomer, thirdCustomer, fourthCustomer, deliveryTruck));
        Collections.shuffle(fatherGenotype);
        father = new VRPIndividual(fatherGenotype);

        ArrayList<Genome> motherGenotype = new ArrayList<>(
                List.of(firstCustomer, secondCustomer, thirdCustomer, fourthCustomer, deliveryTruck));
        Collections.shuffle(motherGenotype);
        mother = new VRPIndividual(motherGenotype);
    }

    @Test
    public void crossover_OrdinaryParents_CorrectChild() {
        EdgeCrossover edgeCrossover = new EdgeCrossover();

        VRPIndividual child = edgeCrossover.crossover(father, mother);

        Assert.assertEquals(father.getGenotype().size(), child.getGenotype().size());
    }
}
