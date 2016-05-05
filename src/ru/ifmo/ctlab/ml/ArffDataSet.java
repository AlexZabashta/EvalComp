package ru.ifmo.ctlab.ml;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.ctlab.ml.core.feat.AbstractEnumFeature;
import ru.ifmo.ctlab.ml.core.feat.AbstractNumericFeature;
import ru.ifmo.ctlab.ml.core.feat.EnumFeature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;
import ru.ifmo.ctlab.ml.core.val.Instance;
import ru.ifmo.ctlab.ml.util.Pair;
import weka.core.Attribute;
import weka.core.Instances;
import weka.experiment.Stats;

public class ArffDataSet {
    public static Pair<List<NumericFeature>, EnumFeature> getFeatureSpace(Instances instances) {
        List<NumericFeature> features = new ArrayList<NumericFeature>();
        EnumFeature classFeature = null;

        int a = instances.numAttributes();
        int c = instances.classIndex();
        final int d = instances.numClasses();

        for (int i = 0; i < a; i++) {
            final Attribute attribute = instances.attribute(i);
            final Stats stats = instances.attributeStats(i).numericStats;

            if (i == c) {
                // FIXME add values
                // classFeature = new AbstractEnumFeature() {
                // @Override
                // public int dimension() {
                // return d;
                // }
                //
                // @Override
                // public int getOrdinal(Instance instance) {
                // double val = instance.classValue();
                // if (Double.isFinite(val)) {
                // return (int) val;
                // } else {
                // return -1;
                // }
                // }
                //
                // };
            } else {
                // FIXME add values
                // features.add(new AbstractNumericFeature() {
                //
                // @Override
                // public double getNumericFeature(Instance instance) {
                // final double val = instance.value(attribute);
                //
                // if (Double.isNaN(val)) {
                // return stats.mean;
                // }
                //
                // if (Double.isInfinite(val)) {
                // if (val < 0) {
                // return stats.min * 3;
                // } else {
                // return stats.max * 3;
                // }
                // }
                //
                // return val;
                // }
                // });
            }

        }

        return new Pair<>(features, classFeature);
    }
}
