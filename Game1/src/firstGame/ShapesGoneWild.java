package firstGame;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShapesGoneWild extends Base {
	
	Group shapes = new Group();
	Scene scene;
	private AShape r;
	private AShape c;
	
	private Point2D homePoint;
	private Point2D shooterPoint;
	
	public ShapesGoneWild(int fps, String title) {
		super(fps, title);
	}

	/**
     * Initialize the game world by update the JavaFX Stage.
     * @param primaryStage
     */
    public void initialize(final Stage primaryStage){
    	
    	// Sets the window title
        primaryStage.setTitle(getWindowTitle());
        
        // Create the scene
        setSceneNodes(shapes);
        
        scene = new Scene(getSceneNodes(), 640, 580, Color.AQUA);
        
        setGameSurface(scene);
        primaryStage.setScene(getGameSurface());
        
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

                    public void handle(final KeyEvent keyEvent) {
                        shoot();
                    }
        });
        
        createShapes();
    	
    }
    
    public void createShapes(){
    	
    	r = new AShape(new Rectangle(0, 0, 100, 100));
    	r.setColor(Color.MEDIUMPURPLE);
    	
    	TranslateTransition translate = 
    	        new TranslateTransition(Duration.millis(20000)); 
    	        translate.setToX(390); 
    	        translate.setToY(390);
    	        
    	ArrayList<Transition> animations = new ArrayList<Transition>();
    	animations.add(translate);
    	        
    	r.setAnimations(animations);
    	
    	c = new AShape(new Circle(300, 300, 50));
    	
    	c.setColor(Color.WHITESMOKE);
    	
    	homePoint = new Point2D(300, 300);
    	System.out.println(homePoint.getX());
    	System.out.println(homePoint.getY());
    	
    	shooterPoint = new Point2D(-100, -100);
    	
    	shapes.getChildren().add(c.getNode());
    	shapes.getChildren().add(r.getNode());
    	
    }
    
    public void shoot(){
    	AShape b = new AShape(new Circle(300, 300, 10));
    	b.setColor(Color.RED);
    	
    	TranslateTransition translate = 
    	        new TranslateTransition(Duration.millis(1000)); 
    	        translate.setToX(shooterPoint.getX()); 
    	        translate.setToY(shooterPoint.getY());
    	        
    	ArrayList<Transition> animations = new ArrayList<Transition>();
    	animations.add(translate);
    	        
    	b.setAnimations(animations);
    	
    	shapes.getChildren().add(b.getNode());
    }

	@Override
	public void updateShapes() {
		
		//System.out.println(homePoint.angle(shooterPoint));
		
		if(c.getNode().getBoundsInParent().getMinX() < r.getNode().getBoundsInParent().getMaxX()){
			if(c.getNode().getBoundsInParent().getMinY() < r.getNode().getBoundsInParent().getMaxY()){
				c.setColor(Color.AQUA);
			}
		}
		else{
			c.setColor(Color.WHITESMOKE);
		}
		
	}

	@Override
	public void checkCollisions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanupShapes() {
		// TODO Auto-generated method stub
		
	}

}
