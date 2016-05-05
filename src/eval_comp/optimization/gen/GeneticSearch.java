package eval_comp.optimization.gen;

import java.util.ArrayList;
import java.util.List;

import eval_comp.distribution.UniformNorm;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Measurable;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;

public class GeneticSearch<T extends Measurable> {

    final SelectBest<T> selectBest;
    final RouletteWheel<T> rouletteWheel;
    final MutationStep<T> mutationStep;
    final PairwiseCrossover<T> crossoverStep;
    final int maxIter = 10000;

    public GeneticSearch(Crossover<T> crossover, Mutation<T> mutation) {
        this.selectBest = new SelectBest<T>();
        this.rouletteWheel = new RouletteWheel<T>(new UniformNorm());
        this.mutationStep = new MutationStep<T>(mutation);
        this.crossoverStep = new PairwiseCrossover<T>(crossover);
    }

    public Result<T> search(List<T> generation, double pc, double pm, StoppingCriterion<T> stoppingCriterion, List<Result<T>> results) {
        int generationSize = generation.size();
        int numFunctionEval = 0;

        int numOfChild = Math.max(generationSize, (int) (generationSize * pc));
        int numOfMut = Math.max(generationSize, (int) (generationSize * pm));
        Result<T> result = null;
        for (int step = 0; step < maxIter; step++) {
            result = new Result<T>(step, numFunctionEval, generation);
            if (results != null) {
                results.add(result);
            }

            if (stoppingCriterion.test(result)) {
                break;
            }

            List<T> newGeneration = new ArrayList<T>();
            newGeneration.addAll(generation);

            List<T> parents = selectBest.perfom(generation, numOfChild);
            List<T> children = crossoverStep.perfom(parents, numOfChild);
            numFunctionEval += numOfChild;
            newGeneration.addAll(children);

            List<T> mutants = mutationStep.perfom(newGeneration, numOfMut);
            numFunctionEval += numOfMut;
            newGeneration.addAll(mutants);

            generation = selectBest.perfom(newGeneration, generationSize);
        }

        return result;
    }
}
