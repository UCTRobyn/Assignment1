/*Friend class which has instance variables name, position, comparisons
and time and get and set methods for the instance variables.*/


public class Friend
{
	public String name;
	public int position;
	public int comparisons = 0;
	public double time = 0.0;
	
	public Friend(String name) 	//In the driver class I create Friend objects by passing each name in toSearchFor.txt 
	{							//through this constructor
		this.name = name;
	}

	public void setPos(int position)
	{
		this.position = position;
	}
		
	public void setCom(int comparisons)
	{
		this.comparisons = comparisons;
	}
	
	public void setTime(double time)
	{
		this.time = time;
	}

	public int getCom()
	{
		return comparisons;
	}
	
	public double getTime()
	{
		return time;
	}	
	
	@Override
	public String toString()  		//Overriding the toString method so that printing a friend object
	{								//displays the name, position, comparisons and time associated with the object
		if (name.length() >= 16)
			return name+"\t"+position+"\t\t"+comparisons+"\t\t"+time;	//aligns name, position, comparisons and time into neat columns
		else
			return name+"\t\t"+position+"\t\t"+comparisons+"\t\t"+time;
	}
}

