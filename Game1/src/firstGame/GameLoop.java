package firstGame;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLoop extends Application {
	
	Base _gameWorld = new ShapesGoneWild(60, "Your Game");
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		 // setup title, scene, stats, controls, and actors.
        _gameWorld.initialize(primaryStage);

        // kick off the game loop
        _gameWorld.beginGameLoop();

        // display window
        primaryStage.show();
		
	}

}
