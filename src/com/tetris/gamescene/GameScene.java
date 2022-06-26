package com.tetris.gamescene;

import java.util.function.Consumer;

import com.tetris.mechanic.GameData;
import com.tetris.playfield.PlayField;

import application.Main;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class GameScene extends StackPane {
	BorderPane gameplay;
	ImageView blur;
	static Image bluring = new Image("/resource/Darken.png", true);
	static Image notBluring = new Image("/resource/Darken.png", true);
	public Node pauseMenu;
	public Consumer<GameData> unpause;
	public Consumer<GameData> restart;
	public Consumer<GameData> exitToMenu;
	
	public GameScene() {
		super();
		
		gameplay = new BorderPane();
		
		gameplay.setCenter(getPlayField());
		gameplay.setTop(makeTop());
		gameplay.setLeft(makeLeft());
		gameplay.setRight(makeRight());
		
		blur = new ImageView();
		blur.setFitHeight(600);
		blur.setFitWidth(400);
		blur.setImage(null);
		
		this.getChildren().add(gameplay);
		this.getChildren().add(blur);
	}
	
	public void OnUpdate(GameData data) {
		field.onUpdate(data.GetBoardColor());
		
		scoreLabel.setText("Score: " + data.score);
		levelLabel.setText("Level: " + data.level);
	}
	
	public void OnPause(GameData data) {
		blur.setImage(bluring);
		this.getChildren().add(makePauseMenu(data.gameOver));
	}
	public void OnUnpause() {
		blur.setImage(null);
		this.getChildren().remove(pauseMenu);
	}
	
	private PlayField field;
	public PlayField getPlayField() {
		if (field == null) {
			field = new PlayField();
		}
		return field;
	}
	
	Label scoreLabel;
	Label levelLabel;
	private Node makeTop() {
		BorderPane top = new BorderPane();
		
		scoreLabel = new Label("Score: 0");
		scoreLabel.setFont(Font.font("Segoe UI", 30));
		levelLabel = new Label("Level: 1");
		levelLabel.setFont(Font.font("Segoe UI", 30));
		
		top.setLeft(scoreLabel);
		top.setRight(levelLabel);
		
		return top;
	}
	
	private Node makeRight() {
		GridPane right = new GridPane();
		
		return right;
	}
	
	private Node makeLeft() {
		GridPane left = new GridPane();
		
		return left;
	}
	
	private Node makePauseMenu(boolean isGameOver) {
		GridPane result = new GridPane();
		result.setVgap(20);
		result.setAlignment(Pos.CENTER);
				
		Button unpause = new Button("Unpause");
		unpause.setOnAction(e -> {
			if (this.unpause != null)
				this.unpause.accept(null);
		});
		
		Button retry = new Button("Retry");
		retry.setOnAction(e -> {
			if (this.restart != null)
				this.restart.accept(null);
		});

		Button toMenu = new Button("Return to menu");
		toMenu.setOnAction(e -> {
			if (this.exitToMenu != null)
				this.exitToMenu.accept(null);
		});
		
		if (!isGameOver)
			result.add(unpause, 0, 0);
		result.add(retry, 0, 1);
		result.add(toMenu, 0, 2);
		
		return pauseMenu = result; 
	}
}

