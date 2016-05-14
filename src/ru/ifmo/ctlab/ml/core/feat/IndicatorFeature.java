package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.data.Instance;

public class IndicatorFeature extends AbstractNumericFeature {
    public final EnumFeature feature;
    public final int index;

    public IndicatorFeature(EnumFeature feature, int index) {
        this.feature = feature;
        this.index = index;
    }

    @Override
    public String featureName() {
        return "if_" + feature.featureName() + "_eq_" + index;
    }

    @Override
    public double numFeature(Instance instance) {
        int ord = feature.getOrdinal(instance);

        if (ord < 0) {
            return AbstractNumericFeature.MISSING_VALUE;
        }

        if (ord == index) {
            return 1.0;
        } else {
            return 0.0;
        }
    }

    @Override
    public String toString() {
        return "I[" + feature + " == " + index + "]";
    }

}
