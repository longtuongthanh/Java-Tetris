package com.tetris.mechanic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.application.TetrisPieceType;

import application.GameConstant;
import javafx.scene.paint.Color;

public class GameData {
	public List<List<TetrisPieceType>> grid;
	TetrisPiece next;
    public int score;

    private TetrisPiece _tile;

    public int tileOffsetX;
    public int tileOffsetY;

    public int kickX = 0;
    public int kickY = 0;

    // Speed of falling
    public int level;
    // Number of lines cleared
    public int lines;
    // Number in seconds between dropdown
    public float dropstep;
    // Number in seconds how long text flash when score increase
    public float flashTextDuration;
    // How much dropstep & flashTextDuration reduces, in %.
    public float reductionSpeed;
    // TODO: Settings
    public float getDefaultReductionSpeed() {
    	return 0.95f;
    }

    public boolean paused = false;
    public boolean gameOver = true;

	public GameTimer timer;

    public TetrisPiece getTile()
    {
            if (_tile == null)
                _tile = TetrisPiece.GetNewTetrisPiece();
            return _tile; 
    }
    public void setTile(TetrisPiece value) {
        _tile = value;
    }

    public GameData()
    {
        score = 0;
        lines = 0;
        level = 1;
        dropstep = 1f;
        flashTextDuration = 2f;
        reductionSpeed = getDefaultReductionSpeed();
        tileOffsetX = GameConstant.maxX / 2;
        tileOffsetY = GameConstant.maxY - 2;
        grid = new ArrayList<List<TetrisPieceType>>();
        for (int i = 0; i < GameConstant.maxY; i++)
            grid.add(NewGridRow());
        GetNewTetrisPiece();
    }
    
    public void SpeedUp()
    {
        if (level < GameConstant.maxLevel)
        {
            dropstep *= reductionSpeed;
            flashTextDuration *= reductionSpeed;
            level++;

    		timer.SetDelay(dropstep);
        }
    }

    public void ResetKick()
    {
        kickX = 0;
        kickY = 0;
    }

    public void GetNewTetrisPiece()
    {
        _tile = next;
        next = TetrisPiece.GetNewTetrisPiece();
        tileOffsetX = GameConstant.maxX / 2;
        tileOffsetY = GameConstant.maxY - 2;
        ResetKick();
    }
    public static TetrisPiece PeekNextTetrisPiece() {
    	return TetrisPiece.PeekNextTetrisPiece();
    }

    public void ClearRow(int index)
    {
        grid.remove(index);
        grid.add(NewGridRow());
    }

    private List<TetrisPieceType> NewGridRow()
    {
        List<TetrisPieceType> row = new ArrayList<TetrisPieceType>();
        for (int j = 0; j < GameConstant.maxX; j++)
            row.add(null);
        return row;
    }
    
    public List<List<TetrisPieceType>> GetBoardColor()
    {
    	List<List<TetrisPieceType>> gridClone = GridClone();
    	for (int i = 0; i < _tile.coordX.length; i++) {
    		int x = tileOffsetX + _tile.coordX[i];
    		int y = tileOffsetY + _tile.coordY[i];
    		
    		if (GameConstant.IsCoordInBound(x, y))
    			gridClone.get(y).set(x, _tile.type);
    	}
    	return gridClone;
    }
    
    public List<List<TetrisPieceType>> GridClone(){
        return grid.stream().map(
                item -> new ArrayList<TetrisPieceType>(item)
            ).collect(Collectors.toList());
    }

    public void ResetData()
    {
        score = 0;
        lines = 0;
        level = 1;
        dropstep = 1f;
        reductionSpeed = 0.95f;
        flashTextDuration = 2f;
        reductionSpeed = getDefaultReductionSpeed();
        gameOver = false;
        paused = false;
        grid = new ArrayList<List<TetrisPieceType>>();
        for (int i = 0; i < GameConstant.maxY; i++)
            grid.add(NewGridRow());
        TetrisPiece.ResetBag();
        GetNewTetrisPiece();
        timer.Reset();
        GetNewTetrisPiece();
    }
}
