package words;

import java.util.ArrayList;

public class WordsSection {
	private int size;
	private WordsOrientation orientation;

	private int[] fixed, origin;
	private char[] letters;

	public WordsSection(char[] letters, int[] origin, WordsOrientation orientation) {
		this.size = letters.length;
		this.orientation = orientation;
		this.letters = letters;
		this.origin = origin;

		fixed = determineFixed(letters);
	}

	private int[] determineFixed(char[] letters) {
		ArrayList<Integer> fixed = new ArrayList<>();

		for (int i = 0; i < letters.length; i++) {
			if (letters[i] != 0) {
				fixed.add(i);
			}
		}

		int[] f = new int[fixed.size()];
		for (int i = 0; i < f.length; i++) {
			f[i] = fixed.get(i);
		}

		return f;
	}

	public int getSize() {
		return size;
	}

	public WordsOrientation getOrientation() {
		return orientation;
	}

	public int[] getFixed() {
		return fixed;
	}

	public int[] getOrigin() {
		return origin;
	}

	public char[] getLetters() {
		return letters;
	}

}
