package gamePackage;

import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/*
 * The class that defines the structure for all of the characters in the game.
 * 
 * Contains the character's image and the file name associated with it, and the
 * character's dimensions and current location. It also activates any animations
 * on the characters.
 * 
 * Subclasses are Alien, Boss, Earth, Shot, and Star
 */
public abstract class Sprite {

    private Image _image;
    private ImageView _imageView;

    private double _width;
    private double _height;
    private double _x;
    private double _y;

    private String _fileName;

    public Sprite(){}

    /*
     * Activates the animation of the character.
     */
    public void setAnimations(ArrayList<Transition> transitions){

        ParallelTransition transition = new ParallelTransition(_imageView, 
                                                               transitions.get(0)); 
        transition.play(); 

    }

    /*
     * Sets up the image for the character.
     */
    public void initializeImage(){
        setImage(new Image(getClass().getClassLoader().getResourceAsStream(_fileName)));
        setImageView(new ImageView());
        getImageView().setImage(getImage());
        getImageView().setFitHeight(getHeight());
        getImageView().setFitWidth(getWidth());
        getImageView().setX(getX());
        getImageView().setY(getY());
    }

    public void setImage(Image image){
        _image = image;
    }

    public Image getImage(){
        return _image;
    }

    public ImageView getImageView(){
        return _imageView;
    }

    public void setImageView(ImageView imageView){
        _imageView = imageView;
    }

    public double getWidth(){
        return _width;
    }

    public void setWidth(double width){
        _width = width;
    }

    public double getHeight(){
        return _height;
    }

    public void setHeight(double height){
        _height = height;
    }

    public double getX(){
        return _x;
    }

    public void setX(double x){
        _x = x;
    }

    public double getY(){
        return _y;
    }

    public void setY(double y){
        _y = y;
    }

    public void setFileName(String fileName){
        _fileName = fileName;
    }

    public String getFileName(){
        return _fileName;
    }

}
