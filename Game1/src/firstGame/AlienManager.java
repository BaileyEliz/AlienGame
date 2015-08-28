package firstGame;

import java.util.ArrayList;

public class AlienManager {
	
	private ArrayList<Alien> ACTIVE_ALIENS;
	
	private ArrayList<Alien> HIT_ALIENS;
	
	public AlienManager(){
		ACTIVE_ALIENS = new ArrayList<Alien>();
		HIT_ALIENS = new ArrayList<Alien>();
	}
	
	public void setAliens(ArrayList<Alien> aliens){
		ACTIVE_ALIENS = aliens;
	}
	
	public ArrayList<Alien> getAliens(){
		return ACTIVE_ALIENS;
	}

}
