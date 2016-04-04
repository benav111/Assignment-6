import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class requestAdapter {
	private Scanner scanner;
	private PrintStream out;
	
	
	public requestAdapter(InputStream in, PrintStream out)
	{
		scanner = new Scanner(in);
		this.out = out;
	}
	public void setIn(InputStream in)
	{
		scanner = new Scanner(in);
	}
	public void setOut(PrintStream out)
	{
		this.out = out;
	}
	
	
	
	public char askChar(String message)
	{
		out.println(message);
		if(scanner.hasNext())
		{
			System.out.println("input recieved");
			return scanner.next().trim().charAt(0);
		}
		else
			return '\r';
	}
	
	public int askNum(String message)
	{
		out.println(message);
		while(!scanner.hasNext()){
			//Do Nothing
		}
		String input = scanner.next();
		int num;
		try{
			num = Integer.parseInt(input);
		} catch(NumberFormatException e) {
			num = -1;
		}
		return  num;
	}
	
}
