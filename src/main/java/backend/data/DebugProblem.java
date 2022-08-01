package backend.data;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.List;

public class DebugProblem implements IData {

    @Override
    public ArrayList<Genome> getCustomers() {
        ArrayList<Genome> customers = new ArrayList<>(List.of(
                new Customer(1, 0.0, 10.0),
                new Customer(2, 0.0, 20.0),
                new Customer(3, 0.0, 30.0),
                new Customer(4, 0.0, 40.0),
                new Customer(5, 0.0, 50.0)));

        return customers;
    }

    @Override
    public Tuple<Double, Double> getFleetHQCoordinates() {
        return new Tuple<>(0.0, 0.0);
    }

    @Override
    public String toString() {
        return "DebugProblem";
    }
}
