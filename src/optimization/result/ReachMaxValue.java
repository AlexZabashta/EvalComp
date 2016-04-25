package optimization.result;

import optimization.Measurable;

public class ReachMaxValue<T extends Measurable> implements StoppingCriterion<T> {

    public final double maxValue;

    public ReachMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public boolean test(Result<T> result) {
        return maxValue <= result.bestValue;
    }

}
