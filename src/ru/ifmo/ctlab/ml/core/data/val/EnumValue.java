package ru.ifmo.ctlab.ml.core.data.val;

import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.Type;
import ru.ifmo.ctlab.ml.core.feat.EnumFeature;

public class EnumValue extends IntegerValue implements EnumFeature {

    private final int dimension;

    public EnumValue(int dimension, int index, Type type) {
        super(index, type);
        this.dimension = dimension;
    }

    @Override
    public int dimension() {
        return dimension;
    }

    @Override
    public int getOrdinal(Instance instance) {
        return instance.intValue(index);
    }

}
