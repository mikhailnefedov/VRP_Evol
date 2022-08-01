package backend.fitness;

import backend.data.IData;
import backend.helper.Tuple;
import backend.models.Customer;
import backend.models.DeliveryTruck;
import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.ArrayList;
import java.util.HashMap;


public class FitnessHelper {

    private static IData data;

    public static void setData(IData dataParam) {
        data = dataParam;
    }

    public static HashMap<DeliveryTruck, ArrayList<Customer>> computeTruckRoutes(ArrayList<Genome> genotype) {
        HashMap<DeliveryTruck, ArrayList<Customer>> deliveryTruckRoutes = new HashMap<>();

        ArrayList<Customer> route = new ArrayList<>();
        for (int i = 0; i < genotype.size(); i++) {
            if (genotype.get(i).getClass() == Customer.class) {
                route.add((Customer) genotype.get(i));
            } else {
                deliveryTruckRoutes.put((DeliveryTruck) genotype.get(i), route);
                route = new ArrayList<>();
            }
        }
        DeliveryTruck lastTruck = new DeliveryTruck();
        lastTruck.setLastTruck(true);
        deliveryTruckRoutes.put(lastTruck, route);
        return deliveryTruckRoutes;
    }

    public static HashMap<DeliveryTruck, Double> computeRouteLengths(HashMap<DeliveryTruck, ArrayList<Customer>> deliveryRoutes) {
        HashMap<DeliveryTruck, Double> routeLengths = new HashMap<>();

        for (DeliveryTruck t : deliveryRoutes.keySet()) {
            routeLengths.put(t, computeSingleRouteLength(deliveryRoutes.get(t)));
        }

        return routeLengths;
    }

    private static double computeSingleRouteLength(ArrayList<Customer> customerRoute) {
        if (customerRoute.size() == 0) {
            return 0;
        }

        double routeLength = 0;

        Tuple<Double, Double> hqCoordinates = data.getFleetHQCoordinates();

        routeLength += customerRoute.get(0)
                .computeDistance(hqCoordinates.getLeft(), hqCoordinates.getRight());

        for (int i = 1; i < customerRoute.size(); i++) {
            routeLength += customerRoute.get(i).computeDistance(customerRoute.get(i - 1));
        }

        routeLength += customerRoute.get(customerRoute.size() - 1)
                .computeDistance(hqCoordinates.getLeft(), hqCoordinates.getRight());

        return routeLength;
    }


}
