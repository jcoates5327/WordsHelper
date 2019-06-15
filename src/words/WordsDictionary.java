package words;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsDictionary {

	private final String DICT_FULL = "enable.txt";

	private HashMap<Integer, ArrayList<String>> wordListMap;

	public WordsDictionary() throws IOException {
		// read ENABLE file
		buildWordLists();

	}

	private void buildWordLists() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(DICT_FULL));
		wordListMap = new HashMap<>();

		String line;
		while ((line = reader.readLine()) != null) {
			int wordLength = line.length();
			if (wordLength > 15) {
				continue;
			}

			ArrayList<String> tmpList;

			if (!wordListMap.containsKey(wordLength)) {
				tmpList = new ArrayList<>();
			} else {
				tmpList = wordListMap.get(wordLength);
			}

			tmpList.add(line);
			wordListMap.put(wordLength, tmpList);

		}

		reader.close();

//		for (int i : wordListMap.keySet()) {
//			String fName = "wordsLen" + i + ".txt";		
//			BufferedWriter writer = new BufferedWriter(new FileWriter(fName));
//			ArrayList<String> tmpList = wordListMap.get(i);
//			
//			System.out.println("Length: " + i + ", count: " + tmpList.size());
//			
//			for (String s : tmpList) {
//				writer.write(s);
//				writer.newLine();
//			}
//			writer.close();
//		}
	}

	public ArrayList<String> getWordListOfSize(int size) {
		return wordListMap.get(size);
	}

	public HashMap<Integer, ArrayList<String>> getWordListMap() {
		return wordListMap;
	}
}
