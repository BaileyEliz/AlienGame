package gamePackage;

import java.util.ArrayList;

/*
 * Class for holding and managing all active aliens in a Mode.
 */
public class AlienManager {

    private ArrayList<Alien> _activeAliens;

    public AlienManager(){
        _activeAliens = new ArrayList<Alien>();
    }

    public void setAliens(ArrayList<Alien> aliens){
        _activeAliens = aliens;
    }

    public ArrayList<Alien> getAliens(){
        return _activeAliens;
    }

    public Alien getAlien(int index){
        return _activeAliens.get(index);
    }

    public void addAlien(Alien alien){
        _activeAliens.add(alien);
    }

    public Alien removeAlien(int index){
        return _activeAliens.remove(index);
    }

    public int getSize(){
        return _activeAliens.size();
    }

}
