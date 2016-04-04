import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class SystemTesting extends Thread {

	public static void main(String[] args) {

			String todo = "P 10 H S P 10 H S P 10 H S P 10 H S P 10 H S P 10 H S Q";

			//Dumb ai instructions to run: play game -> Hit -> Stay -> Quit
			
			ByteArrayOutputStream botfeed = new ByteArrayOutputStream();
			PrintStream b2 = new PrintStream(botfeed);
			//botAnalyzer robot = new botAnalyzer(botfeed);
			
			ByteArrayInputStream b1 = new ByteArrayInputStream(todo.getBytes());
			//ByteArrayInputStream b1 = new ByteArrayInputStream(robot.getOutput());
			
			//ByteArrayInputStream b1 = new ByteArrayInputStream(robot.getOutput().toByteArray());
			
			//Blackjack instantiation and setting
			BlackJackBetting bj = new BlackJackBetting();
			bj.shuffle();
			bj.botGame(b1, b2); // Diverts input and output of program to be fed by bot IO
			//robot.start();
			bj.playBJ();
			
			
	}

}

/*
 * 
 * Attempt at a sophisticated bot to respond to the blackjack program. Currently defunct.
 * 
class botAnalyzer extends Thread
{
	String line, choice;
	String commandStream = "P 10 S Q";
	ByteArrayOutputStream feed, commands;
	public botAnalyzer(ByteArrayOutputStream f)
	{
		feed = f;
		commands = new ByteArrayOutputStream();
	}
	
	public ByteArrayOutputStream getOutput()
	{
		return commands;
		//Gives a line for outputting generated commands for blackjack
		try{
			int bet = Integer.parseInt(commandStream);
			 BigInteger bigInt = BigInteger.valueOf(bet);   
			return bigInt.toByteArray();
		}catch(NumberFormatException e){
			return commandStream.getBytes();
		}
	}
	
	public void run()
	{

		while(this.isAlive())
		{
			String totalFeed[] = feed.toString().split("\n");
			line = totalFeed[totalFeed.length-1]; 
			switch(line){
				case "Press P to play a new hand or Q to quit.":
					System.out.println("SWITCH CASE LIVES!!!");
					choice = "P";
					break;
				case "How much would you like to bet? Please enter amount in whole dollars.":
					System.out.println("SWITCH CASE BETS!!!");
					choice = "10";
					break;
				case "Please press H to Hit, S to stay, D to Double Down or Q to quit.":
					choice="Q";
					break;
				case "Please press H to Hit, S to stay or Q to quit.":
					choice="Q";
					break;
				default:
					choice = " ";
			}
			try {
				commands.write(choice.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			choice = " ";
		
	
		}
	}
	
}*/
