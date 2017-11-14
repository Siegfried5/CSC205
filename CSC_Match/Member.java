import java.io.Serializable;

//get_name string
//get_grade int
//set_name void
//set_grade void
//display_Interest void
//compare_interests(person) int

public class Member implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, gradeStr;
    private int grade;
    private MatchList matchList;
    private OrderedList<Interest> interestList ;
    
    public Member(String name, int grade)
    {
    	interestList = new OrderedList<Interest>();
    	matchList = new MatchList();
    
        this.name = name;
        this.grade = grade;
        setGradeStr();
    }
    
    void addMatch(Member member)
    {
    	matchList.sortAdd(new Match(member,getInterestLevel(member)));
    }
    
    void addInterest(Interest interest)
    {
    	interestList.sortAdd(interest);
    }
    
  // sets a string for grade
    void setGradeStr()
    {
    	switch (grade)
    	{
    	case 1:
    		gradeStr="Freshman";
    		break;
    	case 2:
    		gradeStr="Sophomore";
    		break;
    	case 3:
    		gradeStr="Junior";
    		break;
    	case 4:
    		gradeStr="Senior";
    		break;
    	case 5:
    		gradeStr="Has Degree";
    		break;
    	}
    }

    public String getName()
    {
        return this.name;
    }

    public int getGrade()
    {
        return this.grade;
    }
    
    //prints name/ grade and intrestList and matchList
    void display()
    {
    	System.out.println(name +": " + gradeStr+"["+ grade +"]");
    	System.out.println("-------------Interests-----------");
    	interestList.display();
    	System.out.println("------------Matches------------");
    	matchList.display();
    	
    }
    
    //objects are equal if their name is equal
    public boolean equals(Object o)
    {
    	
    	return this.name.equals(((Member) o).getName());
    }
    

    int getInterestLevel(Member o)
    {
    	int compadability =0;
    	boolean interestMatched = false;
    	
    	for(Interest other: o.interestList)
    	{
    		interestMatched = false;
    		for(Interest my: this.interestList)
    		{
    			if (other.equals(my))
    			{
    				compadability += my.getInterestLevel() * other.getInterestLevel();
    				interestMatched = true;
    			}
    		}
    		if(!interestMatched) {compadability += (other.getInterestLevel()/2);}
    	}
    	
		return compadability;
    }
    
    public String toString()
    {
    	return name +": " + gradeStr+"["+ grade +"]";
    }
}