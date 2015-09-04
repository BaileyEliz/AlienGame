package gamePackage;

/*
 * The star version of a Sprite.
 */
public class Star extends Sprite{

    public Star(double x, double y){
        setWidth(Constants.STAR_WIDTH);
        setHeight(Constants.STAR_HEIGHT);
        setX(x);
        setY(y);
        setFileName(Constants.STAR_IMAGE);
        initializeImage();
        initialize();
    }

    public void initialize(){}

}
