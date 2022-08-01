package backend.data;

import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.Genome;

import java.util.ArrayList;

public class AugeratSetAN32 implements IData {

    @Override
    public ArrayList<Genome> getCustomers() {

        ArrayList<Genome> customers = new ArrayList<>();

        customers.add(new Customer(1, 96.0, 44.0));
        customers.add(new Customer(2, 50.0, 5.0));
        customers.add(new Customer(3, 49.0, 8.0));
        customers.add(new Customer(4, 13.0, 7.0));
        customers.add(new Customer(5, 29.0, 89.0));
        customers.add(new Customer(6, 58.0, 30.0));
        customers.add(new Customer(7, 84.0, 39.0));
        customers.add(new Customer(8, 14.0, 24.0));
        customers.add(new Customer(9, 2.0, 39.0));
        customers.add(new Customer(10, 3.0, 82.0));
        customers.add(new Customer(11, 5.0, 10.0));
        customers.add(new Customer(12, 98.0, 52.0));
        customers.add(new Customer(13, 84.0, 25.0));
        customers.add(new Customer(14, 61.0, 59.0));
        customers.add(new Customer(15, 1.0, 65.0));
        customers.add(new Customer(16, 88.0, 51.0));
        customers.add(new Customer(17, 91.0, 2.0));
        customers.add(new Customer(18, 19.0, 32.0));
        customers.add(new Customer(19, 93.0, 3.0));
        customers.add(new Customer(20, 50.0, 93.0));
        customers.add(new Customer(21, 98.0, 14.0));
        customers.add(new Customer(22, 5.0, 42.0));
        customers.add(new Customer(23, 42.0, 9.0));
        customers.add(new Customer(24, 61.0, 62.0));
        customers.add(new Customer(25, 9.0, 97.0));
        customers.add(new Customer(26, 80.0, 55.0));
        customers.add(new Customer(27, 57.0, 69.0));
        customers.add(new Customer(28, 23.0, 15.0));
        customers.add(new Customer(29, 20.0, 70.0));
        customers.add(new Customer(30, 85.0, 60.0));
        customers.add(new Customer(31, 98.0, 5.0));

        return customers;
    }

    @Override
    public Tuple<Double, Double> getFleetHQCoordinates() {
        return new Tuple<>(82.0, 76.0);
    }

    @Override
    public String toString() {
        return "AugeratSetAN32";
    }
}
