package eval_comp.lab2;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class Plotter {

    public static int convertX(double x) {
        return (int) Math.round(83.9844 * x + 442);
    }

    public static int convertY(double y) {
        return (int) Math.round(444 - 83.9844 * y);
    }

    public static void drawPointsOnGraph(List<Instance> instances, String src, String dst) throws IOException {

        BufferedImage image = ImageIO.read(new File(src));
        int w = image.getWidth(), h = image.getHeight();
        int rad = 3;
        int color = 0xFF0000FF;

        for (Instance instance : instances) {
            int px = convertX(instance.x);
            int py = convertY(instance.y);

            for (int dx = -rad; dx <= rad; dx++) {
                for (int dy = -rad; dy <= rad; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) <= rad) {
                        int x = px + dx, y = py + dy;
                        if (0 <= x && x < w && 0 <= y && y < h) {
                            image.setRGB(x, y, color);
                        }
                    }
                }
            }
        }

        ImageIO.write(image, "png", new File(dst));

    }
}
