import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	public void buildLetterTable() {
		    // Complete
	    letterTable = new HashMap<>();
	    int counter = 0;
	    int ascii = 97;
	    while(counter < primes.length){
	    	letterTable.put((char) (ascii+counter), primes[counter]);
	    	counter++;
		}
	}

	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<>();
	}

	public void addWord(String s) {
		    // Complete
	    long hc = myhashcode(s);
	    if(anagramTable.containsKey(hc)){
	    	ArrayList<String> anagrams = anagramTable.get(hc);
			anagrams.add(s);
			anagramTable.replace(hc, anagrams);
		}
	    else{
	    	ArrayList<String> anagrams = new ArrayList<>();
	    	anagrams.add(s);
	    	anagramTable.put(hc, anagrams);
		}
	}
	
	public long myhashcode(String s) {
		    // Complete
		int counter = 0;
		long hashcode = 1;
		while(counter < s.length()){
			hashcode*=letterTable.get(s.charAt(counter));
			counter++;
		}
		return hashcode;
	}
	
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		ArrayList<Map.Entry<Long, ArrayList<String>>> longestEntries = new ArrayList<>();
		int minValue = Integer.MIN_VALUE;
	    for(Map.Entry<Long, ArrayList<String>> entry : anagramTable.entrySet()){
	    	if (entry.getValue().size() > minValue){
	    		longestEntries = new ArrayList<>();
	    		minValue = entry.getValue().size();
	    		longestEntries.add(entry);
			}
	    	else if(entry.getValue().size() == (minValue)){
	    		longestEntries.add(entry);
			}
		}
	    return longestEntries;
	}
	
	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("empty.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		ArrayList<ArrayList<String>> listOfAnagrams = new ArrayList<>();
		ArrayList<Long> listOfKeys = new ArrayList<>();
		int lengthOfMaxAnagrams = 0;
		for(Map.Entry<Long, ArrayList<String>> entry : maxEntries){
			listOfAnagrams.add(entry.getValue());
			listOfKeys.add(entry.getKey());
			lengthOfMaxAnagrams = entry.getValue().size();
		}
		System.out.println("Time: " + seconds);
		System.out.println("Key of max anagrams: " + listOfKeys);
		System.out.println("List of max anagrams: " + listOfAnagrams);
		System.out.println("Length of list of max anagrams: " + lengthOfMaxAnagrams);
	}
}
