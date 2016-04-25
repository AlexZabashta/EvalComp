package lab1.misc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import lab1.Instance;

public class Plotter {

	public static int convertX(double x) {
		return (int) Math.round(27 * x + 319);
	}

	public static int convertY(double y) {
		return (int) Math.round(291 - (80 * y) / 3);
	}

	public static void drawPointsOnGraph(List<Instance> instances, String src, String dst) throws IOException {

		BufferedImage image = ImageIO.read(new File(src));
		int w = image.getWidth(), h = image.getHeight();
		int rad = 3;
		int color = 0xFFFF0000;

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
