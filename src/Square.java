/**
 * 
 * @author holden
 *
 */
public class Square {

	private boolean visited;
	private int move;
	private int weight;
	Square next;
	int x;
	int y;
	
	public Square(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}
	
	public void setMove(int move)
	{
		this.move = move;
	}
	
	public void setWeight(int weight)
	{
		this.weight = weight;
	}
	
	public boolean getVisited()
	{
		return visited;
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
