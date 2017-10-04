import java.util.Scanner;

/**
 * Demonstrates the use of a stack to evaluate postfix expressions.
 *
 * @auth`or Lewis and Chase
 * @version 4.0
 */
public class PostfixTester    
{
    /**
     * Reads and evaluates multiple postfix expressions.
     */
    public static void main(String[] args)
    {
    	System.out.println("Postfix Expression Evaluator - R. Ponce");
        String expression, again = null;
        int result;
    
        Scanner in = new Scanner(System.in);
      
        do
        {  
            PostfixEvaluator evaluator = new PostfixEvaluator();
			System.out.println("Enter a valid post-fix expression one token " +
							   "at a time with a space between each token (e.g. 5 4 + 3 2 1 - + *)");
			System.out.println("Each token must be an integer or an operator (+,-,*,/,%,^,~,!)");
            expression = in.nextLine();

            try 
            {
            	 result = evaluator.evaluate(expression);
            	 System.out.println();
                 System.out.println("That expression equals " + result);
            }
            catch (Exception ex) 
            {
            	System.out.println("too few operators");
            }
            
            System.out.print("Evaluate another expression [Y/N]? ");
            again = in.nextLine();
            System.out.println();
        }
        while (again.equalsIgnoreCase("y"));
        
        in.close();
  }
}
