import java.util.ArrayList;
import javax.swing.JOptionPane;
public class TextChecker {
	public static ArrayList<Character> compare (String word, String input){
		
		char[] wordCharacters = word.toCharArray();
		char[] inputCharacters = input.toCharArray();
		ArrayList<Character> similarCharacters = new ArrayList<>();
		
		//I just turn a bunch of letters, not thinking about whether there are the same ones
		
		for (int i = 0; i<wordCharacters.length; i++){
			for (int j = 0; j<inputCharacters.length; j++){
				if (wordCharacters[i] == inputCharacters[j]){
					similarCharacters.add(wordCharacters[i]);
				}
			}
		}
		
		return similarCharacters;	
	}
	
}