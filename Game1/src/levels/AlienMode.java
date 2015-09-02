package levels;

import java.util.Random;

import sprites.Alien;

public class AlienMode extends Mode {

	@Override
	public void updateShapes() {
		
		if(getAliensLaunched() > 29){
			return;
		}
		if(getUpdateCount() > 60){
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
		
		int[] randomNumbers = getRandomXY();

		setAlien(new Alien(randomNumbers[0], randomNumbers[1]));

		getAlienManager().addAlien(getAlien());

		getRoot().getChildren().add(getAlien().getImageView());

	}

	public int[] getRandomXY(){
		
		Random rand = new Random();
		int randomNumberOne = rand.nextInt(400);
		int randomNumberTwo = rand.nextInt(400);
		int whichNum = rand.nextInt(2);
		int minOrMax = rand.nextInt(2);

		if(whichNum == 1){
			if(minOrMax == 1){
				randomNumberOne = -100;
			}
			else{
				//randomNumberOne = (int) scene.getWidth();
				randomNumberOne = 700;
			}
		}
		else{
			if(minOrMax == 1){
				randomNumberTwo = -100;
			}
			else{
				//randomNumberTwo = (int) scene.getWidth();
				randomNumberTwo = 700;
			}
		}
		
		int[] randomNums = {randomNumberOne, randomNumberTwo};
		return randomNums;
	}

}
