package application;

import javafx.scene.paint.Color;

public class GameConstant {
    public static final int maxX = 10;
    public static final int maxY = 20;
    public static boolean IsCoordInBound(int x, int y) {
        return 0 <= x && x < maxX && 0 <= y && y < maxY;
    }
    public static final int[] ScoreForRow = new int[] { 100, 300, 500, 800 };
    public static final int maxLevel = 20;
    
    public static final Color nullColor = new Color(0.1f,0.1f,0.1f,1);
}
