package ru.ifmo.ctlab.ml.core;

import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.Type;
import ru.ifmo.ctlab.ml.core.feat.AbstractIntegerFeature;

public class DoubleVector implements Instance {

    private final double[] data;
    private final Type featuresType;

    public DoubleVector(double[] data, Type featuresType) {
        this.data = data;
        this.featuresType = featuresType;
    }

    @Override
    public Type type() {
        return featuresType;
    }

    @Override
    public double distance(Instance other, int feature) {
        return Math.abs(numValue(feature) - other.numValue(feature));
    }

    @Override
    public int compare(Instance other, int feature) {
        return Double.compare(numValue(feature), other.numValue(feature));
    }

    @Override
    public double numValue(int numId) {
        return data[numId];
    }

    @Override
    public int intValue(int numId) {
        double val = data[numId];
        if (Double.isNaN(val)) {
            return Integer.MIN_VALUE;
        } else {
            return (int) Math.rint(val);
        }
    }

}
