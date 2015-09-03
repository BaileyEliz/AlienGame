package loop;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Screen extends Level {
	
	public final String TYPEFACE_NAME = "assets/Fonts/SpecialElite.ttf";
	public final int FONT_SIZE = 25;
	public final int MARGIN_INSET = 60;
	
	private String _text;
	
	public Screen(){
		
	}
	
	public void setString(String text){
		_text = text;
	}
	
	public Scene initialize(){
		
		Text splashText = new Text();
		splashText.setText(_text);
		splashText.setTextAlignment(TextAlignment.CENTER);
		splashText.setFill(Color.GREENYELLOW);

		try {
			Font mFont = Font.loadFont(new FileInputStream(TYPEFACE_NAME), FONT_SIZE);
			splashText.setFont(mFont);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		BorderPane _splashLayout = new BorderPane();
		BorderPane.setAlignment(splashText, Pos.CENTER);
		BorderPane.setMargin(splashText, new Insets(MARGIN_INSET, MARGIN_INSET, MARGIN_INSET, MARGIN_INSET));
		_splashLayout.setCenter(splashText);
		
		getRoot().getChildren().add(_splashLayout);
		return getScene();
		
	}

	public void updateShapes() {
		return;
	}

	public void checkCollisions() {
		return;
	}

	public void cleanupShapes() {
		return;
	}

}
