package gamePackage;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/*
 * The main structure of a level of Alien Attack;
 * can either be a Scene consisting of text and a shooting
 * Earth or a Mode which involves some sort of gameplay.
 * Contains the scene of the level and the viewgroup to
 * which the level is attached, as well as an earth,
 * a manager for any shots fired, and mouse coordinates;
 * these things are common to all levels.
 */
public abstract class Level {

    public Group _root = new Group();
    private Scene _scene = new Scene(_root, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Color.BLACK);

    private Earth _earth;
    private ShotManager _activeShots = new ShotManager();
    private MouseCoords _mouse = new MouseCoords();
    
    /*
     * Creates the Earth of each level and attaches 
     * it to the view.
     */
    public void createEarth(){
        _earth = new Earth();
        getRoot().getChildren().add(_earth.getImageView());
    }

    /*
     * Keeps track of the current position of the mouse by
     * updating it whenever the mouse moves.
     */
    public void mouseCoords(MouseEvent e){
        _mouse.setCoords(e.getSceneX(), e.getSceneY());
    }

    /*
     * Function called when the listener on each scene
     * processes a spacebar press by the player. Creates
     * a shot and adds it to the manager.
     */
    public void shoot(){
        Shot b = new Shot(_mouse, getScene());
        _activeShots.addShot(b);
        getRoot().getChildren().add(b.getImageView());
    }

    /*
     * Function called when the listener on each scene
     * processes a "W" press by the player. Creates eight
     * shots and adds then to the manager.
     */
    public void explode(){
        for(int i = 0; i < Constants.NUMBER_OF_EXPLOSIONS_EARTH; i++){
            Shot b = new Shot(_earth.getX(), _earth.getY(), i, getScene(), Constants.EARTH_EXPLOSION_INDICATOR);
            getShotManager().addShot(b);
            getRoot().getChildren().add(b.getImageView());
        }
    }

    /*
     * Abstract methods for the levels to implement.
     * The first is to set up the level, the second is
     * to keep track of the active elements, and the
     * third is to process any collisions that happen.
     * Cleaning is taken care of by this class.
     */
    public abstract Scene initialize();
    public abstract void updateShapes();
    public abstract void checkCollisions();

    /*
     * For each level this part of the game loop is 
     * the same; get rid of shots that have traveled 
     * to the edge of the board.
     */
    public void cleanupShapes(){

        if(getShotManager().getShots().size() != 0){
            for(int i = 0; i < getShotManager().getSize(); i++){
                if(getShotManager().getShot(i).getImageView().getBoundsInParent().getMinX() <= 0 ||
                        getShotManager().getShot(i).getImageView().getBoundsInParent().getMaxX() >= getScene().getWidth() ||
                        getShotManager().getShot(i).getImageView().getBoundsInParent().getMinY() <= 0 ||
                        getShotManager().getShot(i).getImageView().getBoundsInParent().getMaxY() >= getScene().getHeight()){
                    getRoot().getChildren().remove(getShotManager().removeShot(i).getImageView());
                }
            }
        }
    }

    public void setRoot(Group root){
        _root = root;
    }

    public Group getRoot(){
        return _root;
    }

    public void setScene(Scene scene){
        _scene = scene;
    }

    public Scene getScene(){
        return _scene;
    }

    public Earth getEarth() {
        return _earth;
    }

    public void setEarth(Earth earth) {
        _earth = earth;
    }

    public ShotManager getShotManager() {
        return _activeShots;
    }

    public void setShotManager(ShotManager shotsManager) {
        _activeShots = shotsManager;
    }

    public MouseCoords getMouse() {
        return _mouse;
    }

    public void setMouse(MouseCoords mouse) {
        _mouse = mouse;
    }

}
