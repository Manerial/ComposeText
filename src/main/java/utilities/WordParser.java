package utilities;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordParser {
	

	public static Map<String, Set<String>> wordListWithoutSequence(Set<String> wordSet, String sequence) {
		Map<String, Set<String>> result = new TreeMap<>();
		for(String word : wordSet) {
			if(word.contains(sequence)) {
				String newWord = word.replaceAll(sequence, "");
				if(wordSet.contains(newWord)) {
					Set<String> element = new TreeSet<>();
					element.add(newWord);
					result.put(word, element);
				}
			}
		}
		return result;
	}
	
	public static Map<String, Set<String>> wordListCompositionWithElements(Set<String> wordSet, Set<String> elements) {
		Map<String, Set<String>> result = new TreeMap<>();
		String currentLetterToDisplay = "";
		for(String word : wordSet) {
			Set<String> setForWord = isComposedByElementsInList(word.toLowerCase(), elements);
			if(setForWord.size() > 0) {
				result.put(word, setForWord);
			}
			if(!currentLetterToDisplay.equals(word.substring(0, 1))) {				
				currentLetterToDisplay = word.substring(0, 1);
				System.out.print(currentLetterToDisplay);
			}
		}
		return result;
	}
	
	private static Set<String> isComposedByElementsInList(String word, Set<String> elements) {
		Set<String> elementsInWord = new HashSet<>();
		if(elements.contains(word)) {
			elementsInWord.add(word.substring(0, 1).toUpperCase() + word.substring(1));
			if(word.length() > 1) {
				elementsInWord.addAll(parseShorterWord(word, elements, 1));
			}
		} else if(word.length() > 2) {
			Set<String> elementsInWordPart1 = parseShorterWord(word, elements, 1);
			Set<String> elementsInWordPart2 = parseShorterWord(word, elements, 2);
			if(elementsInWordPart1 != null) {
				elementsInWord.addAll(elementsInWordPart1);
			}
			if(elementsInWordPart2 != null) {
				elementsInWord.addAll(elementsInWordPart2);
			}
		}		
		return elementsInWord;
	}
	
	private static Set<String> parseShorterWord(String word, Set<String> elements, int length) {
		String shorterWord1 = word.substring(0, length);
		String shorterWord2 = word.substring(length, word.length());
		Set<String> shorterWord1List = isComposedByElementsInList(shorterWord1, elements);
		Set<String> shorterWord2List = isComposedByElementsInList(shorterWord2, elements);

		Set<String> parsedWord = new HashSet<>();
		for(String shorterWord1Parsed : shorterWord1List) {
			for(String shorterWord2Parsed : shorterWord2List) {
				parsedWord.add(shorterWord1Parsed + shorterWord2Parsed);
			}
		}
		return parsedWord;
	}
}
