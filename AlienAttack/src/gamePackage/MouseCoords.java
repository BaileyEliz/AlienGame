package gamePackage;

/*
 * Holds the current x and y positions of
 * the mouse.
 */

public class MouseCoords {

    private double _X;
    private double _Y;

    public MouseCoords(){
        _X = 0;
        _Y = 0;
    }

    public void setCoords(double x, double y){
        _X = x;
        _Y = y;
    }

    public double getXCoord(){
        return _X;
    }

    public double getYCoord(){
        return _Y;
    }

}
