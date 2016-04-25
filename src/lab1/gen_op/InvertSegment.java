package lab1.gen_op;

import java.util.Random;

import lab1.Instance;
import optimization.Mutation;

public class InvertSegment implements Mutation<Instance> {

    @Override
    public Instance mutate(Instance source, Random random) {
        return source.mutation(random);
    }

}
