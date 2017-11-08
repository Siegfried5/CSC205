
public class MatchList extends OrderedList<Match>
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
