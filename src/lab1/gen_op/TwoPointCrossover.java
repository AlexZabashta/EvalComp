package lab1.gen_op;

import java.util.Random;

import lab1.Instance;
import optimization.Crossover;

public class TwoPointCrossover implements Crossover<Instance> {

    public void perform(boolean[] x, boolean[] y, Random random) {
        int rightCrossoverPoint = random.nextInt(Math.min(x.length, y.length) + 1);
        int leftCrossoverPoint = random.nextInt(rightCrossoverPoint + 1);

        for (int gen = leftCrossoverPoint; gen < rightCrossoverPoint; gen++) {
            boolean allele = x[gen];
            x[gen] = y[gen];
            y[gen] = allele;
        }
    }

    @Override
    public Child<Instance> cross(Instance sourceX, Instance sourceY, Random random) {
        boolean[] x = sourceX.getChromosome();
        boolean[] y = sourceY.getChromosome();
        perform(x, y, random);
        return new Child<>(new Instance(x), new Instance(y));
    }

}
