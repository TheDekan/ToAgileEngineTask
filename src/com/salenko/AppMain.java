package com.salenko;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class AppMain {

	public static void main(String[] args) {

		BufferedImage image1 = null;
		BufferedImage image2 = null;
		BufferedImage image3 = null;

		try {
			image1 = ImageIO.read(new File("resources\\image1.png"));
			image2 = ImageIO.read(new File("resources\\image2.png"));
			image3 = ImageIO.read(new File("resources\\image2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		int w1 = image1.getWidth();
		int h1 = image1.getHeight();

		int w2 = image2.getWidth();
		int h2 = image2.getHeight();

		if (w1 > 0 && h1 > 0 && w2 > 0 && h2 > 0 && w1 == w2 && h1 == h2) {

			// get difference between images
			boolean[][] difference = new boolean[w1][h1];

			for (int i = 0; i < w1; i++) {
				for (int j = 0; j < h1; j++) {
					if (image1.getRGB(i, j) < 0.9 * image2.getRGB(i, j)
							&& image1.getRGB(i, j) > 1.1 * image2.getRGB(i, j)) {
						difference[i][j] = false;
					} else {
						difference[i][j] = true;
					}
				}
			}

			// calculate rectangles
			ArrayList<Rectangle> rects = MakeOutLines.calculateRectangles(difference, w1, h1);

			// draw them to image3
			for (Rectangle rect : rects) {
				Graphics2D graph = image3.createGraphics();
				graph.setColor(Color.RED);
				graph.draw(rect);
				graph.dispose();
			}

			// save image3 to file
			try {
				ImageIO.write(image3, "png", new File("resources\\image3.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("different sizes!");
		}
	}

}
