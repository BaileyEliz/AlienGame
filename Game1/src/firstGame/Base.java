package firstGame;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Base {
	
	/** The JavaFX Scene as the game surface */
    private Scene _gameSurface;
    /** All nodes to be displayed in the game window. */
    private Group _sceneNodes;
	
	final int _fps;
	final String _title;
	
	private static Timeline _gameLoop;
	
	//private final ShapeManager _shapeManager = new ShapeManager();
	
	public Base(final int fps, final String title){
		_fps = fps;
		_title = title;
		buildAndSetGameLoop();
	}
	
	protected final void buildAndSetGameLoop() {

        final Duration oneFrameAmt = Duration.millis(1000/_fps);
        final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
            new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {

                    // update actors
                    updateShapes();

                    // check for collision
                    checkCollisions();

                    // removed dead things
                    cleanupShapes();

                }
        }); // oneFrame

        // sets the game world's game loop (Timeline)
        setGameLoop(TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build());
    }
	
	protected final void setGameLoop(Timeline gameLoop){
		_gameLoop = gameLoop;
	}
	
	public abstract void updateShapes();
	
	public abstract void checkCollisions();
	
	public abstract void cleanupShapes();
	
	/**
     * Initialize the game world by update the JavaFX Stage.
     * @param primaryStage
     */
    public abstract void initialize(final Stage primaryStage);

    /**Kicks off (plays) the Timeline objects containing one key frame
     * that simply runs indefinitely with each frame invoking a method
     * to update sprite objects, check for collisions, and cleanup sprite
     * objects.
     *
     */
    public void beginGameLoop() {
        getGameLoop().play();
    }
    
    /**
     * Returns the frames per second.
     * @return int The frames per second.
     */
    protected int getFramesPerSecond() {
        return _fps;
    }

    /**
     * Returns the game's window title.
     * @return String The game's window title.
     */
    public String getWindowTitle() {
        return _title;
    }
    
	private Timeline getGameLoop(){
		return _gameLoop;
	}
	
	/**
     * Returns the JavaFX Scene. This is called the game surface to
     * allow the developer to add JavaFX Node objects onto the Scene.
     * @return
     */
    public Scene getGameSurface() {
        return _gameSurface;
    }

    /**
     * Sets the JavaFX Scene. This is called the game surface to
     * allow the developer to add JavaFX Node objects onto the Scene.
     * @param gameSurface The main game surface (JavaFX Scene).
     */
    protected void setGameSurface(Scene gameSurface) {
        _gameSurface = gameSurface;
    }

    /**
     * All JavaFX nodes which are rendered onto the game surface(Scene) is
     * a JavaFX Group object.
     * @return Group The root containing many child nodes to be displayed into
     * the Scene area.
     */
    public Group getSceneNodes() {
        return _sceneNodes;
    }

    /**
     * Sets the JavaFX Group that will hold all JavaFX nodes which are rendered
     * onto the game surface(Scene) is a JavaFX Group object.
     * @param sceneNodes The root container having many children nodes
     * to be displayed into the Scene area.
     */
    protected void setSceneNodes(Group sceneNodes) {
        _sceneNodes = sceneNodes;
    }

}
