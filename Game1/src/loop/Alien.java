package loop;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Alien extends Sprite{
	
	public final int ALIEN_WIDTH = 90;
	public final int ALIEN_HEIGHT = 100;
	public final String ALIEN_IMAGE = "Images/alien.png";
	
	public final int LOWER_SPEED_BOUND = 4000;
	public final int UPPER_SPEED_OFFSET = 3000;
	
	public final int SCREEN_OFFSET = 300;
	
	private boolean hasHitEarth;
	private boolean isSlowMo;

	public Alien(double x, double y, boolean slowMo) {
		setWidth(ALIEN_WIDTH);
		setHeight(ALIEN_HEIGHT);
		setX(x);
		setY(y);
		setFileName(ALIEN_IMAGE);
		isSlowMo = slowMo;
		initializeImage();
		initialize();
	}

	public void initialize(){

		Random rand = new Random();
		int speed;
		if(isSlowMo){
			speed = ((LOWER_SPEED_BOUND + rand.nextInt(UPPER_SPEED_OFFSET)) * 3);
		}
		else{
			speed = ((LOWER_SPEED_BOUND + rand.nextInt(UPPER_SPEED_OFFSET)));
		}
		
		TranslateTransition translate = 
				new TranslateTransition(Duration.millis(speed)); 
		translate.setToX(SCREEN_OFFSET - this.getImageView().getBoundsInParent().getMinX()); 
		translate.setToY(SCREEN_OFFSET - this.getImageView().getBoundsInParent().getMinY());

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
