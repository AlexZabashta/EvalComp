package eval_comp.lab2;

import java.util.Random;

import eval_comp.optimization.Measurable;

public class Instance implements Measurable {

    public static FitnessFunction f = new FitnessFunction();

    public static double val(Random random) {
        return f.L + (f.R - f.L) * random.nextDouble();
    }
    public final boolean dim;
    public final double fitnessFunction;

    public final double x, y, z;

    public Instance(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.dim = false;
        this.fitnessFunction = f.evaluate(x, y);
    }

    public Instance(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = true;
        this.fitnessFunction = f.evaluate(x, y, z);
    }

    public Instance(Random random, boolean dim) {
        this.x = val(random);
        this.y = val(random);
        this.z = dim ? val(random) : 0;
        this.dim = dim;
        this.fitnessFunction = dim ? f.evaluate(x, y, z) : f.evaluate(x, y);
    }

    @Override
    public double fitnessFunction() {
        return fitnessFunction;
    }
}
