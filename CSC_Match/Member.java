//display_Interest void
//compare_interests(person) int

public class Member {

    private String name;
    private int grade;
    
    //constructor
    //@param String name
    //@param int grade
    public Member(String name, int grade)
    {
        this.name = name;
        this.grade = grade;
    }
    
    //returns name of Member
    public String getName()
    {
        return this.name;
    }
    
    //returns the grade of Member
    public int getGrade()
    {
        return this.grade;
    }

}
