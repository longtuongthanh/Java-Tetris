package tetris;

import javax.swing.JOptionPane;

public class Tetris {
    private static StartupForm sf;
    private static LearderBoardForm lf;
    private static OptionForm of;
    private static SelectGameForm sgf;
    
    
    public static void showLearderboard(){
        lf.setVisible(true);
    }
    
    public static void showStartup(){
        sf.setVisible(true);
    }
    
    public static void showOption(){
        of.setVisible(true);
    }
    
    public static void showSelectGame(){
        sgf.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                sf = new StartupForm();
                lf = new LearderBoardForm();
                of = new OptionForm();
                sgf = new SelectGameForm();
                
                sf.setVisible(true);
            }
        });        
    }
    
}
