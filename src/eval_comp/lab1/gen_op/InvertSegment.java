package eval_comp.lab1.gen_op;

import java.util.Random;

import eval_comp.lab1.Instance;
import eval_comp.optimization.Mutation;

public class InvertSegment implements Mutation<Instance> {

    @Override
    public Instance mutate(Instance source, Random random) {
        return source.mutation(random);
    }

}
