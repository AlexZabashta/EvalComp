package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.val.Instance;

public interface IntegerFeature extends NumericFeature {

    int intFeature(Instance instance);

}