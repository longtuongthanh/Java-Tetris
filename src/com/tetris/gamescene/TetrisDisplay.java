package com.tetris.gamescene;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.application.TetrisPieceType;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class TetrisDisplay extends ImageView {
	static Map<TetrisPieceType, Image> tileImage;
	static {
		tileImage = new HashMap<TetrisPieceType, Image>();
		
		tileImage.put(TetrisPieceType.I, new Image(new File("resource/shapes/I.png").toURI().toString(), true));
		tileImage.put(TetrisPieceType.J, new Image(new File("resource/shapes/J.png").toURI().toString(), true));
		tileImage.put(TetrisPieceType.L, new Image(new File("resource/shapes/L.png").toURI().toString(), true));
		tileImage.put(TetrisPieceType.Z, new Image(new File("resource/shapes/Z.png").toURI().toString(), true));
		tileImage.put(TetrisPieceType.O, new Image(new File("resource/shapes/O.png").toURI().toString(), true));
		tileImage.put(TetrisPieceType.T, new Image(new File("resource/shapes/T.png").toURI().toString(), true));
		tileImage.put(TetrisPieceType.S, new Image(new File("resource/shapes/S.png").toURI().toString(), true));
		//tileImage.put(null, new Image(new File("resource/Nothing.png").toURI().toString(), true));
	}
	
	public void SetTetrisPiece(TetrisPieceType type) {
		this.setImage(tileImage.get(type));
	}
}
