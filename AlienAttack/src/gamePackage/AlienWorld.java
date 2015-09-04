package gamePackage;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/*
 * An extension of the Base abstract class which is the main
 * controller for the Alien Attack game; it holds the current
 * scene and level and switches to the next one when necessary 
 * as well as controls the audio that goes along with these switches.
 */
public class AlienWorld extends Base {

    private Stage _primaryStage;
    private Level _activeLevel;
    private Scene _activeScene;

    public AlienWorld(){
        super(Constants.FRAMES_PER_SECOND, Constants.GAME_TITLE);
    }

    public AlienWorld(int fps, String title) {
        super(fps, title);
    }

    /*
     * Calls the updateShapes() function of the active level.
     */
    @Override
    public void updateShapes(){
        _activeLevel.updateShapes();
    }

    /*
     * Calls the checkCollisions() function of the active level.
     */
    @Override
    public void checkCollisions(){
        _activeLevel.checkCollisions();
    }

    /*
     * Calls the cleanupShapes() function of the active level.
     * Here the class also checks to see if the user has lost
     * or advanced so that it can update the level and scene 
     * as necessary.
     */
    @Override
    public void cleanupShapes(){
        _activeLevel.cleanupShapes();

        //to end the game, checks to see if the boss has been defeated
        if(_activeLevel instanceof BossMode){
            if(!((BossMode) _activeLevel).getBoss().getBossAlive()){
                showEnd();
            }
            else if(((BossMode) _activeLevel).getEarth().isAlive() == false){
                showLost();
            }
        }

        //to advance from a prior level, checks how many aliens have been 
        //removed from the board. Once the goal has been reached, the player moves on.
        else if(_activeLevel instanceof StarMode){
            if(((StarMode) _activeLevel).getAliensGone() == Constants.ALIEN_GOAL){
                preBossLevel();
            }
            else if(((StarMode) _activeLevel).getEarth().isAlive() == false){
                showLost();
            }
        }
        else if(_activeLevel instanceof AlienMode){
            if(((AlienMode) _activeLevel).getAliensGone() == Constants.ALIEN_GOAL){
                nextLevel();
            }
            else if(((AlienMode) _activeLevel).getEarth().isAlive() == false){
                showLost();
            }
        }

    }

    /*
     * The initial setup of the AlienWorld. Activates the first
     * Screen and listens for the player to press the key indicating
     * that they are ready to move to gameplay.
     */
    @Override
    public void initialize(Stage primaryStage) {

        _primaryStage = primaryStage;

        _primaryStage.setTitle(getWindowTitle());

        _activeLevel = new Screen(Constants.SPLASH_SCREEN);
        _activeScene = _activeLevel.initialize();

        _activeScene.setOnKeyPressed(e -> startAlienMode(e));

        _primaryStage.setScene(_activeScene);

    }

    /*
     * The function that runs in response to the user indicating
     * they are ready to move on. Manages the audio.
     */
    public void startAlienMode(KeyEvent e){

        if(e.getCode().getName().equals("Enter")){

            _activeLevel = new AlienMode();
            _activeScene = _activeLevel.initialize();

            _primaryStage.setScene(_activeScene);
        }
        else{
            ((Screen) _activeLevel).checkEvent(e);
        }
    }

    /*
     * The function called when this class discovers that the player 
     * has defeated enough aliens to move on. Listens again for 
     * the player to be ready to start.
     */
    public void nextLevel(){

        _activeLevel = new Screen(Constants.STAR_SCREEN);
        _activeScene = _activeLevel.initialize();
        _activeScene.setOnKeyPressed(e -> startStarMode(e));

        _primaryStage.setScene(_activeScene);

    }

    /*
     * The function that runs in response to the user indicating
     * they are ready to move on to Star Mode.
     */
    public void startStarMode(KeyEvent e){

        if(e.getCode().getName().equals("Enter")){
            _activeLevel = new StarMode();
            _activeScene = _activeLevel.initialize();

            _primaryStage.setScene(_activeScene);
        }
        else{
            ((Screen) _activeLevel).checkEvent(e);
        }
    }

    /*
     * The function called when this class discovers that the player 
     * has defeated enough aliens to move on to the boss. Listens again for 
     * the player to be ready to start.
     */
    public void preBossLevel(){

        _activeLevel = new Screen(Constants.BOSS_SCREEN);
        _activeScene = _activeLevel.initialize();
        _activeScene.setOnKeyPressed(e -> startBossMode(e));

        _primaryStage.setScene(_activeScene);

    }

    /*
     * The function that runs in response to the user indicating
     * they are ready to move on to fight the Boss.
     */
    public void startBossMode(KeyEvent e){

        if(e.getCode().getName().equals("Enter")){
            _activeLevel = new BossMode();
            _activeScene = _activeLevel.initialize();

            _primaryStage.setScene(_activeScene);
        }
        else{
            ((Screen) _activeLevel).checkEvent(e);
        }

    }

    /*
     * The function that runs when this class discovers the player
     * has defeated the Boss. Plays an audio message.
     */
    public void showEnd(){

        _activeLevel = new Screen(Constants.END_SCREEN);
        _activeScene = _activeLevel.initialize();

        _activeScene.setOnKeyPressed(e -> endOrRestartGame(e));

        _primaryStage.setScene(_activeScene);

    }

    /*
     * The function that runs when this class discovers 
     * the player has been defeated. Plays an audio message.
     */
    public void showLost(){

        _activeLevel = new Screen(Constants.LOST_SCREEN);
        _activeScene = _activeLevel.initialize();

        _activeScene.setOnKeyPressed(e -> endOrRestartGame(e));

        _primaryStage.setScene(_activeScene);

    }

    /*
     * The function that responds to the user input after
     * they have either lost or won, either restarting the
     * game or exiting from it. Also resets the audio.
     */
    public void endOrRestartGame(KeyEvent e){

        if(e.getCode().getName().equals("E")){
            System.exit(0);
        }
        else if(e.getCode().getName().equals("Enter")){

            _activeLevel = new AlienMode();
            _activeScene = _activeLevel.initialize();

            _primaryStage.setScene(_activeScene);
        }
        else{
            ((Screen) _activeLevel).checkEvent(e);
        }

    }

}
