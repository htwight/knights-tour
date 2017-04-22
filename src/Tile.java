/**
 * 
 * @author holden
 *
 */
public class Tile {

	boolean visited;
	private int move;
	private int weight;
	Tile next;
	int x;
	int y;
	
	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setMove(int move)
	{
		this.move = move;
	}
	
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	public int getMove()
	{
		return move;
	}
	
	public int getWeight()
	{
		return weight;
	}
}
