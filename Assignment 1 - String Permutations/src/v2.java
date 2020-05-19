// -----------------------------------------------------
// Assignment 1
// Question 1
// Written by: xxxxxxxxx (xxxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
//
//A faster way  used to print the permutations of a given string
/**
 * Names: xxxxxxxxx (xxxxxxxx) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #1 Q1-Version 2 
// Due Date: Monday, February 3th 2020
 */





import java.util.ArrayList;
import java.util.List;

public class v2 {

	static List<String> result;


	/* 
	 * Method  to generate all permutations of a String in Java
	 * 
	 */
	
	public static void permutations(String s)
	{
		/*
		 * Creates an empty ArrayList to store (result) permutations
		 * 
		 */
		result = new ArrayList<>();

		/*
		 * Initialises the arraylist with the first character of the passed string 
		 */
		result.add(String.valueOf(s.charAt(0)));

		/*
		 * Stores every character of the string to the result arraylist 
		 */
		for (int i = 1; i < s.length(); i++)
		{

			for (int j = result.size() - 1; j >= 0 ; j--)
			{
				/* 
				 * Removes current permutation from the ArrayList
				 */
				String str = result.remove(j);

				for (int k = 0; k <= str.length(); k++)
				{
					result.add(str.substring(0, k) + s.charAt(i) +
							str.substring(k));
				}
			}
		}

	}
	
	/*
	 * Method to check if each string is part of the permutation 
	 */
	public static void check(String p,List<String> g ) {
		for (String n : g) {
			if((p.contains(n))) {
				System.out.println("Found one match: " + n +" is in " + p + "at location " +
						p.indexOf(n));
				System.out.println();

			}
		}
	}

	/*
	 * Main function of the program which collects the strings that need to be tested for permutations and displays the elapseed time
	 */
	public static void main(String[] args)
	{
		long t1 = System.nanoTime();
		String shortStr = "ckkk";
		permutations(shortStr);
		String longStr = "hhhlajkjgabckkkkcbakkdfjknbbcaat";
		check(longStr,result);
		long t2 = System.nanoTime();
		System.out.println();
		long time =(t2-t1)/1000000;
		System.out.println("The time needed to execute this code (in milliseconds) is "+time);

	}
}


