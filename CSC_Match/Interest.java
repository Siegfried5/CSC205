import java.io.Serializable;

public class Interest implements Comparable<Interest>, Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int interestLevel;
	private String interest;
	
	// constructor
	Interest(String newInterest, int newLevel)
	{
		setInterest(newInterest);
		setInterestLevel(newLevel);
	}
	
	// the following are simple getter/ setter methods
	public String getInterest()
	{return interest;}
	
	public void setInterest(String interest)
	{this.interest = interest;}
	
	
	public int getInterestLevel() {	return interestLevel;}
	
	public void setInterestLevel(int interestLevel) 
	{this.interestLevel = interestLevel;}


	/*returns the value of this interest level minus
	 * the value of the other intrest's interest level
	 */
	@Override
	public int compareTo(Interest o)
	{return this.getInterestLevel()-o.getInterestLevel();}
	
	// returns a formated string
	public String toString()
	{return interest + ": [" + interestLevel + "]";}
	
	public // overriding equals
	//objects are equal if the name matches
	boolean equals(Object o)
	{
		if(this.getInterest().equals(((Interest) o).getInterest()))
		{return true;}
		 return false;
	}
}
