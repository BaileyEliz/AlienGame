package gamePackage;

/*
 * The basic Mode with aliens floating in trying to reach the earth.
 */
public class AlienMode extends Mode {

    /*
     * Responsible for producing the correct number of
     * aliens at the correct rate.
     */
    @Override
    public void updateShapes() {

        if(getAliensLaunched() > Constants.MAX_ALIENS_LAUNCHED){
            return;
        }
        if(getUpdateCount() > Constants.ALIEN_LAUNCH_RATE){
            makeAlien();
            setUpdateCount(0);
            addAliensLaunched();
        }
        else{
            addUpdateCount();
        }

    }

    public int deadAliensNum(){
        return getAliensGone();
    }

    /*
     * Gets the random numbers it needs to create the alien then does,
     * telling it whether to be in slow motion or regular speed.
     */
    public void makeAlien(){

        int[] randomNumbers = getRandomXY(Constants.RANDOM_OFFSCREEN);

        setAlien(new Alien(randomNumbers[0], randomNumbers[1], getSlowMo()));

        getAlienManager().addAlien(getAlien());

        getRoot().getChildren().add(getAlien().getImageView());

    }

}
