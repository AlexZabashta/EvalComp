package ru.ifmo.ctlab.ml.tests;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ru.ifmo.ctlab.ml.core.DoubleVector;
import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.Type;
import ru.ifmo.ctlab.ml.core.data.val.NumericValue;
import ru.ifmo.ctlab.ml.core.feat.AbstractNumericFeature;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;
import ru.ifmo.ctlab.ml.core.feat.PCA;
import ru.ifmo.ctlab.ml.viz.Plotter2D;

public class TestPCA {
    public static void main(String[] args) throws IOException {
        List<Instance> data = new ArrayList<Instance>();

        Type featuresType = null;

        for (int i = 0; i < 100; i++) {
            data.add(new DoubleVector(new double[] { i }, featuresType));
        }

        List<NumericFeature> sourse = new ArrayList<NumericFeature>();

        sourse.add(new AbstractNumericFeature() {

            @Override
            public double numFeature(Instance instance) {
                double x = instance.numValue(0);
                return x;
            }

            @Override
            public String featureName() {
                return "x";
            }
        });

        sourse.add(new AbstractNumericFeature() {

            @Override
            public double numFeature(Instance instance) {
                double x = instance.numValue(0);
                return x * x;
            }

            @Override
            public String featureName() {
                return "x * x";
            }
        });

        sourse.add(new AbstractNumericFeature() {

            @Override
            public double numFeature(Instance instance) {
                double x = instance.numValue(0);
                return Math.sin(x / 33);
            }

            @Override
            public String featureName() {
                return "Math.sin(x / 33)";
            }
        });

        List<NumericFeature> target = PCA.pca(data, sourse, 2);
        RenderedImage image = Plotter2D.plot(data, target.get(0), target.get(1), 1024, 1024);
        ImageIO.write(image, "png", new File("plot.png"));

        // JFrame frame = new ImageViewer(image);
        // frame.setVisible(true);
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
