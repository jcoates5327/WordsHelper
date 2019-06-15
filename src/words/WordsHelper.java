package words;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordsHelper {

	public static void main(String[] args) {
		
		List<Character> tiles = new ArrayList<>();
		tiles.add('o');
		tiles.add('o');
		tiles.add('i');
		tiles.add('a');
		tiles.add('r');
		tiles.add('r');
		tiles.add('u');

		WordsBoardScanner sc = new WordsBoardScanner(tiles);

		try {
			System.out.println(sc.scanHorizontalSections());
			System.out.println(sc.scanVerticalSections());
			
//			BufferedWriter wr = new BufferedWriter(new FileWriter("blank board.txt"));
//			for (int i = 0; i < 15; i++) {
//				wr.write("private char[] r" + i + " = { ");
//				
//				for (int j = 0; j < 14; j++) {
//					wr.write("0,   ");
//				}
//				
//				wr.write("0 };");
//				wr.newLine();
//			}
//			wr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
