package backend.orchestration;

import backend.crossover.ICrossover;
import backend.data.IData;
import backend.fitness.IFitness;
import backend.mutation.IMutation;
import backend.selection.ISelection;
import lombok.Data;

@Data
public class OrchestrationParameters {

    private ISelection parentSelection;
    private ISelection environmentSelection;
    private ICrossover crossover;
    private IMutation mutation;
    private IFitness fitness;
    private int generationCount;
    private int deliveryTruckCount;
    private IData data;
    private int populationSize;
    private int parentsCount;
    private int childrenCount;
}
