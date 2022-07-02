package com.tetris.mechanic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public enum GameMode {
	Normal,
	Line40;
	
	static public Map<GameMode, Consumer<GameData>> gameMod;
	static {
		gameMod = new HashMap<GameMode, Consumer<GameData>>();
		
		gameMod.put(Line40, new Consumer<GameData>(){
			@Override
			public void accept(GameData data) {
				if (data.gameOver)
					data.score = 0;
				if (data.lines >= 40) {
					data.gameOver = true;
					data.score = data.secondCount;
				}
			}
		});
	}
}
