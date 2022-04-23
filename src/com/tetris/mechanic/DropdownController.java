package com.tetris.mechanic;

import java.util.*;

import javafx.scene.paint.Color;

public class DropdownController implements TetrisController {
	public boolean ClearRow(GameData data)
    {
        List<List<Color>> grid = data.grid;

        int rowCount = 0;
        for (int i = 0; i < grid.size(); i++)
            if (!grid.get(i).stream().anyMatch(item -> item == GameConstant.nullColor))
            {
                data.ClearRow(i);
                rowCount++;
                i--;
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

            // TODO: OnRowClear
            //app.soundManager.Play(AudioClipEnum.Congrat);
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
                    data.gameOver = true;
                    // TODO: Notify Game Over
                    //app.NotifyGameOver();
                    //SceneManager.LoadScene("MenuScene");
                }
            }

            boolean hasClearedRow = ClearRow(data);
            if (hasClearedRow)
                data.SpeedUp();

            data.GetNewTetrisPiece();
        }
    }
}
