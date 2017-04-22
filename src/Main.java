/**
 * 
 * @author holden
 *
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{
	@Override
	public void start(Stage primaryStage)
	{
		//Input UI controls
		Label lblWidth = new Label(String.format("%-9s", "Width:"));
		Label lblHeight = new Label(String.format("%-9s", "Height:"));
		TextField tfWidth = new TextField();
		tfWidth.setPrefColumnCount(3);
		TextField tfHeight = new TextField();
		tfHeight.setPrefColumnCount(3);
		Button btnSolve = new Button("Find Tour");	
		
		//Containers for UI controls
		HBox widthContainer = new HBox();
		widthContainer.getChildren().addAll(lblWidth, tfWidth);
		HBox heightContainer = new HBox();
		heightContainer.getChildren().addAll(lblHeight, tfHeight, btnSolve);
		VBox uiControls = new VBox();
		uiControls.getChildren().addAll(widthContainer, heightContainer);
		uiControls.setMinWidth(300);
		
		//Program Output
		Text txtOutput = new Text("Hello World");

		
		HBox userInfo = new HBox();
		userInfo.setPadding(new Insets(10));
		userInfo.getChildren().addAll(uiControls, txtOutput);
		BorderPane root = new BorderPane();
		root.setTop(userInfo);
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args)
	{
		launch(args);
	}
}