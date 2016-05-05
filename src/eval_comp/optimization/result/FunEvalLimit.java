package eval_comp.optimization.result;

import eval_comp.optimization.Measurable;

public class FunEvalLimit<T extends Measurable> implements StoppingCriterion<T> {
	public final int limit;

	public FunEvalLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public boolean test(Result<T> result) {
		return limit <= result.numFunctionEval;
	}
}
