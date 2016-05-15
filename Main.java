import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		ArrayList<Vertex> persons = readData();
		
		Vertex.connectPeople(persons); //create digraph on termins
		Vertex.clean(persons); //remove people with no chances for success
		
		for(Vertex a : persons) System.out.println(a.toString()); //echo cleared input
		
		ArrayList<ArrayList<Vertex>> components = splitToComponents(persons); //split digraphg on connected components
		
		System.out.println("---------------------------------------------");
		System.out.println("Number of connected components: " + components.size());
		
		int componentId = 0;
		for(ArrayList<Vertex> a : components) //do for every component
		{
			Vertex.sortInComp(a, componentId); //add local ids in this component	
			
			System.out.println("---------------------------------------------");
			System.out.println("Number of vertex in this component: "+ a.size());

			int[] result = new HungarianAlgorithm(Vertex.getMatrix(a)).execute();
			
			int success = 0;
	        for(int i = 0; i < result.length; i++) //echo results
	        {
	        	//we need to convert from local ids to global ones
	        	Vertex v = Vertex.findVertexByLocalID(a, i);
	        	Vertex u = Vertex.findVertexByLocalID(a, result[i]);
	        	
	        	v.newTermin = u.myTermin;
	        	
	        	int fromId = v.id;
	        	int toId = u.id;
	        	
	        	if(fromId != toId)
	        	{
	        		System.out.println(fromId + " -> " + toId);
	        		success++;
	        	}
	        }
			System.out.println("Success: " + success );
			componentId++;
		}
		
		for(Vertex a : persons) System.out.println(a.toString()); //echo cleared input
	}
	
	
	private static ArrayList<Vertex> readData() 
	{
		Scanner sc = new Scanner(System.in);
		
		int stPeople = sc.nextInt();
		
		ArrayList<Vertex> persons = new ArrayList<Vertex>();
		for(int i = 0; i < stPeople; i++)
		{
			//int index = sc.nextInt();
			int owned = sc.nextInt();
			int stWishes = sc.nextInt();
			
			ArrayList<Integer> wishes = new ArrayList<Integer>();
			
			for(int j = 0; j < stWishes; j++)
			{
				wishes.add(sc.nextInt());
			}
			//persons.add(new Vertex(index, owned, wishes));
			persons.add(new Vertex(i, owned, wishes));
		}
		sc.close();
		return persons;
	}

	private static ArrayList<ArrayList<Vertex>> splitToComponents(ArrayList<Vertex> persons)
	{
		ArrayList<ArrayList<Vertex>> components = new ArrayList<ArrayList<Vertex>>();

		int StObdelanih = 0;
		Queue<Vertex> queue = new LinkedList<Vertex>();
		while(StObdelanih < persons.size())
		{
			ArrayList<Vertex> current = new ArrayList<Vertex>();
			queue.clear();
			
			for(Vertex a : persons) //najdemo prvega nepovezanega
			{
				if(!a.visited)
				{
					queue.add(a);
					break;
				}
			}
			
			while(!queue.isEmpty()) //prehodimo komponento
			{
				Vertex element = queue.poll();
				element.visited = true;
				StObdelanih++;
				current.add(element);
				
				for(Vertex child : element.connectedTo)
				{
					if(!child.visited && !queue.contains(child)) queue.add(child);
				}
			}
			components.add(current);
		}

		return components;
	}
}
