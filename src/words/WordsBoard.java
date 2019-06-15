package words;

import java.util.HashMap;

/*
 * Handles and stores board state.
 */
public class WordsBoard {
	
	private char[] r0   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,   0,  'k',  0,   0,   0 };
	private char[] r1   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,   0,  'v',  0,   0,   0 };
	private char[] r2   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,  'f', 'a',  0,   0,   0 };
	private char[] r3   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,  'i', 's',  0,   0,  't' };
	private char[] r4   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,  'l',  0,   0,   0,  'h' };
	private char[] r5   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,  't', 'a', 'f', 'i', 'a' };
	private char[] r6   = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,  'e',  0,   0,   0,  't' };
	private char[] r7   = { 0,   0,   0,   0,  0,   0,  'h', 'o', 'n', 'e', 'r', 's',  0,   0,   0 };
	private char[] r8   = { 0,   0,   0,   0, 'c', 'w', 'm',  0,   0,   0,   0,  'p',  0,   0,   0 };
	private char[] r9   = { 0,   0,   0,   0, 'u', 'h',  0,   0,   0,   0,   0,  'a',  0,   0,   0 };
	private char[] r10  = { 0,   0,   0,   0, 'm', 'y',  0,   0,   0,   0,   0,  'd',  0,   0,   0 };
	private char[] r11  = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,   0,  'o',  0,   0,   0 };
	private char[] r12  = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 };
	private char[] r13  = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 };
	private char[] r14  = { 0,   0,   0,   0,  0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0 };
	
	private char[] lr0  = { 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 };
	private char[] lr1  = { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 };
	private char[] lr2  = { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 };
	private char[] lr3  = { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 };
	private char[] lr4  = { 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0 };
	private char[] lr5  = { 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0 };
	private char[] lr6  = { 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2 };
	private char[] lr7  = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] lr8  = { 2, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 2 };
	private char[] lr9  = { 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0 };
	private char[] lr10 = { 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0 };
	private char[] lr11 = { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 };
	private char[] lr12 = { 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0 };
	private char[] lr13 = { 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 };
	private char[] lr14 = { 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0 };
	
	private char[] wr0  = { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 };
	private char[] wr1  = { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 };
	private char[] wr2  = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] wr3  = { 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 };
	private char[] wr4  = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] wr5  = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
	private char[] wr6  = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] wr7  = { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0 };
	private char[] wr8  = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] wr9  = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };
	private char[] wr10 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] wr11 = { 2, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 2 };
	private char[] wr12 = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private char[] wr13 = { 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0 };
	private char[] wr14 = { 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0 };
	
	private char[][]            board = {  r0,  r1,  r2,  r3,  r4,  r5,  r6,  r7,  r8,  r9,  r10,  r11,  r12,  r13,  r14 };
	private char[][] letterBonusBoard = { lr0, lr1, lr2, lr3, lr4, lr5, lr6, lr7, lr8, lr9, lr10, lr11, lr12, lr13, lr14 };
	private char[][]   wordBonusBoard = { wr0, wr1, wr2, wr3, wr4, wr5, wr6, wr7, wr8, wr9, wr10, wr11, wr12, wr13, wr14 };
	
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;

	public WordsBoard() {
		BOARD_HEIGHT = board.length;
		BOARD_WIDTH = board[0].length;
	}

	/*
	 * Returns a slice of the Board array.
	 * 
	 * @param size the size of the desired Section
	 * 
	 * @param origin the coordinates of the Board the slice should start at:
	 * [row][col]
	 * 
	 * @param orientation determines whether the Section is horizontal or vertical
	 * 
	 * @return WordsSection object containing a valid section of the Board
	 */
	public WordsSection getBoardSection(int size, int[] origin, WordsOrientation orientation) {
		if (orientation == WordsOrientation.HORIZONTAL) {
			// validate section doesn't run off the edge of the board
			if (origin[1] + size > BOARD_WIDTH) {
				return null;
			}

			char[] wordLetters = new char[size];
			for (int i = 0; i < size; i++) {
				wordLetters[i] = board[origin[0]][origin[1] + i];
			}

			return new WordsSection(wordLetters, origin, orientation);
		} else {
			// validate section doesn't run off the edge of the board
			if (origin[0] + size > BOARD_HEIGHT) {
				return null;
			}

			char[] wordLetters = new char[size];
			for (int i = 0; i < size; i++) {
				wordLetters[i] = board[origin[0] + i][origin[1]];
			}

			return new WordsSection(wordLetters, origin, orientation);
		}
	}

	/*
	 * MODIFY TO RETURN TRUE/FALSE
	 * 
	 * Determine if a given Section contacts any existing letters on the board
	 * horizontally.
	 * 
	 * @param section the Section of the board to analyze
	 * 
	 * @return size 2 array containing indices of farthest letters Section is
	 * touching; [left, right]; -1 indicates no letters touched
	 */
	public boolean isSectionTouchingExistingLetterHorizontally(WordsSection section) {
		int[] origin = section.getOrigin();

		int row = origin[0];
		int colStart = origin[1] - 1;
		int colEnd = origin[1] + section.getSize();

		// look left
		if (colStart >= 0) {
			char c = board[row][colStart];
			if (c != 0) {
				return true;
			}
		}

		// look right
		if (colEnd < BOARD_WIDTH) {
			char c = board[row][colEnd];
			if (c != 0) {
				return true;
			}
		}

		return false;
	}

	/*
	 * MODIFY TO RETURN TRUE/FALSE
	 * 
	 * Determine if a given Section contacts any existing letters on the board
	 * vertically.
	 * 
	 * @param section the Section of the board to analyze
	 * 
	 * @return size 2 array containing indices of farthest letters Section is
	 * touching; [up, down]; -1 indicates no letters touched
	 */
	public boolean isSectionTouchingExistingLetterVertically(WordsSection section) {
		int[] origin = section.getOrigin();

		int col = origin[1];
		int rowStart = origin[0] - 1;
		int rowEnd = origin[0] + section.getSize();

		// look up
		if (rowStart >= 0) {
			char c = board[rowStart][col];
			if (c != 0) {
				return true;
			}
		}

		// look down
		if (rowEnd < BOARD_HEIGHT) {
			char c = board[rowEnd][col];
			if (c != 0) {
				return true;
			}
		}

		return false;
	}

	public HashMap<Integer, WordsSection> getVerticalBorderSections(WordsSection section) {
		int[] origin = section.getOrigin();
		char[] letters = section.getLetters();
		int size = section.getSize();

		HashMap<Integer, WordsSection> verticalSections = new HashMap<>();

		// for each non-fixed letter in the section:
		// check up and down for existing letters
		// create a new section containing these existing letters, if present
		// add new section to return list
		for (int col = 0; col < size; col++) {
			if (letters[col] != 0) {
				continue;
			}

			int above = -1, below = -1;

			for (int row = origin[0] - 1; row >= 0; row--) {
				char c = board[row][col + origin[1]];

				if (c == 0) {
					break;
				} else {
					above = row;
				}
			}

			for (int row = origin[0] + 1; row < BOARD_HEIGHT; row++) {
				char c = board[row][col + origin[1]];

				if (c == 0) {
					break;
				} else {
					below = row;
				}
			}

			if (above > -1 || below > -1) {
				int[] newOrigin = new int[2];
				newOrigin[0] = origin[0];
				newOrigin[1] = col + origin[1];
				int newSize = 1;

				if (above > -1) {
					newOrigin[0] = above;
					newSize += origin[0] - above;
				}

				if (below > -1) {
					newSize += below - origin[0];
				}

				WordsSection newSection = getBoardSection(newSize, newOrigin, WordsOrientation.VERTICAL);
				verticalSections.put(col, newSection);
			}

		}

		return verticalSections;
	}

	public HashMap<Integer, WordsSection> getHorizontalBorderSections(WordsSection section) {
		int[] origin = section.getOrigin();
		char[] letters = section.getLetters();
		int size = section.getSize();

		HashMap<Integer, WordsSection> horizontalSections = new HashMap<>();

		// for each non-fixed letter in the section:
		// check left and right for existing letters
		// create a new section containing these existing letters, if present
		// add new section to return list
		for (int row = 0; row < size; row++) {
			if (letters[row] != 0) {
				continue;
			}

			int left = -1, right = -1;

			for (int col = origin[1] - 1; col >= 0; col--) {
				char c = board[row + origin[0]][col];

				if (c == 0) {
					break;
				} else {
					left = col;
				}
			}

			for (int col = origin[1] + 1; col < BOARD_WIDTH; col++) {
				char c = board[row + origin[0]][col];

				if (c == 0) {
					break;
				} else {
					right = col;
				}
			}

			if (left > -1 || right > -1) {
				int[] newOrigin = new int[2];
				newOrigin[0] = row + origin[0];
				newOrigin[1] = origin[1];
				int newSize = 1;

				if (left > -1) {
					newOrigin[1] = left;
					newSize += origin[1] - left;
				}

				if (right > -1) {
					newSize += right - origin[1];
				}

				WordsSection newSection = getBoardSection(newSize, newOrigin, WordsOrientation.HORIZONTAL);
				horizontalSections.put(row, newSection);
			}

		}

		return horizontalSections;
	}
	
	public int getLetterBonus(int row, int col) {
		return letterBonusBoard[row][col];
	}
	
	public int getWordBonus(int row, int col) {
		return wordBonusBoard[row][col];
	}

	public boolean allTilesEmpty(WordsSection section) {
		for (char c : section.getLetters()) {
			if (c != 0) {
				return false;
			}
		}

		return true;
	}

	public boolean allTilesFull(WordsSection section) {
		for (char c : section.getLetters()) {
			if (c == 0) {
				return false;
			}
		}

		return true;
	}

	public int getBoardWidth() {
		return BOARD_WIDTH;
	}

	public int getBoardHeight() {
		return BOARD_HEIGHT;
	}

}
