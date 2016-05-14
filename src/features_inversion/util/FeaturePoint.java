package features_inversion.util;

import eval_comp.optimization.Measurable;

public class FeaturePoint<T> extends Point implements Measurable {

    public final Point target;
    private final double fitnessFunction;
    public final T object;

    public FeaturePoint(T object, MetaFeaturesExtractor<T> extractor) {
        super(extractor.extract(object));
        this.object = object;
        this.target = null;
        this.fitnessFunction = 0;
    }

    public FeaturePoint(Point target, FeaturePoint<T> featurePoint, double[] std) {
        super(featurePoint.coordinates());
        double sumOfSquares = 0;
        this.target = target;
        this.object = featurePoint.object;

        int d = dimension();

        if (std.length != d || target.dimension() != d) {
            throw new IllegalArgumentException("Point has different dimension");
        }

        for (int i = 0; i < d; i++) {
            double diff = (super.coordinate(i) - target.coordinate(i)) / std[i];
            sumOfSquares += diff * diff;
        }
        this.fitnessFunction = Math.sqrt(sumOfSquares);
    }

    @Override
    public double fitnessFunction() {
        return fitnessFunction;
    }

}
