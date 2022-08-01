package backend.data;

import backend.helper.Tuple;
import backend.models.Genome;

import java.util.ArrayList;

public interface IData {
    ArrayList<Genome> getCustomers();
    Tuple<Double, Double> getFleetHQCoordinates();
    String toString();
}
