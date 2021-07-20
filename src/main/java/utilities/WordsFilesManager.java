package utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class WordsFilesManager {
	private static String RESOURCE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\";

	public static Set<String> ExtractWordsFromFile(String fileName) throws IOException {
		List<String> wordList = WordsFilesManager.getLinesfromFile(fileName);
		// Remove accents or special chars
		wordList.replaceAll(string -> Normalizer.normalize(string, Normalizer.Form.NFD));
		wordList.replaceAll(string -> string.replaceAll("[^\\p{ASCII}]", ""));
		wordList.replaceAll(String::toLowerCase);
		
		// Using a TreeSet will remove doubles and sort by alphabetical order
		Set<String> wordSet = new TreeSet<>();
		wordSet.addAll(wordList);
		return wordSet;
	}

	/**
	 * Save a list of words in a RESULT file
	 * 
	 * @param analyzer : The analyzer that contains the RESULT file name
	 * @param result : The list of words to save
	 * @throws IOException : All the IO exceptions
	 */
	public static void PrintMapInFile(String fileName, Map<String, Set<String>> result) throws IOException {
		PrintWriter resultFile = getPrinterWriter(RESOURCE_PATH + fileName);
		
		String resultString = result.toString();
		resultString = resultString.substring(1, resultString.length() - 1);
		resultString = resultString.replaceAll("], ", "],\r\n");
		
		resultFile.println(resultString);
		resultFile.close();
	}

	/**
	 * Read the content of a ELEMENT file and return a list
	 * 
	 * @param analyzer : The analyzer that contains the ELEMENT file name
	 * @return A list of words contained in the elementFile
	 * @throws IOException : All the IO exceptions
	 */
	private static List<String> getLinesfromFile(String fileName) throws IOException {
		ArrayList<String> result = new ArrayList<String>();
		BufferedReader br = getBufferReader(RESOURCE_PATH + fileName);
		String line;
		while ((line = br.readLine()) != null) {
			result.add(line);
		}
		br.close();
		return result;
	}

	/**
	 * Return a BufferedReader to read a file
	 * 
	 * @param filePath : the file path to read
	 * @return a BufferedReader with the path of the file to read
	 * @throws FileNotFoundException : All the File Not Found exceptions
	 */
	private static BufferedReader getBufferReader(String filePath) throws FileNotFoundException {
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr = new InputStreamReader(ips);
		return new BufferedReader(ipsr);
	}

	/**
	 * Return a BufferedWriter to write into a file
	 * 
	 * @param filePath : the file path to write
	 * @return a BufferedWriter with the path of the file to write
	 * @throws IOException : All the IO exceptions
	 */
	private static PrintWriter getPrinterWriter(String filePath) throws IOException {
		FileWriter fw = new FileWriter(filePath);
		BufferedWriter bw = new BufferedWriter(fw);
		return new PrintWriter(bw);
	}
}
