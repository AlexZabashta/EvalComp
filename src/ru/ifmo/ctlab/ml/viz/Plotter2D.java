package ru.ifmo.ctlab.ml.viz;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Locale;

import ru.ifmo.ctlab.ml.core.data.Instance;
import ru.ifmo.ctlab.ml.core.data.val.NumericValue;
import ru.ifmo.ctlab.ml.core.feat.NumericFeature;

public class Plotter2D {

    public static BufferedImage plot(List<Instance> dataset, NumericFeature xf, NumericFeature yf, int w, int h) {

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_BGR);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                image.setRGB(x, y, 0xFFFFFFFF);
            }
        }

        double l = Double.POSITIVE_INFINITY, r = -l, u = r, d = l;

        for (Instance val : dataset) {
            double x = xf.numFeature(val);
            double y = yf.numFeature(val);

            l = Math.min(l, x);
            r = Math.max(r, x);
            d = Math.min(d, y);
            u = Math.max(u, y);
        }

        double scale = Math.max(r - l, u - d);

        for (Instance val : dataset) {

            double x = xf.numFeature(val);
            double y = yf.numFeature(val);

            int px = (int) Math.rint(w * (x - l) / scale);
            int py = (int) Math.rint(h * (y - d) / scale);

            int rad = 2;

            for (int dx = -rad; dx <= rad; dx++) {
                for (int dy = -rad; dy <= rad; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) <= rad) {
                        int ix = px + dx;
                        int iy = py + dy;

                        if (0 <= ix && ix < w) {
                            if (0 <= iy && iy < h) {
                                image.setRGB(ix, h - iy - 1, 0xFFFF0000);
                            }
                        }

                    }
                }
            }

        }

        return image;
    }
}
