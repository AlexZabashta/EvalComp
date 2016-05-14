package ru.ifmo.ctlab.ml.dataset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.Type;
import ru.ifmo.ctlab.ml.core.data.val.NumericValue;
import ru.ifmo.ctlab.ml.core.feat.AbstractEnumFeature;
import ru.ifmo.ctlab.ml.core.feat.EnumFeature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class NumericVector implements Type {

    public class NumericVectorInstance implements Instance {

        private final double[] array;
        public final Type type;

        public NumericVectorInstance(double[] values, Type type) {
            array = Arrays.copyOf(values, length);
            this.type = type;
        }

        @Override
        public int compare(Instance other, int feature) {
            return Double.compare(numValue(feature), other.numValue(feature));
        }

        @Override
        public double distance(Instance other, int feature) {
            return Math.abs(numValue(feature) - other.numValue(feature));
        }

        @Override
        public Type type() {
            return type;
        }

        @Override
        public int intValue(int index) {
            return (int) Math.rint(array[index]);
        }

        @Override
        public double numValue(int index) {
            return array[index];
        }
    }

    public final List<NumericFeature> numericFeatures;
    public final int length;

    public NumericVector(final int length, final int numberOfClass) {
        this.length = length;
        this.numericFeatures = new ArrayList<NumericFeature>(length);

        for (int i = 0; i < length; i++) {
            final int index = i;
            numericFeatures.add(new NumericValue(index, this));
        }

    }

    public Instance create(double[] values) {
        return new NumericVectorInstance(values, this);
    }

    @Override
    public int allValues() {
        return length;
    }

    @Override
    public int cmpValues() {
        return 0;
    }

    @Override
    public int intValues() {
        return 0;
    }

    @Override
    public String nameOfValue(int index) {
        return "val" + index;
    }

    @Override
    public int numValues() {
        return length;
    }

}
