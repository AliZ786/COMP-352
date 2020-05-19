// -----------------------------------------------------
// Assignment 1
// Question 1
// Written by: xxxxxxxx (xxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
//
//A class used to print the permutations of a given string
/**
 * Names: xxxxxxxx (xxxxxx) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #1 Q1 
// Due Date: Monday, February 3th 2020
 */





import java.util.Scanner;

public class StringPermu {
	/*
	 * @param longStr = returns the longStr from the user
	 * @param shortString = returns the short string from the user
	 */
	static String longStr;
	static String shortString;


	public static void main(String[] args) {
		long time1 = System.nanoTime();
		Scanner in = new Scanner(System.in); /* Declares scanner */
		System.out.println("Please enter a long string: ");  /*Asks user for the long string */
		longStr = in.next();
		System.out.println("Please enter a short string: "); /*Asks user for the short string */
		shortString= in.next();
		System.out.println();
		permu("",shortString);/*Passing an empty string and the short string */
		long time2 = System.nanoTime();
		long time = ((time2-time1)/1000000);
		System.out.println("The time needed to execute this code (in milliseconds) is "+time);

	}
	/*
	 * The recursive method to find all the permutations of the short string
	 */
	public static void permu(String shortStr, String remaining) {
		/*
		 * Stopping case when we are done with all possible remaining letters
		 */
		if(remaining.length()==0) 
			System.out.println(shortStr);
		/*
		 * Case for all the permutations of the short string
		 */
		for(int i=0; i<remaining.length(); i++) {
			String newStr = shortStr + remaining.charAt(i); /*Stacking all the letters into a new String */
			String newRemaining = remaining.substring(0, i) + remaining.substring(i+1); /*Removing the first letter from the remaining */
			permu(newStr, newRemaining); /*Recursive Step */
			/*
			 * Finds the first time the short string is found in the long string with location of the index
			 */
			if((newStr.length()==shortString.length())&&longStr.contains(newStr))
				System.out.println("Found one match: " + newStr +" is in " + longStr + "at location " +
						longStr.indexOf(newStr));
		}

	}

}
