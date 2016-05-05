package ru.ifmo.ctlab.ml.util;

import java.util.ArrayList;
import java.util.List;

import ru.ifmo.ctlab.ml.core.feat.EnumFeature;
import ru.ifmo.ctlab.ml.core.feat.IndicatorFeature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class Features {

    public static List<NumericFeature> getDistribution(final EnumFeature feature) {

        int d = feature.dimension();
        List<NumericFeature> features = new ArrayList<NumericFeature>();

        for (int i = 0; i < d; i++) {
            features.add(new IndicatorFeature(feature, i));
        }

        return features;
    }

}
