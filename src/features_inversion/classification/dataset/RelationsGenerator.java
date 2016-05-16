package features_inversion.classification.dataset;

import java.util.Random;

import misc.Permutation;

public class RelationsGenerator {
    public double[][] genaddRelations(double[][] values, int size, int numAttr, Random random) {
        int n = values.length;

        double[][] extRel = new double[size][numAttr];

        for (int i = 0; i < size; i++) {
            int[] p = Permutation.random(numAttr, random);

            int j = 0;
            while (j < numAttr) {
                int inst = random.nextInt(n);
                int len = random.nextInt(numAttr - j) + 1;

                for (int k = 0; k < len; k++) {
                    int attribute = p[j + k];
                    extRel[i][attribute] = values[inst][attribute];
                }

                j += len;
            }

        }

        return extRel;
    }
}
