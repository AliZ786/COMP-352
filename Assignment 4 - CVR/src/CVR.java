// -----------------------------------------------------
// Assignment 4
// Question 1
// Written by: xxxxxxxxxx (xxxxxxxxx) & Mohammad Ali Zahir (40077619)
// -----------------------------------------------------
// The main implementation for the CVR class

/**
 * Names: xxxxxxxxxxxx (xxxxxxx) & Mohammad Ali Zahir (40077619)
// Comp 352- Winter 2020 Section S
// Assignment : #4 
// Due Date: Sunday April 19, 2020
 */



import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

public class CVR<K,V> {
	
	private int method; //stores the value to see if it will use sequence or hash table
	private int length; //length of the string 
	private List<K> allKey; //all keys for sequence
	private List<K> allTreeKey; //all keys for hash table
	private Map<K, List<V>> m1; //map for sequence
	private SortedMap<K, List<V>> m2; //map for hash table
	private static char[] alphanumeric = {'A','B','C','D','E','F','G','H','I','J',
										'K','L','M','N','O','P','Q','R','S','T','U',
										'V','W','X','Y','Z','0','1','2','3','4','5',
										'6','7','8','9'};
	
	/**
	 * Constructor
	 */
	public CVR() {
		m1 = new LinkedHashMap<K,List<V>>();
		m2 = new TreeMap<K, List<V>>();
	}
	
	/**
	 * Method to set the threshold and based on that decide the ADT to use
	 * @param threshold
	 * Complexity: O(1)
	 */
	public void setThreshold(int threshold) { 
		if(threshold > 0 && threshold < 100) {
			method = 1;
		}
		else if(threshold >=100 && threshold <= 900000) {
			method = 2;
		}
		else
			System.out.println("Error in the threshold!");
	}
	
	/**
	 * Setting the string length of the key
	 * @param length
	 * Complexity: O(1)
	 */
	public void setKeyLength(int length) {
		if(length >= 10 && length <= 17) 
			this.length = length;
		else
			System.out.println("Error! Length is not within range(10 - 17)");
	}
	
	/**
	 * Generates n number of random keys and adds it
	 * @param n
	 * Complexity: O(n)
	 */
	@SuppressWarnings("unchecked")
	public void generate(int n) {
		K vin;
		V ranValues;
		boolean con = false;
		Random random = new Random();
		if(method == 1) {
			for(int i = 0; i < n; i++) {
				vin = (K)generateRandom();
				con = m1.containsKey(vin);
				while(con == true) {
					vin = (K)generateRandom();
					con = m1.containsKey(vin);
				}
				ranValues = (V)String.valueOf(random.nextInt(121)+1900); //Randomly generates a number between 1900 and 2020
				add(vin, ranValues);
			}
		}
		else if(method == 2) {
			for(int i = 0; i < n; i++) {
				vin = (K)generateRandom();
				con = m2.containsKey(vin);
				while(con == true) {
					vin = (K)generateRandom();
					con = m2.containsKey(vin);
				}
				ranValues = (V)String.valueOf(random.nextInt(121)+1900); //Randomly generates a number between 1900 and 2020
				add(vin, ranValues);
			}
		}
	}
	
	/**
	 * Returns all the keys depending on the threshold
	 * @return
	 * Complexity: O(n)
	 */
	public List<K> allKeys(){
		if(method == 1) {
			allKey = new ArrayList<K>(m1.keySet());
			allKey.sort(null);
			return allKey;
		}
		else if(method == 2) {
			allTreeKey = new ArrayList<K>(m2.keySet());
			allTreeKey.sort(null);
			return allTreeKey;
		}
		return null;
	}
	
	/**
	 * Add method to add key and value to the list. 
	 * @param key
	 * @param value
	 * Complexity: O(1)
	 */
	public void add(K key, V value) {
		if(method == 1) {
			if(key != null) {
				if(!m1.containsKey(key)) {
					m1.put(key, new ArrayList<V>()); //Adds to the list
				}
				m1.get(key).add(value);
			}
		}
		else if(method == 2) {
			if(key != null) {
				if(!m2.containsKey(key)) {
					m2.put(key, new ArrayList<V>()); //Adds to the list
				}
				m2.get(key).add(value);
			}
		}
	}
	
	/**
	 * Removes key and value from list
	 * @param key
	 * Complexity : O(1)
	 */
	public void remove(K key) {
		
		if(method == 1)
			m1.remove(key);
		else if(method == 2) 
			m2.remove(key);
			
	}
	
	/**
	 * Returns value of the given key
	 * @param key
	 * @return
	 * Complexity : O(1)
	 */
	public V getValues(K key) {
		
		List<V> newValue = new ArrayList<V>();
		if(method == 1) {
			newValue = m1.get(key);
			return newValue.get(newValue.size()-1);
		}
		else if(method == 2) {
			newValue = m2.get(key);
			return newValue.get(newValue.size()-1);
		}
		return null;
	
	}
	
	/**
	 * Returns next key of the given key
	 * @param key
	 * @return
	 * Complexity: O(1)
	 */
	public K nextKey(K key) {
		 if(method == 1){
	            if(allKey.indexOf(key) == allKey.size()-1){
	                System.out.println("There is no successor");
	                return null;
	            }
	            return allKey.get(allKey.indexOf(key)+1);
	        }
	        else if(method == 2){
	            if(allTreeKey.indexOf(key) == allTreeKey.size()-1){
	                System.out.println("There is no successor");
	                return null;
	            }
	            return allTreeKey.get(allTreeKey.indexOf(key)+1);
	        }
	        return null;
	}
	
	/**
	 * Returns previous key of the given key
	 * @param key
	 * @return
	 * Complexity: O(1)
	 */
	public K prevKey(K key) {
		
		if(method == 1){
            if(allKey.indexOf(key) == 0){
                System.out.println("There is no predecessor");
                return null;
            }
            return allKey.get(allKey.indexOf(key) - 1);
        }
        else if(method == 2){
            if(allTreeKey.indexOf(key) == 0){
                System.out.println("There is no predecessor");
                return null;
            }
            return allTreeKey.get(allTreeKey.indexOf(key)-1);
        }
        return null;
		
	}
	
	/**
	 * Returns all keys that are registered to have been in an accident before the given key
	 * @param key
	 * @return
	 * Complexity: O(1)
	 */
	@SuppressWarnings("unchecked")
	public List<V> prevAccids(K key){
		
		List<V> preValues = new ArrayList<V>();
		if(method == 1) {
			preValues = m1.get(key);
			preValues = preValues.subList(0, preValues.size()-1);
			preValues.sort((Comparator<? super V>) Comparator.reverseOrder());
			return preValues;
		}
		else if(method == 2) {
			preValues = m2.get(key);
			preValues = preValues.subList(0, preValues.size()-1);
			preValues.sort((Comparator<? super V>) Comparator.reverseOrder());
			return preValues;
		}
		else {
			System.out.println("No previous accidents");
			return null;
		}
		
	}
	
	/**
	 * Method used to generate random alphanumeric string
	 * @return
	 * Complexity : O(n)
	 */
	private String generateRandom() {
		Random random = new Random();
		char[] def_temp = new char[length];
		
		for(int i = 0; i < length; i++) {
			int x = random.nextInt(alphanumeric.length);
			def_temp[i] = alphanumeric[x];
		}
		
		String result = String.valueOf(def_temp);
		return result;
	}
}
