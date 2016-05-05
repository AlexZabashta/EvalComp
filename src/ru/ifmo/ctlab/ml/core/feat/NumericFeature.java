package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.val.Instance;

public interface NumericFeature extends Feature {

    double numFeature(Instance instance);

}