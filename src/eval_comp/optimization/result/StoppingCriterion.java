package eval_comp.optimization.result;

import eval_comp.optimization.Measurable;

public interface StoppingCriterion<T extends Measurable> {
	public boolean test(Result<T> result);
}
