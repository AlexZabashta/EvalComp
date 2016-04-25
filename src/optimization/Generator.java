package optimization;

import java.util.Random;

public interface Generator<T> {

    public T generate(Random random);

}
