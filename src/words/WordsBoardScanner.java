package words;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WordsBoardScanner {

	List<Character> playerTiles;

	public WordsBoardScanner(List<Character> playerTiles) {
		this.playerTiles = playerTiles;
	}

	public void scanHorizontalSections() throws IOException {
		// start with sections of size 2, horizontal
		WordsBoard wb = new WordsBoard();
		WordsValidator wv = new WordsValidator(new WordsDictionary());
		WordsScorer ws = new WordsScorer();

		int boardWidth = wb.getBoardWidth();
		int boardHeight = wb.getBoardHeight();
		int maxSectionSize = boardWidth;
		int highestPointValue = 0;
		int[] highestScoringWordCoords = {0, 0};
		String highestScoringWord = "";

		// iterate over each row of the board
		for (int i = 0; i < boardHeight; i++) {
			for (int sectionSize = 2; sectionSize <= maxSectionSize; sectionSize++) {
				int numSections = (boardWidth - sectionSize) + 1;

				// iterate over each horizontal section of $sectionSize in a row
				for (int j = 0; j < numSections; j++) {
					// build section of $sectionSize with origin at [i,j]
					// if section completely full or completely empty, skip to next section
					// if section is touching an existing letter horizontally, skip it until we
					// process larger sections
					// determine if section is touching any letters vertically and include those
					// vertical sections in validation/scoring
					// build valid list for section

					int size = sectionSize;
					int[] origin = { i, j };
					WordsSection section = wb.getBoardSection(size, origin, WordsOrientation.HORIZONTAL);

					// determine if the current section is touching any existing letters on the left
					// or right
					// if so, skip for now as it can be checked along with larger sections
					if (wb.isSectionTouchingExistingLetterHorizontally(section)) {
						continue;
					}

					if (wb.allTilesFull(section)) {
						continue;
					}

					HashMap<Integer, WordsSection> verticalSections = wb.getVerticalBorderSections(section);

					if (wb.allTilesEmpty(section) && verticalSections.size() <= 0) {
						continue;
					}

					// for each valid word in horizontal section:
					// for each column in valid word:
					// get vertical section (if present) for column
					// determine if vertical section contains valid word
					// if any vertical section fails, discard current horizontal section

					for (String s : wv.buildListOfValidWordsForSection(playerTiles, section)) {

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
								verticalSectionPoints += ws.calculatePoints(vertWord, verticalSec, wb, WordsOrientation.VERTICAL);
							}
						}

						if (valid) {
							int points = ws.calculatePoints(s, section, wb, WordsOrientation.HORIZONTAL);
							points += verticalSectionPoints;
							
							// add 35 bonus points for using all tiles
							if (section.getSize() >= 7 && wb.allTilesEmpty(section)) {
								points += 35;
							}

							if (points > highestPointValue) {
								highestPointValue = points;
								highestScoringWord = s;
								highestScoringWordCoords = section.getOrigin();

//								System.out.println("(h) " + highestScoringWord + ": [" + highestScoringWordCoords[0]
//										+ ", " + highestScoringWordCoords[1] + "]");
//								System.out.println(highestPointValue + " pts");
							}
						}
					}
				}
			}
		}
		
		System.out.println("(h) " + highestScoringWord + ": [" + highestScoringWordCoords[0]
				+ ", " + highestScoringWordCoords[1] + "]");
		System.out.println(highestPointValue + " pts");
	}

	public void scanVerticalSections() throws IOException {
		// start with sections of size 2, horizontal
		WordsBoard wb = new WordsBoard();
		WordsValidator wv = new WordsValidator(new WordsDictionary());
		WordsScorer ws = new WordsScorer();

		int boardWidth = wb.getBoardWidth();
		int boardHeight = wb.getBoardHeight();
		int maxSectionSize = boardHeight;
		int highestPointValue = 0;
		int[] highestScoringWordCoords = {0, 0};
		String highestScoringWord = "";

		// iterate over each row of the board
		for (int i = 0; i < boardWidth; i++) {
			for (int sectionSize = 2; sectionSize <= maxSectionSize; sectionSize++) {
				int numSections = (boardHeight - sectionSize) + 1;

				// iterate over each horizontal section of $sectionSize in a row
				for (int j = 0; j < numSections; j++) {
					// build section of $sectionSize with origin at [i,j]
					// if section completely full or completely empty, skip to next section
					// if section is touching an existing letter horizontally, skip it until we
					// process larger sections
					// determine if section is touching any letters vertically and include those
					// vertical sections in validation/scoring
					// build valid list for section

					int size = sectionSize;
					int[] origin = { j, i };
					WordsSection section = wb.getBoardSection(size, origin, WordsOrientation.VERTICAL);

					// determine if the current section is touching any existing letters on the left
					// or right
					// if so, skip for now as it can be checked along with larger sections
					if (wb.isSectionTouchingExistingLetterVertically(section)) {
						continue;
					}

					if (wb.allTilesFull(section)) {
						continue;
					}

					HashMap<Integer, WordsSection> horizontalSections = wb.getHorizontalBorderSections(section);

					if (wb.allTilesEmpty(section) && horizontalSections.size() <= 0) {
						continue;
					}

					// for each valid word in horizontal section:
					// for each column in valid word:
					// get vertical section (if present) for column
					// determine if vertical section contains valid word
					// if any vertical section fails, discard current horizontal section

					for (String s : wv.buildListOfValidWordsForSection(playerTiles, section)) {
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
								horizontalSectionPoints += ws.calculatePoints(horizontalWord, horizontalSec, wb, WordsOrientation.HORIZONTAL);
							}
						}

						if (valid) {
							int points = ws.calculatePoints(s, section, wb, WordsOrientation.VERTICAL);
							points += horizontalSectionPoints;
							
							// add 35 bonus points for using all tiles
							if (section.getSize() >= 7 && wb.allTilesEmpty(section)) {
								points += 35;
							}

							if (points > highestPointValue) {
								highestPointValue = points;
								highestScoringWord = s;
								highestScoringWordCoords = section.getOrigin();

//								System.out.println("(v) " + highestScoringWord + ": [" + highestScoringWordCoords[0]
//										+ ", " + highestScoringWordCoords[1] + "]");
//								System.out.println(highestPointValue + " pts");
							}
						}
					}
				}
			}
		}
		
		System.out.println("(v) " + highestScoringWord + ": [" + highestScoringWordCoords[0]
				+ ", " + highestScoringWordCoords[1] + "]");
		System.out.println(highestPointValue + " pts");
	}

}
