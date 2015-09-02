package sprites;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Star extends Sprite{
	
	public Star(double x, double y){
		setWidth(30);
		setHeight(30);
		setX(x);
		setY(y);
		setFileName("Images/star.png");
		initializeImage();
		initialize();
	}
	
	public void initialize(){}

}
