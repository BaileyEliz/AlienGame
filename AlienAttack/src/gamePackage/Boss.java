package gamePackage;

/*
 * The Boss version of the Sprite. Knows how many hits
 * it has.
 */

public class Boss extends Sprite{

    private int timesHit;

    public Boss(double x, double y) {
        setWidth(Constants.BOSS_WIDTH);
        setHeight(Constants.BOSS_HEIGHT);
        setX(x);
        setY(y);
        setFileName(Constants.BOSS_IMAGE);
        initializeImage();
    }

    public void initialize(){
        timesHit = 0;
    }

    public void addHit(){
        timesHit++;
    }

    public int getHits(){
        return timesHit;
    }

    public void setHits(int i){
        timesHit = i;
    }

    public Boss getBoss(){
        return this;
    }

    public boolean getBossAlive(){
        return ((Constants.BOSS_LIVES - timesHit) > 0);
    }

}
