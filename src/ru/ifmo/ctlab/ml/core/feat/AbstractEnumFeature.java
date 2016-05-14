package ru.ifmo.ctlab.ml.core.feat;

import ru.ifmo.ctlab.ml.core.data.Instance;

public abstract class AbstractEnumFeature extends AbstractIntegerFeature implements EnumFeature {

    @Override
    public int intFeature(Instance instance) {
        int ord = getOrdinal(instance);

        if (ord < 0) {
            return AbstractIntegerFeature.MISSING_VALUE;
        }

        return ord;
    }

}
