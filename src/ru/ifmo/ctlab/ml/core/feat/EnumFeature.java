package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.val.Instance;

public interface EnumFeature extends IntegerFeature {

    int dimension();

    int getOrdinal(Instance instance);

}