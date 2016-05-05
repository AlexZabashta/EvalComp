package eval_comp.lab1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import eval_comp.lab1.gen_op.InvertSegment;
import eval_comp.lab1.gen_op.OnePointCrossover;
import eval_comp.lab1.gen_op.TwoPointCrossover;
import eval_comp.lab1.misc.Plotter;
import eval_comp.lab1.misc.TernarySearch;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.gen.GeneticSearch;
import eval_comp.optimization.result.FunEvalLimit;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;

public class Main {
    static String q = File.separator;
    static String res = "result" + q + Main.class.getSimpleName() + q;
    static String basePlot = "plot" + q + "cos.png";

    public static void main(String[] args) throws IOException {
        FitnessFunction fitnessFunction = new FitnessFunction();
        Crossover<Instance> crossover = new TwoPointCrossover();

        double maxX = TernarySearch.maximize(-9, -8, fitnessFunction);
        double maxY = fitnessFunction.evaluate(maxX);

        int generationSize = 20;
        int chromosomeLength = 20;
        int limit = 500;

        Random random = new Random();
        List<Instance> generation = new ArrayList<Instance>();

        for (int i = 0; i < generationSize; i++) {
            generation.add(new Instance(random, chromosomeLength));
        }

        double pMutation = 0.01;
        double pCrossover = 0.5;

        List<Result<Instance>> results = new ArrayList<Result<Instance>>();

        Mutation<Instance> mutation = new InvertSegment();
        GeneticSearch<Instance> search = new GeneticSearch<>(crossover, mutation);

        StoppingCriterion<Instance> stoppingCriterion = new FunEvalLimit<>(limit);
        search.search(generation, pCrossover, pMutation, stoppingCriterion, results);

        try (PrintWriter out = new PrintWriter(new File(res + "value.txt"))) {
            out.printf(Locale.ENGLISH, "real solution = %8.5f%n", maxY);

            int index = 1;
            for (Result<Instance> result : results) {
                out.printf(Locale.ENGLISH, "generation %2d = %8.5f%n", index, result.bestValue);
                Plotter.drawPointsOnGraph(result.instances, basePlot, res + "generation" + index + ".png");
                ++index;
            }
        }
    }
}
