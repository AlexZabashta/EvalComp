package eval_comp.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.optimization.Crossover;

public class NumCrossover implements Crossover<Instance> {

    public static final int n = 2;

    @Override
    public List<Instance> cross(Instance sourceX, Instance sourceY, Random random) {
        List<Instance> list = new ArrayList<Instance>();

        for (int i = 0; i < n; i++) {
            double a = random.nextDouble();
            double b = random.nextDouble();
            double c = random.nextDouble();

            double x = a * sourceX.x + (1 - a) * sourceY.x;
            double y = b * sourceX.y + (1 - b) * sourceY.y;
            double z = c * sourceX.z + (1 - c) * sourceY.z;

            if (sourceX.dim | sourceY.dim) {
                list.add(new Instance(x, y, z));
            } else {
                list.add(new Instance(x, y));
            }

        }

        return list;
    }

}
