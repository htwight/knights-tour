import java.util.ArrayList;

/**
 * 
 * @author holden
 *
 */
public class Board {

	private Tile[][] tiles;
	private int width;
	private int height;
	
	public Board(int width, int height)
	{
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				tiles[x][y] = new Tile(x, y);
	}
	
	public Tile[][] findTour()
	{
		Tile current = tiles[0][0];
		for (int i = 0; i < width * height; i++)
		{
			current.visited = true;
			current.setMove(i + 1);
			current.next = findNextMove(current);
			current = current.next;
		}
		return tiles;
	}
	
	private Tile findNextMove(Tile current)
	{
		ArrayList<Tile> moves = getMoves(current);
		if (moves.size() == 0)
			return new Tile(0,0);
		Tile nextMove = moves.get(0);
		for (int i = 0; i < moves.size(); i++)
		{
			setWeights(moves.get(i));
			if (moves.get(i).getWeight() < nextMove.getWeight())
				nextMove = moves.get(i);
		}
		return nextMove;
		
	}
	
	private ArrayList<Tile> getMoves(Tile t)
	{
		//Represents distance moved {left/right, up/down}
		int[][] moveSet = {
				{-2, 1}, {-1, 2}, {1, 2}, {2, 1},
		        {2, -1}, {1, -2}, {-1, -2}, {-2, -1}
		};
		
		ArrayList<Tile> moves = new ArrayList<>();
		for (int[] move : moveSet)
			if (isValidMove(t.x + move[0], t.y + move[1]))
				if (!tiles[t.x + move[0]][t.y + move[1]].visited)
					moves.add(tiles[t.x + move[0]][t.y + move[1]]);
		return moves;
	}
	
	private void setWeights(Tile t)
	{
		ArrayList<Tile> moves = getMoves(t);
		t.setWeight(moves.size());
	}
	
	private boolean isValidMove(int x, int y)
	{
		return x >= 0 && y >= 0 && x < width && y < height;
	}	
	
}
