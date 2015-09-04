package gamePackage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * The Earth version of the Sprite. Knows how
 * many times it has been hit.
 */
public class Earth extends Sprite {

    private int _hits;

    public Earth() {
        setWidth(Constants.EARTH_WIDTH);
        setHeight(Constants.EARTH_HEIGHT);
        setX(Constants.ORIGIN);
        setY(Constants.ORIGIN);
        setFileName(Constants.EARTH_IMAGE);
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
        getImageView().setX(getX() - Constants.IMAGE_OFFSET);
        getImageView().setY(getY() - Constants.IMAGE_OFFSET);
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
        return (_hits < Constants.EARTH_LIVES);
    }

}
