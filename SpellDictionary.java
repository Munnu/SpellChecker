import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.net.URL;
import java.util.*;
import java.io.*;

/**
 * This program reads in the dictionary file
 * and compares the user input to the words in the
 * hashmap/dictionary. If the word is incorrect it will
 * try to find suggested words by deleting, inserting,
 * transposition, substitution, or splitting
 *
 *  @author  Monique Blake
 *  @version CSC 212, 13 April 2011
 */

public class SpellDictionary {
  /** Empty hashmap to load in dictionary words */
  HashMap dictionary;
  
  /** String of alphabet*/
  private static final String alpha = "abcdefghijklmnopqrstuvwxyz";
  
  /** array of alphabet */
  private static final char[] alphabet = alpha.toCharArray();
  
  public SpellDictionary() throws IOException {
    /** initalize HashMap */
    dictionary = new HashMap();
    
    BufferedReader input = 
      new BufferedReader(new FileReader("engDict.txt"));
    
    String line;
    line = input.readLine();
    
    /** LOAD FILE INTO HASHMAP */
    while( (line) != null){
      String word = line;
      dictionary.put(word, null);
      line = input.readLine();
    }
    input.close();  
  }
  
  /**
   * Checks to see if the word the user inputs exists in the dictionary
   * 
   * @param word is the word the user inputs
   * @returns whether the word was found in the dictionary
   */
  public boolean lookup(String word) {
    // if the word is found
    if (dictionary.containsKey(word)) {
      return true;
    } else {
      return false;
    }
  } //end of lookup method
  
  /**
   * Does deletion, insertion, substitution, transposition, and split
   * on a word if it is not already found in the dictionary and prints
   * out a list of suggested words
   * 
   * @param word is the word the user inputs
   */
  public void suggestWords (String word) {
    // initalize arraylist
    ArrayList<String> suggested = new ArrayList<String>();
    
    /** deletes one character in a word per iteration
      * until word resembles word in dictionary
      * ex: coww -> oww -> cww -> cow
    */
    
    // calling StringBuilder constructor
    StringBuilder builder = new StringBuilder(word);
    
    for ( int i = 0; i < word.length(); i++ ) {
      // compare word inputted to dictionary word to see if they're alike
      if ( !lookup(word) ) {
        builder = new StringBuilder(word);
        // if word is not found, delete one char at a time using StringBuilder
        StringBuilder dModifiedWord = builder.deleteCharAt(i);
        String modified = dModifiedWord.toString();
        // if modified word in array list isn't already in there
        if ( (dictionary.containsKey(modified)) && (!suggested.contains(modified))) {
          
          // add stringbuilder word into dictionary
          suggested.add(modified);
        }
        System.out.println("Word after "+ modified);
      } 
    } // end of delete
    
    /** insert: uses all of the words in the alphabet, 
      * inserts a letter into current word
      */
    
    for( int i = 0; i<word.length()+1; i++ ) {
      for( int j = 0; j< alphabet.length; j++) {
        if ( !lookup(word) ) {
          builder = new StringBuilder(word);        
          //String builder insert char
          StringBuilder iModifiedWord = builder.insert(i,alphabet[j]);
          String modified = iModifiedWord.toString();
          
          if ( (dictionary.containsKey(modified)) && (!suggested.contains(modified))) {
            // add stringbuilder word into dictionary
            suggested.add(modified);
          }
          System.out.println("Word after "+modified);
          
        }
      }
    } //end of insert
    
    /** Substitutes letter in the word until it finds matches */
    for ( int i = 0; i < word.length(); i++ ) {                                                                                                                                                          for(int j = 0; j<alphabet.length; j++) {
      // compare word inputted to dictionary word to see if they're alike                                                                                                                                
      if ( !lookup(word) ) {                                                                                                                                                                             
        builder = new StringBuilder(word);                                                                                                                                                             
        // if word is not found, delete one char at a time using StringBuilder                                                                                                                           
        StringBuilder dModifiedWord = builder.deleteCharAt(i);                                                                                                
        StringBuilder iModifiedWord = builder.insert(i,alphabet[j]);
        String modified = dModifiedWord.toString();                                                                                                                                                 
        
        if ( (dictionary.containsKey(modified)) && (!suggested.contains(modified))) {
          // add stringbuilder word into dictionary                                                                                                                                                      
          suggested.add(modified);                                                                                                                                                                              }                                                                                                                                                                                                
        System.out.println("Word after "+ modified);                                                                                                                                                     
      }                                                                                                                                                                                                  }
    } // end of new substitution
    
    
    /** Transposition:  swaps adjacent characters */
    
    for(int i = 0; i< word.length()-1; i++ ) {
      if( !lookup(word) ) {
        builder = new StringBuilder(word);  
        // swap words
        char first = builder.charAt(i), second = builder.charAt(i+1);
        builder.setCharAt(i, second);
        builder.setCharAt(i+1, first);
        String modified = builder.toString();
        
        if ( (dictionary.containsKey(modified)) && (!suggested.contains(modified))) {
          // add stringbuilder word into dictionary
          suggested.add(modified);
        }
        System.out.println("Word after "+modified);
      }
    } //end of transposition
    
    
    /** splits: splits word into two strings
      *see if the two new strings are both actual words
      */
    String combined = "";
    for(int i = 0; i <word.length()-1; i++ ) {
      if( !lookup(word) ) {
        builder = new StringBuilder(word);  
        String sLeft = word.substring(0, i);
        String sRight = word.substring(i, word.length());
        if ((dictionary.containsKey(sLeft)) && (dictionary.containsKey(sRight))) {
          combined = sLeft+ " " + sRight;
          // add sLeft and sRight into arraylist
          if (!suggested.contains(combined) ) {
            suggested.add(combined);
            
            // found a word from the dictionary, set lookup as true
            lookup(word);
          }
        }
      }
    } // end of splits
    
    // print list here
    System.out.println("List of suggested words: "+suggested);
  }
  
}
