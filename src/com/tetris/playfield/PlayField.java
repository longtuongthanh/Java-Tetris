package com.tetris.playfield;

import javafx.application.Platform;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.*;

import application.GameConstant;

public class PlayField extends GridPane {
	List<List<Rectangle>> tileGrid = new ArrayList<List<Rectangle>>();
	
	public PlayField() {
		super();
		for (int i = GameConstant.maxY - 1; i >= 0; i--) {
			List<Rectangle> row = new ArrayList<Rectangle>();
			for (int j = 0; j < GameConstant.maxX; j++) {
				Rectangle tile = new Rectangle();
				
				tile.setHeight(10);
				tile.setWidth(10);
				
				tile.setFill(GameConstant.nullColor);
				
				row.add(tile);
				
				this.add(tile, j, i);
			}
			tileGrid.add(row);
		}
	}
	
	public void onUpdate(List<List<Color>> colorGrid) {
		Platform.runLater(()->{
			for (int i = 0; i < GameConstant.maxY; i++) {
				List<Rectangle> tileRow = tileGrid.get(i);
				List<Color> colorRow = colorGrid.get(i);
				for (int j = 0; j < GameConstant.maxX; j++) {
					tileRow.get(j).setFill(colorRow.get(j));
				}
			}
		});
	}
}
