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
	GridPane pauseMenu;
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
	
	private GridPane makePauseMenu(boolean isGameOver) {
		GridPane result = new GridPane();
		
		FlowPane unpausePane = new FlowPane();
		unpausePane.setVgap(20);
		unpausePane.setAlignment(Pos.CENTER);
		
		Button unpause = new Button("Unpause");
		unpause.setOnAction(e -> {
			if (this.unpause != null)
				this.unpause.accept(null);
		});
		unpausePane.getChildren().add(unpause);
		
		Button retry = new Button("Retry");
		retry.setOnAction(e -> {
			this.OnUnpause();
			if (restart != null)
				this.restart.accept(null);
		});
		FlowPane retryPane = new FlowPane();
		retryPane.setVgap(20);
		retryPane.setAlignment(Pos.CENTER);
		retryPane.getChildren().add(retry);
		

		Button toMenu = new Button("Return to menu");
		toMenu.setOnAction(e -> {
			this.OnUnpause();
			if (exitToMenu != null)
				this.exitToMenu.accept(null);
		});
		FlowPane toMenuPane = new FlowPane();
		toMenuPane.setVgap(20);
		toMenuPane.setAlignment(Pos.CENTER);
		toMenuPane.getChildren().add(toMenuPane);
		
		if (!isGameOver)
			result.add(unpausePane, 0, 0);
		result.add(retryPane, 0, 1);
		result.add(toMenuPane, 0, 2);
		
		return pauseMenu = result; 
	}
}

