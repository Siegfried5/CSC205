import java.io.Serializable;

public class MatchList extends OrderedList<Match>
implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	void addMatch(Match newMatch)
	{
		sortAdd(newMatch);
		
		while(size()>5)
		{
			removeLast();
		}
	}
}
