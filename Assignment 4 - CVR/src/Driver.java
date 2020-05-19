//-----------------------------------------------------
//Assignment 4
//Question 1
//Written by: xxxxxxxxx (xxxxxxxx) & Mohammad Ali Zahir (40077619)
//-----------------------------------------------------
//Driver for the test files by using a hash table 

/**
* Names: xxxxxxxxx (xxxxxxx) & Mohammad Ali Zahir (40077619)
//Comp 352- Winter 2020 Section S
//Assignment : #4 
//Due Date: Sunday April 19, 2020
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		CVR<String, String> cvr = new CVR<String, String>();
		cvr.setThreshold(10000); //setting threshold
		cvr.setKeyLength(10); //setting length
		Scanner sc = null;
		PrintWriter pw = null;

		String key;
		String value;
		int count = 0; //counter
		Random random = new Random();

		try {
			sc = new Scanner(new FileInputStream("ar_test_file3.txt"));
			pw = new PrintWriter(new FileOutputStream("output3.txt"),true);
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				if(!line.equals("")) {
					key = line;
					value = String.valueOf(random.nextInt(121)+1900);
					cvr.add(key, value);
					count++;
				}
			}
			
			pw.println("There are " + count + " keys in the file"); //printing the total number of keys
			pw.println();
			pw.println("All keys in sorted: " + cvr.allKeys()); //printing sorted keys
			pw.println();
			
			/**
			 * Adding some keys and values
			 */
			pw.println("Added some keys and values");
			cvr.add("G4B10E78FB", "2002");
			cvr.add("G4B10E78FB", "2004");
			cvr.add("G4B10E78FB", "2005");
			cvr.add("G5C10E78FB", "2007");
			cvr.add("E5C16E78JP", "2007");
			
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
			pw.println();
			/**
			 * Generating 10 new keys to the list
			 */
			pw.println("Generating new key that do not exist");
			cvr.generate(10);
			pw.println();
			pw.println("All keys in sorted: " + cvr.allKeys());
			
			sc.close();
			pw.flush();
			pw.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Cannot find file");
		}
	}

}
