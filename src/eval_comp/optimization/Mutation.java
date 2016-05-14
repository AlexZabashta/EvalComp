package eval_comp.optimization;

import java.util.List;
import java.util.Random;

public interface Mutation<T> {

    public List<T> mutate(T source, Random random);

}
