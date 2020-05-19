// -----------------------------------------------------
// Assignment 4
// Question 1
// Written by: xxxxxxxx (xxxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
// Driver which uses the sequence and add keys by ourselves

/**
 * Names: xxxxxxxxxx(xxxxxxxxxx) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #4 
// Due Date: Sunday April 19, 2020
 */


import java.util.Random;
import java.io.*;

public class Driver2 {

	public static void main(String[] args) {
		CVR<String, String> cvr = new CVR<String, String>();
		int threshold = 0;
		int length = 0;
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new FileOutputStream("test_output.txt"));
			Random random = new Random();
			
			threshold = (int)random.nextInt(90)+10; // random threshold between 10 and 99
			pw.println("The threshold is " + threshold);
			cvr.setThreshold(threshold);
			
			length = (int)random.nextInt(8)+10; // random length between 10 and 17
			pw.println("The length is " + length);
			cvr.setKeyLength(length);
			
			pw.println();
			cvr.generate(10); // generates 10 random new keys
			pw.println("All keys in sorted: " + cvr.allKeys());
			pw.println();
			
			/*

			 */
			pw.println("Added some keys and values");
			cvr.add("G4B10E78FB", "2002");
			cvr.add("G4B10E78FB", "2004");
			cvr.add("G4B10E78FB", "2005");
			cvr.add("G5C10E78FB", "2007");
			cvr.add("E5C16E78JP", "2007");
			pw.println();
			pw.println("All keys in sorted: " + cvr.allKeys());
			pw.println();
			
			/**
			 * Removing a key
			 */
			pw.println("Removed a key(E5C16E78JP)");
			cvr.remove("E5C16E78JP");
			pw.println();
			pw.println("All keys in sorted: " + cvr.allKeys());
			pw.println();
			
			/**
			 * Checking methods for getValue(), nextKey(), prevKey() and prevAccids()
			 */
			pw.println("Value of key G5C10E78FB: " + cvr.getValues("G5C10E78FB"));
			pw.println("Successor of key G5C10E78FB: " + cvr.nextKey("G5C10E78FB"));
			pw.println("Predecessor of key G5C10E78FB: " + cvr.prevKey("G5C10E78FB"));
			pw.println("All previous accidents of key G5C10E78FB: " + cvr.prevAccids("G5C10E78FB"));
			
			pw.flush();
			pw.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file");
		}
	}

}
