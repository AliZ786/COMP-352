// -----------------------------------------------------
// Assignment 2
// Question 1 Version 2
// Written by: xxxxxxxxx (xxxxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
//
// Building an arithmetic calculator without the use of stacks, but recursion 
/**
 * Names: xxxxxxx (xxxxxxxx) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #2 Q1-Version 1
// Due Date: Monday, February 25 2020
 */






import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class V2A2 {

	public static void main(String[] args) throws IOException{
		Scanner in = null;
		PrintWriter p = null;

		try {
			in = new Scanner(new FileInputStream("Input.txt"));
			p = new PrintWriter(new FileOutputStream("OutputV2.txt"));

			while(in.hasNext()) {

				String s = in.nextLine();
				String exp = s.replaceAll("\\s", "");
				if(exp.equals("")) {
					p.println();
					continue;

				}
				p.print(s + " = " + eval(exp) +"\n");

			}
			in.close();
			p.flush();
			p.close();

		}
		catch(FileNotFoundException e) {
			System.out.println("File cannot be found/created");
		}





	}
	/**
	 * Method which evaluates the passed string from the output
	 * @param s a token string that is passed for the method 
	 * @return the result of the evaluation 
	 */
	public static int eval(String s) {
		int result = 0;
		String re = s;
		String newResult = "";
		int i = 0;
		if(re.length() == 0)
			return 0;
/**
 * Returns the string re as an int if the string contains one of these 
 */
		if(!re.contains("!") && !re.contains("^") && !re.contains("*") && !re.contains("/") && !re.contains("+") 
				&& !(re.substring(1)).contains("-") && !re.contains("(") && !re.contains(")") && !re.contains("^") 
				&& !re.contains("<") && !re.contains("<=") && !re.contains(">") && !re.contains(">=") && !re.contains("==") 
				&& !re.contains("!="))
			return Integer.parseInt(re);

		if(re.contains("(")) { /** If the last index is an opening bracket, return an error*/
			i = re.indexOf('(');
			if(i == re.length()-1) {
				System.out.println("Error! Invalid Expression");
			}
			else {
				int j;
				int count = 0;
				char closing =')';
				String inner = "";

				for(j = i+1; j < re.length(); j++) {
					if(re.charAt(j) == re.charAt(i)) {
						count++;
					}
					if(re.charAt(j) == closing) {
						if(count == 0) {
							inner = re.substring(i+1,j);
							break;
						}
						count--;
					}
				}
				result = eval(inner);
				newResult = String.valueOf(result);

				if(re.charAt(0) == '(') {
					re = newResult + re.substring(j+1);
				}
				else {
					re = re.substring(0,i) + newResult + re.substring(j+1);
				}
				return eval(re);
			}
		}

		if(re.contains("!")) { 
			i = re.indexOf('!');
			if(i == 0 || isNumber(re.charAt(i-1)) == false)
				System.out.println("Error! Invalid Expression");

			if(i < re.length()-1 && re.charAt(i+1) == '=') {
				String left = re.substring(0, re.indexOf('!'));
				String right = re.substring(i+2, re.length());

				if(eval(left) != eval(right)) {

					return 1;
				}
				else {

					return 0;
				}
			}
			else {
				String op = "";
				int j;
				for(j = i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op = re.charAt(j-1) + op;
				}

				result = factorial(Integer.parseInt(op));
				newResult = String.valueOf(result);
				re = re.substring(0, j) + newResult + re.substring(i+1, re.length());
				return eval(re);
			}

		}

		if(re.contains("^")) { /** Returns a 0 if the last power sign is followed by a - */
			i = re.indexOf('^');

			if(i < re.length()-1 && re.charAt(i+1) == '-') {
				re = re.substring(0,i) + "*0*" + re.substring(i+2);
				return eval(re);
			}

			if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
				System.out.println("Error! Invalid Expression");


			else {
				String op1 = "";
				String op2 = "";
				int j;
				int k;
				for(j=i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op1 = re.charAt(j-1) + op1;
				}

				for(k=i; k < re.length()-1 && isNumber(re.charAt(k+1)) == true; k++) {
					op2 += re.charAt(k+1);
				}

				if(j == 1 && re.charAt(0)== '-')
					result = power(-Integer.parseInt(op1), Integer.parseInt(op2));
				else
					result = power(Integer.parseInt(op1), Integer.parseInt(op2));

				newResult = String.valueOf(result);

				if(re.charAt(0) == '-')
					re = newResult + re.substring(k+1, re.length());
				else
					re = re.substring(0, j) + newResult + re.substring(k+1, re.length());
				return eval(re);
			}
		}

		if(re.contains("*-")) {
			int j;
			for(j=0; j < re.length()-1; j++) {
				if(re.charAt(j) == '*' && re.charAt(j+1)=='-') {
					i=j;
					break;
				}
			}

			re = "-" + re.replace("*-", "*");
			return eval(re);
		}

		if(re.contains("/-")) {
			int j;
			for(j=0; j < re.length()-1; j++) {
				if(re.charAt(j) == '/' && re.charAt(j+1)=='-') {
					i=j;
					break;
				}
			}

			re = "-" + re.replace("/-", "/");
			return eval(re);
		}

		if(re.contains("*") && re.contains("/")) {
			if(re.indexOf('*') < re.indexOf('/')) 
				i = re.indexOf('*');
			else
				i = re.indexOf('/');

			if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
				System.out.println("Error! Invalid Expression");
			else {
				String op1 = "";
				String op2 = "";
				int j;
				int k;
				for(j=i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op1 = re.charAt(j-1) + op1;
				}

				for(k=i; k < re.length()-1 && isNumber(re.charAt(k+1)) == true; k++) {
					op2 += re.charAt(k+1);
				}

				if(re.charAt(i) == '*')
					result = (Integer.parseInt(op1) * Integer.parseInt(op2));
				else if(Integer.parseInt(op2) == 0)
					System.out.println("Error! Division by zero");
				else if(j == 1 && re.charAt(0)== '-')
					result = (-Integer.parseInt(op1) / Integer.parseInt(op2));
				else
					result = (Integer.parseInt(op1) / Integer.parseInt(op2));

				newResult = String.valueOf(result);

				if(re.charAt(0) == '-')
					re = newResult + re.substring(k+1, re.length());
				else
					re = re.substring(0, j) + newResult + re.substring(k+1, re.length());
				return eval(re);
			}
		}

		if(re.contains("*")) {
			i = re.indexOf('*');

			if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
				System.out.println("Error! Invalid Expression");
			else {
				String op1 = "";
				String op2 = "";
				int j;
				int k;
				for(j=i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op1 = re.charAt(j-1) + op1;
				}

				for(k=i; k < re.length()-1 && isNumber(re.charAt(k+1)) == true; k++) {
					op2 += re.charAt(k+1);
				}

				if(j == 1 && re.charAt(0)== '-')
					result = (-Integer.parseInt(op1) * Integer.parseInt(op2));
				else
					result = (Integer.parseInt(op1) * Integer.parseInt(op2));

				newResult = String.valueOf(result);

				if(re.charAt(0) == '-')
					re = newResult + re.substring(k+1, re.length());
				else
					re = re.substring(0, j) + newResult + re.substring(k+1, re.length());
				return eval(re);
			}
		}

		if(re.contains("/")) {
			i = re.indexOf('/');

			if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
				System.out.println("Error! Invalid Expression");
			else {
				String op1 = "";
				String op2 = "";
				int j;
				int k;
				for(j=i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op1 = re.charAt(j-1) + op1;
				}

				for(k=i; k < re.length()-1 && isNumber(re.charAt(k+1)) == true; k++) {
					op2 += re.charAt(k+1);
				}

				if(Integer.parseInt(op2) == 0) {
					System.out.println("Error! Division by zero");
				}
				else if(j == 1 && re.charAt(0)== '-')
					result = (-Integer.parseInt(op1) / Integer.parseInt(op2));
				else
					result = (Integer.parseInt(op1) / Integer.parseInt(op2));

				newResult = String.valueOf(result);

				if(re.charAt(0) == '-')
					re = newResult + re.substring(k+1, re.length());
				else
					re = re.substring(0, j) + newResult + re.substring(k+1, re.length());
				return eval(re);
			}
		}
		if(re.contains("--")) {
			int j;
			for(j=0; j < re.length()-1; j++) {
				if(re.charAt(j) == '-' && re.charAt(j+1)=='-') {
					i=j;
					break;
				}
			}
			if(i==0)
				re = re.substring(j+2);
			else
				re = re.substring(0,i) + "+" + re.substring(j+2);
			return eval(re);
		}

		if(re.contains("+-")) {
			int j;
			for(j=0; j < re.length()-1; j++) {
				if(re.charAt(j) == '+' && re.charAt(j+1)=='-') {
					i=j;
					break;
				}
			}
			re = re.substring(0,i) + re.substring(j+1);
			return eval(re);
		}

		if((re.substring(1)).contains("-")) {
			i = (re.substring(1)).indexOf('-') + 1;

			if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
				System.out.println("Error! Invalid Expression");
			else {

				String op1 = "";
				String op2 = "";
				int j;
				int k;
				for(j=i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op1 = re.charAt(j-1) + op1;
				}

				for(k=i; k < re.length()-1 && isNumber(re.charAt(k+1)) == true; k++) {
					op2 += re.charAt(k+1);
				}

				if(j == 1 && re.charAt(0)== '-')
					result = (-Integer.parseInt(op1) - Integer.parseInt(op2));
				else
					result = (Integer.parseInt(op1) - Integer.parseInt(op2));

				newResult = String.valueOf(result);

				if(re.charAt(0) == '-')
					re = newResult + re.substring(k+1, re.length());
				else
					re = re.substring(0, j) + newResult + re.substring(k+1, re.length());
				return eval(re);
			}
		}


		if(re.contains("+")) {
			i = re.indexOf('+');

			if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
				System.out.println("Error! Invalid Expression");
			else {
				String op1 = "";
				String op2 = "";
				int j;
				int k;
				for(j=i; j > 0 && isNumber(re.charAt(j-1)) == true; j--) {
					op1 = re.charAt(j-1) + op1;
				}

				for(k=i; k < re.length()-1 && isNumber(re.charAt(k+1)) == true; k++) {
					op2 += re.charAt(k+1);
				}

				if(j == 1 && re.charAt(0)== '-')
					result = (-Integer.parseInt(op1) + Integer.parseInt(op2));
				else
					result = (Integer.parseInt(op1) + Integer.parseInt(op2));

				newResult = String.valueOf(result);

				if(re.charAt(0) == '-')
					re = newResult + re.substring(k+1, re.length());
				else
					re = re.substring(0, j) + newResult + re.substring(k+1, re.length());
				return eval(re);
			}
		}


		if(re.contains(">")) {
			i=re.indexOf('>');
			if(re.charAt(i+1) == '=') {
				if(i == 0 || i== re.length()-2 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+2)) == false) 
					System.out.println("Error! Invalid Expression");
				else {
					String left = re.substring(0, re.indexOf('>'));
					String right = re.substring(i+2, re.length());

					if(eval(left) >= eval(right)) {

						return 1;
					}
					else {

						return 0;
					}
				}
			}
			else {
				if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
					System.out.println("Error! Invalid Expression");
				else {
					String left = re.substring(0, re.indexOf('>'));
					String right = re.substring(i+1, re.length());

					if(eval(left) > eval(right)) {

						return 1;
					}
					else {

						return 0;
					}
				}

			}
		}

		if(re.contains("<")) {
			i=re.indexOf('<');
			if(re.charAt(i+1) == '=') {
				if(i == 0 || i== re.length()-2 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+2)) == false) 
					System.out.println("Error! Invalid Expression");
				else {
					String left = re.substring(0, re.indexOf('<'));
					String right = re.substring(i+2, re.length());

					if(eval(left) <= eval(right)) {

						return 1;
					}
					else {

						return 0;
					}
				}
			}
			else {
				if(i == 0 || i== re.length()-1 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+1)) == false) 
					System.out.println("Error! Invalid Expression");
				else {
					String left = re.substring(0, re.indexOf('<'));
					String right = re.substring(i+1, re.length());

					if(eval(left) < eval(right)) {

						return 1;
					}
					else {

						return 0;
					}
				}

			}
		}
		if(re.contains("==")) {
			i=re.indexOf('=');
			if(i == 0 || i== re.length()-2 || isNumber(re.charAt(i-1)) == false || isNumber(re.charAt(i+2)) == false) 
				System.out.println("Error! Invalid Expression");
			else {
				String left = re.substring(0, re.indexOf('='));
				String right = re.substring(i+2, re.length());

				if(eval(left) == eval(right)) {

					return 1;
				}
				else {

					return 0;
				}
			}
		}
		return 0;
	}
/**
 * Checks if passed character is a number or not 
 * @param c a character 
 * @return a boolean value depending if the given character is a number or not 
 */
	public static boolean isNumber(char c) {
		if(c >= '0' && c <= '9')
			return true;
		return false;
	}
	/**
	 * Method which returns the exponential operator 
	 * @param x : base of the function
	 * @param y : exponent of the function 
	 * @return power: returns the power of two given numbers
	 * 
	 */

	public static int power(int x, int y) {
		if(y == 0)
			return 1;
		else 
			return x * power(x, y - 1);

	}

	/**
	 * Method which returns the factorial value 
	 * @param x : an int value to do the factorial value
	 * @return factorial : returns the factorial of the int x passed
	 */

	public static int factorial(int x) {
		if(x == 0)
			return 1;
		else 
			return x * factorial(x-1);
	}
}



