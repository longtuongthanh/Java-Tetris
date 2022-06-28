package application;
	
import com.tetris.mechanic.GameMechanic;
import com.tetris.playfield.PlayField;
import com.tetris.playfield.Sound;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import com.tetris.gamescene.GameScene;
import com.tetris.main.StartupForm;
import com.tetris.main.Tetris;

import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Sound.Inst().SetSong(3);
			Sound.Inst().Play();
			
			
			BorderPane root = new BorderPane();
			//Nhan
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
	        
			//End Nhan
	        SwingNode cmp = new SwingNode();
	        Tetris.Inst().parent = cmp;
	        Tetris.Inst().showStartup();
	        root.setCenter(cmp);
			Scene scene = new Scene(root,400,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			//Long
			GameMechanic mechanic = new GameMechanic(scene);
			GameScene field = new GameScene();
			
			Tetris.Inst().onStartPressed = data ->{
				Platform.runLater(()->{
					root.setCenter(field);
					mechanic.OnNewGame();
				});
			};
			
			mechanic.SetOnNotifyBoardChanged(data -> {
				Platform.runLater(() -> field.OnUpdate(data));
			});
			mechanic.SetOnLockTile(data -> {
				Platform.runLater(() -> Sound.Inst().PlayOnce(3));
			});
			mechanic.SetOnClearLine((data, listInt) -> {
				Platform.runLater(() -> Sound.Inst().PlayOnce(2));
			});
			//mechanic.SetOnMove(data->System.out.println(data.tileOffsetX + " " + data.tileOffsetY));
			mechanic.onPause = data -> {
				Platform.runLater(() -> field.OnPause(data));
			};
			mechanic.onUnpause = data -> {
				Platform.runLater(() -> field.OnUnpause());
			};
			mechanic.SetOnGameOver(data -> {
				Platform.runLater(() -> field.OnPause(data));
				Sound.Inst().SetSong(0);
			});
			field.unpause = item -> mechanic.OnUnpause();
			field.restart = item -> {
				mechanic.OnNewGame();
				field.OnUnpause();
			};
			field.exitToMenu = item -> {
				Platform.runLater(()->{
					root.setCenter(cmp);

			        Tetris.Inst().showStartup();

					field.OnUnpause();
				});
			};
			
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
