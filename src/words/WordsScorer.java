package words;

public class WordsScorer {
	
	private int[] letterValues = {1, 4, 4, 2, 1, 4, 3, 3, 1, 10, 5, 2, 4, 2, 1, 4, 10, 1, 1, 1, 2, 5, 4, 8, 3, 10};
	
	
	public WordsScorer() {
		
	}
	
	/*
	 * Calculate the point total of a given word if played in the given section of the board.
	 * 
	 * @param section The section of the board 
	 */
	public int calculatePoints(String word) {
		char[] wordChars = word.toCharArray();
		int total = 0;
		
		for (char c : wordChars) {
			total += getLetterValue(c);
		}
		
		return total;
	}
	
	public int getLetterValue(char c) {
		// 97 == a
		int index = c - 97;
		return letterValues[index];
	}
	
}
