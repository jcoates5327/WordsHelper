package words;

public class WordsScorer {

	private int[] letterValues = { 1, 4, 4, 2, 1, 4, 3, 3, 1, 10, 5, 2, 4, 2, 1, 4, 10, 1, 1, 1, 2, 5, 4, 8, 3, 10 };

	public WordsScorer() {

	}

	/* 
	 * Calculate the point total of a given word if played in the given section of
	 * the board.
	 * 
	 * @param section The section of the board
	 */
	public int calculatePoints(String word, WordsSection section, WordsBoard board, WordsOrientation orientation) {
		char[] wordChars = word.toCharArray();
		char[] letters = section.getLetters();
		int[] origin = section.getOrigin();
		int wordBonus = 0;
		int total = 0;
		
		for (int i = 0; i < wordChars.length; i++) {
			char c = wordChars[i];
			int ltrVal = getLetterValue(c);
			int rOffset = 0;
			int cOffset = 0;
			
			if (orientation == WordsOrientation.HORIZONTAL) {
				cOffset = i;
			} else {
				rOffset = i;
			}
			
			// don't count bonus tiles for letters already on the board
			if (letters[i] == 0) {
				ltrVal += ltrVal * board.getLetterBonus(origin[0] + rOffset, origin[1] + cOffset);
				wordBonus += board.getWordBonus(origin[0] + rOffset, origin[1] + cOffset);
			}

			total += ltrVal;
		}
		
		total += total * wordBonus;
		
		

		return total;
	}

	public int getLetterValue(char c) {
		// 97 == a
		int index = c - 97;
		return letterValues[index];
	}

}
