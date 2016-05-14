package ru.ifmo.ctlab.ml.core.data.val;

import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.Type;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class NumericValue extends ComparableValue implements NumericFeature {

    public static final double MISSING_VALUE = Double.NaN;

    public NumericValue(int index, Type type) {
        super(index, type);
    }

    @Override
    public int compare(Instance x, Instance y) {
        return Double.compare(numFeature(x), numFeature(y));
    }

    @Override
    public double distance(Instance x, Instance y) {
        return Math.abs(numFeature(x) - numFeature(y));
    }

    @Override
    public double numFeature(Instance instance) {
        return instance.numValue(index);
    }
}
