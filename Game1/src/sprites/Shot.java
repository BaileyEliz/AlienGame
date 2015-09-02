package sprites;

import java.util.ArrayList;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import loop.MouseCoords;

public class Shot extends Sprite {
	
	private MouseCoords _mouse;
	private Scene _scene;

	public Shot(MouseCoords mouse, Scene scene) {
		setWidth(20);
		setHeight(20);
		setX(300);
		setY(300);
		setFileName("Images/fire.png");
		initializeImage();
		_mouse = mouse;
		_scene = scene;
		initializeShot();
	}
	
	public Shot(double x, double y, int index, Scene scene){
		setWidth(20);
		setHeight(20);
		setX(x);
		setY(y);
		setFileName("Images/fire.png");
		initializeImage();
		_scene = scene;
		initializeExplosion(index);
	}
	
	//use trig to find the trajectory of the shot based on the mouse click
	private void initializeShot(){

		double miniHypot;
		double largeHypot;
		double edgePoint;
		TranslateTransition translate;

		if(_mouse.getXCoord() <= 300){
			if(_mouse.getYCoord() <= 300){
				miniHypot = Math.sqrt(Math.pow((300 - _mouse.getXCoord()), 2)
						+ Math.pow((300 - _mouse.getYCoord()), 2));
				largeHypot = ((300/(300 - _mouse.getXCoord())) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow(300, 2));

				translate = 
						new TranslateTransition(Duration.millis(500)); 
				translate.setToX(-300); 
				translate.setToY(-edgePoint);
			}
			else{
				miniHypot = Math.sqrt(Math.pow((300 - _mouse.getXCoord()), 2)
						+ Math.pow((_mouse.getYCoord() - 300), 2));
				largeHypot = ((300/(300 - _mouse.getXCoord())) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow(300, 2));

				translate = 
						new TranslateTransition(Duration.millis(500)); 
				translate.setToX(-300); 
				translate.setToY(edgePoint);
			}
		}
		else{
			if(_mouse.getYCoord() <= 300){
				miniHypot = Math.sqrt(Math.pow((_mouse.getXCoord() - 300), 2)
						+ Math.pow((300 - _mouse.getYCoord()), 2));
				largeHypot = (((_scene.getWidth() - 300)/(_mouse.getXCoord() - 300)) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow((_scene.getWidth() - 300), 2));

				translate = 
						new TranslateTransition(Duration.millis(500)); 
				translate.setToX(_scene.getWidth() - 300); 
				translate.setToY(-edgePoint);
			}
			else{
				miniHypot = Math.sqrt(Math.pow((_mouse.getXCoord() - 300), 2)
						+ Math.pow((_mouse.getYCoord() - 300), 2));
				largeHypot = (((_scene.getWidth() - 300)/(_mouse.getXCoord() - 300)) * miniHypot);
				edgePoint = Math.sqrt(Math.pow(largeHypot, 2) - Math.pow((_scene.getWidth() - 300), 2));

				translate = 
						new TranslateTransition(Duration.millis(500)); 
				translate.setToX(_scene.getWidth() - 300); 
				translate.setToY(edgePoint);
			}
		}

		ArrayList<Transition> animations = new ArrayList<Transition>();
		animations.add(translate);

		this.setAnimations(animations);
	}
	
	public void initializeExplosion(int i){
		
		TranslateTransition translate;
		
		translate = 
				new TranslateTransition(Duration.millis(5000)); 
		
		if(i == 0){
			translate.setToX(0 - getX());
			translate.setToY((0 - getY()) + (_scene.getHeight()/2));
		}
		else if(i == 1){
			translate.setToX((0 - getX()) + (_scene.getWidth()/2));
			translate.setToY(0 - getY());
		}
		else if(i == 2){
			translate.setToX(_scene.getWidth() - getX());
			translate.setToY((0 - getY()) + (_scene.getHeight()/2));
		}
		else if(i == 3){
			translate.setToX((_scene.getWidth() - getX()) - (_scene.getWidth()/2));
			translate.setToY((_scene.getHeight() - getY()));
		}
		else if(i == 4){
			translate.setToX(0 - getX());
			translate.setToY((0 - getY()) - (getY()/2)); 
		}
		
		ArrayList<Transition> animations = new ArrayList<Transition>();
		animations.add(translate);

		this.setAnimations(animations);
	}

}
