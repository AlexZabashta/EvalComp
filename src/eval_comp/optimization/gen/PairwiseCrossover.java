package eval_comp.optimization.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.optimization.Crossover;
import eval_comp.optimization.Measurable;

public class PairwiseCrossover<T extends Measurable> extends TransformStep<T> {

    public final Crossover<T> crossover;

    public PairwiseCrossover(Crossover<T> crossover, Random random) {
        super(random);
        this.crossover = crossover;
    }

    public PairwiseCrossover(Crossover<T> crossover) {
        super();
        this.crossover = crossover;
    }

    @Override
    public List<T> perfom(List<T> list, int expectedSize, Random random) {

        if (expectedSize < 0) {
            throw new RuntimeException("Can't select " + expectedSize + " negative number of objects");
        }

        // int expectedSize = m;
        int n = list.size();

        List<T> result = new ArrayList<T>(expectedSize);

        for (int i = 1; i < n; i += 2) {
            int u = i - 1, v = i;
            List<T> childs = crossover.cross(list.get(u), list.get(v), random);
            result.addAll(childs);
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
