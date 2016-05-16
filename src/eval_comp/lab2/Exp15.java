package eval_comp.lab2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.imageio.ImageIO;

import eval_comp.lab1.GeneticSearch;
import eval_comp.lab1.PlotBuilder;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.result.ReachMaxValue;
import eval_comp.optimization.result.Result;
import eval_comp.optimization.result.StoppingCriterion;
import misc.FolderUtils;

public class Exp15 {

    public static void main(String[] args) throws IOException {
        String res = FolderUtils.clearOrCreate();

        Crossover<Instance> crossover = new NumCrossover();

        StoppingCriterion<Instance> sc = new ReachMaxValue<>(0 - 0.1);
        Mutation<Instance> mutation = new NumMutation();
        GeneticSearch<Instance> search = new GeneticSearch<>(50, crossover, mutation);

        int m = 500;
        boolean dim = false;

        Random random = new Random();

        int generationSize = 30;

        int width = 1024, height = 1024;

        double[] array = new double[1024];
        int len = 0;
        for (int step = 0; step <= generationSize; step++) {
            double p = 1.0 * step / generationSize;

            int numOfMut = step;
            int numOfChild = (generationSize + 1) / 2;

            double time = 0;
            int k = 0;
            for (int e = 0; e < m; e++) {
                List<Instance> generation = new ArrayList<Instance>();

                for (int i = 0; i < generationSize; i++) {
                    generation.add(new Instance(random, dim));
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
