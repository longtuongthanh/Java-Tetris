package com.tetris.mechanic;

import java.util.*;
import java.util.function.Consumer;

import com.application.TetrisPieceType;

import application.GameConstant;
import javafx.scene.paint.Color;

public class TetrisController {
	public Consumer<GameData> onMove;
	public Consumer<List<List<TetrisPieceType>>> notifyBoardChanged;
	@Semi_pureFunction
	protected boolean Move(GameData data, int offsetX, int offsetY)
    {
        TetrisPiece tile = data.getTile();
        int x = data.tileOffsetX + offsetX;
        int y = data.tileOffsetY + offsetY;
        if (CheckFit(data.grid, tile, x, y))
        {
            data.tileOffsetX += offsetX;
            data.tileOffsetY += offsetY;

            data.kickX = 0;

        	if (onMove != null)
        		onMove.accept(data);

        	return true;
        }
        return false;
    }

	@Semi_pureFunction
	protected boolean RotateWithKick(GameData data, boolean isLeftRotation)
    {
        TetrisPiece tile = (TetrisPiece) data.getTile().Clone();
        if (isLeftRotation)
            tile.RotateUpToLeft();
        else
            tile.RotateUpToRight();
        int x = data.tileOffsetX - data.kickX;
        int y = data.tileOffsetY - data.kickY;
        if (CheckFit(data.grid, tile, x, y))
        {
            data.setTile(tile);
            data.tileOffsetX = x;
            data.tileOffsetY = y;
            data.ResetKick();
            return true;
        }
        if (CheckFit(data.grid, tile, x + 1, y))
        {
            data.setTile(tile);
            data.tileOffsetX = x + 1;
            data.tileOffsetY = y;
            data.ResetKick();
            data.kickX = 1;
            return true;
        }
        if (CheckFit(data.grid, tile, x - 1, y))
        {
            data.setTile(tile);
            data.tileOffsetX = x - 1;
            data.tileOffsetY = y;
            data.ResetKick();
            data.kickX = -1;
            return true;
        }
        if (CheckFit(data.grid, tile, x, y + 1))
        {
            data.setTile(tile);
            data.tileOffsetX = x;
            data.tileOffsetY = y + 1;
            data.ResetKick();
            data.kickY = 1;
            return true;
        }
        return false;
    }

	@Semi_pureFunction
	protected boolean CheckFit(List<List<TetrisPieceType>> grid, TetrisPiece tile, int coordX, int coordY)
    {
        for (int i = 0; i < tile.coordX.length; i++)
        {
            int x = tile.coordX[i] + coordX;
            int y = tile.coordY[i] + coordY;

            if (!(0 <= x && x < GameConstant.maxX && 0 <= y) ||		          					// If not in bound, upper limit notwithstanding
                ((y < GameConstant.maxY) && grid.get(y).get(x) != null))     	// or tile occupied
                return false;
        }
        return true;
    }
}
