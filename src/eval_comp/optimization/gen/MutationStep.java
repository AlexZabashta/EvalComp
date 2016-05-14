package eval_comp.optimization.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.optimization.Measurable;
import eval_comp.optimization.Mutation;

public class MutationStep<T extends Measurable> extends TransformStep<T> {

    public final Mutation<T> mutation;

    public MutationStep(Mutation<T> mutation, Random random) {
        super(random);
        this.mutation = mutation;
    }

    public MutationStep(Mutation<T> mutation) {
        super();
        this.mutation = mutation;
    }

    @Override
    public List<T> perfom(List<T> list, int expectedSize, Random random) {

        if (expectedSize < 0) {
            throw new RuntimeException("Can't select " + expectedSize + " negative number of objects");
        }

        // int expectedSize = m;
        int n = list.size();

        List<T> result = new ArrayList<T>(expectedSize);

        for (int i = 0; i < n; i++) {
            List<T> mutants = mutation.mutate(list.get(i), random);
            result.addAll(mutants);
            if (result.size() >= expectedSize) {
                break;
            }
        }

        if (result.size() >= expectedSize) {
            return result.subList(0, expectedSize);
        }
        throw new RuntimeException("Can't select " + expectedSize + " objects more than list size = " + n);
    }
}
