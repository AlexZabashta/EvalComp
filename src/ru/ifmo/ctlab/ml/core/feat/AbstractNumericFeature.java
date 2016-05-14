package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.data.Instance;

public abstract class AbstractNumericFeature implements NumericFeature {
    public static final double MISSING_VALUE = Double.NaN;

    @Override
    public int compare(Instance x, Instance y) {
        return Double.compare(numFeature(x), numFeature(y));
    }

    @Override
    public double distance(Instance x, Instance y) {
        return Math.abs(numFeature(x) - numFeature(y));
    }

    @Override
    public String toString() {
        return featureName();
    }
}
