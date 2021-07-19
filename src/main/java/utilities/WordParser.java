package utilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordParser {
	public static Set<String> isComposedByElements(String word, List<String> elements) {
		Set<String> result = new HashSet<>();
		if(elements.contains(word)) {
			result.add(word.substring(0, 1).toUpperCase() + word.substring(1));
			if(word.length() > 1) {
				result.addAll(a(word, elements, 1));
			}
		} else if(word.length() > 2) {
			Set<String> newA = a(word, elements, 1);
			Set<String> newB = a(word, elements, 2);
			if(newA != null) {
				result.addAll(newA);
			}
			if(newB != null) {
				result.addAll(newB);
			}
		}		
		return result;
	}
	
	public static Set<String> a(String word, List<String> elements, int length) {
		Set<String> result = new HashSet<>();
		Set<String> part1List = isComposedByElements(word.substring(0, length), elements);
		Set<String> part2List = isComposedByElements(word.substring(length, word.length()), elements);
		for(String part1 : part1List) {
			for(String part2 : part2List) {
				result.add(part1 + part2);
			}
		}
		return result;
	}
}
