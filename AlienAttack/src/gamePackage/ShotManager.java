package gamePackage;

import java.util.ArrayList;

/*
 * Class that holds and manages the active shots.
 */
public class ShotManager {

    private ArrayList<Shot> _activeShots;

    public ShotManager(){
        _activeShots = new ArrayList<Shot>();
    }

    public void setShots(ArrayList<Shot> shots){
        _activeShots = shots;
    }

    public ArrayList<Shot> getShots(){
        return _activeShots;
    }

    public void addShot(Shot add){
        _activeShots.add(add);
    }

    public int getSize(){
        return _activeShots.size();
    }

    public Shot getShot(int index){
        return _activeShots.get(index);
    }

    public Shot removeShot(int index){
        return _activeShots.remove(index);
    }

}
