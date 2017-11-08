import java.util.Scanner;


public class CSCMatch {

	private static boolean again = true;
	private static boolean changeMade = false;
	private static char input;
	static Scanner keyboard;
	
	public static void main(String[] args)
	{
		keyboard = new Scanner(System.in);
		
		while(again)
		{
			showMenue();
			input = getInput();
			selector(input);
		}
		
		keyboard.close();

	}

	static void showMenue()
	{
		print("--------Menue-------");
		print("A) Load Members");
		print("B) Save Members");
		print("C) List All Members");
		print("D) Add a Member");
		print("E) Remove a Member");
		print("F) List a Member");
		print("G) Add Interests To a Member");
		print("H) Quit");
		
	}

	static void selector (char x)
	{
		switch(x)
		{
		case'a':
			print("sorry Load Not supported");
			break;
		case'b':
			print("sorry Save Not Suported");
			changeMade = false;
			break;
		case 'c':
			print("sorry list all members Not Suported");
			break;
			
		case 'd':

			print("Enter the name of the member: ");
			String memberName = keyboard.nextLine();

			print("Enter the Grade of the member: ");
			int memberGrade = keyboard.nextInt();

			Member member = new Member(memberName, memberGrade);
			print("New member Created: "+ member.getName() + " : "+ member.getGrade());

			changeMade = true;

			break;
			
		case 'e':
			print("sorry remove member Not supported");
			changeMade = true;
			break;
			
		case 'f':
			print("sorry list Member Not supported");
			break;
			
		case 'g':
			print("sorry Add Interests not supported");
			
			break;
		case 'h':
			if(changeMade)
			{
				print("are you sure you want to quit without Saving? Y/N");
				if(getInput() =='y'){again=false;}
			}else{again=false;}
			break;
		default:
			print("i'm sorry that doesn't seem to be a valid choice");
		}
		
	}
	
	static void print(String x)
	{
		System.out.println(x);
	}


	// this function is giving me fucken aids right now
	static char getInput()
	{
		String newinput;
		newinput= keyboard.nextLine();
		newinput = newinput.toLowerCase();
		print(newinput);
		
		return  newinput.charAt(0);
	}
}
