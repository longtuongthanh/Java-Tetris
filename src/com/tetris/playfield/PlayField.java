package com.tetris.playfield;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.*;

import com.application.TetrisPieceType;

import application.GameConstant;

public class PlayField extends GridPane {
	static Map<TetrisPieceType, Image> tileImage;
	static {
		tileImage = new HashMap<TetrisPieceType, Image>();
		
		tileImage.put(TetrisPieceType.I, new Image("/resource/I.png", true));
		tileImage.put(TetrisPieceType.J, new Image("/resource/J.png", true));
		tileImage.put(TetrisPieceType.L, new Image("/resource/L.png", true));
		tileImage.put(TetrisPieceType.Z, new Image("/resource/Z.png", true));
		tileImage.put(TetrisPieceType.O, new Image("/resource/O.png", true));
		tileImage.put(TetrisPieceType.T, new Image("/resource/T.png", true));
		tileImage.put(TetrisPieceType.S, new Image("/resource/S.png", true));
		tileImage.put(null, new Image("/resource/Nothing.png", true));
	}
	
	List<List<ImageView>> tileGrid = new ArrayList<List<ImageView>>();
	
	public PlayField() {
		super();
		for (int i = GameConstant.maxY - 1; i >= 0; i--) {
			List<ImageView> row = new ArrayList<ImageView>();
			for (int j = 0; j < GameConstant.maxX; j++) {
				ImageView tile = new ImageView();
				
				tile.setFitHeight(20);
				tile.setFitWidth(20);
				tile.setPreserveRatio(true);
				tile.setSmooth(true);
				
				tile.setImage(tileImage.get(null));
				
				row.add(tile);
				
				this.add(tile, j, i);
			}
			tileGrid.add(row);
		}
	}
	
	public void onUpdate(List<List<TetrisPieceType>> colorGrid) {
		Platform.runLater(()->{
			for (int i = 0; i < GameConstant.maxY; i++) {
				List<ImageView> tileRow = tileGrid.get(i);
				List<TetrisPieceType> colorRow = colorGrid.get(i);
				for (int j = 0; j < GameConstant.maxX; j++) {
					TetrisPieceType tile = colorRow.get(j);
					Image color = tileImage.get(tile);
					tileRow.get(j).setImage(color);
				}
			}
		});
	}
}
