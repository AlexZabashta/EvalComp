package features_inversion.classification;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ifmo.recommendersystem.metafeatures.MetaFeatureExtractor;
import com.ifmo.recommendersystem.utils.MetaFeatureExtractorsCollection;

import eval_comp.optimization.result.Result;
import features_inversion.classification.fus.Fusion;
import weka.core.Instances;

public class CrMuGeneticSearch {

    public static void main(String[] args) {
        // int generationSize = generation.size();
        // int numFunctionEval = 0;
        //
        // int numOfChild = Math.max(generationSize, (int) (generationSize *
        // pc));
        // int numOfMut = Math.max(generationSize, (int) (generationSize * pm));
        // Result<T> result = null;
        // for (int step = 0; step < maxIter; step++) {
        // result = new Result<T>(step, numFunctionEval, generation);
        // if (results != null) {
        // results.add(result);
        // }
        //
        // if (stoppingCriterion.test(result)) {
        // break;
        // }
        //
        // List<T> newGeneration = new ArrayList<T>();
        // newGeneration.addAll(generation);
        //
        // List<T> parents = selectBest.perfom(generation, numOfChild);
        // List<T> children = crossoverStep.perfom(parents, numOfChild);
        // numFunctionEval += numOfChild;
        // newGeneration.addAll(children);
        //
        // List<T> mutants = mutationStep.perfom(newGeneration, numOfMut);
        // numFunctionEval += numOfMut;
        // newGeneration.addAll(mutants);
        //
        // generation = selectBest.perfom(newGeneration, generationSize);
        // }
        //
        // return result;
    }

