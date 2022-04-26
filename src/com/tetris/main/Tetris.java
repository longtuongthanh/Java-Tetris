package com.tetris.main;

import javax.swing.JOptionPane;

public class Tetris {
    private static StartupForm sf;
    private static LearderBoardForm lf;
    
    public static void start(){
//        su kien khi nhan nut start, chuyen den game form
    }
    
    public static void showLearderboard(){
        lf.setVisible(true);
    }
    
    public static void showStartup(){
        sf.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                sf = new StartupForm();
                lf = new LearderBoardForm();

                sf.setVisible(true);
            }
        });        
    }
    
}
