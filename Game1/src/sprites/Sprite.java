package sprites;

import java.util.ArrayList;

import javafx.animation.ParallelTransition;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Sprite {
	
	private Image _image;
	private ImageView _imageView;
	
	private double _width;
	private double _height;
	private double _x;
	private double _y;
	
	private String _fileName;
    
    public Sprite(){
    	
    }
    
    public void setAnimations(ArrayList<Transition> transitions){
    	
    	ParallelTransition transition = new ParallelTransition(_imageView, 
    	        transitions.get(0)); 
    	        transition.play(); 
    	
    }
    
    public void initializeImage(){
    	setImage(new Image(getFileName()));
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
