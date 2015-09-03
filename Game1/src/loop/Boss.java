package loop;

public class Boss extends Sprite{
	
	public final int BOSS_WIDTH = 150;
	public final int BOSS_HEIGHT = 70;
	public final String BOSS_IMAGE = "Images/mothership.png";
	
	private int timesHit;
	private boolean _bossAlive;
	
	public Boss(double x, double y) {
		setWidth(BOSS_WIDTH);
		setHeight(BOSS_HEIGHT);
		setX(x);
		setY(y);
		setFileName(BOSS_IMAGE);
		initializeImage();
		_bossAlive = true;
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
		return _bossAlive;
	}
	
	public void setBossAlive(boolean alive){
		_bossAlive = alive;
	}

}
