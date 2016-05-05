package eval_comp.optimization;

import java.util.Random;

public interface Mutation<T> {

    public  T mutate(T source, Random random);

}
