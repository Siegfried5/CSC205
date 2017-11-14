import java.io.Serializable;
import java.util.LinkedList;

public class SerializableList<T> extends LinkedList<T> implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	void display()
	{
		for(int i=0; i<size(); i++){System.out.println("["+i+"] "+get(i));	}
		
	}
	
}
