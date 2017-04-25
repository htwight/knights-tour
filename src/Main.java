
/**
 * A program that displays a knight's tour on an m x n chessboard. The knight's tour is a
 * set of moves made by the knight, such that all squares are visited, and no square is
 * visited more than once. The knight moves in L-shaped patterns two sqaures in one direction,
 * then one more square perpendicular to the previous 2.
 *  
 * @author holden
 *
 * This code is not published under any copyright license. 
 * Feel free to do whatever you want with it, including
 * stealing it and selling it.
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
        /* Input UI controls */
        // Input for board width
        Label lblWidth = new Label(String.format("%-9s", "Width:"));
        TextField tfWidth = new TextField();
        tfWidth.setPrefColumnCount(3);
        // input for board height
        Label lblHeight = new Label(String.format("%-9s", "Height:"));
        TextField tfHeight = new TextField();
        tfHeight.setPrefColumnCount(3);

        /* Containers for UI controls */
        // Holds label and textField for width
        HBox widthContainer = new HBox();
        widthContainer.getChildren().addAll(lblWidth, tfWidth);
        //// Holds label and textField for height, as well as the solve button.
        HBox heightContainer = new HBox();
        Button btnSolve = new Button("Show Moves"); // Find and display knight's tour given width and height
        heightContainer.getChildren().addAll(lblHeight, tfHeight, btnSolve);
        // Display UI controls vertically
        VBox uiControls = new VBox();
        uiControls.getChildren().addAll(widthContainer, heightContainer);
        uiControls.setMinWidth(300);

        // Program Output
        Text txtOutput = new Text("Boards larger than 30x30 may have problems rendering properly within\n"
                + "the size of the original window. (800x900)");

        // Entire display
        BorderPane root = new BorderPane();
        // Display UI and output at the top
        HBox userInfo = new HBox();
        userInfo.setPadding(new Insets(10));
        userInfo.getChildren().addAll(uiControls, txtOutput);
        root.setTop(userInfo);
        // Show scene
        Scene scene = new Scene(root, 800, 900);
        primaryStage.setScene(scene);
        primaryStage.show();

        btnSolve.setOnMouseClicked(e ->
        {
            Board board = null; // Board to be displayed, none if input is invalid.
            // Check to make sure the input is an integer for both fields
            try
            {
                // Create board to be displayed
                board = new Board(Integer.parseInt(tfWidth.getText()), Integer.parseInt(tfHeight.getText()));
            } catch (NumberFormatException ex)
            {
                // If input is not integers, inform the user
                txtOutput.setText("Invalid input. Please enter an integer for width and height.");
            }

            // Finally, draw the board, with knights tour solution, in the
            // center of the stage.
            StackPane solution = board.draw();
            solution.setAlignment(Pos.CENTER);
            solution.setPadding(new Insets(10));
            root.setCenter(solution);
            txtOutput.setText("Knights Tour. Any squares marked red were not visited.");
        });

    }

    public static void main(String[] args)
    {
        launch(args);
    }
}