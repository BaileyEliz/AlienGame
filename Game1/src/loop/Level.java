package loop;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public abstract class Level {
	
	public final int SCREEN_WIDTH = 640;
	public final int SCREEN_HEIGHT = 580;
	
	public Group _root = new Group();
	private Scene _scene = new Scene(_root, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
	
	public abstract Scene initialize();
	public abstract void updateShapes();
	public abstract void checkCollisions();
	public abstract void cleanupShapes();
	
	public void setRoot(Group root){
		_root = root;
	}
	
	public Group getRoot(){
		return _root;
	}
	
	public void setScene(Scene scene){
		_scene = scene;
	}
	
	public Scene getScene(){
		return _scene;
	}
	
}
