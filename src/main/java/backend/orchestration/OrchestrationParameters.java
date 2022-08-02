package backend.orchestration;

import backend.crossover.ICrossover;
import backend.data.IData;
import backend.fitness.IFitness;
import backend.mutation.IMutation;
import backend.selection.ISelection;
import lombok.Data;

@Data
public class OrchestrationParameters {

    private ISelection parentSelectionType;
    private ISelection environmentSelectionType;
    private ICrossover crossoverType;
    private IMutation mutationType;
    private IFitness fitnessType;
    private int generationCount;
    private int deliveryTruckCount;
    private IData mapType;
    private int populationSize;
    private int parentsCount;
    private int childrenCount;
}
