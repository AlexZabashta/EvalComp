package eval_comp.lab1.gen_op;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.lab1.Instance;
import eval_comp.optimization.Crossover;

public class OnePointCrossover implements Crossover<Instance> {

    public void perform(boolean[] x, boolean[] y, Random random) {
        int crossoverPoint = random.nextInt(Math.min(x.length, y.length) + 1);

        for (int gen = 0; gen < crossoverPoint; gen++) {
            boolean allele = x[gen];
            x[gen] = y[gen];
            y[gen] = allele;
        }
    }

    @Override
    public List<Instance> cross(Instance sourceX, Instance sourceY, Random random) {
        List<Instance> result = new ArrayList<Instance>();
        boolean[] x = sourceX.getChromosome();
        boolean[] y = sourceY.getChromosome();
        perform(x, y, random);
        result.add(new Instance(x));
        result.add(new Instance(y));
        return result;
    }


}
