package features_inversion.classification.dataset;

import java.util.Arrays;
import java.util.List;

import com.ifmo.recommendersystem.metafeatures.MetaFeatureExtractor;
import com.ifmo.recommendersystem.utils.MetaFeatureExtractorsCollection;

import features_inversion.classification.ArffConverter;
import features_inversion.util.MetaFeaturesExtractor;
import weka.core.Instances;

public class DAMetaFeatureExtractor implements MetaFeaturesExtractor<double[][][]> {

    private final List<MetaFeatureExtractor> list;
    private final int n;

    public DAMetaFeatureExtractor() {
        list = MetaFeatureExtractorsCollection.getMetaFeatureExtractors();
        n = list.size();
    }

    @Override
    public int numberOfFeatures() {
        return n;
    }

    @Override
    public double[] extract(double[][][] object) {
        double[] features = new double[n];
        Arrays.fill(features, Double.NaN);

        Instances instances = ArffConverter.convert(object);
        if (instances != null) {
            for (int i = 0; i < n; i++) {
                try {
                    features[i] = list.get(i).extractValue(instances);
                } catch (Exception e) {
                }
            }
        }
        return features;
    }

}
