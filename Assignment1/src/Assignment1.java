import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment1 
{
	final static String fileName1 = "toSearchFor.txt";
	final static String fileName2 = "toSearchIn.txt";
	static ArrayList<Friend> list1;
	static ArrayList<String> list2;
	static int totalComparisons = 0;
	static double totalTime = 0.0;
	
	public static void main(String[] args) throws FileNotFoundException 
	{

		makeArray(); 										//see comments on method makeArray below 
		Scanner k = new Scanner(System.in);
		System.out.println("Enter 's' for sequential search or 'b' for binary search.");
		String option = k.next();
		if (option.equalsIgnoreCase("b"))
		{
			System.out.println("Binary Search");
			System.out.println("Name\t\t\tPosition\tComparisons\tTime (ms)");
			for (int i = 0; i < list1.size(); i++)
			{
				int pos = binarySearch(list1.get(i), list2);
				list1.get(i).setPos(pos);
				totalComparisons += list1.get(i).getCom();
				totalTime += list1.get(i).getTime();
				System.out.println(list1.get(i));
			}
			totalTime = Math.round(totalTime*1000.0)/1000.0;
			System.out.println("Total Comparisons to Find Mutual Friends: "+totalComparisons);
			System.out.println("Total time to Find Mutual Friends: "+totalTime);
		}
		else if (option.equalsIgnoreCase("s"))
		{
			System.out.println("Sequential Search");
			System.out.println("Name\t\t\tPosition\tComparisons\tTime (ms)");
			for (int i = 0; i < list1.size(); i++)
			{
				int pos = sequentialSearch(list1.get(i), list2);
				list1.get(i).setPos(pos);
				totalComparisons += list1.get(i).getCom();
				totalTime += list1.get(i).getTime();
				System.out.println(list1.get(i));
			}			
			totalTime = Math.round(totalTime*100.0)/100.0;
			System.out.println("Total Comparisons to Find Mutual Friends: "+totalComparisons);
			System.out.println("Total time to Find Mutual Friends: "+totalTime);
		}
		
		else
		{
			System.out.println("Incorrect input. Run program again");
			System.exit(0);
		}
	}
	
	/*This method creates list1, an Array List of Friend objects,
	 * and list 2 an Array List of Strings*/

	public static void makeArray() throws FileNotFoundException
	{
		File file1 = new File(fileName1);
		File file2 = new File(fileName2);
		Scanner scanner1 = new Scanner(file1);
		Scanner scanner2 = new Scanner(file2);
		list1 = new ArrayList<Friend>();
		list2 = new ArrayList<String>();
	
		while (scanner1.hasNext())
			list1.add(new Friend(scanner1.nextLine())); //Create a new Friend object for each name in toSearchFor.txt
														//and add it to list1
		while (scanner2.hasNext())
			list2.add(scanner2.nextLine());				//Add each name in toSearchFor.txt to list2
	}

	/*The method sequentialSearch searches for a given friend in a given Array List of Strings
	 * The method loops through the Array List; comparing the friend object's name to a String element at each iteration.   
	 */
	
	public static int sequentialSearch(Friend friend, ArrayList<String> list)
	{			
		long startTime = System.nanoTime();
		
		for (int j = 0; j < list.size(); j++)
		{
			
			if (friend.name.compareTo(list.get(j)) == 0) 				//found friend at position j in our list
			{
				long endTime = System.nanoTime();
				double time = (double)((endTime - startTime)/1000000.0); 
				double roundOff = Math.round(time * 100.0) / 100.0; 	//rounding time to 2 decimal places
				friend.setTime(roundOff); 								//set the friend object's time instance variable
				friend.setCom(j+1); 									//set the number of comparisons taken to find the friend
				return j+1;
			}
						
			else if (friend.name.compareTo(list.get(j)) < 0)			//the name in the list at position j is bigger (lexicographically)
			{															//than the friend's name and conclude that the name is not				
				long endTime = System.nanoTime();						//in the list
				double time = (double)((endTime - startTime)/1000000.0);
				double roundOff = Math.round(time * 100.0) / 100.0;
				friend.setTime(roundOff);
				friend.setCom(j+1);
				return -1;
			}
			
			else											//the name in the list at position j is smaller than the friend's
				continue;									//name and so we move to the next element in the array list of names
		}
		
		long endTime = System.nanoTime();								//this considers the case when the friend's name is not in the list
		double time = (double)((endTime - startTime)/1000000.0);		//and is larger than every name in the list (worst case scenario)
		double roundOff = Math.round(time * 100.0) / 100.0;
		friend.setTime(roundOff);
		friend.setCom(list.size());										//we made a comparison at every element in the list
		return -1;													
	}

	
	public static int binarySearch(Friend friend, ArrayList<String> list)
	{
		int low = 0;
		int high = list.size() - 1;
		int mid;
		int count = 0;
		long startTime = System.nanoTime();
		long endTime = 0;
		double time = 0;
		
		while (low <= high)
		{
			mid = (low + high)/2;
			count++;
						
			if (list.get(mid).compareTo(friend.name) < 0)
			{
				low = mid + 1;
			}
			else if (list.get(mid).compareTo(friend.name) > 0)
			{
				high = mid - 1;	
			}
			else
			{
				endTime = System.nanoTime();
				time = (double)(endTime - startTime)/1000000.0;
				double roundOff = Math.round(time * 1000.0) / 1000.0;
				friend.setTime(roundOff);
				friend.setCom(count);
				return mid + 1;
			}
		}
		
		endTime = System.nanoTime();
		time = (double)(endTime - startTime)/1000000.0;
		double roundOff = Math.round(time * 1000.0) / 1000.0;
		friend.setTime(roundOff);
		friend.setCom(count);
		return -1;
		
	}


	
}

	

