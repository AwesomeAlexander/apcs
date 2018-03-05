package algorithms;

import java.io.*;
import java.util.*;

public class ArithmeticTester {

	public static void main(String[] args) {
		System.out.println("Welcome to the Arithmetic Tester!  Some informative but not really relevant values: ");
		printValues();
		System.out.println();
		
		System.out.println("This amazing program lets you do many things");
		
		while(true) {
			System.out.println("\n(1) Multiply integers (2) Divide integers\n"
					+ "(3) Square root an integer (4) Read a number from a file"
					+ "\n(-1) Quit");
			System.out.print("Choice: ");

			Scanner stdin = new Scanner(System.in);
			int op = stdin.nextInt(); 
			
			switch(op) { 
				case -1:
					System.out.println("Goodbye...Program terminating!");
					stdin.close();
					System.exit(0);
				case 1: 
					multiply(stdin.nextInt(),stdin.nextInt());
					break;
				case 2: 
					divide(stdin.nextInt(),stdin.nextInt());
					break;
				case 3:
					squareRoot(stdin.nextInt(),stdin.nextInt());
					break;
				case 4: 
					readNumFromFile(stdin);
					break;
				default:
					System.out.println("Select between choices (1) - (4) or get yourself a new program, buddy");
			}	
			

		}
	}
	
	private static void printValues() {
		System.out.println("Integer.MAX_VALUE: " + Integer.MAX_VALUE);
		System.out.println("Integer.MIN_VALUE: " + Integer.MIN_VALUE);
		System.out.println("Integer.MAX_VALUE + 1: " + (Integer.MAX_VALUE + 1));
		System.out.println("Long.MAX_VALUE: " + Long.MAX_VALUE);
	}
	
	private static void multiply(Scanner stdin) {
		System.out.println("Enter an integer: ");
		int x = stdin.nextInt();
		System.out.println("Enter an integer to multiply by: ");
		int y = stdin.nextInt();
		
		if(okToMultiply(x,y))
			System.out.println(x + " * " + y + " = " + (x * y));
	}
	
	private static void divide(Scanner stdin) {
		System.out.println("Enter an integer: ");
		int x = stdin.nextInt();
		System.out.println("Enter an integer to divide by: ");
		int y = stdin.nextInt();
		
		if(okToDivide(x,y))
			System.out.println(x + " / " + y + " = " + (x / (double)y));
	}
	
	private static void squareRoot(Scanner stdin) {
		System.out.println("Enter an integer to take the square root of: ");
		int x = stdin.nextInt();
	
		if(radicandOK(x)) 
			System.out.println("Square root of " + x + " = " + Math.sqrt(x));
	}
	
	private static void readNumFromFile(Scanner stdin)  {
        FileReader reader = null;
        String fileName = "test.txt";
	}
	
	private static boolean okToMultiply(int x, int y) {
		return true;
	}
	
	private static boolean okToDivide(int x, int y) {
		return true;
	}
	
	private static boolean radicandOK(int x){
		return true;
	}
	
	private static boolean isNegative(int x) {
		return false;
	}
	
}