import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;


public class CSCMatch {

	private static boolean again = true;
	private static boolean changeMade = false;
	private static char input;
	static Scanner keyboard;
	private static SerializableList<Member> memberList= new SerializableList<Member>();
	private static SerializableList<String> saves= new SerializableList<String>();

	public static void main(String[] args)
	{
		try
		{
			ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream("saves"));
			saves = (SerializableList<String>) objectIn.readObject();
			
		}catch(IOException | ClassNotFoundException e) {print("No Saves Found.");}
		
		while(again)
		{
			keyboard = new Scanner(System.in);
			showMenu();
			input =getCharInput();
			selector(input);
		}
		keyboard.close();
	}

	static void showMenu()
	{
		print("--------Menu-------");
		print("A) Load Members");
		print("B) Save Members");
		print("C) List All Members");
		print("D) Add a Member");
		print("E) Remove a Member");
		print("F) List a Member");
		print("G) Add Interests To a Member");
		print("H) Quit");

	}


	/* This Fuction will Load the files that have been saved 
	// if the file name that has been inputed does not exist 
	// it should keep asking for a correct one
	*/
	static void load() 
	{
		
		boolean sysLoad = false;
		String fileName=null;
		while(!sysLoad)
		{
			int index = -1;
			if(saves.isEmpty()) {print("No Saves Found");}
			else
			{
				print("What File would you like to load?");
				saves.display();
				index = keyboard.nextInt();
				keyboard.nextLine();
				fileName = saves.get(index);
			}
			try {
				
				FileInputStream fis = new FileInputStream(fileName);
				ObjectInputStream ois = new ObjectInputStream(fis);
				// Don't know if this line of code will be final
				memberList = (SerializableList<Member>) ois.readObject();
				ois.close();
				sysLoad = true;

			} catch(IOException | ClassNotFoundException e)
			{
				print("File Does not Exist");
				sysLoad = false;
			}
		}
	} 


	// This Function will save members and all objects tied to them
	// it will key trying to save until it is successful
	static void save()
	{
		boolean saved= false;
		while (!saved) 
		{
			try {
				if(!saves.isEmpty()) {saves.display();}
				print("Enter the name of the file.");
				String fileName = keyboard.nextLine();
				
				File file = new File(fileName);
				if(!file.exists()) {file.createNewFile();}
				
				FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(memberList);
				oos.flush();
				oos.close();
				saved=true;
				if(!saves.contains(fileName)) {saves.addLast(fileName);}
				ObjectOutputStream save = new ObjectOutputStream(
						new FileOutputStream(new File("saves")));
				
				
				save.writeObject(saves);
				save.flush();
				save.close();
			} catch (IOException e) {
				print(e.toString());
				e.printStackTrace();
				saved=false;
			}
		}

		changeMade = false;
	}

	
	static void listAllMembers()
	{
		memberList.display();
	}

	/* This function will ask the end user for
	// A member name and Grade than create a
	// New member object based on that information
	 * 
	 */
	static void addMember()
	{

		String memberName = "";
		int memberGrade=-1;
		boolean flag = false;

		do {
			flag = false;
			print("Enter the name of the member: ");
			memberName = keyboard.nextLine();
			memberName=removeSpace(memberName);

			while(memberGrade > 5 || memberGrade<1)
			{
				print("Enter the Grade of the member: ");
				print("1: Freshman");
				print("2: Sophomore");
				print("3: Junior");
				print("4: Senior");
				print("5: Has A Degree");
				try {
				memberGrade = keyboard.nextInt();
				}catch(Exception e) {memberGrade = -1;}
				keyboard.nextLine();
				if(memberGrade > 5 || memberGrade<1)
				{print("Sorry a grade can only be 1-5");}

			}

			if(memberList.contains(new Member(memberName,0)))
			{
				print("sorry that user already Exists");
				print("would you like to try a difrent Name? (Y/n)");
				if (getCharInput()=='y') {flag = true;}
			}

		} while(flag);
		Member member= new Member(memberName,memberGrade);

		memberList.addLast(member);
		print("New member Created: "+ memberName + ": "+ memberGrade);
		print("Would you like to add interests to"+memberName+"? (Y/n)");
		if(getCharInput()=='y') {addInterest(member);}

		changeMade = true;
	}

	static void removeMember()
	{
		Member member=null;
		
		member= getExistingMember();
		if(member!= null) {memberList.remove(member);}

		
	}

	static void displayMember()
	{
		Member member;
		
		member = getExistingMember();
		
		if(member != null) { 
			updateMember(member);
			member.display();
		}
	}

	static Member getExistingMember()
	{
		boolean flag = false;
		String name;
		Member member=null;

		do {
			flag=false;
			print("Please enter the name of the member:");
			name=keyboard.nextLine();
			name= removeSpace(name);
			if(memberList.contains(new Member(name,0)))
			{
				member = memberList.get(memberList.indexOf(new Member(name, 0)));
				changeMade = true;
			}
			else 
			{
				print("I'm sorry it looks like that member doesn't exsist...");
				print("Would you like to try again?");
				if(getCharInput()=='y') {flag=true;}
			}
			
		}while(flag);
		return member;

	}

	static void addInterest(Member member)
	{
		String interestName="";
		int interestLVL=-1;
		boolean flag;
		do {
			flag = false;
			interestLVL =-1;

			print("Enter the member's new interest");
			interestName=keyboard.nextLine();
			
			
			while(interestLVL > 10 || interestLVL< 0)
			{
				print("What's the level of interest int:" + interestName + "? (0-10)");

				try
				{
					interestLVL = keyboard.nextInt();
				}catch(Exception e) 
				{
					print("");
					interestLVL = -1;
				}
				keyboard.nextLine();
				if(interestLVL == 0) 
				{
					print("A level of 0 means you hate that subject.");
					print("You will be less likley to match with people who have also listed it");
					print("Would you like to change the score?");
					if(getCharInput()=='y') {interestLVL=-1; }
				}

			}
			
			member.addInterest(new Interest(interestName, interestLVL));;
			
			print("Would you like to add another one?");
			if(getCharInput() == 'y') {flag=true;}
		}while(flag);
		
	}
	
	static void quit(){if(changeMade)
	{
		print("are you sure you want to quit without Saving? Y/N");
		if(input=='y')
			again=false;
	} else
		again=false;


	}

	static void selector (char x)
	{
		switch(x)
		{
		case'a':
			load();
			break;
		case'b':
			save();
			break;
		case 'c':
			listAllMembers();
			break;
		case 'd':
			addMember();
			break;
		case 'e':
			removeMember();
			break;

		case 'f':
			displayMember();
			break;

		case 'g':
			Member member = getExistingMember();
			if (member!= null) {addInterest(member);}
			break;
		case 'h':
			quit();
			break;
		default:
			print("i'm sorry that doesn't seem to be a valid choice");
		}

	}
	
	static void updateMember(Member member)
	{
		for(Member m: memberList) 
		{
			if(!member.equals(m))
			{
				member.addMatch(m);
			}
		}
	}

	static void print(String x)
	{
		System.out.println(x);
	}

	static char getCharInput()
	{
		String newinput;
		newinput= keyboard.nextLine();
		newinput = newinput.toLowerCase();

		return  newinput.charAt(0);
	}

	static String removeSpace(String toClean)
	{
		int index= 0;
		while(toClean.contains("  ")) 
		{
			index = toClean.indexOf(' ');
			toClean = toClean.substring(0,index) + toClean.substring(index+1);}

		return toClean;
	}
}















































































































