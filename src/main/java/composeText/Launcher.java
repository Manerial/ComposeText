package composeText;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import utilities.WordParser;
import utilities.WordsFilesManager;

public class Launcher {
	private static String inputFile = "French.txt";
	private static String elementsFile = "PeriodicTable.txt";
	private static String outputFile = "Result.txt";
	private static String sequence = "";
	private static E_Parser parser;

	public static void main(String[] args) throws IOException {
		String message = parseArgs(args);
		Map<String, Set<String>> result = null;
		if(message != null) {
			System.out.print(message);
		} else {
			Set<String> wordSet = WordsFilesManager.ExtractWordsFromFile(inputFile);
			switch (parser) {
			case composition:
				System.out.print("Parsing " + inputFile + " composition with elements of " + elementsFile);
				Set<String> periodicTable = WordsFilesManager.ExtractWordsFromFile(elementsFile);	
				result = WordParser.wordListCompositionWithElements(wordSet, periodicTable);
				break;
			case removeLetter:
				System.out.print("Try removing " + sequence + " in each elements of " + inputFile);
				result = WordParser.wordListWithoutSequence(wordSet, sequence);
				break;
			default:
				System.out.print("No parser selected. Use -h for help");
				break;
			}
		}
		WordsFilesManager.PrintMapInFile(outputFile, result);
	}

	private static String parseArgs(String[] args) {
		String message = null;
		for(int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "-i" :
				inputFile = args[i+1];
				break;
			case "-e":
				elementsFile = args[i+1];
				break;
			case "-o" :
				outputFile = args[i+1];
				break;
			case "-c":
				parser = E_Parser.composition;
				break;
			case "-rl":
				parser = E_Parser.removeLetter;
				sequence = args[i+1];
				break;
			case "-h":
				message = String.join("\r\n",
						"",
						"Available arguments: ",
						"",
						"===Parsers===",
						"-rl <letter>\t: Use the \"remove letter\" parser : try to remove a sequence in a list of elements and check if the new element still exist in the list.",
						"-c \t\t: Use the \"composition\" parser : try to compose elements of a list with elements of an other list. Example : the word \"coco\" and the periodic table => [COCO, CoCo, CoCO, COCo] (C = Carbin, O = Oxygen, Co = Cobalt)",
						"",
						"===Options===",
						"-i <input>\t: The file containing the inputs. Default : French.txt",
						"-e <elements>\t: The file containing the elements. Default : PeriodicTable.txt",
						"-o <output>\t: The file containing the results. Default : Result.txt",
						"-h\t\t: Help");
				break;
			}
		}

		return message;
	}

	enum E_Parser {
		removeLetter, composition
	}
}
