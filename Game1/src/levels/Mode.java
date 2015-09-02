package levels;

import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import loop.MouseCoords;
import sprites.Alien;
import sprites.AlienManager;
import sprites.Earth;
import sprites.Shot;
import sprites.ShotManager;

public abstract class Mode extends Level {
	
	public final int MONITOR_HEIGHT = 30;
	public final int COUNT_STARTER = 61;

	private Alien _alien;
	private Earth _earth;

	private int _updateCount;

	private int _aliensShot;
	private int _aliensLaunched;
	private int _aliensGone;

	private MouseCoords _mouse;

	private AlienManager _alienManager;
	private ShotManager _shotsManager;

	private Rectangle _earthMonitor;

	@Override
	public Scene initialize() {

		createEarth();
		createManagers();
		makeLifeMonitor();

		setUpdateCount(COUNT_STARTER);
		_aliensShot = 0;
		_aliensLaunched = 0;
		_aliensGone = 0;

		_mouse = new MouseCoords();

		getScene().setOnKeyPressed(e -> shoot(e));
		getScene().setOnMouseMoved(e -> mouseCoords(e));

		return getScene();

	}

	public void createEarth(){

		_earth = new Earth();
		getRoot().getChildren().add(_earth.getImageView());

	}

	public void createManagers(){

		_alienManager = new AlienManager();
		_shotsManager = new ShotManager();

	}

	public void makeLifeMonitor(){
		_earthMonitor = new Rectangle(0, 0, getScene().getWidth(), MONITOR_HEIGHT);
		_earthMonitor.setFill(Color.GREENYELLOW);
		getRoot().getChildren().add(_earthMonitor);
	}

	public void shoot(KeyEvent e){
		if(e.getCode().getName().equals("Space")){
			Shot b = new Shot(_mouse, getScene());

			_shotsManager.addShot(b);

			getRoot().getChildren().add(b.getImageView());
		}
		else if(e.getCode().getName().equals("W")){
			for(int i = 0; i < 5; i++){
				Shot b = new Shot(_earth.getX(), _earth.getY(), i, getScene());
				_shotsManager.addShot(b);
				System.out.println(i);
				getRoot().getChildren().add(b.getImageView());
			}
		}
	}

	public void mouseCoords(MouseEvent e){
		_mouse.setCoords(e.getSceneX(), e.getSceneY());
	}

	@Override
	public abstract void updateShapes();

	@Override
	public void checkCollisions() {

		if(_alienManager.getSize() != 0){
			for(int j = 0; j < _alienManager.getSize(); j++){

				//check if the alien has hit the earth
				if(!_alienManager.getAlien(j).getHasHitEarth()){
					if(_earth.getImageView().getBoundsInParent().intersects(_alienManager.getAlien(j).getImageView().getBoundsInParent())){
						//System.out.println(_earth.getImageView().getBoundsInParent().getMinX() + " " + _alienManager.getAlien(j).getImageView().getBoundsInParent().getMinX());
						_alienManager.getAlien(j).setHasHitEarth(true);
						getRoot().getChildren().remove(_alienManager.removeAlien(j).getImageView());
						_aliensGone++;
						_earth.addHit();
						_earthMonitor.setWidth(((_earth.EARTH_LIVES - _earth.getHits())/_earth.EARTH_LIVES) * getScene().getWidth());
						return;
					}
				}

				//check if a shot has hit an alien
				for(int i = 0; i < _shotsManager.getSize(); i++){
					//System.out.println(_shotsManager.getShot(i).getImageView().getBoundsInParent().getMinX() + " " + _alienManager.getAlien(j).getImageView().getBoundsInParent().getMinX());
					if(_alienManager.getAlien(j).getImageView().getBoundsInParent().intersects(_shotsManager.getShot(i).getImageView().getBoundsInParent())){
						getRoot().getChildren().remove(_alienManager.removeAlien(j).getImageView());
						_aliensGone++;
						getRoot().getChildren().remove(_shotsManager.removeShot(i).getImageView());
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

		if(_shotsManager.getShots().size() != 0){
			for(int i = 0; i < _shotsManager.getSize(); i++){
				if(_shotsManager.getShot(i).getImageView().getBoundsInParent().getMinX() <= 0 ||
						_shotsManager.getShot(i).getImageView().getBoundsInParent().getMaxX() >= getScene().getWidth()){
					getRoot().getChildren().remove(_shotsManager.removeShot(i).getImageView());
				}
			}
		}

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

	public Earth getEarth() {
		return _earth;
	}

	public void setEarth(Earth earth) {
		_earth = earth;
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

	public MouseCoords getMouse() {
		return _mouse;
	}

	public void setMouse(MouseCoords mouse) {
		_mouse = mouse;
	}

	public AlienManager getAlienManager() {
		return _alienManager;
	}

	public void setAlienManager(AlienManager alienManager) {
		_alienManager = alienManager;
	}

	public ShotManager getShotsManager() {
		return _shotsManager;
	}

	public void setShotsManager(ShotManager shotsManager) {
		_shotsManager = shotsManager;
	}

}
