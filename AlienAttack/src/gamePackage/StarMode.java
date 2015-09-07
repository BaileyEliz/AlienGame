package gamePackage;

import javafx.scene.Scene;

/*
 * Extends the basic Alien class to add stars.
 */
public class StarMode extends AlienMode {

    private StarManager _starManager = new StarManager();
    private int _starCounter;

    @Override
    public Scene initialize() {

        Scene scene = super.initialize();
        _starCounter = 0;
        setScene(scene);
        return getScene();

    }

    public void makeStars(){

        int[] randomNumbers = getRandomXY(Constants.RANDOM_ONSCREEN);

        Star s = new Star(randomNumbers[0], randomNumbers[1]);

        _starManager.addStar(s);
        getRoot().getChildren().add(s.getImageView());
    }

    /*
     * Responsible for creating stars at the correct rate. Does
     * so by using the frame rate.
     */
    @Override
    public void updateShapes() {

        super.updateShapes();

        if(_starCounter > Constants.STAR_CREATION_RATE){
            _starCounter = 0;
            makeStars();
        }
        else{
            _starCounter++;
        }

    }

    /*
     * Adds a collision check between stars and shots to
     * the one implemented in Mode.
     */
    @Override
    public void checkCollisions() {

        super.checkCollisions();

        if(_starManager.getStarLength() != 0){
            for(int i = 0; i < _starManager.getStarLength(); i++){
                for(int j = 0; j < getShotManager().getSize(); j++){
                    if(_starManager.getStar(i).getImageView().getBoundsInParent()
                            .intersects(getShotManager().getShot(j).getImageView().getBoundsInParent())){
                        explode(_starManager.getStar(i).getImageView().getBoundsInParent().getMinX(), 
                                _starManager.getStar(i).getImageView().getBoundsInParent().getMinY());
                        getRoot().getChildren().remove(getShotManager().removeShot(j).getImageView());
                        getRoot().getChildren().remove(_starManager.removeStar(i).getImageView());
                        return;
                    }
                }
            }
        }

    }

    /*
     * Another explode function that creates five new shots when a
     * star is shot, starting at the star's x and y coordinates.
     */
    public void explode(double x, double y){

        for(int i = 0; i < Constants.NUMBER_OF_EXPLOSIONS_STAR; i++){
            //changed from Shot to Explosion
            Shot b = new Explosion(x, y, i, getScene(), Constants.STAR_EXPLOSION_INDICATOR);
            getShotManager().addShot(b);
            System.out.println(i);
            getRoot().getChildren().add(b.getImageView());
        }
    }

    public StarManager getStarManager(){
        return _starManager;
    }

    public void setStarManager(StarManager starManager){
        _starManager = starManager;
    }

    public int getStarCounter(){
        return _starCounter;
    }

    public void setStarCounter(int starCounter){
        _starCounter = starCounter;
    }

    public void addStarCounter(){
        _starCounter++;
    }

}
