package backend.orchestration;

import backend.crossover.ICrossover;
import backend.data.IData;
import backend.fitness.IFitness;
import backend.mutation.IMutation;
import backend.selection.ISelection;
import lombok.Data;

@Data
public class OrchestrationParameters {

    private ISelection selectionType;
    private ICrossover crossoverType;
    private double crossoverRate;
    private IMutation mutationType;
    private IFitness fitnessType;
    private int generationCount;
    private int deliveryTruckCount;
    private IData mapType;
    private int populationCount;
    private int parentsCount;
}
