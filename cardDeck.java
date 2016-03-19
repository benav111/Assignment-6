import java.io.*;
import java.util.*;

public class cardDeck
{
    //-------------Constant---------------------
    private final int DECKSIZE = 52;  //Number of cards in deck

    //-------------Static member----------------
    private static String[ ] cards= {" ", 
        "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "TS", "JS", "QS", "KS",
        "AH", "2H", "3H","4H", "5H","6H", "7H", "8H", "9H", "TH", "JH", "QH", "KH",
        "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "TD", "JD", "QD", "KD",
        "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "TC", "JC", "QC", "KC"};

    //-------------Instance members----------------
    private int nextCard;    //Next card to be dealt from the deck
    private int[ ] deck = new int[DECKSIZE + 1];   //Deck of cards
    private int loopCount;     //Counts the loop passes to shuffle deck

    //-------------Constructor----------------
    public cardDeck()
    {
        nextCard = 1;
    }
    
    //-------------Accessor and Mutator Methods----------------
    public int getDeckSize()
    {
        return DECKSIZE;  //How many cards in the deck
    }

    public int getNextCard()
    {
        return nextCard;
    }
	
	public void setNextCard(int value)
    {
		if (value > 0 && value <= deck.length)
		{
			nextCard = value;
		}
    }
	
    public int getPassCount()
    {
        return loopCount;
    }
	
	//-------------Other  Methods----------------
    public int shuffleDeck()
    {
        int index;
        int val;
        Random rand = new Random( );

        loopCount = 0; //Counts how many times through the while loop
        index = 1;
        for (int i = 0; i < deck.length; i++)
		{
			deck[i] = 0;  //Initialize array to 0's
		}

        while (index < deck.length)
        {   
            val = rand.nextInt(DECKSIZE) + 1;   //Generates a value 1 thru 52
            if (deck[val] == 0)
            {          					 //If the card place referred to in the deck is 
                deck[val] = index;   	 //unused/not intiated yet, then assign it a card
                index++;            	 //Get next card
            }
            loopCount++;
        } 
        nextCard = 1;           //Prepare to deal the first card
        return loopCount;
    }
	
    public String getOneCard (int index)
    {
        if (index > 0 && index <= deck.length && nextCard <= deck.length)
        {
            nextCard++;
            return cards[deck[index]];
        }
        else
        {
            return " ";         //Error
        }
    }
	
    public int getOneCard()
    {
		//Returns the nextCard in int form
        nextCard++;
        if (nextCard <= DECKSIZE)
	    {
		    return deck[nextCard];
	    }
        else
        {
            return 0;
        }
    }
	
    public int getCardsLeftInDeck()
    {
        return DECKSIZE - nextCard;
    }


    public String getCard(int index)
    {
        if (index > 0 && index <= DECKSIZE)
            return this.cards[index];
        else
            return " ";    //Error
    }
	
	public void printDeck()
	{
		for(int i = 1; i <= DECKSIZE; i++)
		{ 
			if (i%10 == 0)
			{
				System.out.println();
			}
			System.out.print(cards[deck[i]] + " ");
		}
	}
}
