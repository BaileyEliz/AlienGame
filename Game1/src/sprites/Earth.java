package sprites;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

public class Earth extends Sprite {
	
	public final double EARTH_LIVES = 3;
	
	private int _hits;

	public Earth() {
		setWidth(100);
		setHeight(100);
		setX(300);
		setY(300);
		setFileName("Images/earth.png");
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
    	getImageView().setX(getX() - 50);
    	getImageView().setY(getY() - 50);
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
		return (_hits < 3);
	}

}
