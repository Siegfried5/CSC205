//get_name string
//get_grade int
//set_name void
//set_grade void
//display_Interest void
//compare_interests(person) int

public class Member {

    private String name;
    private int grade;

    public Member(String name, int grade)
    {
        this.name = name;
        this.grade = grade;
    }

    public String getName()
    {
        return this.name;
    }

    public int getGrade()
    {
        return this.grade;
    }

}