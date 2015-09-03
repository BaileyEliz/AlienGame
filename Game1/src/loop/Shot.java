package loop;

import java.util.ArrayList;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;

public class Shot extends Sprite {

	public final int SHOT_WIDTH = 20;
	public final int SHOT_HEIGHT = 20;
	public final String SHOT_IMAGE = "Images/fire.png";

	public final int ORIGIN = 300;

	public final int TOP_LEFT_CORNER = 0;
	public final int TOP_RIGHT_CORNER = 1;
	public final int BOTTOM_RIGHT_CORNER = 2;
	public final int BOTTOM_LEFT_CORNER = 3;
	public final int TOP_MIDDLE = 4;
	public final int RIGHT_MIDDLE = 5;
	public final int BOTTOM_MIDDLE = 6;
	public final int LEFT_MIDDLE = 7;

	public final int STAR_EXPLOSION_INDICATOR = 0;
	public final int EARTH_EXPLOSION_INDICATOR = 1;

	private MouseCoords _mouse;
	private Scene _scene;

	public Shot(MouseCoords mouse, Scene scene) {
		setX(ORIGIN);
		setY(ORIGIN);
		_mouse = mouse;
		setupShot(scene);
		initializeShot();
	}

	public Shot(double x, double y, int index, Scene scene, int indicator){
		setX(x);
		setY(y);
		setupShot(scene);
		initializeExplosion(index, indicator);
	}

	public void setupShot(Scene scene){
		setWidth(SHOT_WIDTH);
		setHeight(SHOT_HEIGHT);
		setFileName(SHOT_IMAGE);
		initializeImage();
		_scene = scene;
	}

	//use trig to find the trajectory of the shot based on the mouse click
	private void initializeShot(){

		double miniHypot;
		double largeHypot;
		double edgePoint;
		double distance;
		TranslateTransition translate;

		if(_mouse.getXCoord() <= ORIGIN){
			if(_mouse.getYCoord() <= ORIGIN){
				miniHypot = Math.sqrt(Math.pow((ORIGIN - _mouse.getXCoord()), 2)
						+ Math.pow((ORIGIN - _mouse.getYCoord()), 2));
				largeHypot = ((ORIGIN/(ORIGIN - _mouse.getXCoord())) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow(ORIGIN, 2));

				distance = (Math.sqrt(Math.pow(-ORIGIN, 2) + Math.pow((-edgePoint), 2)));

				translate =  
						new TranslateTransition(Duration.millis(1.7 * distance)); 
				translate.setToX(-ORIGIN); 
				translate.setToY(-edgePoint);
			}
			else{
				miniHypot = Math.sqrt(Math.pow((ORIGIN - _mouse.getXCoord()), 2)
						+ Math.pow((_mouse.getYCoord() - ORIGIN), 2));
				largeHypot = ((ORIGIN/(ORIGIN - _mouse.getXCoord())) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow(ORIGIN, 2));

				distance = (Math.sqrt(Math.pow(-ORIGIN, 2) + Math.pow((edgePoint), 2)));

				translate =  
						new TranslateTransition(Duration.millis(1.7 * distance)); 
				translate.setToX(-ORIGIN); 
				translate.setToY(edgePoint);
			}
		}
		else{
			if(_mouse.getYCoord() <= ORIGIN){
				miniHypot = Math.sqrt(Math.pow((_mouse.getXCoord() - ORIGIN), 2)
						+ Math.pow((ORIGIN - _mouse.getYCoord()), 2));
				largeHypot = (((_scene.getWidth() - ORIGIN)/(_mouse.getXCoord() - ORIGIN)) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow((_scene.getWidth() - ORIGIN), 2));

				distance = (Math.sqrt(Math.pow((_scene.getWidth() - ORIGIN), 2) + Math.pow((-edgePoint), 2)));

				translate =  
						new TranslateTransition(Duration.millis(1.7 * distance));  
				translate.setToX(_scene.getWidth() - ORIGIN); 
				translate.setToY(-edgePoint);
			}
			else{
				miniHypot = Math.sqrt(Math.pow((_mouse.getXCoord() - ORIGIN), 2)
						+ Math.pow((_mouse.getYCoord() - ORIGIN), 2));
				largeHypot = (((_scene.getWidth() - ORIGIN)/(_mouse.getXCoord() - ORIGIN)) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow((_scene.getWidth() - ORIGIN), 2));

				distance = (Math.sqrt(Math.pow((_scene.getWidth() - ORIGIN), 2) + Math.pow((edgePoint), 2)));

				translate =  
						new TranslateTransition(Duration.millis(1.7 * distance));  
				translate.setToX(_scene.getWidth() - ORIGIN); 
				translate.setToY(edgePoint);
			}
		}

		ArrayList<Transition> animations = new ArrayList<Transition>();
		animations.add(translate);

		this.setAnimations(animations);
	}

	public void initializeExplosion(int i, int indicator){

		TranslateTransition translate;

		translate = 
				new TranslateTransition(Duration.millis(5000)); 

		if(indicator == EARTH_EXPLOSION_INDICATOR){

			if(i == TOP_MIDDLE){
				translate.setToX((0 - getX()) + (_scene.getWidth()/2));
				translate.setToY(0 - getY());
			}
		}
		else if(indicator == STAR_EXPLOSION_INDICATOR){
			if(i == TOP_MIDDLE){
				translate.setToX(0);
				translate.setToY(0 - getY());
			}
		}

		if(i == TOP_LEFT_CORNER){
			translate.setToX(0 - getX());
			translate.setToY((0 - getY()));
		}
		else if(i == TOP_RIGHT_CORNER){
			translate.setToX(_scene.getWidth() - getX());
			translate.setToY(0 - getY());
		}
		else if(i == BOTTOM_RIGHT_CORNER){
			translate.setToX(_scene.getWidth() - getX());
			translate.setToY(_scene.getHeight() - getY());
		}
		else if(i == BOTTOM_LEFT_CORNER){
			translate.setToX(0 - getX());
			translate.setToY(_scene.getHeight() - getY());
		}
		else if(i == RIGHT_MIDDLE){
			translate.setToX(_scene.getWidth() - getX());
			translate.setToY((0 - getY()) + (_scene.getHeight()/2));
		}
		else if(i == BOTTOM_MIDDLE){
			translate.setToX((_scene.getWidth() - getX()) - (_scene.getWidth()/2));
			translate.setToY((_scene.getHeight() - getY()));
		}
		else if(i == LEFT_MIDDLE){
			translate.setToX(0 - getX());
			translate.setToY((0 - getY()) + (_scene.getHeight()/2));
		}

		ArrayList<Transition> animations = new ArrayList<Transition>();
		animations.add(translate);

		this.setAnimations(animations);
	}

}
