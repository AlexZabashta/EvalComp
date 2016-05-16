package eval_comp.lab1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;

import eval_comp.lab1.gen_op.InvertSegment;
import eval_comp.lab1.gen_op.TwoPointCrossover;
import eval_comp.lab1.misc.TernarySearch;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.result.ReachMaxValue;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;
import misc.FolderUtils;

public class Exp14 {

    public static void main(String[] args) throws IOException {
        String res = FolderUtils.clearOrCreate();

        FitnessFunction fitnessFunction = new FitnessFunction();
        Crossover<Instance> crossover = new TwoPointCrossover();

        double maxX = TernarySearch.maximize(-9, -8, fitnessFunction);
        double maxY = fitnessFunction.evaluate(maxX);

        StoppingCriterion<Instance> sc = new ReachMaxValue<>(maxY - 0.1);
        Mutation<Instance> mutation = new InvertSegment();
        GeneticSearch<Instance> search = new GeneticSearch<>(50, crossover, mutation);

        int m = 500;

        Random random = new Random();

        int chromosomeLength = 20;
        int generationSize = 30;

        int width = 1024, height = 1024;

        double[] array = new double[1024];
        int len = 0;

        for (int step = 0; step <= generationSize; step++) {
            double p = 1.0 * step / generationSize;

            int numOfMut = (generationSize + 9) / 10;
            int numOfChild = step;

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
            array[len++] = time;

            System.out.printf(Locale.ENGLISH, "(%.3f; %.3f)%n", p, time);
        }
        BufferedImage image = PlotBuilder.simpleSplot(Arrays.copyOf(array, len), width, height);
        ImageIO.write(image, "png", new File(res + "plot.png"));

    }
}
