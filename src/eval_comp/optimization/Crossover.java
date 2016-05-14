package eval_comp.optimization;

import java.util.List;
import java.util.Random;

public interface Crossover<T> {

    public List<T> cross(T sourceX, T sourceY, Random random);

}
