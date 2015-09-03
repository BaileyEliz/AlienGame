package loop;

public class AlienMode extends Mode {
	
	public final int MAX_ALIENS_LAUNCHED = 29;
	public final int ALIEN_LAUNCH_RATE = 30;

	@Override
	public void updateShapes() {
		
		if(getAliensLaunched() > MAX_ALIENS_LAUNCHED){
			return;
		}
		if(getUpdateCount() > ALIEN_LAUNCH_RATE){
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

	public void makeAlien(){
		
		int[] randomNumbers = getRandomXY(RANDOM_OFFSCREEN);

		setAlien(new Alien(randomNumbers[0], randomNumbers[1], getSlowMo()));

		getAlienManager().addAlien(getAlien());

		getRoot().getChildren().add(getAlien().getImageView());

	}

}
