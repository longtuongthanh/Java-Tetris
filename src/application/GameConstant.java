package application;

import java.util.HashMap;
import java.util.Map;

import com.application.TetrisPieceType;

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

    public static final Map<TetrisPieceType, Color> tileColor;
    static {
    	tileColor = new HashMap<TetrisPieceType, Color>();

        tileColor.put(TetrisPieceType.I, new Color(1,0,0.5f,1));
        tileColor.put(TetrisPieceType.L, new Color(1,0,1,1));
        tileColor.put(TetrisPieceType.O, new Color(1,0,0,1));
        tileColor.put(TetrisPieceType.J, new Color(1,1,0,1));
        tileColor.put(TetrisPieceType.S, new Color(0,1,1,1));
        tileColor.put(TetrisPieceType.T, new Color(0,0,1,1));
        tileColor.put(TetrisPieceType.Z, new Color(0,1,0,1));
    }

}
