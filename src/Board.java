import java.util.ArrayList;

/**
 * 
 * @author holden
 *
 */
public class Board {

	private Square[][] squares;
	private int width;
	private int height;
	
	public Board(int width, int height)
	{
		this.width = width;
		this.height = height;
		squares = new Square[width][height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				squares[x][y] = new Square(x, y);
	}
	
	public Square[][] findTour()
	{
		Square current = squares[0][0];
		for (int i = 0; i < width * height; i++)
		{
			current.setVisited(true);
			current.setMove(i);
			current.next = findNextMove(current);
			current = current.next;
		}
		return squares;
	}
	
	private Square findNextMove(Square current)
	{
		ArrayList<Square> moves = getMoves(current);
		if (moves.size() == 0)
			return new Square(0,0);
		Square nextMove = moves.get(0);
		for (int i = 0; i < moves.size(); i++)
		{
			calcWeight(moves.get(i));
			if (moves.get(i).getWeight() < nextMove.getWeight())
				nextMove = moves.get(i);
		}
		return nextMove;
		
	}
	
	private ArrayList<Square> getMoves(Square s)
	{
		//Represents distance moved {left/right, up/down}
		int[][] moveSet = {
				{-2, 1}, {-1, 2}, {1, 2}, {2, 1},
		        {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
		};
		
		ArrayList<Square> moves = new ArrayList<>();
		for (int[] move : moveSet)
			if (isValidMove(s.x + move[0], s.y + move[1]))
				if (squares[s.x + move[0]][s.y + move[1]].getVisited() == false)
					moves.add(squares[s.x + move[0]][s.y + move[1]]);
		return moves;
	}
	
	private void calcWeight(Square s)
	{
		ArrayList<Square> moves = getMoves(s);
		s.setWeight(moves.size());
	}
	
	private boolean isValidMove(int x, int y)
	{
		return x >= 0 && y >= 0 && x < width && y < height;
	}	
	
}
