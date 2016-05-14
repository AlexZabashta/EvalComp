package eval_comp.lab1.gen_op;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.lab1.Instance;
import eval_comp.optimization.Mutation;

public class InvertSegment implements Mutation<Instance> {

    @Override
    public List<Instance> mutate(Instance source, Random random) {
        List<Instance> mutants = new ArrayList<Instance>();
        mutants.add(source.mutation(random));
        return mutants;
    }

}
