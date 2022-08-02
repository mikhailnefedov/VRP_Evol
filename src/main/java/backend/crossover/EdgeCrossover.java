package backend.crossover;

import backend.models.Genome;
import backend.models.VRPIndividual;

import java.util.*;

/**
 * Models the edge crossover on a VRPIndividual
 */
public class EdgeCrossover implements ICrossover {

    private Random random;

    @Override
    public VRPIndividual crossover(VRPIndividual father, VRPIndividual mother) {
        random = new Random();
        HashMap<Genome, Set<Genome>> adjacencyMap = createAdjacencyMap(father, mother);
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

    /**
     * Gets neighbours of a given genome from the adjacency map and deletes the given genome from the
     * neighbours list of its neighbours
     * @param genome
     * @param adjacencyMap
     * @return
     */
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

    /**
     * Creates the adjacency map of the 2 parent individuals.
     * @param father
     * @param mother
     * @return Each genome links to its neighbours across the 2 individuals
     */
    private HashMap<Genome, Set<Genome>> createAdjacencyMap(VRPIndividual father, VRPIndividual mother) {
        HashMap<Genome, Set<Genome>> adjacencyMap = new HashMap<>();

        for (Genome g : father.getGenotype()) {
            Set<Genome> neighbours = new HashSet<>();
            neighbours.addAll(getNeighbouringGenomes(g, father));
            neighbours.addAll(getNeighbouringGenomes(g, mother));
            adjacencyMap.put(g, neighbours);
        }

        return adjacencyMap;
    }

    /**
     * Returns the neighbouring genomes of a given genome g in an individual
     */
    private Set<Genome> getNeighbouringGenomes(Genome g, VRPIndividual individual) {
        Set<Genome> neighbours = new HashSet<>();

        ArrayList<Genome> genotype = individual.getGenotype();
        int index = genotype.indexOf(g);

        if (isStart(index)) {
            neighbours.add(genotype.get(index + 1));
            neighbours.add(genotype.get(genotype.size() - 1));
        } else if (isLast(index, genotype)) {
            neighbours.add(genotype.get(0));
            neighbours.add(genotype.get(index - 1));
        } else {
            neighbours.add(genotype.get(index + 1));
            neighbours.add(genotype.get(index - 1));
        }

        return neighbours;
    }

    private boolean isStart(int index) {return index == 0;}
    private boolean isLast(int index, ArrayList<Genome> genotype) {return index == genotype.size() - 1;}

    @Override
    public String toString() {
        return "EdgeCrossover";
    }
}
