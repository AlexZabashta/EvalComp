package eval_comp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import eval_comp.distribution.ReRankViaSort;
import eval_comp.distribution.UniformNorm;
import eval_comp.optimization.Crossover;
import eval_comp.optimization.Measurable;
import eval_comp.optimization.Mutation;
import eval_comp.optimization.gen.MutationStep;
import eval_comp.optimization.gen.PairwiseCrossover;
import eval_comp.optimization.gen.RouletteWheel;

public class Test {
    public static void main(String[] args) {
        List<Measurable> list = new ArrayList<Measurable>();

        int n = 39;

        int[] d = new int[n];

        for (int i = 0; i < n; i++) {
            final int num = i;

            list.add(new Measurable() {

                @Override
                public double fitnessFunction() {
                    return num * num;
                }

                @Override
                public String toString() {
                    return Integer.toString(num);
                }
            });
        }

        Mutation<Measurable> mutation = new Mutation<Measurable>() {
            @Override
            public List<Measurable> mutate(Measurable source, Random random) {
                List<Measurable> mutants = new ArrayList<>();
                mutants.add(source);
                return mutants;
            }
        };

        // RouletteWheel<Measurable> wheel = new RouletteWheel<Measurable>(new
        // UniformNorm());
        //
        // for (int rep = 0; rep < 100; rep++) {
        // for (Measurable val : wheel.perfom(list, 20)) {
        // ++d[Integer.parseInt(val.toString())];
        // }
        // }
        //
        // for (int val : d) {
        // System.out.println(val);
        //
        // }

        Crossover<Measurable> crossover = new Crossover<Measurable>() {

            @Override
            public List<Measurable> cross(Measurable sourceX, Measurable sourceY, Random random) {
                List<Measurable> c = new ArrayList<>();
                c.add(sourceX);
                c.add(sourceY);
                return c;
            }

        };

        PairwiseCrossover<Measurable> pairwiseCrossover = new PairwiseCrossover<>(crossover);

        for (int m = 0; m <= n; m++) {
            List<Measurable> res = pairwiseCrossover.perfom(list, m);
            System.out.println(res.size() + " " + res);
        }

    }
}
