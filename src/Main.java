/**
 * 
 * @author holden
 *
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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
		
		//Containers for UI controls
		HBox widthContainer = new HBox();
		widthContainer.getChildren().addAll(lblWidth, tfWidth);
		HBox heightContainer = new HBox();
		heightContainer.getChildren().addAll(lblHeight, tfHeight);
		VBox uiControls = new VBox();
		uiControls.getChildren().addAll(widthContainer, heightContainer);
		uiControls.setMinWidth(300);
		
		//Path buttons
		Button btnNumbers = new Button("Show Moves");
		Button btnPath = new Button("Show Path");
		Button btnAnimate = new Button("Play Animation");
		HBox buttons = new HBox();
		buttons.getChildren().addAll(btnNumbers, btnPath, btnAnimate);
		
		//Program Output
		Text txtOutput = new Text("Hello World");

		BorderPane root = new BorderPane();		
		HBox userInfo = new HBox();
		userInfo.setPadding(new Insets(10));
		userInfo.getChildren().addAll(uiControls, txtOutput);
		root.setBottom(buttons);
		root.setTop(userInfo);
		Scene scene = new Scene(root, 800, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		btnNumbers.setOnMouseClicked(e -> {
			Board board = new Board(Integer.parseInt(tfWidth.getText()),Integer.parseInt(tfHeight.getText()));
			StackPane solution = drawBoardNumbers(board.findTour());
			solution.setAlignment(Pos.CENTER);
			root.setCenter(solution);
		});
		
	}
	public static void main(String[] args)
	{
		launch(args);
	}
	
	private static StackPane drawBoardNumbers(Square[][] squares)
	{
		StackPane board = new StackPane();
		VBox rows = new VBox();
		
		for (int i = 0; i < squares.length; i++)
		{			
			HBox columns = new HBox();
			for (int j = 0; j < squares[i].length; j++)
			{				
				Label square = new Label();
				if (i % 2 == 0 && j % 2 == 1 || i % 2 == 1 && j % 2 == 0)
					square.setStyle("-fx-background-color:#F7F7F7");
				else
					square.setStyle("-fx-background-color:#9DD3Df");
				square.setMinSize(20, 20);
				square.setText(String.format("%2d", squares[i][j].getMove()));
				columns.getChildren().add(square);
			}
			columns.setAlignment(Pos.CENTER);
			rows.getChildren().add(columns);
		}
		
		rows.setAlignment(Pos.CENTER);
		board.setStyle("-fx-border-width:3");
		board.getChildren().add(rows);
		return board;
	}
	
	
}