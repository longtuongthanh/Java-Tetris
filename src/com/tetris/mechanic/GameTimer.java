package com.tetris.mechanic;

import java.util.*;
import java.util.function.Consumer;

public class GameTimer implements AutoCloseable {
	Timer timer;
	
	int delay;
	public boolean canceled = false;
	public Consumer<GameTimer> callback;
	GameTimerTask task;
	
	public GameTimer(float delay, Consumer<GameTimer> callback) {
		SetDelay(delay);
		if (callback == null)
			throw new NullPointerException();
		this.callback = callback;
		
		//Reset();
	}
	
	public void SetDelay(float delay) {
		this.delay = (int)(delay * 1000);
		assert this.delay > 0 : "Invalid timer";
	}
	
	public void Stop() {
		if (timer != null)
			timer.cancel();
		canceled = true;
	}
	
	public void Reset() {
		timer = new Timer();
		task = new GameTimerTask(this);
		timer.schedule(task, this.delay);
	}

	@Override
	public void close() {
		Stop();
	}
}