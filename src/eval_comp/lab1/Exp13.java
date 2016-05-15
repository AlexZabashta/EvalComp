package eval_comp.lab1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import eval_comp.lab1.gen_op.InvertSegment;
import eval_comp.lab1.gen_op.TwoPointCrossover;
import eval_comp.lab1.misc.TernarySearch;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.result.ReachMaxValue;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;
import misc.FolderUtils;

public class Exp13 {

    public static void main(String[] args) throws IOException {
        String res = FolderUtils.clearOrCreate();

        FitnessFunction fitnessFunction = new FitnessFunction();
        Crossover<Instance> crossover = new TwoPointCrossover();

        double maxX = TernarySearch.maximize(-9, -8, fitnessFunction);
        double maxY = fitnessFunction.evaluate(maxX);

        StoppingCriterion<Instance> sc = new ReachMaxValue<>(maxY - 0.1);
        Mutation<Instance> mutation = new InvertSegment();
        GeneticSearch<Instance> search = new GeneticSearch<>(50, crossover, mutation);

        int m = 50;

        Random random = new Random();

        try (PrintWriter out = new PrintWriter(new File(res + "depend.txt"))) {
            for (int n = 5; n <= 100; n += 5) {
                int generationSize = n;
                int chromosomeLength = 20;
                int numOfMut = (generationSize + 9) / 10;
                int numOfChild = (generationSize + 1) / 2;

                double time = 0;
                int k = 0;
                for (int e = 0; e < m; e++) {
                    List<Instance> generation = new ArrayList<Instance>();

                    for (int i = 0; i < generationSize; i++) {
                        generation.add(new Instance(random, chromosomeLength));
                    }

                    Result<Instance> result = search.search(generation, numOfChild, numOfMut, sc, null);
                    if (result.numSteps > 45) {
                        time += 1;
                    }
                    ++k;
                }

                time /= k;
                if (time < 50)
                    System.out.printf(Locale.ENGLISH, "(%d; %.3f)%n", n, time);
            }
        }

    }
}
