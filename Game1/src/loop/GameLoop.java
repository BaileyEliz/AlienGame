package loop;

import javafx.application.Application;
import javafx.stage.Stage;

public class GameLoop extends Application {
	
	public final int FRAMES_PER_SECOND = 60;
	
	private AlienWorld _game = new AlienWorld(FRAMES_PER_SECOND, "Alien Attack");
	
	public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		 // create the level manager
        _game.initialize(primaryStage);

        // kick off the game loop
        _game.beginGameLoop();

        // display window
        primaryStage.show();
		
	}

}
