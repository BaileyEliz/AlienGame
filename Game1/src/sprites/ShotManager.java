package sprites;

import java.util.ArrayList;

public class ShotManager {
	
	private ArrayList<Shot> ACTIVE_SHOTS;
	
	public ShotManager(){
		ACTIVE_SHOTS = new ArrayList<Shot>();
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
	
	public int getSize(){
		return ACTIVE_SHOTS.size();
	}
	
	public Shot getShot(int index){
		return ACTIVE_SHOTS.get(index);
	}
	
	public Shot removeShot(int index){
		return ACTIVE_SHOTS.remove(index);
	}

}
