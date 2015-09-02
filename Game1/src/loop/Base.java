package loop;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Base {

	private Timeline _gameLoop;
	private int _framesPerSecond;
	private String _title;

	public Base(final int fps, final String title){
		_framesPerSecond = fps;
		_title = title;
		buildAndSetGameLoop();
	}

	protected final void buildAndSetGameLoop() {

		final Duration oneFrameAmt = Duration.millis(1000/_framesPerSecond);
		final KeyFrame oneFrame = new KeyFrame(oneFrameAmt,
				new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

					// update actors
					updateShapes();

					// check for collision
					checkCollisions();

					// remove dead things
					cleanupShapes();

			}
		});

		// sets the game world's game loop
		setGameLoop(TimelineBuilder.create()
				.cycleCount(Animation.INDEFINITE)
				.keyFrames(oneFrame)
				.build());
	}

	public abstract void initialize(final Stage primaryStage);
	
	public abstract void updateShapes();

	public abstract void checkCollisions();

	public abstract void cleanupShapes();

	public void beginGameLoop() {
		getGameLoop().play();
	}
	
	protected int getFramesPerSecond() {
		return _framesPerSecond;
	}

	public String getWindowTitle() {
		return _title;
	}

	private Timeline getGameLoop(){
		return _gameLoop;
	}
	
	protected final void setGameLoop(Timeline gameLoop){
		_gameLoop = gameLoop;
	}

}
