package ru.ifmo.ctlab.ml.core.data;

import java.util.List;

import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public interface DataSet extends List<Instance> {
    public Type type();

    public List<NumericFeature> numericFeatures();

}
