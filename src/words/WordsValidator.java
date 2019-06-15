package words;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/*
 * Contains methods for validating Words.
 */

public class WordsValidator {
	private WordsDictionary dict;

	public WordsValidator(WordsDictionary dict) {
		this.dict = dict;
	}

	public boolean isValidWord(String word) {
		int size = word.length();
		List<String> wordList = dict.getWordListOfSize(size);

		if (wordList.contains(word)) {
			return true;
		}

		return false;
	}

	/*
	 * Builds a list of valid Words for the given Section of the Board.
	 * 
	 * @param playerTiles List of the Player's current set of letters
	 * 
	 * @param section the Section of the Board to fill
	 * 
	 * @param dict the Dictionary to use when building list of Words
	 * 
	 * @return a List of all valid Words that can be played in the given Section
	 */
	public List<String> buildListOfValidWordsForSection(List<Character> playerTiles, WordsSection section) {
		// takes in a section of the board, in the form of a char[], where a word can be
		// played
		// $fixed contains indices of chars that are already on the board
		// return a List of all valid words that can be played in the given section
		// using the player's current tiles

		char[] wordChars;
		List<String> validWords = new ArrayList<>();
		List<String> wordList = dict.getWordListOfSize(section.getSize());

		for (String word : wordList) {
			wordChars = word.toCharArray();

			// skip current Word if it doesn't contain fixed letters
			if (!containsFixedLetters(wordChars, section)) {
				continue;
			}

			// skip current Word if it doesn't contain only letters the Player has available
			if (containsLetters(playerTiles, wordChars, section)) {
				// word is valid!
				validWords.add(word);
			}

		}

		return validWords;
	}

	private boolean containsFixedLetters(char[] wordChars, WordsSection section) {
		int[] fixed = section.getFixed();
		char[] letters = section.getLetters();

		for (int i : fixed) {
			if (wordChars[i] != letters[i]) {
				return false;
			}
		}
		return true;
	}

	private boolean containsLetters(List<Character> playerTiles, char[] wordChars, WordsSection section) {
		List<Character> wordCharSet = new ArrayList<>();
		int[] fixed = section.getFixed();

		// collect all non-fixed letters in given word
		for (int i = 0; i < wordChars.length; i++) {
			boolean isFixedLetter = false;
			for (int n : fixed) {
				if (i == n) {
					isFixedLetter = true;
					break;
				}
			}

			if (!isFixedLetter) {
				wordCharSet.add(wordChars[i]);
			}
		}

		if (CollectionUtils.isSubCollection(wordCharSet, playerTiles)) {
			return true;
		}

		return false;
	}
}
