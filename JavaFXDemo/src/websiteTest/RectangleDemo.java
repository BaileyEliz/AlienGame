package websiteTest;

import javafx.animation.Animation;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.scene.paint.Color; 
import javafx.scene.shape.Rectangle; 
import javafx.scene.shape.Circle;
import javafx.stage.Stage; 
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.animation.ParallelTransition; 
import javafx.animation.RotateTransition; 
import javafx.animation.ScaleTransition; 
import javafx.animation.TranslateTransition; 
import javafx.util.Duration; 
 
public class RectangleDemo extends Application { 
	
	Rectangle r;
	Circle c;
	Timeline tl;
 
    @Override 
    public void start(Stage stage) { 
        Group root = new Group(); 
        Scene scene = new Scene(root, 500, 500, Color.BLACK); 
        r = new Rectangle(0, 0, 250, 250); 
        r.setFill(Color.BLUE); 
        c = new Circle(390, 390, 25);
        c.setFill(Color.LIGHTSEAGREEN);
        root.getChildren().add(r); 
        root.getChildren().add(c);
 
        TranslateTransition translate = 
        new TranslateTransition(Duration.millis(2000)); 
        translate.setToX(390); 
        translate.setToY(390); 
 
        FillTransition fill = new FillTransition(Duration.millis(750)); 
        fill.setToValue(Color.RED); 
 
        RotateTransition rotate = new RotateTransition(Duration.millis(750)); 
        rotate.setToAngle(360); 
 
        ScaleTransition scale = new ScaleTransition(Duration.millis(750)); 
        scale.setToX(0.1); 
        scale.setToY(0.1); 
 
        ParallelTransition transition = new ParallelTransition(r, 
        translate, fill, rotate, scale); 
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true); 
        transition.play(); 
 
        stage.setTitle("JavaFX Scene Graph Demo"); 
        stage.setScene(scene); 
        stage.show(); 
    } 
 
    public static void main(String[] args) { 
        launch(args); 
    } 
}