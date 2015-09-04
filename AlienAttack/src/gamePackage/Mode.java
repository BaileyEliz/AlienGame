package gamePackage;

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * The type of level that involves some sort of gameplay.
 * This framework creates an earth, keeps track of its 
 * health in a monitor bar, processes key events, and 
 * creates aliens as well as monitors collisions between
 * aliens and the earth and aliens and shots.
 */
public abstract class Mode extends Level {

    private Alien _alien;

    private int _updateCount;

    private int _aliensShot = 0;
    private int _aliensLaunched = 0;
    private int _aliensGone = 0;

    private AlienManager _alienManager = new AlienManager();

    private Rectangle _earthMonitor;

    private boolean _slowMo = false;

    /*
     * On creation, makes an earth and a monitor and 
     * sets up listeners.
     */
    @Override
    public Scene initialize() {

        createEarth();
        makeLifeMonitor();

        setUpdateCount(Constants.COUNT_STARTER);

        getScene().setOnKeyPressed(e -> processKey(e));
        getScene().setOnMouseMoved(e -> mouseCoords(e));

        return getScene();

    }

    /*
     * Creates the monitor that shows that status of the earth.
     */
    public void makeLifeMonitor(){
        _earthMonitor = new Rectangle(0, 0, getScene().getWidth(), Constants.MONITOR_HEIGHT);
        _earthMonitor.setFill(Color.GREENYELLOW);
        getRoot().getChildren().add(_earthMonitor);
    }

    /*
     * Listener event that takes a key press and calls the
     * correct process.
     */
    public void processKey(KeyEvent e){
        if(e.getCode().getName().equals("Space")){
            shoot();
        }
        else if(e.getCode().getName().equals("W")){
            explode();
        }
        else if(e.getCode().getName().equals("S")){
            changeSpeed();
        }
    }

    /*
     * Sets a boolean to turn the slow motion off or on
     * on a key press.
     */
    public void changeSpeed(){
        _slowMo = !_slowMo;
    }

    /*
     * Returns two random numbers corresponding with an x
     * and a y. Receives an integer parameter to determine
     * the function of the numbers: either to generate numbers
     * outside of the bounds of the screen or inside the 
     * bounds of the screen.
     */
    public int[] getRandomXY(int identity){
        
        int randomNumberOne;
        int randomNumberTwo;

        //generate these numbers so that aliens can float in from outside
        //the screen
        if(identity == Constants.RANDOM_OFFSCREEN){

            Random rand = new Random();
            
            //gets a random x and a random y
            randomNumberOne = rand.nextInt(Constants.SCREEN_WIDTH);
            randomNumberTwo = rand.nextInt(Constants.SCREEN_HEIGHT);
            
            //determines whether the x or y will be changed to be outside
            //of the screen
            int whichNum = rand.nextInt(Constants.GET_ZERO_OR_ONE);
            
            //determines whether the chosen variable will be pulled down
            //to the negative of the screen or push out to the positive
            int minOrMax = rand.nextInt(Constants.GET_ZERO_OR_ONE);

            if(whichNum == 1){
                if(minOrMax == 1){
                    randomNumberOne = - Constants.OFF_SCREEN_OFFSET;
                }
                else{
                    randomNumberOne = ((int) getScene().getWidth()) + Constants.OFF_SCREEN_OFFSET ;
                }
            }
            else{
                if(minOrMax == 1){
                    randomNumberTwo = - Constants.OFF_SCREEN_OFFSET;
                }
                else{
                    randomNumberTwo = ((int) getScene().getWidth()) + Constants.OFF_SCREEN_OFFSET ;
                }
            }
            
            int[] randomNumbers = {randomNumberOne, randomNumberTwo};
            return randomNumbers;

        }
        //generates these numbers for stars and the boss that needs to be inside the screen
        else if(identity == Constants.RANDOM_ONSCREEN){

            Random rand = new Random();

            //gets an x to the left of Earth
            int smallX = rand.nextInt((Constants.SCREEN_WIDTH/2) - (Constants.EARTH_WIDTH/2) - Constants.INNER_RANDOM_BUFFER);
            //gets a y above Earth
            int smallY = rand.nextInt((Constants.SCREEN_HEIGHT/2) - (Constants.EARTH_HEIGHT/2) - Constants.INNER_RANDOM_BUFFER);
            //gets an x to the right of Earth
            int bigX = (((Constants.SCREEN_WIDTH/2) + (Constants.EARTH_WIDTH/2) + Constants.INNER_RANDOM_BUFFER) + 
                    rand.nextInt((int) Constants.SCREEN_WIDTH - (Constants.SCREEN_WIDTH/2) - (Constants.EARTH_WIDTH/2) - Constants.INNER_RANDOM_BUFFER));
            //gets a y below Earth
            int bigY = (((Constants.SCREEN_HEIGHT/2) + (Constants.EARTH_HEIGHT/2) + Constants.INNER_RANDOM_BUFFER) + 
                    rand.nextInt((int) Constants.SCREEN_HEIGHT - (Constants.SCREEN_HEIGHT/2) - (Constants.EARTH_HEIGHT/2) - Constants.INNER_RANDOM_BUFFER));
            //determines whether the large or small value of x is chosen
            int bigOrSmallX = rand.nextInt(Constants.GET_ZERO_OR_ONE);
            //determines whether the large or small value of y is chosen
            int bigOrSmallY = rand.nextInt(Constants.GET_ZERO_OR_ONE);

            if(bigOrSmallX == 0){
                randomNumberOne = smallX;
            }
            else{
                randomNumberOne = bigX;
            }

            if(bigOrSmallY == 0){
                randomNumberTwo = smallY;
            }
            else{
                randomNumberTwo = bigY;
            }
            
            int[] randomNumbers = {randomNumberOne, randomNumberTwo};
            return randomNumbers;

        }
        else{
            return null;
        }
        
    }

