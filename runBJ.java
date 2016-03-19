import java.io.*;

public class runBJ
{
	public static void main(String[] args) 
	{
		cardDeck deck = new cardDeck();
		deck.shuffleDeck();
		deck.printDeck();
	}
}