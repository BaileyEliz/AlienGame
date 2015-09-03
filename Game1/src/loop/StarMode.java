package loop;

import javafx.scene.Scene;

public class StarMode extends AlienMode {

	public final int STAR_CREATION_RATE = 120;
	public final int STAR_EXPLOSION_INDICATOR = 0;
	public final int EXPLOSION_NUMBER = 5;
	
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
		
		int[] randomNumbers = getRandomXY(RANDOM_ONSCREEN);
		
		Star s = new Star(randomNumbers[0], randomNumbers[1]);

		_starManager.addStar(s);
		getRoot().getChildren().add(s.getImageView());
	}

	@Override
	public void updateShapes() {

		super.updateShapes();
		
		if(_starCounter > STAR_CREATION_RATE){
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

		for(int i = 0; i < EXPLOSION_NUMBER; i++){
			Shot b = new Shot(x, y, i, getScene(), STAR_EXPLOSION_INDICATOR);
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
