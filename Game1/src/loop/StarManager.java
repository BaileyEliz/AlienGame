package loop;

import java.util.ArrayList;

public class StarManager {
	
	private ArrayList<Star> STARS;
	
	public StarManager(){
		STARS = new ArrayList<Star>();
	}
	
	public void addStar(Star s){
		STARS.add(s);
	}
	
	public int getStarLength(){
		return STARS.size();
	}
	
	public Star getStar(int i){
		return STARS.get(i);
	}
	
	public Star removeStar(int i){
		return STARS.remove(i);
	}

}
