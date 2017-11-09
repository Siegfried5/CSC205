
public class Interest implements Comparable<Interest> 
{
	private int interestLevel;
	private String interest;
	
	// constructor
	// @Param String newInterest
	// @Param int newLevel
	Interest(String newInterest, int newLevel)
	{
		setInterest(newInterest);
		setInterestLevel(newLevel);
	}
	
	// the following are simple getter/ setter methods
	public String getInterest()
	{
		return interest;
	}
	
	// sets Interest for object
	// @Param String interest
	public void setInterest(String interest)
	{
		this.interest = interest;
	}
	
	// returns interestLevel
	public int getInterestLevel()
	{
		return interestLevel;
	}
	
	// sets the Interest Level for object
	// @Param int interestLevel
	public void setInterestLevel(int interestLevel) 
	{
		this.interestLevel = interestLevel;
	}


	/*returns the value of this interest level minus
	 * the value of the other intrest's interest level
	 */
	@Override
	public int compareTo(Interest o)
	{
		return this.getInterestLevel()-o.getInterestLevel();
	}
	
	// returns a formated string
	public String toString()
	{
		return interest + ": [" + interestLevel + "]";
	}
	
	// overriding equals
	// objects are equal if the name matches
	public boolean equals(Object o)
	{
		if(this.getInterest().equals(((Interest) o).getInterest()))
		{
			return true;
		}
		return false;
	}
}
