package eval_comp.lab1;

import java.util.Arrays;
import java.util.Random;

import eval_comp.optimization.Measurable;

public class Instance implements Measurable {

    public static FitnessFunction fitnessFunction = new FitnessFunction();

    public static boolean[] randomChromosome(Random random, int len) {
        boolean[] chromosome = new boolean[len];
        for (int i = 0; i < chromosome.length; i++) {
            chromosome[i] = random.nextBoolean();
        }
        return chromosome;
    }

    private final boolean[] chromosome;

    public final double x, y;

    public int length() {
        return chromosome.length;
    }

    public Instance(boolean[] chromosome) {
        this.chromosome = chromosome;
        x = fitnessFunction.encode(chromosome);
        y = fitnessFunction.evaluate(x);
    }

    public Instance(Random random, int len) {
        this(randomChromosome(random, len));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Instance other = (Instance) obj;
        if (!Arrays.equals(chromosome, other.chromosome))
            return false;
        return true;
    }

    public boolean[] getChromosome() {
        return chromosome.clone();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(chromosome);
        return result;
    }

    public Instance mutation(Random random) {
        boolean[] mutant = getChromosome();

        int len = mutant.length;

        int offset = random.nextInt(len);

        for (int i = 0; i < len; i++) {
            int index = (i + offset) % len;
            mutant[index] = !mutant[index];
            if (random.nextBoolean()) {
                break;
            }
        }

        return new Instance(mutant);
    }

    @Override
    public double fitnessFunction() {
        return y;
    }
}
