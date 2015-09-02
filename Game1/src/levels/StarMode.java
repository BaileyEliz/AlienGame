package levels;

import java.util.Random;

import javafx.scene.Scene;
import sprites.Shot;
import sprites.Star;
import sprites.StarManager;

public class StarMode extends AlienMode {

	private StarManager _starManager;
	private int _starCounter;

	@Override
	public Scene initialize() {
		
		Scene scene = super.initialize();
		_starCounter = 0;
		setScene(scene);
		return getScene();

	}

	@Override
	public void createManagers(){

		super.createManagers();
		_starManager = new StarManager();

	}

	public void makeStars(){
		
		int[] randomNumbers = getInnerRandomXY();
		
		Star s = new Star(randomNumbers[0], randomNumbers[1]);

		_starManager.addStar(s);
		getRoot().getChildren().add(s.getImageView());
	}
	
	public int[] getInnerRandomXY(){
		
		Random rand = new Random();

		int smallX = rand.nextInt(180);
		int smallY = rand.nextInt(180);
		int bigX = (420 + rand.nextInt((int) getScene().getWidth() - 420));
		int bigY = (420 + rand.nextInt((int) getScene().getWidth() - 420));
		int bigOrSmallX = rand.nextInt(2);
		int bigOrSmallY = rand.nextInt(2);

		int randomNumberOne;
		int randomNumberTwo;

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

	@Override
	public void updateShapes() {

		super.updateShapes();
		
		if(_starCounter > 120){
			_starCounter = 0;
			makeStars();
		}
		else{
			_starCounter++;
		}
		
	}

	@Override
	public void checkCollisions() {
		
		super.checkCollisions();

		if(_starManager.getStarLength() != 0){
			for(int i = 0; i < _starManager.getStarLength(); i++){
				for(int j = 0; j < getShotsManager().getSize(); j++){
					if(_starManager.getStar(i).getImageView().getBoundsInParent()
							.intersects(getShotsManager().getShot(j).getImageView().getBoundsInParent())){
						explode(_starManager.getStar(i).getImageView().getBoundsInParent().getMinX(), 
								_starManager.getStar(i).getImageView().getBoundsInParent().getMinY());
						getRoot().getChildren().remove(getShotsManager().removeShot(j).getImageView());
						getRoot().getChildren().remove(_starManager.removeStar(i).getImageView());
						return;
					}
				}
			}
		}

	}

	public void explode(double x, double y){

		for(int i = 0; i < 5; i++){
			Shot b = new Shot(x, y, i, getScene());
			getShotsManager().addShot(b);
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
