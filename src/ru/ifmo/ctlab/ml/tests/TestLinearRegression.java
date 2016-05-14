package ru.ifmo.ctlab.ml.tests;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import ru.ifmo.ctlab.ml.LinearRegression;
import ru.ifmo.ctlab.ml.core.DoubleVector;
import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.Type;
import ru.ifmo.ctlab.ml.core.data.val.NumericValue;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class TestLinearRegression {

    static void println(double[] array) {
        for (double val : array) {
            System.out.printf(Locale.ENGLISH, "%7.3f ", val);
        }
        System.out.println();
    }

    public static void main(String[] args) throws FileNotFoundException {

        Random random = new Random();

        int numInst = 100;

        int numS = 10; // Number of source features.
        int numT = 5; // Number of target features.

        int instLen = numS + numT;

        double[][] alpha = new double[numT][numS];

        double noise = 1; // Noise factor.

        for (int i = 0; i < numT; i++) {
            for (int j = 0; j < numS; j++) {
                alpha[i][j] = random.nextGaussian() * (i + j);
            }
        }

        List<Instance> data = new ArrayList<Instance>();

        Type featuresType = null;

        for (int id = 0; id < numInst; id++) {
            double[] inst = new double[instLen];

            int index = 0;
            for (int i = 0; i < numS; i++, index++) {
                inst[index] = random.nextGaussian();
            }

            for (int i = 0; i < numT; i++, index++) {

                // Add some noise in data
                inst[index] = random.nextGaussian() * noise;

                for (int j = 0; j < numS; j++) {
                    inst[index] += alpha[i][j] * inst[j];
                }
            }

            data.add(new DoubleVector(inst, featuresType));
        }

        List<NumericFeature> sf = new ArrayList<NumericFeature>();
        List<NumericFeature> tf = new ArrayList<NumericFeature>();
        {
            int index = 0;
            for (int i = 0; i < numS; i++) {
                sf.add(new NumericValue((index++), featuresType));
            }
            for (int i = 0; i < numT; i++) {
                tf.add(new NumericValue((index++), featuresType));
            }
        }

        LinearRegression c = new LinearRegression(data, sf, tf);

        for (int i = 0; i < numT; i++) {
            println(alpha[i]);
            println(c.getCoef(i));
            System.out.println();
        }

    }
}
