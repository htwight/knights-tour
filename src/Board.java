
/**
 * Representation of an m x n chess board. Searches for a knight's tour using Warnsdorff's
 * heuristic: the knight always moves the the square with the fewest amount of possible moves.
 * If more than one square qualifies, the knight moves to the first square found with the
 * fewest moves. The Board is represented by a 2 dimensional array of Square objects.
 * Each square maps to one tile on the board. The moves of the tour are represented by integers.
 * The knight begins in square 1, and ends in square m x n.
 * 
 * @author holden
 *
 * This code is not published under any copyright license. 
 * Feel free to do whatever you want with it, including
 * stealing it and selling it.
 */

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Board
{

    private Square[][] squares; // A chessboard made up of squares
    private int files; // Number of columns
    private int ranks; // Number of rows

    /*
     * Create a new board consisting of m x n squares.
     */
    public Board(int files, int ranks)
    {
        this.files = files;
        this.ranks = ranks;
        squares = new Square[files][ranks];

        // Set each squares x and y coordinate, beginning with (0,0).
        for (int y = 0; y < ranks; y++)
            for (int x = 0; x < files; x++)
                squares[x][y] = new Square(x, y);
    }

    /*
     * Returns the set of squares visited by the knight, keeping track of the
     * move number that the square was visited, starting with 1. Because
     * Warnsdorff's heuristic is based on moving to the square with the least
     * possible moves, it makes sense to start in a corner.
     */
    public Square[][] findTour()
    {
        Square current = squares[0][0];         // Start in the top left.
        for (int i = 0; i < files * ranks; i++) // Loop until each square is
                                                // visited.
        {
            current.visited = true;             // Visit current square
            current.move = i + 1;               // Keep track of when square was visited
            current = findNextMove(current);    // Move to the next square
        }
        return squares;
    }

    /*
     * Based on the current position on the board, this method finds the next
     * move. Following Warnsdorff's heuristic, the next move is the square with
     * the lowest number of possible moves. If there are multiple square that
     * qualify, the first one found is visited.
     */
    private Square findNextMove(Square current)
    {
        ArrayList<Square> moves = getValidMoves(current);   // Get a list of all possible moves
        if (moves.size() == 0)                              // If there are no possible moves, return to
            return new Square(0, 0);                        // the beginning, which has always been visited.
        Square nextMove = moves.get(0);                     // Otherwise, begin considering each possible move.
        for (int i = 0; i < moves.size(); i++)              // Check each possible move
        {
            weigh(moves.get(i)); // Find the number of possible moves from each candidate
            if (moves.get(i).weight < nextMove.weight) // Choose the square with the lowest number of moves
                nextMove = moves.get(i);
        }
        return nextMove;
    }

    /*
     * Given a position on the chessboard, return a list of moves that the
     * knight can make. Moves are valid if they are within the domain of the
     * chess board, and have not already been visited.
     */
    private ArrayList<Square> getValidMoves(Square s)
    {
        // Every possible movement of a knight, modeled as {left/right, up/down}
        int[][] moveSet = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

        ArrayList<Square> moves = new ArrayList<>(); // Create a list to store all valid moves
        for (int[] move : moveSet)                          //Cycle through each of the knight's movements            
            if (isValidMove(s.x + move[0], s.y + move[1]))  // If the move is valid, add it to the list.
                if (!squares[s.x + move[0]][s.y + move[1]].visited)
                    moves.add(squares[s.x + move[0]][s.y + move[1]]);
        return moves;
    }

    /*
     * Given a square, set its weight to the number of possible next moves.
     */
    private void weigh(Square s)
    {
        ArrayList<Square> moves = getValidMoves(s); // Find all possible moves
        s.weight = moves.size();                    // Set the weight
    }

    /*
     * Checks if the next move is within the domain of the chessboard. Both x
     * and y coordinates must fall inclusively between 0 and files - 1 and ranks
     * - 1, respectively.
     */
    private boolean isValidMove(int x, int y)
    {
        return x >= 0 && y >= 0 && x < files && y < ranks;
    }

    /*
     * Returns the final chessboard labeled with each move number. Squares not
     * visited are marked in red.
     */
    public StackPane draw()
    {
        return draw(this.findTour());
    }

    /*
     * Create a stackpane to model the chessboard row by row given its knight's
     * tour. The chessboard is blue and white, marking unvisitied squares in
     * red.
     */
    private StackPane draw(Square[][] squares)
    {
        StackPane board = new StackPane(); // Container for the board.
        VBox rows = new VBox(); // Contains each row of the board.

        // Iterate through each row
        for (int i = 0; i < squares.length; i++)
        {
            HBox columns = new HBox(); // contains the squares of each row
            // Iterate through each sqaure of the current row
            for (int j = 0; j < squares[i].length; j++)
            {
                Label square = new Label(); // Each square to be labeled with its corresponding move

                // If the square was not visited, color it red, otherwise, color
                // it in an
                // alternating blue and white color.
                if (squares[i][j].move == 0)
                    square.setStyle("-fx-background-color:#DC143C");
                else if (i % 2 == 0 && j % 2 == 1 || i % 2 == 1 && j % 2 == 0)
                    square.setStyle("-fx-background-color:#9DD3Df");
                else
                    square.setStyle("-fx-background-color:#F7F7F7");

                // Format size and alignment of squares.
                square.setAlignment(Pos.CENTER);
                square.setMaxSize(100, 100);
                square.prefHeightProperty().bind(rows.heightProperty());
                square.prefWidthProperty().bind(rows.heightProperty());
                square.setText(String.format("%2d", squares[i][j].move));
                // Add each sqaure to the current row
                columns.getChildren().add(square);
            }
            // add current row to the final board
            columns.setAlignment(Pos.CENTER);
            rows.getChildren().add(columns);
        }
        // add all squares to the board
        rows.setAlignment(Pos.CENTER);
        board.getChildren().add(rows);
        return board;
    }

    /*
     * Data structure to represent each square on a chessboard. The x and y
     * coordinates begin at the top left (0, 0) and end at the bottom right
     * (files - 1, ranks - 1).
     */
    private class Square
    {

        private boolean visited;    // Whether or not a the knight has visited this square.
        private int weight;         // The number of next possible moves to be made from this square.
        private int move;           // Starting with 1, the move which the knight visited this square.
        private int x;              // The x position of this square.
        private int y;              // The y position of this square.

        /* Create a new square given a set of x and y coordinates */
        public Square(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }
}
