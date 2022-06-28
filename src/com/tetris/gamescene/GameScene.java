package com.tetris.gamescene;

import java.util.function.Consumer;

import com.tetris.mechanic.GameData;
import com.tetris.playfield.PlayField;

import application.Main;
import javafx.geometry.Insets;
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

		BorderPane.setMargin(gameplay, new Insets(30));
		
		blur = new ImageView();
		blur.setFitHeight(600);
		blur.setFitWidth(400);
		blur.setImage(null);
		
		this.getChildren().add(gameplay);
		this.getChildren().add(blur);
	}
	
	public void OnUpdate(GameData data) {
		field.onUpdate(data.GetBoardColor());
		
		scoreLabel.setText(Integer.toString(data.score));
		levelLabel.setText(Integer.toString(data.level));
		nextDisplay.SetTetrisPiece(data.next.type);
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
	TetrisDisplay nextDisplay;
	private Node makeTop() {
		GridPane top = new GridPane();
		
		return top;
	}
	
	private Node makeRight() {
		GridPane right = new GridPane();
		
		scoreLabel = new Label("000");
		scoreLabel.setFont(Font.font("Segoe UI", 30));
		scoreLabel.setAlignment(Pos.TOP_CENTER);
		levelLabel = new Label("1");
		levelLabel.setFont(Font.font("Segoe UI", 30));
		levelLabel.setAlignment(Pos.TOP_CENTER);

		Label scoreText = new Label("Score:");
		scoreText.setFont(Font.font("Segoe UI", 30));
		scoreText.setAlignment(Pos.BOTTOM_CENTER);
		Label nextText = new Label("Next:");
		nextText.setFont(Font.font("Segoe UI", 30));
		nextText.setAlignment(Pos.BOTTOM_CENTER);
		Label levelText = new Label("Level:");
		levelText.setFont(Font.font("Segoe UI", 30));
		levelText.setAlignment(Pos.BOTTOM_CENTER);
		
		nextDisplay = new TetrisDisplay();
		
		right.add(scoreText, 0, 0);
		right.add(scoreLabel, 0, 1);
		right.add(nextText, 0, 2);
		right.add(nextDisplay, 0, 3);
		right.add(levelText, 0, 4);
		right.add(levelLabel, 0, 5);
		
		scoreText.setMinHeight(100);
		scoreLabel.setMinHeight(100);
		nextText.setMinHeight(100);
		nextDisplay.minHeight(100);
		levelText.setMinHeight(100);
		levelLabel.setMinHeight(100);
		
		return right;
	}
	
	private Node makeLeft() {
		BorderPane left = new BorderPane();
		
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
		unpause.setAlignment(Pos.CENTER);
		
		Button retry = new Button("Retry");
		retry.setOnAction(e -> {
			if (this.restart != null)
				this.restart.accept(null);
		});
		retry.setAlignment(Pos.CENTER);

		Button toMenu = new Button("Return to menu");
		toMenu.setOnAction(e -> {
			if (this.exitToMenu != null)
				this.exitToMenu.accept(null);
		});
		toMenu.setAlignment(Pos.CENTER);
		
		if (!isGameOver)
			result.add(unpause, 0, 0);
		result.add(retry, 0, 1);
		result.add(toMenu, 0, 2);
		
		return pauseMenu = result; 
	}
}

