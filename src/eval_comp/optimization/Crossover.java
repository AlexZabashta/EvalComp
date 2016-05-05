package eval_comp.optimization;

import java.util.Random;

public interface Crossover<T> {

    public class Child<C> {
        public final C fst, snd;

        public Child(C fst, C snd) {
            this.fst = fst;
            this.snd = snd;
        }
    }

    public Child<T> cross(T sourceX, T sourceY, Random random);

}
