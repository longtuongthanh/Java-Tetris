package com.tetris.mechanic;

import java.util.*;

import com.application.TetrisPieceType;

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
        TetrisPieceType.Z,
        //TetrisPieceType.X,
        //TetrisPieceType.BOMB
    };
    public static Deque<TetrisPiece> currentBag = new LinkedList<TetrisPiece>();

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
			case BOMB:
				coordX = new int[] { 0 };
				coordY = new int[] { 0 };
				break;
			case X:
				coordX = new int[] { 0, 1, 1, -1, -1 };
				coordY = new int[] { 0, 1, -1, 1, -1 };
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
        for (int i = 0; i < coordX.length; i++)
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
        for (int i = 0; i < coordY.length; i++)
            coordY[i] = -coordY[i];
    }

    public static TetrisPiece GetNewTetrisPiece()
    {
        if (currentBag.size() <= 0)
        {
            ResetBag();
        }

        TetrisPiece result = currentBag.removeFirst();
        
        if (result == null)
        	System.out.println("null");

        return result;
    }
    
    public static TetrisPiece PeekNextTetrisPiece() {
    	if (!currentBag.isEmpty()) {
    		return currentBag.getFirst();
    	}
    	return null;
    }

    public static void ResetBag()
    {
        currentBag.clear();
        
        ArrayList<TetrisPiece> temp = new ArrayList<TetrisPiece>();
        
        for (TetrisPieceType item : bag)
        {
            temp.add(new TetrisPiece(item));
        }
        
        Random rng = new Random();
        while (temp.size() > 0) {
        	int i = rng.nextInt(temp.size());
        	currentBag.add(temp.remove(i));
        }
        /*
        for (TetrisPieceType item : bag)
        {
            currentBag.add(new TetrisPiece(item));
        }
        //*/
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
    
    public void AddTetrisPieceToBag(TetrisPieceType type) {
    	currentBag.addFirst(new TetrisPiece(type));
    }
}
