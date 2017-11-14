import java.io.Serializable;

public class Match implements Comparable<Match>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Member match;
	private int compatibility;
	
	// constructor
	Match(Member newMatch, int score)
	{
		match = newMatch;
		compatibility = score;
	}
	
	
	
	/*
	 * the following are simple getter / setter methods
	 */
	public Member getMatch() 
	{return match;}
	
	public void setMatch(Member match) 
	{this.match = match;}
	
	public int getCompadablity() 
	{return compatibility;}
	
	public void setCompadablity(int compadablity) 
	{this.compatibility = compadablity;}

	
	/*
	 * returns the value of this matche's compatibility
	 * minus the other's compatibility score
	 */
	@Override
	public int compareTo(Match o) {
		
		return this.getCompadablity()-o.getCompadablity();
	}

	// gives a format so it can properly print it's self
	public String toString()
	{
		return match.getName()+ ": ["+ compatibility +"]";
	}

	public // overriding equals
	//objects are equal if the name matches
	boolean equals(Object o)
	{
		if(this.getMatch().getName().equals(((Match) o).getMatch().getName()))
		{return true;}
		 return false;
	}
}
