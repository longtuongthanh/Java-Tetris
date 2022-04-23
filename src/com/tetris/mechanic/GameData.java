package com.tetris.mechanic;

import java.util.*;
import java.util.stream.Collectors;

import javafx.scene.paint.Color;

public class GameData {
	public List<List<Color>> grid;
    public int score;

    private TetrisPiece _tile;

    public int tileOffsetX;
    public int tileOffsetY;

    public int kickX = 0;
    public int kickY = 0;

    public int level;
    public int lines;
    public float dropstep;
    public float flashTextDuration;
    public float reductionSpeed;

    public boolean paused = false;
    public boolean gameOver = false;

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
        reductionSpeed = 0.95f;
        tileOffsetX = GameConstant.maxX / 2;
        tileOffsetY = GameConstant.maxY - 2;
        grid = new ArrayList<List<Color>>();
        for (int i = 0; i < GameConstant.maxY; i++)
            grid.add(NewGridRow());
    }
    
    public void SpeedUp()
    {
        if (level < GameConstant.maxLevel)
        {
            dropstep *= reductionSpeed;
            flashTextDuration *= reductionSpeed;
            level++;
        }
    }

    public void ResetKick()
    {
        kickX = 0;
        kickY = 0;
    }

    public void GetNewTetrisPiece()
    {
        _tile = TetrisPiece.GetNewTetrisPiece();
        tileOffsetX = GameConstant.maxX / 2;
        tileOffsetY = GameConstant.maxY - 2;
        ResetKick();
    }

    public void ClearRow(int index)
    {
        grid.remove(index);
        grid.add(NewGridRow());
    }

    private List<Color> NewGridRow()
    {
        List<Color> row = new ArrayList<Color>();
        for (int j = 0; j < GameConstant.maxX; j++)
            row.add(GameConstant.nullColor);
        return row;
    }
    
    public List<List<Color>> GridClone()
    {
        return grid.stream().map(
                item -> new ArrayList<Color>(item)
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
        gameOver = false;
        paused = false;
        grid = new ArrayList<List<Color>>();
        for (int i = 0; i < GameConstant.maxY; i++)
            grid.add(NewGridRow());
        TetrisPiece.ResetBag();
        GetNewTetrisPiece();
    }
}
