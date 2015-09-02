package sprites;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Alien extends Sprite{
	
	private boolean hasHitEarth;

	public Alien(double x, double y) {
		setWidth(90);
		setHeight(100);
		setX(x);
		setY(y);
		setFileName("Images/alien.png");
		initializeImage();
		initialize();
	}

	public void initialize(){

		Random rand = new Random();
		int speed = ((2 + rand.nextInt(9)) * 1000);
		
		TranslateTransition translate = 
				new TranslateTransition(Duration.millis(speed)); 
		translate.setToX(300 - this.getImageView().getBoundsInParent().getMinX()); 
		translate.setToY(300 - this.getImageView().getBoundsInParent().getMinY());

		ArrayList<Transition> animations = new ArrayList<Transition>();
		animations.add(translate);

		this.setAnimations(animations);
		
		hasHitEarth = false;
	}
	
	public void setHasHitEarth(boolean value){
		hasHitEarth = value;
	}
	
	public boolean getHasHitEarth(){
		return hasHitEarth;
	}

}
