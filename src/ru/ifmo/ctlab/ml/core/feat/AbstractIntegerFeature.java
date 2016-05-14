package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.val.NumericValue;

public abstract class AbstractIntegerFeature extends AbstractNumericFeature implements IntegerFeature {

    public static final int MISSING_VALUE = Integer.MIN_VALUE;

    @Override
    public double numFeature(Instance instance) {
        int intValue = intFeature(instance);
        if (intValue == MISSING_VALUE) {
            return NumericValue.MISSING_VALUE;
        }
        return intValue;
    }

}