    /*
     * Subclasses of Mode must keep track of their own unique shapes.
     */
    @Override
    public abstract void updateShapes();

    /*
     * Does the work common across all Modes to check the collisions between
     * aliens, shots, and Earth.
     */
    @Override
    public void checkCollisions() {

        if(_alienManager.getSize() != 0){
            for(int j = 0; j < _alienManager.getSize(); j++){

                //check if the alien has hit the earth
                if(!_alienManager.getAlien(j).getHasHitEarth()){
                    if(getEarth().getImageView().getBoundsInParent().intersects(_alienManager.getAlien(j).getImageView().getBoundsInParent())){
                        _alienManager.getAlien(j).setHasHitEarth(true);
                        getRoot().getChildren().remove(_alienManager.removeAlien(j).getImageView());
                        _aliensGone++;
                        getEarth().addHit();
                        _earthMonitor.setWidth(((Constants.EARTH_LIVES - getEarth().getHits())/Constants.EARTH_LIVES) * getScene().getWidth());
                        return;
                    }
                }

                //check if a shot has hit an alien
                for(int i = 0; i < getShotManager().getSize(); i++){
                    if(_alienManager.getAlien(j).getImageView().getBoundsInParent().intersects(getShotManager().getShot(i).getImageView().getBoundsInParent())){
                        getRoot().getChildren().remove(_alienManager.removeAlien(j).getImageView());
                        _aliensGone++;
                        getRoot().getChildren().remove(getShotManager().removeShot(i).getImageView());
                        _aliensShot++;
                        System.out.println(_aliensShot);
                        return;
                    }
                }
            }
        }

    }

    @Override
    public void cleanupShapes() {
        super.cleanupShapes();
    }

    public int getUpdateCount() {
        return _updateCount;
    }

    public void setUpdateCount(int updateCount) {
        _updateCount = updateCount;
    }

    public void addUpdateCount(){
        _updateCount++;
    }

    public Alien getAlien() {
        return _alien;
    }

    public void setAlien(Alien alien) {
        _alien = alien;
    }

    public int getAliensShot() {
        return _aliensShot;
    }

    public void setAliensShot(int aliensShot) {
        _aliensShot = aliensShot;
    }

    public int getAliensLaunched() {
        return _aliensLaunched;
    }

    public void addAliensLaunched(){
        _aliensLaunched++;
    }

    public void setAliensLaunched(int aliensLaunched) {
        _aliensLaunched = aliensLaunched;
    }

    public int getAliensGone() {
        return _aliensGone;
    }

    public void setAliensGone(int aliensGone) {
        _aliensGone = aliensGone;
    }

    public AlienManager getAlienManager() {
        return _alienManager;
    }

    public void setAlienManager(AlienManager alienManager) {
        _alienManager = alienManager;
    }

    public boolean getSlowMo() {
        return _slowMo;
    }

    public void setSlowMo(boolean slowMo) {
        _slowMo = slowMo;
    }

}
