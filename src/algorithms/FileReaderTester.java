package algorithms;

import java.io.*;
// https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html

// The purpose of this class is to read in a .txt file named below
// and output it to System.out in all caps.  
// If the named file cannot be found, output the proper message
// If there is an error during the file read, output the proper message
// If everything finished with no problems, output the proper message
// No matter what happened, make sure to close the BufferedReader object
public class FileReaderTester {
    public static void main(String [] args) {
    	//*** Member Variables***
    	BufferedReader bufferedReader = null;
		FileReader fileReader = null;
		
        // The name of the file to open.
        String fileName = "dialog.txt";
        // This will reference one line at a time
        String line = null;
		//*** End Member Variables***
		
		try {
    		// FileReader reads text files in the default encoding.
        	fileReader = new FileReader(fileName);
    		// Always wrap FileReader in BufferedReader.
    		//http://stackoverflow.com/questions/9648811/specific-difference-between-bufferedreader-and-filereader
        	bufferedReader = new BufferedReader(fileReader);
    
    		System.out.println("Starting to read the file...");
    		// BufferedReader allows us to read in file input line by line
    		// instead of FileReader's inefficient char by char 
    		while(true) {
    			line = bufferedReader.readLine();
    			randomChanceEvent(bufferedReader);
    			System.out.println(line.toUpperCase());
    		}   
		} catch (FileNotFoundException e) {
			// Print this if the file specified by fileName can't be located
			System.out.println("Unable to open file '" + fileName + "'");                
		} catch (IOException e) {
        	// Print this if there is an error while bufferedReader is reading the stream
        	// (simulated by randomChanceEvent())
        	System.out.println("Error reading file '" + fileName + "'");                  
		} catch (NullPointerException e) {
			// N/A
		} finally {
        	// Print this after File is done reading
			System.out.println("...Finished reading file");
			try {bufferedReader.close();} catch(Exception e) {}
		}
	}
    
    private static void randomChanceEvent(BufferedReader br) throws IOException {
    		int eventResult = (int)(Math.random()*100);
    		if(eventResult < 4) // simulates a random corruption that might cause the
    			br.close(); // stream to be corrupted (e.g. it is closed or disrupted somehow)
    }
}