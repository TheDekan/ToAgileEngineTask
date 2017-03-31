package com.salenko;

import java.awt.Rectangle;
import java.util.ArrayList;

public class MakeOutLines {

    public static ArrayList<Rectangle> calculateRectangles(boolean[][] matrixx, int x, int y) {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (matrixx[i][j] == true) {
                    Rectangle rect = new Rectangle(i - 2, j - 2, 5, 5);
                    if (rectangles.isEmpty()) {
                        rectangles.add(rect);
                    } else {
                        Boolean added = true;
                        while (added) {
                            added = false;
                            for (int ii = 0; ii < rectangles.size(); ii++) {
                                if (rectangles.get(ii).intersects(rect)) {
                                    rect.add(rectangles.get(ii));
                                    rectangles.remove(rectangles.get(ii));
                                    added = true;
                                    break;
                                }
                            }
                        }
                        rectangles.add(rect);
                    }
                }
            }
        }
        return rectangles;
    }

}
