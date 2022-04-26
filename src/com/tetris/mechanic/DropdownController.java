package com.tetris.mechanic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import application.GameConstant;
import javafx.scene.paint.Color;

public class DropdownController extends TetrisController {
	public BiConsumer<GameData, List<Integer>> onClearLine;
	public Consumer<GameData> onGameOver;
	
	private boolean ClearRow(GameData data)
    {
        List<List<Color>> grid = data.grid;

        int rowCount = 0;
        
        List<Integer> rowsToClear = new ArrayList<Integer>(4);
        {
	        int i = 0;
	        for (List<Color> row : grid) {
	            if (!row.stream().anyMatch(item -> item == GameConstant.nullColor))
	            {
	            	rowsToClear.add(i);
	            }
	            i++;
	        }
        }
        Collections.reverse(rowsToClear);
        for (Integer i : rowsToClear) {
            data.ClearRow(i);
            rowCount++;
        }
        
        int score = 0;
        if (rowCount > 0)
        {
            if (rowCount <= 4)
                score = GameConstant.ScoreForRow[rowCount - 1] * data.level;
            else
            {
                score = GameConstant.ScoreForRow[3] * data.level;
                System.out.println("Too many rows cleared at once (" + rowCount + "). Cheat detected.");
            }
            data.score += score;
            data.lines += rowCount;

            if (onClearLine != null)
            	onClearLine.accept(data, rowsToClear);
        }

        return rowCount != 0;
    }

    public void PushDown(GameData data)
    {
        TetrisPiece tile = data.getTile();

        if (!Move(data, 0, -1))
        {
            List<List<Color>> grid = data.grid;

            for (int i = 0; i < 4; i++)
            {
                int x = data.tileOffsetX + tile.coordX[i];
                int y = data.tileOffsetY + tile.coordY[i];

                if (GameConstant.IsCoordInBound(x, y) && (grid.get(y).get(x) == GameConstant.nullColor))
                    grid.get(y).set(x, TetrisPiece.tileColor.get(tile.type));
                else
                {
                    if (!data.gameOver && onGameOver != null)
                    	onGameOver.accept(data);
                    
                    data.gameOver = true;
                }
            }

            boolean hasClearedRow = ClearRow(data);
            if (hasClearedRow)
                data.SpeedUp();

            data.GetNewTetrisPiece();
        }

    	if (notifyBoardChanged != null)
    		notifyBoardChanged.accept(data.GetBoardColor());
    }
}
