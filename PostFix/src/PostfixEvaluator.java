import java.util.Stack;
import java.util.Scanner;


/**
 * Represents an integer evaluator of postfix expressions. Assumes 
 * the operands are constants.
 *
 * @author Lewis and Chase
 * @version 4.0
 */


public class PostfixEvaluator
{
    private final static char ADD = '+';
    private final static char SUBTRACT = '-';
    private final static char MULTIPLY = '*';
    private final static char DIVIDE = '/';
    private final static char MODULUS = '%';
    private final static char POWER = '^';
    private final static char UNARY_MINUS = '~';
    private final static char FACTORIAL = '!';
    private final static char GREATER = '>';
    private final static char LESS = '<';
    private final static char EQUAL = '=';
    private final static char AND = '&';
    private final static char OR = '|';
    private final static char TERTIARY = '?';

    private Stack<Integer> stack;

    /**
     * Sets up this evalutor by creating a new stack.
     */
    public PostfixEvaluator()
    {
        stack = new Stack<Integer>();
    }
    /**
     * Evaluates the specified postfix expression. If an operand is
     * encountered, it is pushed onto the stack. If an operator is
     * encountered, two operands are popped, the operation is
     * evaluated, and the result is pushed onto the stack.
     * @param expr string representation of a postfix expression
     * @return value of the given expression
     */
    public int evaluate(String expr) throws tooFewOperators
    {
    	int op1, op2, result = 0;
        String token;
        Scanner parser = new Scanner(expr);

        while (parser.hasNext())
        {
            token = parser.next();

            if (isOperator(token))
            {
            	if(unaryOperators(token)) 
            	{
        			op2 = (stack.pop()).intValue();
        			result = evaluateSingleOperator(token.charAt(0), 0, op2);
        			stack.push(new Integer(result));
        		}
            	
            	else 
            	{     	
	            	try 
	            	{
	                	if(BooleanTERTIARYOperators(token))
	                	{
	                		op2 = (stack.pop()).intValue();
	                		op1 = (stack.pop()).intValue();
	                        result = evaluateSingleOperator(token.charAt(0), op1, op2);
	                        stack.push(new Integer(result));
	                	}
	                	else
	                	{
	                		op2 = (stack.pop()).intValue();
	                		op1 = (stack.pop()).intValue();
	                        result = evaluateSingleOperator(token.charAt(0), op1, op2);
	                        stack.push(new Integer(result));
	                	}
	            	}
	            	catch (java.util.EmptyStackException ex)
	            	{
	            		System.out.println("ERROR: Insufficient operands for " + token);
	            	}
            	}
            }
            else
                stack.push(new Integer(Integer.parseInt(token)));
        }
        
        if (stack.size() == 1) 
        {
   			return result;
        }
        else {
        	throw new tooFewOperators();
        }
    }
    
    private boolean unaryOperators(String token) 
    {
    	return (token.equals("~") || token.equals("!")) ? true : false;
    }
    
    private boolean BooleanTERTIARYOperators(String token) 
    {
    	return (token.equals(">") || token.equals("<") || token.equals("=") || 
    		    token.equals("&") || token.equals("|") || token.equals("?")) ? true : false;
    }
        		

    /**
     * Determines if the specified token is an operator.
     * @param token the token to be evaluated 
     * @return true if token is operator
     */
    private boolean isOperator(String token)
    {
        return ( token.equals("+") || token.equals("-") || // OR return ("+-*/".indexOf(token) >= 0);
                 token.equals("*") || token.equals("/") || token.equals("%") || token.equals("^") ||
        		 token.equals("~") || token.equals("!") || token.equals(">") || token.equals("<") ||
        		 token.equals("=") || token.equals("&") || token.equals("|") || token.equals("?") );
    }

    /**
     * Performs integer evaluation on a single expression consisting of 
     * the specified operator and operands.
     * @param operation operation to be performed
     * @param op1 the first operand
     * @param op2 the second operand
     * @return value of the expression
     */
    private int evaluateSingleOperator(char operation, int op1, int op2)
    {
        int result = 0;

        switch (operation)
        {
            case ADD:
                result = op1 + op2;
                break;
            case SUBTRACT:
                result = op1 - op2;
                break;
            case MULTIPLY:
                result = op1 * op2;
                break;
            case DIVIDE:
                result = op1 / op2;
                break;
            case MODULUS:
            	result = op1 % op2;
            	break;
            case POWER:
            	result = (int) Math.pow(op1, op2);
            	break;
            case UNARY_MINUS:
            	result = op2 * -1;
            	break;
            case FACTORIAL:
            	result = 1;
            	for (int x = op2; x >= 1; x--) 
            	{
            		result *= x;
            	}
            	break;
            case GREATER:
            	if (op1 > op2) {
            		result = 1;
            	}
            	
            	else
            	{
            		result = 0;
            	}
            	break;
            case LESS:
            	if (op1 < op2)
            	{
            		result = 1;
            	}
            	
            	else
            	{
            		result = 0;
            	}
            	break;
            case EQUAL:
            	if (op1 == op2)
            	{
            		result = 1;
            	}
            	
            	else
            	{
            		result = 0;
            	}
            	break;
            case AND:
            	if ( op1 != 0 && op2 != 0)
            	{
            		result = 1;
            	}
            	
            	else
            	{
            		result = 0;
            	}
            	break;
            case OR:
            	if ( op1 == 1 || op2 == 1 )
            	{
            		result = 1;
            	}
            	
            	else
            	{
            		result = 0;
            	}
            	break;
            case TERTIARY:
            	try {
            		int op3 = (stack.pop()).intValue();
 
        			if (op3 != 0) 
        			{
        				result = op1;
        			}
        			
        			else
        			{
        				result = op2;
        			}
            	}
            	catch (java.util.EmptyStackException ex)
            	{
            		System.out.println("ERROR: Insufficient operands for ?");
            	}
            	break;
        }

        return result;
    }
}

