package com.tetris.mechanic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javafx.scene.input.KeyCode;

public class PlayerController extends TetrisController {
	public GameMechanic parent;
	
	// TODO: Settings
	public int numOfPlayer = 1;
	public static final KeyCode[] keyLeft = new KeyCode[] {KeyCode.LEFT, KeyCode.A, KeyCode.KP_LEFT};
	public static final KeyCode[] keyRight = new KeyCode[] {KeyCode.RIGHT, KeyCode.D, KeyCode.KP_RIGHT};
	//public static final KeyCode[] keyUp = new KeyCode[] {KeyCode.UP, KeyCode.KP_UP, KeyCode.W};
	public static final KeyCode[] keyDown = new KeyCode[] {KeyCode.DOWN, KeyCode.S, KeyCode.KP_DOWN};
	public static final KeyCode[] keyRotateLeft = new KeyCode[] {KeyCode.UP, KeyCode.Q, KeyCode.Z, KeyCode.KP_UP, KeyCode.W};
	public static final KeyCode[] keyRotateRight = new KeyCode[] {KeyCode.X, KeyCode.E};
	public static final KeyCode[] keyPause = new KeyCode[] {KeyCode.P, KeyCode.ESCAPE};
	
	private Map<KeyCode, Function<GameData, Boolean>> mapKeyToAction;
	public PlayerController(GameMechanic parent) {
		this.parent = parent;
		mapKeyToAction = new HashMap<KeyCode, Function<GameData, Boolean>>();
		for (KeyCode key : keyLeft) {
			mapKeyToAction.put(key, data -> MoveLeft(data));
		}
		for (KeyCode key : keyRight) {
			mapKeyToAction.put(key, data -> MoveRight(data));
		}
		for (KeyCode key : keyDown) {
			mapKeyToAction.put(key, data -> MoveDown(data));
		}
		/*//
		for (KeyCode key : keyUp) {
			mapKeyToAction.put(key, data -> MoveUp(data));
		}
		//*/
		for (KeyCode key : keyRotateLeft) {
			mapKeyToAction.put(key, data -> RotateLeft(data));
		}
		for (KeyCode key : keyRotateRight) {
			mapKeyToAction.put(key, data -> RotateRight(data));
		}
		for (KeyCode key : keyPause) {
			mapKeyToAction.put(key, data -> { this.parent.OnPause(); return true; });
		}
	}
    
    public void OnKeyDown(KeyCode key, GameData data) {
		if (data.gameOver || data.paused)
			return;
    	
		Function<GameData, Boolean> action = mapKeyToAction.get(key);
		
		boolean changed;
		if (action != null) {
			changed = action.apply(data);
		}
		else
			changed = false;
		
    	if (changed && notifyBoardChanged != null)
    		notifyBoardChanged.accept(data);
    }
    
    private boolean MoveLeft(GameData gameData)
    {
    	//System.out.print("left");
        return Move(gameData, -1, 0);
    }
    private boolean MoveRight(GameData gameData)
    {
    	//System.out.print("right");
        return Move(gameData, 1, 0);
    }
    private boolean MoveDown(GameData gameData)
    {
    	boolean moved = false;
        while (Move(gameData, 0, -1))
        	moved = true;
        return moved;
    }
    private boolean RotateLeft(GameData gameData)
    {
        return RotateWithKick(gameData, true);
    }
    private boolean RotateRight(GameData gameData)
    {
        return RotateWithKick(gameData, false);
    }
}
