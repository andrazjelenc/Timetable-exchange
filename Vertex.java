import java.util.HashSet;

public class Vertex 
{
	public int termin;
	public HashSet<Integer> wishes;
	
	public Vertex (int termin, HashSet<Integer> wishes)
	{
		this.termin = termin;
		this.wishes = wishes;
	}
}