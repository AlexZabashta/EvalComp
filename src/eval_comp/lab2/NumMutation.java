package eval_comp.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.optimization.Mutation;

public class NumMutation implements Mutation<Instance> {
    public static final int n = 1;
    public final double std = 1;

    @Override
    public List<Instance> mutate(Instance source, Random random) {
        List<Instance> list = new ArrayList<Instance>();

        for (int i = 0; i < n; i++) {

            double x = source.x + random.nextGaussian() * std;
            double y = source.y + random.nextGaussian() * std;
            double z = source.z + random.nextGaussian() * std;

            if (source.dim) {
                list.add(new Instance(x, y, z));
            } else {
                list.add(new Instance(x, y));
            }

        }

        return list;
    }

}
