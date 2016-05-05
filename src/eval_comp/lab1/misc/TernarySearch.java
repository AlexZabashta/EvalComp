package eval_comp.lab1.misc;

import eval_comp.lab1.FitnessFunction;

public class TernarySearch {
	public static double maximize(double l, double r, FitnessFunction function) {
		for (int iter = 0; iter < 128; iter++) {
			double d = r - l;

			double ll = l + d / 3;
			double rr = r - d / 3;

			if (function.evaluate(ll) < function.evaluate(rr)) {
				l = ll;
			} else {
				r = rr;
			}
		}

		return (l + r) / 2;
	}
}
