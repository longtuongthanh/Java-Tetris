package application;
	
import com.tetris.mechanic.GameMechanic;
import com.tetris.playfield.PlayField;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import com.tetris.main.StartupForm;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			//Nhan
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
	        StartupForm main = new StartupForm();
	        
			//End Nhan
	        SwingNode cmp = new SwingNode();
	        cmp.setContent(main);
	        root.setCenter(cmp);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//Long
			GameMechanic mechanic = new GameMechanic(scene);
			PlayField field = new PlayField();
			
			main.onStartPressed = data ->{
				Platform.runLater(()->{
					root.setCenter(field);
					mechanic.OnNewGame();
				});
			};
			
			mechanic.SetOnNotifyBoardChanged(grid -> field.onUpdate(grid));
			//mechanic.SetOnMove(data->System.out.println(data.tileOffsetX + " " + data.tileOffsetY));
			
			primaryStage.setOnCloseRequest(a -> mechanic.close());
			
			//End Long
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
