import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {
	public static void main(String args[]) {
		String[] nigga;
		nigga = getFileContents("stages1.txt");
		System.out.println(nigga[0]);

	}

	public static String[] getFileContents(String fileName) {
		String[] contents = null;
		int length = 0;
		try {
			// input
			String folderName = "assets/"; // if the file is contained in the same folder as the .class file, make this
											// equal to the empty string
			String resource = fileName;

			// this is the path within the jar file
			InputStream input = TileMap.class.getResourceAsStream(folderName + resource);
			if (input == null) {
				// this is how we load file within editor (eg eclipse)
				input = TileMap.class.getClassLoader().getResourceAsStream(resource);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(input));

			in.mark(999999999); // see api

			// count number of lines in file
			while (in.readLine() != null) {
				length++;
			}

			in.reset(); // rewind the reader to the start of file
			contents = new String[length]; // give size to contents array

			// read in contents of file and print to screen
			for (int i = 0; i < length; i++) {
				contents[i] = in.readLine();
			}
			in.close();
		} catch (Exception e) {
			System.out.println("File Input Error");
		}

		return contents;

	} // getFileContents
}