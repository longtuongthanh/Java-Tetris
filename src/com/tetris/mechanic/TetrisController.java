package com.tetris.mechanic;

import java.util.*;

import javafx.scene.paint.Color;

public interface TetrisController {
	default public boolean Move(GameData data, int offsetX, int offsetY)
    {
        TetrisPiece tile = data.getTile();
        int x = data.tileOffsetX + offsetX;
        int y = data.tileOffsetY + offsetY;
        if (CheckFit(data.grid, tile, x, y))
        {
            data.tileOffsetX += offsetX;
            data.tileOffsetY += offsetY;

            data.kickX = 0;

            // TODO: Add OnMove
            //app.soundManager.Play(AudioClipEnum.Click);

            return true;
        }
        return false;
    }

	default public boolean RotateWithKick(GameData data, boolean isLeftRotation)
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

	default public boolean CheckFit(List<List<Color>> grid, TetrisPiece tile, int coordX, int coordY)
    {
        for (int i = 0; i < 4; i++)
        {
            int x = tile.coordX[i] + coordX;
            int y = tile.coordY[i] + coordY;
            if (!(GameConstant.IsCoordInBound(x, y)) ||          // If not in bound
                ((y < GameConstant.maxY) && grid.get(y).get(x) != null))      // or tile occupied
                return false;
        }
        return true;
    }
}
