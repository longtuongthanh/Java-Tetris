package com.tetris.mechanic;

import java.util.TimerTask;

public class GameTimerTask extends TimerTask{
	GameTimer parent;
	public GameTimerTask(GameTimer parent) {
		this.parent = parent;
	}
	@Override
	public void run() {
		if (parent.canceled) {
			parent = null;
			return;
		}
		
		parent.callback.accept(parent);
		
		parent.timer.schedule(new GameTimerTask(parent), parent.delay);
	}
}