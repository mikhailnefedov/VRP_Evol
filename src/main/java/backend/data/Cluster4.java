package backend.data;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.Genome;

import java.util.ArrayList;
import java.util.List;

public class Cluster4 implements IData {

    @Override
    public ArrayList<Genome> getCustomers() {
        ArrayList<Genome> customers = new ArrayList<>(List.of(
                new Customer(1,74.0,91.0),
                new Customer(2,50.0,93.0),
                new Customer(3,95.0,74.0),
                new Customer(4,77.0,86.0),
                new Customer(5,52.0,85.0),
                new Customer(6,57.0,78.0),
                new Customer(7,81.0,65.0),
                new Customer(8,74.0,71.0),
                new Customer(9,58.0,92.0),

                new Customer(10,34.0,90.0),
                new Customer(11,37.0,82.0),
                new Customer(12,41.0,90.0),
                new Customer(13,49.0,65.0),
                new Customer(14,40.0,55.0),
                new Customer(15,10.0,95.0),
                new Customer(16,39.0,64.0),
                new Customer(17,14.0,60.0),
                new Customer(18,7.0,67.0),
                new Customer(19,34.0,57.0),

                new Customer(20,42.0,7.0),
                new Customer(21,38.0,7.0),
                new Customer(22,30.0,28.0),
                new Customer(23,3.0,20.0),
                new Customer(24,3.0,3.0),
                new Customer(25,24.0,2.0),
                new Customer(26,34.0,0.0),
                new Customer(27,47.0,14.0),
                new Customer(28,44.0,24.0),
                new Customer(29,26.0,28.0),

                new Customer(30,92.0,39.0),
                new Customer(31,82.0,31.0),
                new Customer(32,98.0,32.0),
                new Customer(33,54.0,29.0),
                new Customer(34,77.0,35.0),
                new Customer(35,98.0,45.0),
                new Customer(36,61.0,38.0),
                new Customer(37,85.0,2.0),
                new Customer(38,60.0,41.0),
                new Customer(39,73.0,33.0)));

        return customers;
    }

    @Override
    public Tuple<Double, Double> getFleetHQCoordinates() {
        return new Tuple<>(50.0, 50.0);
    }

    @Override
    public String toString() {
        return "Cluster4";
    }
}
