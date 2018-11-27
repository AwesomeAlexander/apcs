package algorithms;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ArrayImporter {
	
	public static int[] readArrayFile(String fileName) {
		//Make sure that the file has been dragged into the PROJECT folder
		//(not the src folder) in Eclipse. You should be able to see the file
		//below the JRE System Library in the Package Explorer view.
		try {
			Scanner scanner = new Scanner(new File(fileName));
			ArrayList<Integer> nums = new ArrayList<Integer>();
			while (scanner.hasNextInt()) {	
				int n = scanner.nextInt();
				nums.add(n);
			}
			int[] numsArray = new int[nums.size()];
			for (int i = 0; i < numsArray.length; i++) {
				numsArray[i] = nums.get(i);
			}

			scanner.close();
			return numsArray;
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}
