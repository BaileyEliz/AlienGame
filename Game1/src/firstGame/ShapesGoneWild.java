package firstGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ShapesGoneWild extends Base {
	
	Group shapes = new Group();
	Scene scene;
	private Alien r;
	private Earth c;
	
	private int updateCount;
	
	private Point2D homePoint;
	private Point2D shooterPoint;
	
	AlienManager _alienManager;
	ShotManager _activeShots;
	
	MouseCoords _mouse;
	
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
        
        scene = new Scene(getSceneNodes(), 640, 580, Color.BLACK);
        
        setGameSurface(scene);
        primaryStage.setScene(getGameSurface());
        
        /*scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

                    public void handle(final KeyEvent keyEvent) {
                        shoot();
                    }
        });*/
        
        scene.setOnKeyPressed(e -> shoot());
        scene.setOnMouseMoved(e -> mouseCoords(e));
        
        createEarth();
        createManagers();
    	
        updateCount = 181;
        _mouse = new MouseCoords();
    }
    
    public void createEarth(){
    	
    	c = new Earth(new Circle(300, 300, 50));
    	c.setColor(Color.BLUE);
    	shapes.getChildren().add(c.getNode());
    	
    }
    
    public void createManagers(){
    	
    	_alienManager = new AlienManager();
    	_activeShots = new ShotManager();
    	
    	homePoint = new Point2D(300, 300);
    	System.out.println(homePoint.getX());
    	System.out.println(homePoint.getY());
    	
    	shooterPoint = new Point2D(0, 0);
    	
    }
    
    public void makeAlien(){
    	
    	Random rand = new Random();
    	int randomNumberOne = rand.nextInt(400);
    	int randomNumberTwo = rand.nextInt(400);
    	int whichNum = rand.nextInt(2);
    	int minOrMax = rand.nextInt(2);
    	
    	if(whichNum == 1){
    		if(minOrMax == 1){
    			randomNumberOne = 0;
    		}
    		else{
    			//randomNumberOne = (int) scene.getWidth();
    			randomNumberOne = 500;
    		}
    	}
    	else{
    		if(minOrMax == 1){
    			randomNumberTwo = 0;
    		}
    		else{
    			//randomNumberTwo = (int) scene.getWidth();
    			randomNumberTwo = 500;
    		}
    	}
    	
    	r = new Alien(new Rectangle(randomNumberOne, randomNumberTwo, 100, 100));
    	r.setColor(Color.MEDIUMPURPLE);
    	
    	TranslateTransition translate = 
    	        new TranslateTransition(Duration.millis(10000)); 
    	        translate.setToX(300 - r.getNode().getBoundsInParent().getMinX()); 
    	        translate.setToY(300 - r.getNode().getBoundsInParent().getMinY());
    	        
    	ArrayList<Transition> animations = new ArrayList<Transition>();
    	animations.add(translate);
    	        
    	r.setAnimations(animations);
    	
    	shapes.getChildren().add(r.getNode());
    	
    }
    
    public void shoot(){
    	Shot b = new Shot(new Circle(300, 300, 10));
    	b.setColor(Color.RED);
    	
    	TranslateTransition translate = 
    	        new TranslateTransition(Duration.millis(500)); 
    	        translate.setToX(_mouse.getXCoord() - 300); 
    	        translate.setToY(_mouse.getYCoord() - 300);
    	        
    	ArrayList<Transition> animations = new ArrayList<Transition>();
    	animations.add(translate);
    	        
    	b.setAnimations(animations);
    	
    	_activeShots.addShot(b);
    	
    	shapes.getChildren().add(b.getNode());
    }
    
    public void mouseCoords(MouseEvent e){
    	_mouse.setCoords(e.getSceneX(), e.getSceneY());
    }

	@Override
	public void updateShapes() {
		
		if(updateCount > 180){
			makeAlien();
			updateCount = 0;
		}
		else{
			updateCount++;
		}
		
	}

	@Override
	public void checkCollisions() {
		
		if(c.getNode().getBoundsInParent().getMinX() < r.getNode().getBoundsInParent().getMaxX()){
			if(c.getNode().getBoundsInParent().getMinY() < r.getNode().getBoundsInParent().getMaxY()){
				c.setColor(Color.AQUA);
			}
		}
		else{
			c.setColor(Color.WHITESMOKE);
		}

		if(_activeShots.getShots().size() != 0){
			for(Shot aShot:_activeShots.getShots()){
				if(r.getNode().getBoundsInParent().intersects(aShot.getNode().getBoundsInParent())){
					r.setColor(Color.WHITE);
					System.out.println(r.getNode().getBoundsInParent().getMinX());
					System.out.println(aShot.getNode().getBoundsInParent().getMinX());
				}
			}
		}

	}

	@Override
	public void cleanupShapes() {
		// TODO Auto-generated method stub
		
	}

}
