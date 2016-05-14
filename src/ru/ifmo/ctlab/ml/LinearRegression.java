package ru.ifmo.ctlab.ml;

import java.util.List;

import Jama.Matrix;
import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class LinearRegression {

    final double[][] alpha;
    final int n, m;

    public double[] getCoef(int tid) {
        return alpha[tid].clone();
    }

    final List<NumericFeature> f;

    public LinearRegression(List<Instance> dataset, List<NumericFeature> features, List<NumericFeature> target) {
        f = features;
        n = target.size();
        m = features.size();

        double[][] a = new double[m][m];
        double[][] b = new double[n][m];

        for (Instance instance : dataset) {
            for (int i = 0; i < m; i++) {
                double val = f.get(i).numFeature(instance);
                for (int j = 0; j < i; j++) {
                    double mul = val * f.get(j).numFeature(instance);
                    a[i][j] += mul;
                    a[j][i] += mul;
                }

                a[i][i] += val * val;

                for (int tid = 0; tid < n; tid++) {
                    b[tid][i] += val * target.get(tid).numFeature(instance);
                }
            }
        }

        alpha = (new Matrix(b, n, m)).times((new Matrix(a, m, m)).inverse()).getArray();
    }

    public double[] solve(Instance instance) {
        double[] res = new double[n];

        for (int tid = 0; tid < n; tid++) {
            for (int i = 0; i < m; i++) {
                res[tid] += alpha[tid][i] * f.get(i).numFeature(instance);
            }
        }

        return res;
    }

}
