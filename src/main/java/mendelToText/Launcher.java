package mendelToText;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import utilities.WordParser;
import utilities.WordsFilesManager;

public class Launcher {
	private static String inputFile = "French.txt";
	private static String elementsFile = "PeriodicTable.txt";
	private static String outputFile = "Result.txt";

	public static void main(String[] args) throws IOException {
		String message = parseArgs(args);
		if(message != null) {
			System.out.print(message);
		} else {
			Set<String> wordSet = WordsFilesManager.ExtractWordsFromFile(inputFile);
			Set<String> periodicTable = WordsFilesManager.ExtractWordsFromFile(elementsFile);		
			Map<String, Set<String>> result = WordParser.parseList(wordSet, periodicTable);
			WordsFilesManager.PrintMapInFile(outputFile, result);
		}
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
			case "-h":
				message = String.join("\r\n",
						"",
						"Available arguments: ",
						"",
						"-i <input>\t: The file containing the inputs. Default : French.txt",
						"-e <elements>\t: The file containing the elements. Default : PeriodicTable.txt",
						"-o <output>\t: The file containing the results. Default : Result.txt",
						"-h\t\t: Help");
				break;
			}
		}
		
		return message;
	}
}
