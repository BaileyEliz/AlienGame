package levels;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sprites.Alien;
import sprites.Boss;

public class BossMode extends StarMode {

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

		if(getUpdateCount() > 30){

			if(_boss.getBossAlive()){

				int[] randomNumbers = getInnerRandomXY();

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
		
		if(getStarCounter() > 120){
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
					_monitor.setWidth(((9 - _boss.getHits())/9.0) * getScene().getWidth());
					if(_boss.getBoss().getHits() > 8){
						getRoot().getChildren().remove(_boss.getBoss().getImageView());
						_boss.setBossAlive(false);
					}
					getRoot().getChildren().remove(getShotsManager().removeShot(i).getImageView());
				}
			}
		}

	}

	public void makeAlienChild(int x, int y){

		setAlien(new Alien(x, y));

		getAlienManager().addAlien(getAlien());

		getRoot().getChildren().add(getAlien().getImageView());

	}

	public void makeBoss(){

		_boss = new Boss(0, 0);
		_boss.setHits(0);

		getRoot().getChildren().add(_boss.getBoss().getImageView());

	}
	
	public void makeBossLifeMonitor(){
		_monitor = new Rectangle(0, (getScene().getHeight() - 30), getScene().getWidth(), 30);
		_monitor.setFill(Color.RED);
		getRoot().getChildren().add(_monitor);
	}
	
	public Boss getBoss(){
		return _boss;
	}

}
