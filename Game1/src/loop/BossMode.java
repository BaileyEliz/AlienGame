package loop;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BossMode extends StarMode {

	public final double BOSS_LIVES = 9;
	public final int BOSS_MOVE_RATE = 30;
	
	private int oldRandomNumberOne;
	private int oldRandomNumberTwo;

	private Boss _boss;
	private Rectangle _monitor;

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

	@Override
	public void updateShapes() {

		if(getUpdateCount() > BOSS_MOVE_RATE){

			if(_boss.getBossAlive()){

				int[] randomNumbers = getRandomXY(RANDOM_ONSCREEN);

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
		
		if(getStarCounter() > STAR_CREATION_RATE){
			setStarCounter(0);
			makeStars();
		}
		else{
			addStarCounter();
		}

	}

	@Override
	public void checkCollisions() {
		
		super.checkCollisions();

		if(_boss.getBossAlive()){
			for(int i = 0; i < getShotsManager().getSize(); i++){
				if(_boss.getBoss().getImageView().getBoundsInParent().intersects(getShotsManager().getShot(i).getImageView().getBoundsInParent())){
					_boss.getBoss().addHit();
					_monitor.setWidth(((BOSS_LIVES - _boss.getHits())/BOSS_LIVES) * getScene().getWidth());
					if(_boss.getBoss().getHits() == BOSS_LIVES){
						getRoot().getChildren().remove(_boss.getBoss().getImageView());
						_boss.setBossAlive(false);
					}
					getRoot().getChildren().remove(getShotsManager().removeShot(i).getImageView());
				}
			}
		}

	}

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
		_monitor = new Rectangle(0, (getScene().getHeight() - MONITOR_HEIGHT), getScene().getWidth(), MONITOR_HEIGHT);
		_monitor.setFill(Color.RED);
		getRoot().getChildren().add(_monitor);
	}
	
	public Boss getBoss(){
		return _boss;
	}

}
