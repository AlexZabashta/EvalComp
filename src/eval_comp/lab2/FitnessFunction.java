package eval_comp.lab2;

public class FitnessFunction {

    public final double L = -5.12, R = +5.12;

    public double evaluate(double x) {
        return 10 * (Math.cos(2 * Math.PI * x) - 1) - x * x;
    }

    public double evaluate(double x, double y) {
        double sum = 0;
        sum += evaluate(x);
        sum += evaluate(y);
        return sum;
    }

    public double evaluate(double x, double y, double z) {
        double sum = 0;
        sum += evaluate(x);
        sum += evaluate(y);
        sum += evaluate(z);
        return sum;
    }

}
