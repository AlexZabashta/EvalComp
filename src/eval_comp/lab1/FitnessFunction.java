package eval_comp.lab1;

public class FitnessFunction {

	public final static double L = -10, R = +10;

	public double encode(boolean[] chromosome) {
		return normalizedValue(chromosome) * (R - L) + L;
	}

	public double evaluate(boolean[] chromosome) {
		return evaluate(encode(chromosome));
	}

	public double evaluate(double x) {
		return (x - 1) * Math.cos(3 * x - 15);
	}

	public double normalizedValue(boolean[] chromosome) {
		double value = 0;
		double powerOfTwo = 1;
		boolean one = true;

		for (boolean gen : chromosome) {
			powerOfTwo /= 2;
			one &= gen;
			if (gen) {
				value += powerOfTwo;
			}
		}

		if (one) {
			return 1.0;
		} else {
			return value;
		}
	}

}
