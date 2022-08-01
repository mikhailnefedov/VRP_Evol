package backend.data;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.List;

public class TestProblem implements IData {
    @Override
    public ArrayList<Genome> getCustomers() {
        ArrayList<Genome> customers = new ArrayList<>(List.of(
                new Customer(1, 100.0, 50.0),
                new Customer(2, 100.0, 25.0),
                new Customer(3, 100.0, 0.0),
                new Customer(4, 75.0, 0.0),
                new Customer(5, 50.0, 0.0),
                new Customer(6, 50.0, 75.0),
                new Customer(7, 50.0, 100.0),
                new Customer(8, 25.0, 100.0),
                new Customer(9, 0.0, 100.0),
                new Customer(10, 0.0, 75.0),
                new Customer(11, 0.0, 50.0)));

        return customers;
    }

    @Override
    public Tuple<Double, Double> getFleetHQCoordinates() {
        return new Tuple<>(50.0, 50.0);
    }

    @Override
    public String toString() {
        return "TestProblem";
    }
}
