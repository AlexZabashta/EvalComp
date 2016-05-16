package eval_comp.lab2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import eval_comp.lab1.GeneticSearch;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.result.FunEvalLimit;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;
import misc.FolderUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String res = FolderUtils.clearOrCreate();
        String basePlot = FolderUtils.openData("2dplotg.png");

        Crossover<Instance> crossover = new NumCrossover();

        int generationSize = 20;
        int limit = 256;

        Random random = new Random();
        List<Instance> generation = new ArrayList<Instance>();

        for (int i = 0; i < generationSize; i++) {
            generation.add(new Instance(random, false));
        }

        int numOfMut = (generationSize + 9) / 10;
        int numOfChild = (generationSize + 1) / 2;

        List<Result<Instance>> results = new ArrayList<Result<Instance>>();

        Mutation<Instance> mutation = new NumMutation();
        GeneticSearch<Instance> search = new GeneticSearch<>(500, crossover, mutation);

        StoppingCriterion<Instance> stoppingCriterion = new FunEvalLimit<>(limit);
        search.search(generation, numOfChild, numOfMut, stoppingCriterion, results);

        try (PrintWriter out = new PrintWriter(new File(res + "value.txt"))) {
            out.printf(Locale.ENGLISH, "real solution = %8.5f%n", 0.0);

            int index = 1;
            for (Result<Instance> result : results) {
                out.printf(Locale.ENGLISH, "generation %2d = %8.5f%n", index, result.bestValue);
                Plotter.drawPointsOnGraph(result.instances, basePlot, res + "generation" + index + ".png");
                ++index;
            }
        }
    }
}
