package backend.crossover;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.*;

public class EdgeCrossover implements ICrossover {

    private Random random;

    @Override
    public VRPIndividual crossover(VRPIndividual father, VRPIndividual mother) {

        random = new Random();
        HashMap<Genome, Set<Genome>> adjacencyMap = computeAdjacencyMap(father, mother);
        Genome startGenome = selectRandomGenome(adjacencyMap);
        ArrayList<Genome> neighbours = getNeighboursAndDeleteFromMap(startGenome, adjacencyMap);
        ArrayList<Genome> genotype = new ArrayList<>(List.of(startGenome));

        for (int i = 1; i < father.getGenotype().size(); i++) {

            int minAdjacencySize = Integer.MAX_VALUE;
            for (Genome neighbour : neighbours) {
                if (adjacencyMap.get(neighbour).size() < minAdjacencySize) {
                    startGenome = neighbour;
                    minAdjacencySize = adjacencyMap.get(neighbour).size();
                } else if (adjacencyMap.get(neighbour).size() == minAdjacencySize && random.nextDouble() < 0.5) {
                    startGenome = neighbour;
                    minAdjacencySize = adjacencyMap.get(neighbour).size();
                }
            }
            if (neighbours.size() == 0) {
                startGenome = selectRandomGenome(adjacencyMap);
            }

            neighbours = getNeighboursAndDeleteFromMap(startGenome, adjacencyMap);
            genotype.add(startGenome);
        }

        return new VRPIndividual(genotype);
    }

    private ArrayList<Genome> getNeighboursAndDeleteFromMap(Genome genome, HashMap<Genome, Set<Genome>> adjacencyMap) {
        Set<Genome> neighbours = adjacencyMap.get(genome);
        for (Genome neighbour : neighbours) {
            adjacencyMap.get(neighbour).remove(genome);
        }
        adjacencyMap.remove(genome);
        return new ArrayList<>(neighbours);
    }

    private Genome selectRandomGenome(HashMap<Genome, Set<Genome>> adjacencyMap) {
        List<Genome> keysAsArray = new ArrayList<>(adjacencyMap.keySet());
        return keysAsArray.get(random.nextInt(keysAsArray.size()));
    }

    private HashMap<Genome, Set<Genome>> computeAdjacencyMap(VRPIndividual father, VRPIndividual mother) {
        HashMap<Genome, Set<Genome>> adjacencyMap = new HashMap<>();

        for (Genome g : father.getGenotype()) {
            Set<Genome> neighboursInFather = getNeighbouringGenomes(g, father);
            Set<Genome> neighboursInMother = getNeighbouringGenomes(g, mother);
            neighboursInFather.addAll(neighboursInMother);

            adjacencyMap.put(g, neighboursInFather);
        }

        return adjacencyMap;
    }

    private Set<Genome> getNeighbouringGenomes(Genome g, VRPIndividual individual) {
        Set<Genome> neighbours = new HashSet<>();

        ArrayList<Genome> genotype = individual.getGenotype();
        int index = genotype.indexOf(g);

        if (index == 0) {
            neighbours.add(genotype.get(1));
            neighbours.add(genotype.get(genotype.size() - 1));
        } else if (index == genotype.size() - 1) {
            neighbours.add(genotype.get(0));
            neighbours.add(genotype.get(genotype.size()- 2));
        } else {
            neighbours.add(genotype.get(index + 1));
            neighbours.add(genotype.get(index - 1));
        }

        return neighbours;
    }

    @Override
    public String toString() {
        return "EdgeCrossover";
    }
}
