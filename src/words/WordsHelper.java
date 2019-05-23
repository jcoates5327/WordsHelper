package words;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordsHelper {
	private static char[] r1 = {0,   'a',  0,   0};
	private static char[] r2 = {0,    0,   0,   0};
	private static char[] r3 = {'r', 'a', 'n', 't'};
	private static char[] r4 = {0,    0,   0,   0};
	
	private static char[] tiles = {'a', 'r', 't', 't', 't', 't', 't'};

	public static void main(String[] args) {
		
//		char[][] board = {r1, r2, r3, r4};
//		WordsBoard wb = new WordsBoard(board);
//		char[] letters = {'a', 'e'};
//		int[] origin = {0, 0};
//		WordsSection section = new WordsSection(letters, origin, WordsOrientation.VERTICAL);
//		
//		int[] test = wb.isSectionTouchingExistingLetterVertically(section);
//		for (int j : test) {
//			System.out.println(j);
//		}
		
		try {
			scanSections();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		char[][] board = {r1, r2, r3, r4};
//		char[] tiles = {'a', 'e', 'r', 'm', 'p', 'l', 'o'};
//
//		WordsBoard wb = new WordsBoard(board);
//		
//		int[] origin = {0, 3};
//		WordsSection section = wb.getBoardSection(3, origin, WordsOrientation.VERTICAL);
//		
//		
//		WordsValidator wv;
//		try {
//			WordsDictionary wd = new WordsDictionary();
//			wv = new WordsValidator();
//			
//			List<Character> playerTiles = new ArrayList<>();		
//			playerTiles.add('a');
//			playerTiles.add('e');
//			playerTiles.add('i');
//			playerTiles.add('m');
//			playerTiles.add('p');
//			playerTiles.add('p');
//			playerTiles.add('s');
//			
//			for (String s : wv.buildListOfValidWordsForSection(playerTiles, section, new WordsDictionary())) {
//				System.out.println(s);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		

	}
	
	public static void scanSections() throws IOException {
		// start with sections of size 2, horizontal
		char[][] board = {r1, r2, r3, r4};
		WordsBoard wb = new WordsBoard(board);
		WordsValidator wv = new WordsValidator();
		
		List<Character> playerTiles = new ArrayList<>();		
		playerTiles.add('a');
		playerTiles.add('e');
		playerTiles.add('i');
		playerTiles.add('m');
		playerTiles.add('p');
		playerTiles.add('p');
		playerTiles.add('s');
		
		
		int boardWidth = 4;
		int sectionSize = 2;
		int numSections = (boardWidth - sectionSize) + 1;
		List<String> words = new ArrayList<>();
		
		for (int i = 0; i < boardWidth; i++) {
			for (int j = 0; j < numSections; j++) {
				int[] origin = {i, j};
				WordsSection section = wb.getBoardSection(sectionSize, origin, WordsOrientation.HORIZONTAL);
				if (!wb.allTilesEmpty(section)) {
					continue;
				}
				
				int[] touching = wb.isSectionTouchingExistingLetterHorizontally(section);
				int touchingLeft = touching[0];
				int touchingRight = touching[1];
				int[] newOrigin = origin;
				int newSize = sectionSize;
				
				if (touchingLeft > -1 || touchingRight > -1) {
					if (touchingLeft > -1) {
						newOrigin[1] = touchingLeft;
						newSize++;
					}
					
					if (touchingRight > -1) {
						newOrigin[1] = touchingRight;
						newSize++;
					}
					
					section = wb.getBoardSection(newSize, newOrigin, WordsOrientation.HORIZONTAL);
					if (section == null) {
						continue;
					}
					
					for (String s : wv.buildListOfValidWordsForSection(playerTiles, section, new WordsDictionary())) {
						words.add(s);
						System.out.println(s + ": [" + section.getOrigin()[0] + ", " + section.getOrigin()[1] + "]");
					}
				}
			}
		}
		
//		for (String s : words) {
//			System.out.println(s);
//		}
	}
	

}
