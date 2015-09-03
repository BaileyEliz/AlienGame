package loop;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Earth extends Sprite {
	
	public final double EARTH_LIVES = 3;
	
	public final int EARTH_WIDTH = 100;
	public final int EARTH_HEIGHT = 100;
	public final int ORIGIN = 300;
	public final String EARTH_IMAGE = "Images/earth.png";
	public final int IMAGE_OFFSET = 50;
	
	private int _hits;

	public Earth() {
		setWidth(EARTH_WIDTH);
		setHeight(EARTH_HEIGHT);
		setX(ORIGIN);
		setY(ORIGIN);
		setFileName(EARTH_IMAGE);
		initializeImage();
		_hits = 0;
	}
	
	@Override
	public void initializeImage(){
		setImage(new Image(getFileName()));
    	setImageView(new ImageView());
    	getImageView().setImage(getImage());
    	getImageView().setFitHeight(getHeight());
    	getImageView().setFitWidth(getWidth());
    	getImageView().setX(getX() - IMAGE_OFFSET);
    	getImageView().setY(getY() - IMAGE_OFFSET);
	}
	
	public void setHits(int i){
		_hits = i;
	}
	
	public int getHits(){
		return _hits;
	}
	
	public void addHit(){
		_hits++;
	}
	
	public boolean isAlive(){
		return (_hits < EARTH_LIVES);
	}

}
