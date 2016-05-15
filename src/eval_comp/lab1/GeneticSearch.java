package eval_comp.lab1;

import java.util.ArrayList;
import java.util.List;

import eval_comp.distribution.UniformNorm;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Measurable;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.gen.MutationStep;
import eval_comp.optimization.gen.PairwiseCrossover;
import eval_comp.optimization.gen.RouletteWheel;
import eval_comp.optimization.gen.SelectBest;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;

public class GeneticSearch<T extends Measurable> {

    final SelectBest<T> selectBest;
    final RouletteWheel<T> rouletteWheel;
    final MutationStep<T> mutationStep;
    final PairwiseCrossover<T> crossoverStep;
    final int maxIter;

    public GeneticSearch(int maxIter, Crossover<T> crossover, Mutation<T> mutation) {
        this.maxIter = maxIter;
        this.selectBest = new SelectBest<T>();
        this.rouletteWheel = new RouletteWheel<T>(new UniformNorm());
        this.mutationStep = new MutationStep<T>(mutation);
        this.crossoverStep = new PairwiseCrossover<T>(crossover);
    }

    public Result<T> search(List<T> generation, int numOfChild, int numOfMut, StoppingCriterion<T> stoppingCriterion, List<Result<T>> results) {
        int generationSize = generation.size();
        int numFunctionEval = 0;

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

            List<T> parents = rouletteWheel.perfom(generation, generationSize);
            List<T> childrens = crossoverStep.perfom(parents, numOfChild);
            numFunctionEval += childrens.size();
            newGeneration.addAll(childrens);

            List<T> sourse = rouletteWheel.perfom(newGeneration, numOfMut);
            List<T> mutants = mutationStep.perfom(sourse, numOfMut);
            numFunctionEval += mutants.size();
            newGeneration.addAll(mutants);

            generation = selectBest.perfom(newGeneration, generationSize);
        }

        return result;
    }
}
