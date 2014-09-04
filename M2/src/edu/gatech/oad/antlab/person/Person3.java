package edu.gatech.oad.antlab.person;
/**
 *  A simple class for person 3 (Cindy Wang in Team Space Ferret)
 *  returns their name and a
 *  reversed string 
 *  
 *  @author  Cindy Wang
 *  @version 1.0
 */
public class Person3 {
   /** Holds the persons real name */  
	private String name = "Cindy Wang";
	
	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
	public Person3(String pname){
	  name = pname;
	}
	
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
	
	/**
	 * This method should take the string
	 * input and return its reverse.
	 * given "gtg123b" it should return
	 * b321gtg.
	 *
	 * @param input the string to be reversed
	 * @return the reversed string
	 */
	private String calc(String input) {
		char[] strChar = input.toCharArray();
		String reverse = "";

		for( int i = strChar.length - 1 ; i >= 0 ; i-- ) {
			reverse = reverse + strChar[i];
		}
		return reverse;
	}
}
