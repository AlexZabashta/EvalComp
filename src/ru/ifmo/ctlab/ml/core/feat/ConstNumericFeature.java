package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.data.Instance;

public class ConstNumericFeature implements NumericFeature {

    final public double value;

    public ConstNumericFeature(double value) {
        this.value = value;
    }

    @Override
    public int compare(Instance x, Instance y) {
        return 0;
    }

    @Override
    public double distance(Instance x, Instance y) {
        return 0;
    }

    @Override
    public String featureName() {
        return "const" + Double.toString(value);
    }

    @Override
    public double numFeature(Instance instance) {
        return value;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

}
