package gamePackage;

import java.util.ArrayList;

/*
 * Class that holds and manages the active stars.
 */
public class StarManager {

    private ArrayList<Star> _activeStars;

    public StarManager(){
        _activeStars = new ArrayList<Star>();
    }

    public void addStar(Star s){
        _activeStars.add(s);
    }

    public int getStarLength(){
        return _activeStars.size();
    }

    public Star getStar(int i){
        return _activeStars.get(i);
    }

    public Star removeStar(int i){
        return _activeStars.remove(i);
    }

}
