package com.tetris.main;

import java.util.function.Consumer;

import javax.swing.JOptionPane;

import javafx.embed.swing.SwingNode;

public class Tetris {
    //Long: call out to other classes
    public Consumer<SaveData> onStartPressed;
    public Consumer<SaveData> onOptionsPressed;
    public Consumer<SaveData> onLeaderboardPressed;
    public Consumer<SaveData> onQuit;
    public SaveData data;
    //End Long

    public StartupForm sf;
    private OptionForm of;
    private LearderBoardForm lf;
    public SelectGameForm sgf;
    public SwingNode parent;
    
    private static Tetris _inst;
    public static Tetris Inst() {
    	if (_inst == null)
    		_inst = new Tetris();
    	return _inst;
    }
    
    public void start(){
//        su kien khi nhan nut start, chuyen den game form
    }
    
    public void showLearderboard(){
    		lf = new LearderBoardForm();
        parent.setContent(lf);
    }
    
    public void showOption(){
    		of = new OptionForm();
        parent.setContent(of);
    }
    
    public void showStartup(){
    		sf = new StartupForm();
        parent.setContent(sf);
    }
    
    public void showSelectGame(){
    		sgf = new SelectGameForm();
        parent.setContent(sgf);    
    }    
}
