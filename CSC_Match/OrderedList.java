import java.io.Serializable;
import java.util.LinkedList;

public class OrderedList<T extends Comparable<T>> extends LinkedList<T> 
implements Serializable
{

	/**
	 * if element already exists, it is first removed.
	 * 
	 * walks a linked list and adds the element when the next element
	 * is of a lower value than this one. (using compareTo)
	 */
	private static final long serialVersionUID = 1L;

	void sortAdd(T newElement)
	{
		removeFirstOccurrence(newElement);
		
		T temp;
		int i=0;
		
		while (i < size()) 
		{
			temp=get(i);
			
			if(  temp.compareTo( newElement)<0) 
			{break; }
			
			i ++;
		}
		
		
		add(i, newElement);	
	}

	void display()
	{
		for(int i=0; i<size(); i++){System.out.println(get(i));	}
		
	}
}
