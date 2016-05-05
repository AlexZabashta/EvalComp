package ru.ifmo.ctlab.ml.core.val;

import ru.ifmo.ctlab.ml.core.feat.Feature;

public class ComparableValue implements Feature {

    public final int index;
    public final Type type;

    public ComparableValue(int index, Type type) {
        this.index = index;
        this.type = type;
    }

    @Override
    public int compare(Instance x, Instance y) {
        return x.compare(y, index);
    }

    @Override
    public double distance(Instance x, Instance y) {
        return x.distance(y, index);
    }

    @Override
    public String toString() {
        return type.nameOfValue(index);
    }

    @Override
    public String featureName() {
        return type.nameOfValue(index);
    }

}
