package loop;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class AlienWorld extends Base {
	
	public static final String GAME_TITLE = "Alien Attack";
	public static final int FRAMES_PER_SECOND = 60;
	public final int ALIEN_GOAL = 30;

	private Stage _primaryStage;
	private Level _activeLevel;
	private Scene _activeScene;

	public AlienWorld(){
		super(FRAMES_PER_SECOND, GAME_TITLE);
	}

	public AlienWorld(int fps, String title) {
		super(fps, title);
	}

	@Override
	public void updateShapes(){
		_activeLevel.updateShapes();
	}

	@Override
	public void checkCollisions(){
		_activeLevel.checkCollisions();
	}

	@Override
	public void cleanupShapes(){
		_activeLevel.cleanupShapes();
		
		if(_activeLevel instanceof BossMode){
			if(!((BossMode) _activeLevel).getBoss().getBossAlive()){
				showEnd();
			}
			else if(((BossMode) _activeLevel).getEarth().isAlive() == false){
				showLost();
			}
		}
		else if(_activeLevel instanceof StarMode){
			if(((StarMode) _activeLevel).getAliensGone() == ALIEN_GOAL){
				preBossLevel();
			}
			else if(((StarMode) _activeLevel).getEarth().isAlive() == false){
				showLost();
			}
		}
		else if(_activeLevel instanceof AlienMode){
			if(((AlienMode) _activeLevel).getAliensGone() == ALIEN_GOAL){
				nextLevel();
			}
			else if(((AlienMode) _activeLevel).getEarth().isAlive() == false){
				showLost();
			}
		}

	}

	@Override
	public void initialize(Stage primaryStage) {

		_primaryStage = primaryStage;

		// Sets the window title
		_primaryStage.setTitle(getWindowTitle());

		_activeLevel = new SplashScreen();
		_activeScene = _activeLevel.initialize();

		//changed for easier testing, in final project make
		//startAlienMode()
		_activeScene.setOnKeyPressed(e -> startAlienMode(e));

		_primaryStage.setScene(_activeScene);

	}

	public void startAlienMode(KeyEvent e){

		if(e.getCode().getName().equals("Enter")){
			_activeLevel = new AlienMode();
			_activeScene = _activeLevel.initialize();

			_primaryStage.setScene(_activeScene);
		}
	}

	public void nextLevel(){

		_activeLevel = new StarScreen();
		_activeScene = _activeLevel.initialize();
		_activeScene.setOnKeyPressed(e -> startStarMode(e));

		_primaryStage.setScene(_activeScene);

	}
	
	public void startStarMode(KeyEvent e){

		if(e.getCode().getName().equals("Enter")){
			_activeLevel = new StarMode();
			_activeScene = _activeLevel.initialize();

			_primaryStage.setScene(_activeScene);
		}
	}
	
	public void preBossLevel(){
		
		_activeLevel = new BossScreen();
		_activeScene = _activeLevel.initialize();
		_activeScene.setOnKeyPressed(e -> startBossMode(e));

		_primaryStage.setScene(_activeScene);
		
	}

	public void startBossMode(KeyEvent e){

		if(e.getCode().getName().equals("Enter")){
			_activeLevel = new BossMode();
			_activeScene = _activeLevel.initialize();

			_primaryStage.setScene(_activeScene);
		}

	}

	public void showEnd(){

		_activeLevel = new EndScreen();
		_activeScene = _activeLevel.initialize();

		_activeScene.setOnKeyPressed(e -> endOrRestartGame(e));

		_primaryStage.setScene(_activeScene);

	}
	
	public void showLost(){
		
		_activeLevel = new LostScreen();
		_activeScene = _activeLevel.initialize();

		_activeScene.setOnKeyPressed(e -> endOrRestartGame(e));

		_primaryStage.setScene(_activeScene);
		
	}
	
	public void endOrRestartGame(KeyEvent e){
		
		if(e.getCode().getName().equals("E")){
			System.exit(0);
		}
		else if(e.getCode().getName().equals("Enter")){
			_activeLevel = new AlienMode();
			_activeScene = _activeLevel.initialize();

			_primaryStage.setScene(_activeScene);
		}
		
	}

}
