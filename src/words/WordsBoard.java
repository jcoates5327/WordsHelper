package words;


/*
 * Handles and stores board state.
 */
public class WordsBoard {
	private final int BOARD_WIDTH = 4;
	private final int BOARD_HEIGHT = 4;
	
	private char[][] board;	// 2x2 array containing all of the letters currently on the Board; 0 represents a blank tile: [y][x]
	
	public WordsBoard(char[][] board) {
		this.board = board;
	
	}
	
	/*
	 * Returns a slice of the Board array.
	 * 
	 * @param size    	  the size of the desired Section
	 * @param origin 	  the coordinates of the Board the slice should start at: [row][col]
	 * @param orientation determines whether the Section is horizontal or vertical: 'h' / 'v'
	 * 
	 * @return 		  	  WordsSection object containing a valid section of the Board
	 */
	public WordsSection getBoardSection(int size, int[] origin, WordsOrientation orientation) {
		if (orientation == WordsOrientation.HORIZONTAL) {
			// validate section doesn't run off the edge of the board
			if (origin[1] + size > BOARD_WIDTH) {
				return null;
			}
			
			// determine if section will touch an existing word on the board
			
			
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
	 * Determine if a given Section contacts any existing letters on the board horizontally.
	 * 
	 * @param section the Section of the board to analyze
	 * 
	 * @return size 2 array containing indices of farthest letters Section is touching; [left, right]; -1 indicates no letters touched
	 */
	public int[] isSectionTouchingExistingLetterHorizontally(WordsSection section) {
		int[] touchingIndex = {-1, -1};
		int[] origin = section.getOrigin();
		
		int row = origin[0];
		int colStart = origin[1] - 1;
		int colEnd = origin[1] + section.getSize();
		
		// look left
		for (int i = colStart; i >= 0; i--) {
			char c = board[row][i];
			if (c == 0) {
				break;
			} else {
				touchingIndex[0] = i;
			}
		}
		
		// look right
		for (int i = colEnd; i < BOARD_WIDTH; i++) {
			char c = board[row][i];
			if (c == 0) {
				break;
			} else {
				touchingIndex[1] = i;
			}
		}
		
		return touchingIndex;
	}
	
	/*
	 * Determine if a given Section contacts any existing letters on the board vertically.
	 * 
	 * @param section the Section of the board to analyze
	 * 
	 * @return size 2 array containing indices of farthest letters Section is touching; [up, down]; -1 indicates no letters touched
	 */
	public int[] isSectionTouchingExistingLetterVertically(WordsSection section) {
		int[] touchingIndex = {-1, -1};
		int[] origin = section.getOrigin();
		
		int col = origin[1];
		int rowStart = origin[0] - 1;
		int rowEnd = origin[0] + section.getSize();
		
		// look up
		for (int i = rowStart; i >= 0; i--) {
			char c = board[i][col];
			if (c == 0) {
				break;
			} else {
				touchingIndex[0] = i;
			}
		}
		
		// look down
		for (int i = rowEnd; i < BOARD_HEIGHT; i++) {
			char c = board[i][col];
			if (c == 0) {
				break;
			} else {
				touchingIndex[1] = i;
			}
		}
		
		return touchingIndex;
	}
	
	public boolean allTilesEmpty(WordsSection section) {
		for (char c : section.getLetters()) {
			if (c != 0) {
				return false;
			}
		}
		
		return true;
	}
	
	public void printBoard() {
		for (char[] cols : board) {
			for (char c : cols) {
				if (c == 0) {
					System.out.print(" * ");
				} else {
					System.out.print(" " + c + " ");
				}
			}
			System.out.println();
		}
	}

}
