package ru.ifmo.ctlab.ml.core.feat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import Jama.EigenvalueDecomposition;
import Jama.Matrix;
import ru.ifmo.ctlab.ml.core.data.Instance;

public class PCA {

    public static List<NumericFeature> pca(List<Instance> dataset, final List<NumericFeature> sourse, int m) {

        final int n = sourse.size();

        if (m > n) {
            // FIXME Add exception?
            return sourse;
        }

        double[] sum1 = new double[n];
        double[][] sum2 = new double[n][];

        int k = dataset.size();

        for (Instance instance : dataset) {
            for (int i = 0; i < n; i++) {
                sum2[i] = new double[i + 1];

                double x = sourse.get(i).numFeature(instance);
                sum1[i] += x;
                sum2[i][i] += x * x;

                for (int j = 0; j < i; j++) {
                    double xy = x * sourse.get(j).numFeature(instance);
                    sum2[i][j] += xy;
                }
            }
        }

        final double[] std = new double[n];
        final double[] mean = new double[n];

        for (int i = 0; i < n; i++) {
            mean[i] = sum1[i] / k;
            double var = (sum1[i] * sum1[i] / k - sum2[i][i]) / (k - 1);
            std[i] = Math.sqrt(var);
        }

        double[][] cor = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                double cov = sum2[i][j] / k - mean[i] * mean[j];
                double sigma = std[i] * std[j];
                cor[i][j] = cor[j][i] = cov / sigma;

                if (Double.isNaN(cor[i][j])) {
                    System.out.println(i + " " + j);
                }

            }
        }

        Matrix matrix = new Matrix(cor, n, n);
        EigenvalueDecomposition e = matrix.eig();

        final double[] real = e.getRealEigenvalues();
        final double[] imag = e.getImagEigenvalues();
        final double[] norm = new double[n];
        for (int i = 0; i < n; i++) {
            norm[i] = real[i] * real[i] + imag[i] * imag[i];
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }

        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                // FIXME norm[] or real[] ???
                return -Double.compare(norm[y], norm[x]);
            }
        });

        final double[][] ev = e.getV().getArray();

        List<NumericFeature> dest = new ArrayList<>();
        for (int f = 0; f < m; f++) {
            final int index = order[f];

            dest.add(new AbstractNumericFeature() {

                @Override
                public String featureName() {
                    // FIXME add name
                    return "pca" + index;
                }

                @Override
                public double numFeature(Instance instance) {
                    double scalar = 0;

                    for (int i = 0; i < n; i++) {

                        double val = (sourse.get(i).numFeature(instance) - mean[i]) / std[i];

                        // FIXME ev[i][index] or ev[index][i] ???
                        scalar += ev[index][i] * val;
                    }

                    return scalar;
                }

            });
        }

        return dest;
    }

}
