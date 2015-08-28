package firstGame;

import java.util.ArrayList;

public class ShotManager {
	
	private ArrayList<Shot> ACTIVE_SHOTS;
	
	private ArrayList<Shot> DEAD_SHOTS;
	
	public ShotManager(){
		ACTIVE_SHOTS = new ArrayList<Shot>();
		DEAD_SHOTS = new ArrayList<Shot>();
	}
	
	public void setShots(ArrayList<Shot> shots){
		ACTIVE_SHOTS = shots;
	}
	
	public ArrayList<Shot> getShots(){
		return ACTIVE_SHOTS;
	}
	
	public void addShot(Shot add){
		ACTIVE_SHOTS.add(add);
	}

}
