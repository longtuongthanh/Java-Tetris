package com.tetris.mechanic;

import java.util.*;

import javafx.scene.paint.Color;

public class TetrisPiece implements Cloneable {
	public int[] coordX;
    public int[] coordY;
    public TetrisPieceType type;
    public static final TetrisPieceType[] bag = new TetrisPieceType[] {
        TetrisPieceType.I,
        TetrisPieceType.L,
        TetrisPieceType.O,
        TetrisPieceType.J,
        TetrisPieceType.S,
        TetrisPieceType.T,
        TetrisPieceType.Z
    };
    public static Deque<TetrisPiece> currentBag = new LinkedList<TetrisPiece>();
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

    public enum TetrisPieceType
    {
        I,
        L,
        J,
        Z,
        S,
        T,
        O
    }
    
    public TetrisPiece(TetrisPieceType type)
    {
        this.type = type;
        switch (type)
        {
            case I:
                coordX = new int[] { 0, 0, 0, 0 };
                coordY = new int[] { -1, 0, 1, 2 };
                break;
            case L:
                coordX = new int[] { 1, 1, 0, -1 };
                coordY = new int[] { 1, 0, 0, 0 };
                break;
            case J:
                coordX = new int[] { -1, 1, 0, -1 };
                coordY = new int[] { 1, 0, 0, 0 };
                break;
            case Z:
                coordX = new int[] { -1, 0, 0, 1 };
                coordY = new int[] { 1, 1, 0, 0 };
                break;
            case S:
                coordX = new int[] { -1, 0, 0, 1 };
                coordY = new int[] { 0, 0, 1, 1 };
                break;
            case T:
                coordX = new int[] { 0, 1, 0, -1 };
                coordY = new int[] { 1, 0, 0, 0 };
                break;
            case O:
                coordX = new int[] { 1, 1, 0, 0 };
                coordY = new int[] { 1, 0, 0, 1 };
                break;
        }
    }
    public void RotateUpToLeft()
    {
        if (type == TetrisPieceType.O)
            return;

        // swap coordX & coordY
        int[] temp = coordX;
        coordX = coordY;
        coordY = temp;

        if (type == TetrisPieceType.I)
            return;

        // invert X axis
        for (int i = 0; i < 4; i++)
            coordX[i] = -coordX[i];
    }
    public void RotateUpToRight()
    {
        if (type == TetrisPieceType.O) 
            return;

        // swap coordX & coordY
        int[] temp = coordX;
        coordX = coordY;
        coordY = temp;

        if (type == TetrisPieceType.I)
            return;

        // invert Y axis
        for (int i = 0; i < 4; i++)
            coordY[i] = -coordY[i];
    }

    public static TetrisPiece GetNewTetrisPiece()
    {
        if (currentBag.size() <= 0)
        {
            ResetBag();
        }

        TetrisPiece result = currentBag.removeFirst();

        return result;
    }

    public static void ResetBag()
    {
        currentBag.clear();
        
        for (TetrisPieceType item : bag)
        {
            currentBag.add(new TetrisPiece(item));
        }
    }

    public Object Clone()
    {
    	try {
	        TetrisPiece temp = (TetrisPiece) super.clone();
	        temp.coordX = temp.coordX.clone();
	        temp.coordY = temp.coordY.clone();
	    	
	        return temp;
    	} catch(Exception e) {
    		return null;
    	}
    }
}
