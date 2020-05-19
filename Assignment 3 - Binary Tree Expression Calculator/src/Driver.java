// -----------------------------------------------------
// Assignment 3
// Question 1
// Written by: xxxxxxxxx (xxxxxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
// The driver file of the of the binary tree expression calculator

/**
 * Names: Souvik Palal Alam (40044092) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #3 Q1
// Due Date: Wednesday April 1,2020
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
	public static void main(String[] args) throws IOException {
		A3 e = new A3();		
		Scanner in = null;
		PrintWriter p = null;

		/**
		 * The try catch block for the file 
		 */
		try {
			in = new Scanner(new FileInputStream("BinaryInput.txt"));
			p = new PrintWriter(new FileOutputStream("Output.txt"));

			while(in.hasNext()) {

				String s = in.nextLine();
				String exp = s.replaceAll("\\s", "");
				String[] str = exp.split("");
				if(exp.equals("")) {
					p.println();
					continue;
					
				}
				p.print(s + " = " + e.evaluateExpression(str) +"\n");

			}
			in.close();
			p.flush();
			p.close();

		}
		catch(FileNotFoundException e1) {
			System.out.println("File cannot be found/created");
		}
		
		
	}
}