    public static void print(String name, List<double[]> points, double tx, double ty) throws IOException {

        int w = 1600;
        int h = 1200;

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                image.setRGB(x, h - y - 1, 0xFFFFFF);
            }
        }

        int rad = 4;
        for (double[] p : points) {
            if (p == null) {
                continue;
            }
            int px = (int) Math.floor(p[0] * w);
            int py = (int) Math.floor(p[1] * h);

            for (int dx = -rad; dx <= rad; dx++) {
                for (int dy = -rad; dy <= rad; dy++) {
                    int ds = Math.abs(dx) + Math.abs(dy);
                    if (ds <= rad) {
                        int x = px + dx;
                        int y = py + dy;
                        if (0 <= x && x < w && 0 <= y && y < h) {
                            image.setRGB(x, h - y - 1, 0x00AF00);
                        }

                    }

                }
            }
        }

        {
            int y = (int) Math.floor(ty * h);
            if (y >= h) {
                y = h - 1;
            }
            for (int x = 0; x < w; x++) {
                image.setRGB(x, h - y - 1, 0xFF0000);
            }
        }

        {
            int x = (int) Math.floor(tx * w);
            if (x >= w) {
                x = w - 1;
            }
            for (int y = 0; y < h; y++) {
                image.setRGB(x, h - y - 1, 0xFF0000);
            }
        }

        ImageIO.write(image, "png", new File("results/SimpleGeneticSearch/" + name + ".png"));
    }

    public static void main0(String[] args) throws Exception {
        File dataFolder = new File("data/carff");

        Random random = new Random();
        List<MetaFeatureExtractor> mfel = MetaFeatureExtractorsCollection.getMetaFeatureExtractors();
        int fx = 33, fy = 38;

        boolean test = false;

        double tx = 0.8, ty = 0.8;
        int top = 20;

        MetaFeatureExtractor ex = mfel.get(fx);
        MetaFeatureExtractor ey = mfel.get(fy);

        List<Instances> data = new ArrayList<>();
        for (File arff : dataFolder.listFiles()) {
            try {
                Instances instances = new Instances(new FileReader(arff));
                instances.setClassIndex(instances.numAttributes() - 1);
                data.add(instances);
            } catch (Exception err) {
                System.out.println(arff + " " + err.getMessage());
            }
        }

        Fusion fusion = new Fusion(random);

        double xSum0 = 0, xSum1 = 0, xSum2 = 0;
        double ySum0 = 0, ySum1 = 0, ySum2 = 0;

        double l = Double.POSITIVE_INFINITY, r = Double.NEGATIVE_INFINITY;
        double d = Double.POSITIVE_INFINITY, u = Double.NEGATIVE_INFINITY;

        for (int step = 0; step < 20; step++) {
            List<double[]> points = new ArrayList<>();
            for (Instances instances : data) {
                try {
                    double x = ex.extractValue(instances);
                    double y = ey.extractValue(instances);

                    if (step == 0) {
                        if (!Double.isNaN(x) && !Double.isInfinite(x)) {
                            double val = 1;
                            xSum0 += val;
                            val *= x;
                            xSum1 += val;
                            val *= x;
                            xSum2 += val;
                            l = Math.min(l, x);
                            r = Math.max(r, x);
                        }

                        if (!Double.isNaN(y) && !Double.isInfinite(y)) {
                            double val = 1;
                            ySum0 += val;
                            val *= y;
                            ySum1 += val;
                            val *= y;
                            ySum2 += val;
                            d = Math.min(d, y);
                            u = Math.max(u, y);
                        }
                    }

                    points.add(new double[] { x, y });
                } catch (Exception err) {
                    points.add(new double[] { Double.NaN, Double.NaN });
                }
            }
            if (step == 0) {
                double xMean = xSum1 / xSum0;
                double xDisp = xSum2 / xSum0 - xMean * xMean;
                double xSigm = Math.sqrt(xDisp);

                double yMean = ySum1 / ySum0;
                double yDisp = ySum2 / ySum0 - yMean * yMean;
                double ySigm = Math.sqrt(yDisp);

                double scale = 3;

                l = Math.max(l, xMean - scale * xSigm);
                r = Math.min(r, xMean + scale * xSigm);
                d = Math.max(d, yMean - scale * ySigm);
                u = Math.min(u, yMean + scale * ySigm);

            }

            for (double[] p : points) {
                double x = p[0];
                if (Double.isNaN(x) || Double.isInfinite(x)) {
                    p[0] = 100;
                } else {
                    p[0] = (x - l) / (r - l);
                }

                double y = p[1];
                if (Double.isNaN(y) || Double.isInfinite(y)) {
                    p[1] = 100;
                } else {
                    p[1] = (p[1] - d) / (u - d);
                }

                if (!test && step == 0 && Math.hypot(p[0] - tx, p[1] - ty) < 0.2) {
                    p[0] = p[1] = 100;
                }
            }

            int m = points.size();
            Integer[] order = new Integer[m];
            for (int i = 0; i < m; i++) {
                order[i] = i;
            }

            double best = 200;
            double[] dist = new double[m];
            for (int i = 0; i < m; i++) {
                double[] p = points.get(i);
                dist[i] = Math.hypot(p[0] - tx, p[1] - ty);
                best = Math.min(best, dist[i]);
            }
            System.out.println(best);
            print("step" + step, points, tx, ty);

            Arrays.sort(order, new Comparator<Integer>() {
                @Override
                public int compare(Integer i, Integer j) {
                    return Double.compare(dist[i], dist[j]);
                }
            });

            List<Instances> betterData = new ArrayList<>();

            double[][][][] vals = new double[top][][][];
            for (int i = 0; i < top; i++) {
                Instances instances = data.get(order[i]);
                betterData.add(instances);
                vals[i] = ArffConverter.convert(instances, 20);
            }

            for (int i = 0; i < top; i++) {
                for (int j = i + 1; j < top; j++) {
                    double[][][] v = fusion.fuse(vals[i], vals[j]);
                    betterData.add(ArffConverter.convert(v));
                }
            }
            data = betterData;

            if (test) {
                break;
            }
        }

    }
}