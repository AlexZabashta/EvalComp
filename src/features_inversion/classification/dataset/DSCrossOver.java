package features_inversion.classification.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import eval_comp.optimization.Crossover;
import features_inversion.util.FeaturePoint;
import features_inversion.util.MetaFeaturesExtractor;

public class DSCrossOver implements Crossover<FeaturePoint<double[][][]>> {

    public final MetaFeaturesExtractor<double[][][]> extractor;

    public DSCrossOver(MetaFeaturesExtractor<double[][][]> extractor) {
        this.extractor = extractor;
    }

    @Override
    public List<FeaturePoint<double[][][]>> cross(FeaturePoint<double[][][]> sourceX, FeaturePoint<double[][][]> sourceY, Random random) {
        List<FeaturePoint<double[][][]>> result = new ArrayList<FeaturePoint<double[][][]>>();

        double[][][] ds1 = sourceX.object, ds2 = sourceY.object;
        int numArttr1 = ds1[0][0].length;
        int numArttr2 = ds2[0][0].length;

        double[][] p1 = ds1[0], p2 = ds1[1];

        double[][] p5 = ds1[0], p6 = ds1[1];

        return result;
    }

}
