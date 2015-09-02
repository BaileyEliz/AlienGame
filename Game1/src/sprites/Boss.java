package sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class Boss extends Sprite{
	
	private int timesHit;
	private boolean _bossAlive;
	
	public Boss(double x, double y) {
		setWidth(150);
		setHeight(70);
		setX(x);
		setY(y);
		setFileName("Images/mothership.png");
		initializeImage();
		_bossAlive = true;
	}

	public void initialize(){
		timesHit = 0;
	}
	
	public void addHit(){
		timesHit++;
	}
	
	public int getHits(){
		return timesHit;
	}
	
	public void setHits(int i){
		timesHit = i;
	}
	
	public Boss getBoss(){
		return this;
	}
	
	public boolean getBossAlive(){
		return _bossAlive;
	}
	
	public void setBossAlive(boolean alive){
		_bossAlive = alive;
	}

}
