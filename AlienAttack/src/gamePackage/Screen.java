package gamePackage;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/*
 * The other extension of Level. Screens involve no gameplay
 * but let the user know what is going on.
 */
public class Screen extends Level {

    private String _text;

    public Screen(String text){
        _text = text;
    }

    public void setString(String text){
        _text = text;
    }

    /*
     * Creates a pane to hold the text in the GUI, along with the 
     * earth that is held simply in the root.
     */
    public Scene initialize(){

        FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
        flowPane.setPrefSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        flowPane.setAlignment(Pos.TOP_CENTER);
        flowPane.setPadding(new Insets(Constants.SCREEN_HEIGHT/10));

        Text splashText = new Text();
        splashText.setText(_text);
        splashText.setTextAlignment(TextAlignment.CENTER);
        splashText.setFill(Color.GREENYELLOW);

        try {
            Font mFont = Font.loadFont(new FileInputStream(Constants.TYPEFACE_NAME), Constants.FONT_SIZE);
            splashText.setFont(mFont);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        flowPane.getChildren().add(splashText);

        StackPane.setAlignment(splashText,Pos.CENTER);

        getRoot().getChildren().add(flowPane);

        createEarth();

        getScene().setOnKeyPressed(e -> checkEvent(e));
        getScene().setOnMouseMoved(e -> mouseCoords(e));

        return getScene();

    }

    /*
     * Processes user key presses to respond appropriately.
     */
    public void checkEvent(KeyEvent e){
        if(e.getCode().getName().equals("Space")){
            shoot();
        }
        else if(e.getCode().getName().equals("W")){
            explode();
        }
    }

    public void updateShapes() {
        return;
    }

    public void checkCollisions() {
        return;
    }

    /*
     * Removes all shots from the outside of its screen.
     */
    public void cleanupShapes() {
        super.cleanupShapes();
    }

}
