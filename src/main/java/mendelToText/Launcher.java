package mendelToText;

import java.io.IOException;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import utilities.WordParser;
import utilities.WordsFilesManager;

public class Launcher {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> wordList = WordsFilesManager.parseElementsFileInList("French.txt");
		wordList.replaceAll(string -> Normalizer.normalize(string, Normalizer.Form.NFD));
		wordList.replaceAll(string -> string.replaceAll("[^\\p{ASCII}]", ""));
		
		Set<String> wordSet = new TreeSet<>();
		wordSet.addAll(wordList);
		
		List<String> periodicTable = WordsFilesManager.parseElementsFileInList("PeriodicTable.txt");
		periodicTable.replaceAll(String::toLowerCase);
		
		
		Map<String, Set<String>> result = new TreeMap<>();
		String firstLetter = "";
		for(String word : wordSet) {
			Set<String> setForWord = WordParser.isComposedByElements(word.toLowerCase(), periodicTable);
			if(setForWord.size() > 0) {
				result.put(word, setForWord);
			}
			if(!firstLetter.equals(word.substring(0, 1))) {				
				firstLetter = word.substring(0, 1);
				System.out.print(firstLetter);
			}
		}
		WordsFilesManager.printStringListInFile("result.txt", result);
	}

}
