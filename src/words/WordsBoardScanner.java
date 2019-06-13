package words;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordsBoardScanner {
	
	private char[] r1 = { 0,   'a',  0,   0};
	private char[] r2 = {'t',   0,   0,  'r'};
	private char[] r3 = {'r',  'r', 'n', 't'};
	private char[] r4 = { 0,    0,   0,   0};
	
	List<Character> playerTiles;
	
	public WordsBoardScanner() {
		playerTiles = new ArrayList<>();		
		playerTiles.add('a');
		playerTiles.add('e');
		playerTiles.add('i');
		playerTiles.add('m');
		playerTiles.add('r');
		playerTiles.add('p');
		playerTiles.add('s');
		
	}
	
	public WordsBoardScanner(List<Character> playerTiles) {
		this.playerTiles = playerTiles;
	}
	
	public void scanHorizontalSections() throws IOException {
		// start with sections of size 2, horizontal
		char[][] board = {r1, r2, r3, r4};
		WordsBoard wb = new WordsBoard(board);
		WordsValidator wv = new WordsValidator(new WordsDictionary());
		WordsScorer ws = new WordsScorer();
				
		int boardWidth = wb.getBoardWidth();
		int boardHeight = wb.getBoardHeight();
		int maxSectionSize = boardWidth;
		int highestPointValue = 0;
		int[] highestScoringWordCoords;
		String highestScoringWord;
		
		List<String> words = new ArrayList<>();	// final list of valid words
		
		// iterate over each row of the board
		for (int i = 0; i < boardHeight; i++) {
			for (int sectionSize = 2; sectionSize <= maxSectionSize; sectionSize++) {
				int numSections = (boardWidth - sectionSize) + 1;
				
				// iterate over each horizontal section of $sectionSize in a row
				for (int j = 0; j < numSections; j++) {
					// build section of $sectionSize with origin at [i,j]
					// if section completely full or completely empty, skip to next section
					// if section is touching an existing letter horizontally, skip it until we process larger sections
					// determine if section is touching any letters vertically and include those vertical sections in validation/scoring
					// build valid list for section
					
					int size = sectionSize;
					int[] origin = {i, j};
					WordsSection section = wb.getBoardSection(size, origin, WordsOrientation.HORIZONTAL);
					
					// determine if the current section is touching any existing letters on the left or right
					// if so, skip for now as it can be checked along with larger sections
//					int[] touching = wb.isSectionTouchingExistingLetterHorizontally(section);			
//					if (touching[0] > -1 || touching[1] > -1) {
//						continue;
//					}
					
					if (wb.isSectionTouchingExistingLetterHorizontally(section)) {
						continue;
					}
					
					if (wb.allTilesEmpty(section) || wb.allTilesFull(section)) {
						continue;
					}
					
					// for each valid word in horizontal section:
					//     for each column in valid word:
					//         get vertical section (if present) for column
					//	       determine if vertical section contains valid word
					//  	   if any vertical section fails, discard current horizontal section
					
					for (String s : wv.buildListOfValidWordsForSection(playerTiles, section)) {
						HashMap<Integer, WordsSection> verticalSections = wb.getVerticalBorderSections(section);
						boolean valid = true;
						int verticalSectionPoints = 0;
						
						for (int k = 0; k < sectionSize; k++) {
							WordsSection verticalSec = verticalSections.get(k);
							if (verticalSec == null) {
								continue;
							}
							
							char existing = s.charAt(k);
							String vertWord = "";
							for (char c : verticalSec.getLetters()) {
								if (c == 0) {
									vertWord += existing;
								} else {
									vertWord += c;
								}
							}
							
							if (!wv.isValidWord(vertWord)) {
								valid = false;
							} else {
								verticalSectionPoints += ws.calculatePoints(vertWord);
							}
						}
						
						if (valid) {
							int points = ws.calculatePoints(s);
							points += verticalSectionPoints;
							
							if (points > highestPointValue) {
								highestPointValue = points;
								highestScoringWord = s;
								highestScoringWordCoords = section.getOrigin();
								
								System.out.println(highestScoringWord + ": [" + highestScoringWordCoords[0] + ", "
										+ highestScoringWordCoords[1] + "]");
								System.out.println(highestPointValue + " pts");
							}
							
							//words.add(s);
							//System.out.println(s + ": [" + section.getOrigin()[0] + ", " + section.getOrigin()[1] + "]");
						}
					}
				}
			}
		}
	}
	
	public void scanVerticalSections() throws IOException {
		// start with sections of size 2, horizontal
		char[][] board = {r1, r2, r3, r4};
		WordsBoard wb = new WordsBoard(board);
		WordsValidator wv = new WordsValidator(new WordsDictionary());
		WordsScorer ws = new WordsScorer();
		
		int boardWidth = wb.getBoardWidth();
		int boardHeight = wb.getBoardHeight();
		int maxSectionSize = boardHeight;
		int highestPointValue = 0;
		int[] highestScoringWordCoords;
		String highestScoringWord;
		
		List<String> words = new ArrayList<>();	// final list of valid words
		
		// iterate over each row of the board
		for (int i = 0; i < boardWidth; i++) {
			for (int sectionSize = 2; sectionSize <= maxSectionSize; sectionSize++) {
				int numSections = (boardHeight - sectionSize) + 1;
				
				// iterate over each horizontal section of $sectionSize in a row
				for (int j = 0; j < numSections; j++) {
					// build section of $sectionSize with origin at [i,j]
					// if section completely full or completely empty, skip to next section
					// if section is touching an existing letter horizontally, skip it until we process larger sections
					// determine if section is touching any letters vertically and include those vertical sections in validation/scoring
					// build valid list for section
					
					int size = sectionSize;
					int[] origin = {j, i};
					WordsSection section = wb.getBoardSection(size, origin, WordsOrientation.VERTICAL);
					
					// determine if the current section is touching any existing letters on the left or right
					// if so, skip for now as it can be checked along with larger sections
//					int[] touching = wb.isSectionTouchingExistingLetterHorizontally(section);			
//					if (touching[0] > -1 || touching[1] > -1) {
//						continue;
//					}
					
					if (wb.isSectionTouchingExistingLetterVertically(section)) {
						continue;
					}
					
					if (wb.allTilesEmpty(section) || wb.allTilesFull(section)) {
						continue;
					}
					
					// for each valid word in horizontal section:
					//     for each column in valid word:
					//         get vertical section (if present) for column
					//	       determine if vertical section contains valid word
					//  	   if any vertical section fails, discard current horizontal section
					
					for (String s : wv.buildListOfValidWordsForSection(playerTiles, section)) {
						HashMap<Integer, WordsSection> horizontalSections = wb.getHorizontalBorderSections(section);
						boolean valid = true;
						int horizontalSectionPoints = 0;
						
						List<String> hwords = new ArrayList<>();
						
						for (int k = 0; k < sectionSize; k++) {
							WordsSection horizontalSec = horizontalSections.get(k);
							if (horizontalSec == null) {
								continue;
							}
							
							char existing = s.charAt(k);
							String horizontalWord = "";
							for (char c : horizontalSec.getLetters()) {
								if (c == 0) {
									horizontalWord += existing;
								} else {
									horizontalWord += c;
								}
							}
							
							if (!wv.isValidWord(horizontalWord)) {
								valid = false;
							} else {
								hwords.add(horizontalWord);
								horizontalSectionPoints += ws.calculatePoints(horizontalWord);
							}
						}
						
						if (valid) {
							int points = ws.calculatePoints(s);
							points += horizontalSectionPoints;
							
							if (points > highestPointValue) {
								highestPointValue = points;
								highestScoringWord = s;
								highestScoringWordCoords = section.getOrigin();
																
								System.out.println(highestScoringWord + ": [" + highestScoringWordCoords[0] + ", "
										+ highestScoringWordCoords[1] + "]");
								System.out.println(highestPointValue + " pts");
								
//								for (String ss : hwords) {
//									System.out.print(ss + ", ");
//								}
//								System.out.println("\n");
								
//								for (char c : wdltrs) {
//									int pts = ws.getLetterValue(c);
//									System.out.print(c + "/" + pts + " ");
//								}
//								System.out.println();
							}
							
							//words.add(s);
							//System.out.println(s + ": [" + section.getOrigin()[0] + ", " + section.getOrigin()[1] + "]");
						}
					}
				}
			}
		}
	}

}
