import java.util.HashSet;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] args) 
	{
		/**
		 * Read data in format:
		 * number_of_peoples
		 * my_termin number_of_wanted_termins termin1 termin2...
		 * ...
		 */
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt(); //numbers of students
		Vertex students[] = new Vertex[n];
		
		for(int i = 0; i < n; i++)
		{
			int termin = sc.nextInt();
			int nWishes = sc.nextInt();
			
			HashSet<Integer> wishes = new HashSet<Integer>();
			for(int j = 0; j < nWishes; j++)
			{
				wishes.add(sc.nextInt());
			}
			students[i] = new Vertex(termin, wishes);
		}
		sc.close();
		
		/**
		 *  Calculate matrix
		 */
		long startTime = System.nanoTime();
		double matrix[][] = new double[n][n]; //default contains only zeros
		
		for (int i = 0; i < n; i++)
		{
			//working with students[i]
			for(int j = 0; j < n; j++)
			{
				//comparing to students[j]
				if (i == j){
					matrix[i][i] = 1; //we can keep termin
					continue;
				}
								
				boolean canConnect = false;
				for(int wish : students[i].wishes)
				{
					if(wish == students[j].termin)
					{
						canConnect = true;
						break;
					}
				}
				
				if(!canConnect)
				{
					matrix[i][j] = n+1; //cannot exchange
				}
			}
		}
		//printMatrix(matrix);
		
		/**
		 * Student i will exchange his termin with student result[i]
		 */
		int result[] = new Hungarian(matrix).execute();
		
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		
		//System.out.println(Arrays.toString(result));
		int success = 0;
		for (int i = 0; i < n; i++)
		{
			if(result[i] != i)
			{
				System.out.println(i + " -> " + students[i].wishes.toString()+ " " + students[result[i]].termin);
				success++;
			}
		}
		System.out.println("Success: " + success);
	}
	
	/**
	 * Debug method to print out nxn matrix after establish connections
	 * @param matrix
	 */
	private static void printMatrix(double[][] matrix) 
	{
		for (int i = 0; i < matrix.length; i++)
		{
			for (int j = 0; j < matrix[i].length; j++)
			{
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
