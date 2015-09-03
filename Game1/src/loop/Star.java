package loop;

public class Star extends Sprite{
	
	public final int STAR_WIDTH = 30;
	public final int STAR_HEIGHT = 30;
	public final String STAR_IMAGE = "Images/star.png";
	
	public Star(double x, double y){
		setWidth(STAR_WIDTH);
		setHeight(STAR_HEIGHT);
		setX(x);
		setY(y);
		setFileName(STAR_IMAGE);
		initializeImage();
		initialize();
	}
	
	public void initialize(){}

}
