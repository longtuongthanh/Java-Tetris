package com.tetris.mechanic;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.application.TetrisPieceType;
import com.tetris.playfield.Sound;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class GameMechanic implements AutoCloseable {
	GameData data;
	DropdownController dropdownMechanic;
	PlayerController controlMechanic;
	
	// CALLING TO OTHER CLASSES
	public Consumer<GameData> onPause, onUnpause;
	public void SetOnNotifyBoardChanged(Consumer<GameData> notifyBoardChanged) {
		controlMechanic.notifyBoardChanged = notifyBoardChanged;
		dropdownMechanic.notifyBoardChanged = notifyBoardChanged;
	}
	public void SetOnGameOver(Consumer<GameData> onGameOver) {
		dropdownMechanic.onGameOver = (data)->{
			Sound.Inst().SetPlaybackRate(Sound.initialRate);
			if (onGameOver != null)
				onGameOver.accept(data);
		};
	}
	// outputs game data and list of lines cleared
	public void SetOnClearLine(BiConsumer<GameData, List<Integer>> onClearLine) {
		dropdownMechanic.onClearLine = onClearLine;
	}
	public void SetOnLockTile(Consumer<GameData> onLockTile) {
		dropdownMechanic.onLockTile = onLockTile;
	}
	public void SetOnMove(Consumer<GameData> onMove) {
		controlMechanic.onMove = onMove;
		//dropdownMechanic.onMove = onMove;
	}
	// END CALLING TO OTHER CLASSES
	
	public GameMechanic(Scene scene) {
		data = new GameData();
		controlMechanic = new PlayerController(this);
		dropdownMechanic = new DropdownController();
		
		data.timer = new GameTimer(data.dropstep, timer -> {
			dropdownMechanic.PushDown(data);
			if (data.gameOver)
				data.timer.Stop();
		});
		
		final EventHandler<? super KeyEvent> prevHandler = scene.getOnKeyPressed();
		scene.setOnKeyPressed(ev -> {
			if (prevHandler != null)
				prevHandler.handle(ev);

			this.controlMechanic.OnKeyDown(ev.getCode(), data);
		});
	}
	
	public void OnPause() {

		System.out.println("pause");
		data.paused = true;
		data.timer.Stop();
		if (onPause != null)
			onPause.accept(data);
	}
	public void OnUnpause() {
		System.out.println("unpause");
		data.paused = false;
		data.timer.Reset();
		if (onPause != null)
			onUnpause.accept(data);
	}
	@Override
	public void close() {
		data.timer.close();
	}
	public void OnNewGame() {
		Sound.Inst().SetSong(1);
		Sound.Inst().SetPlaybackRate(Sound.initialRate);
		data.ResetData();
		if (controlMechanic.notifyBoardChanged != null)
			controlMechanic.notifyBoardChanged.accept(data);
	}
}
