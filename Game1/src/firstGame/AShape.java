package firstGame;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class AShape {
	
	private Shape _node;
	
	/** Animation for the node */
    public List _animations = new ArrayList<>();
    
    public AShape(Shape nodeType){
    	_node = nodeType;
    }
    
    public void setColor(Color color){
    	_node.setFill(color);
    }
    
    public void setAnimations(ArrayList<Transition> transitions){
    	
    	ParallelTransition transition = new ParallelTransition(_node, 
    	        transitions.get(0)); 
    	        transition.play(); 
    	
    }
    
    public Node getNode(){
    	return _node;
    }

}
