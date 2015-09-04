package gamePackage;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/*
 * Extends the Star Mode to add a Boss.
 */
public class BossMode extends StarMode {

    private int oldRandomNumberOne;
    private int oldRandomNumberTwo;

    private Boss _boss;
    private Rectangle _monitor;

    /*
     * Creates the boss and the monitor to show how much life
     * it has left.
     */
    @Override
    public Scene initialize() {

        Scene scene = super.initialize();

        makeBoss();
        makeBossLifeMonitor();

        oldRandomNumberOne = 0;
        oldRandomNumberTwo = 0;

        setScene(scene);
        return getScene();

    }

    /*
     * Overrides the Star Mode class to include a Boss which 
     * moves rapidly around the gameplay area. Gets random X
     * and Y coordinates every half of a second and moves the 
     * Boss to there, and releases an alien from the place 
     * which the Boss moved from.
     */
    @Override
    public void updateShapes() {

        if(getUpdateCount() > Constants.BOSS_MOVE_RATE){

            if(_boss.getBossAlive()){

                int[] randomNumbers = getRandomXY(Constants.RANDOM_ONSCREEN);

                _boss.getBoss().getImageView().setTranslateX(randomNumbers[0]);
                _boss.getBoss().getImageView().setTranslateY(randomNumbers[1]);

                if(oldRandomNumberOne != 0 && oldRandomNumberTwo != 0){
                    makeAlienChild(oldRandomNumberOne, oldRandomNumberTwo);
                }

                oldRandomNumberOne = randomNumbers[0];
                oldRandomNumberTwo = randomNumbers[1];
            }

            setUpdateCount(0);

        }
        else{
            addUpdateCount();
        }	

        if(getStarCounter() > Constants.STAR_CREATION_RATE){
            setStarCounter(0);
            makeStars();
        }
        else{
            addStarCounter();
        }

    }

    /*
     * Adds, in addition to checking the aliens, earth, shots,
     * and stars, a check to watch for the collision of shots 
     * with the boss. Changes the number of the boss's lives
     * so AlienWorld can monitor the status of the level.
     */
    @Override
    public void checkCollisions() {

        super.checkCollisions();

        if(_boss.getBossAlive()){
            for(int i = 0; i < getShotManager().getSize(); i++){
                if(_boss.getBoss().getImageView().getBoundsInParent().intersects(getShotManager().getShot(i).getImageView().getBoundsInParent())){
                    _boss.getBoss().addHit();
                    _monitor.setWidth(((Constants.BOSS_LIVES - _boss.getHits())/Constants.BOSS_LIVES) * getScene().getWidth());
                    if(_boss.getBoss().getHits() == Constants.BOSS_LIVES){
                        getRoot().getChildren().remove(_boss.getBoss().getImageView());
                        //_boss.setBossAlive(false);
                    }
                    getRoot().getChildren().remove(getShotManager().removeShot(i).getImageView());
                }
            }
        }

    }

    /*
     * The alien is created whenever the Boss moves.
     */
    public void makeAlienChild(int x, int y){

        setAlien(new Alien(x, y, getSlowMo()));

        getAlienManager().addAlien(getAlien());

        getRoot().getChildren().add(getAlien().getImageView());

    }

    public void makeBoss(){

        _boss = new Boss(0, 0);
        _boss.setHits(0);

        getRoot().getChildren().add(_boss.getBoss().getImageView());

    }

    public void makeBossLifeMonitor(){
        _monitor = new Rectangle(0, (getScene().getHeight() - Constants.MONITOR_HEIGHT), getScene().getWidth(), Constants.MONITOR_HEIGHT);
        _monitor.setFill(Color.RED);
        getRoot().getChildren().add(_monitor);
    }

    public Boss getBoss(){
        return _boss;
    }

}
