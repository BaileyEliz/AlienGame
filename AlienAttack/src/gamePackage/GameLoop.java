package gamePackage;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 * The outer class of the Alien Attack game which creates a new
 * instance of the game and starts the game loop.
 */
public class GameLoop extends Application {

    private AlienWorld _game = new AlienWorld(Constants.FRAMES_PER_SECOND, Constants.GAME_TITLE);

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
