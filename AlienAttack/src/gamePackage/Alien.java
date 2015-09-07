package gamePackage;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

/*
 * The character the player is trying to kill.
 * 
 * Knows whether it has hit the earth and whether
 * it is moving at a slow or normal speed.
 */
public class Alien extends Sprite{

    private boolean hasHitEarth;
    private boolean isSlowMo;

    public Alien(double x, double y, boolean slowMo) {
        setWidth(Constants.ALIEN_WIDTH);
        setHeight(Constants.ALIEN_HEIGHT);
        setX(x);
        setY(y);
        setFileName(Constants.ALIEN_IMAGE);
        isSlowMo = slowMo;
        initializeImage();
        initialize();
    }

   /*
    * Sets the alien in motion from its start place, which is passed 
    * to each alien as a parameter, towards the earth, accounting for the 
    * preexisting x and y values, at a random speed.
    */
    public void initialize(){

        int speed = getSpeed();
        TranslateTransition translate = 
                new TranslateTransition(Duration.millis(speed)); 
        translate.setToX(Constants.SCREEN_OFFSET - this.getImageView().getBoundsInParent().getMinX()); 
        translate.setToY(Constants.SCREEN_OFFSET - this.getImageView().getBoundsInParent().getMinY());

        ArrayList<Transition> animations = new ArrayList<Transition>();
        animations.add(translate);

        this.setAnimations(animations);

        hasHitEarth = false;
    }
    
    /*
     * Gets a random speed between the upper and lower bound and multiplies
     * by a constant factor if slow motion has been activated.
     */
    public int getSpeed(){
        
        Random rand = new Random();
        if(isSlowMo){
            return ((Constants.LOWER_SPEED_BOUND + rand.nextInt(Constants.UPPER_SPEED_OFFSET)) * Constants.SLOW_MO);
        }
        else{
            return ((Constants.LOWER_SPEED_BOUND + rand.nextInt(Constants.UPPER_SPEED_OFFSET)));
        }
        
    }

    public void setHasHitEarth(boolean value){
        hasHitEarth = value;
    }

    public boolean getHasHitEarth(){
        return hasHitEarth;
    }

}
