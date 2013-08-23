import java.io.FileReader;
import java.io.Reader;
import java.util.StringTokenizer;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * This program reads in the user input/file input
 * then sends words to the SpellDictionary method
 *
 *  @author  Monique Blake
 *  @version CSC 212, 13 April 2011
 */
public class SpellChecker {
  public static void main(String[] args) throws IOException {
    
    /** string tokenizer breaks up expression */
    StreamTokenizer tokens;
    /** current line on token */
    String currentLine;
    
    /** allows SpellDictionary to be called */
    SpellDictionary d = new SpellDictionary();
    
    if (args.length != 0) {
      /** this assumes user input */
      for(String word: args) {
        // set to lowercase
        word.toLowerCase();
        // nonstatic call in a static method
        //note to self: SpellDictionary d = new SpellDictionary();
        d.suggestWords(word);
      }
      
    } else {
      /** call standard input, for files */
      BufferedReader file = new BufferedReader(new InputStreamReader(System.in));
      tokens = new StreamTokenizer(file);
      String line;
      
      // read contents
      while(tokens.nextToken() != StreamTokenizer.TT_EOF ) {
        
        ArrayList<String> userWord = new ArrayList<String>();
        String stringVal = tokens.sval;
        System.out.println("this is string val "+stringVal.toLowerCase());
        userWord.add(stringVal.toLowerCase());
        //     userWord.replaceAll("\\p{P}+", " ");
        int i = 0;
        String word = userWord.get(i).toString();
        d.suggestWords(word);
        
      }
    }
    
  }
}
