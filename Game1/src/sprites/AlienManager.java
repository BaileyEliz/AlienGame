package sprites;

import java.util.ArrayList;

public class AlienManager {
	
	private ArrayList<Alien> ACTIVE_ALIENS;
	
	public AlienManager(){
		ACTIVE_ALIENS = new ArrayList<Alien>();
	}
	
	public void setAliens(ArrayList<Alien> aliens){
		ACTIVE_ALIENS = aliens;
	}
	
	public ArrayList<Alien> getAliens(){
		return ACTIVE_ALIENS;
	}
	
	public Alien getAlien(int index){
		return ACTIVE_ALIENS.get(index);
	}
	
	public void addAlien(Alien alien){
		ACTIVE_ALIENS.add(alien);
	}
	
	public Alien removeAlien(int index){
		return ACTIVE_ALIENS.remove(index);
	}
	
	public int getSize(){
		return ACTIVE_ALIENS.size();
	}

}
