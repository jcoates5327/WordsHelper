package words;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * Handles and stores board state.
 */
public class WordsBoard {
	private int BOARD_WIDTH;
	private int BOARD_HEIGHT;
	
	private char[][] board;	// 2x2 array containing all of the letters currently on the Board; 0 represents a blank tile: [y][x]
	
	public WordsBoard(char[][] board) {
		this.board = board;
		
		BOARD_HEIGHT = board.length;
		BOARD_WIDTH = board[0].length;
	}
	
	/*
	 * Returns a slice of the Board array.
	 * 
	 * @param size    	  the size of the desired Section
	 * @param origin 	  the coordinates of the Board the slice should start at: [row][col]
	 * @param orientation determines whether the Section is horizontal or vertical
	 * 
	 * @return 		  	  WordsSection object containing a valid section of the Board
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
	
	/* MODIFY TO RETURN TRUE/FALSE
	 * 
	 * Determine if a given Section contacts any existing letters on the board horizontally.
	 * 
	 * @param section the Section of the board to analyze
	 * 
	 * @return size 2 array containing indices of farthest letters Section is touching; [left, right]; -1 indicates no letters touched
	 */
	public boolean isSectionTouchingExistingLetterHorizontally(WordsSection section) {
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
				return true;
				// touchingIndex[0] = i;
			}
		}
		
		// look right
		for (int i = colEnd; i < BOARD_WIDTH; i++) {
			char c = board[row][i];
			if (c == 0) {
				break;
			} else {
				return true;
				// touchingIndex[1] = i;
			}
		}
		
		return false;
		// return touchingIndex;
	}
	
	/* MODIFY TO RETURN TRUE/FALSE
	 * 
	 * Determine if a given Section contacts any existing letters on the board vertically.
	 * 
	 * @param section the Section of the board to analyze
	 * 
	 * @return size 2 array containing indices of farthest letters Section is touching; [up, down]; -1 indicates no letters touched
	 */
	public boolean isSectionTouchingExistingLetterVertically(WordsSection section) {
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
				return true;
//				touchingIndex[0] = i;
			}
		}
		
		// look down
		for (int i = rowEnd; i < BOARD_HEIGHT; i++) {
			char c = board[i][col];
			if (c == 0) {
				break;
			} else {
				return true;
//				touchingIndex[1] = i;
			}
		}
		
		return false;
//		return touchingIndex;
	}
	
	public HashMap<Integer, WordsSection> getVerticalBorderSections(WordsSection section) {
		int[] origin = section.getOrigin();
		char[] letters = section.getLetters();
		int size = section.getSize();
		
		HashMap<Integer, WordsSection> verticalSections = new HashMap<>();
		
		// for each non-fixed letter in the section:
		//     check up and down for existing letters
		//	   create a new section containing these existing letters, if present
		//	   add new section to return list
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
		//     check left and right for existing letters
		//	   create a new section containing these existing letters, if present
		//	   add new section to return list
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
	
	public int getBoardWidth() {
		return BOARD_WIDTH;
	}
	
	public int getBoardHeight() {
		return BOARD_HEIGHT;
	}

}
