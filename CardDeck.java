import java.util.*;

public class CardDeck
{
    //--------------------------------------Constants--------------------------------------
    private final int DECKSIZE = 52;  //Number of cards in deck

    //------------------------------------Static Members-----------------------------------
	
	//Each of the cards represented by their value followed by their suite
	//So a 'QH' means Queen of Hearts
	
    private static String[ ] cards= { 
        "AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "TS", "JS", "QS", "KS",
        "AH", "2H", "3H","4H", "5H","6H", "7H", "8H", "9H", "TH", "JH", "QH", "KH",
        "AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "TD", "JD", "QD", "KD",
        "AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "TC", "JC", "QC", "KC"};

   //--------------------------------------Instance Members-------------------------------
   
    private int nextCard;    //Next card to be dealt from the deck
    private int[ ] deck = new int[DECKSIZE];   //Deck of cards

	//--------------------------------------Constructor------------------------------------
    public CardDeck()
    {
        nextCard = 0;		//Next card to be dealt is the first card in the deck
    }
    
    //------------------------------Accessor and Mutator Methods----------------------------
    public int getDeckSize()
    {
        return DECKSIZE;  //How many cards in the deck
    }

   	public void setNextCard(int value)
    {
		if (value > 0 && value < deck.length)
		{
			nextCard = value;
		}
    }
	
	//--------------------------------------Print Methods----------------------------------
	
	public void printDeck()
	{
		//Prints the deck of cards 
		for(int i = 0; i < DECKSIZE; i++)
		{ 
			if (i%10 == 0)
			{
				System.out.println();
			}
			System.out.print(cards[deck[i]] + " ");
		}
		System.out.println();
	}
	
	
	//--------------------------------------Other Methods-----------------------------------
   
   public void shuffleDeck()
    {
		//Shuffles deck of cards
        int index = 0;
        int val;
        Random rand = new Random( );

        for (int i = 0; i < deck.length; i++)
		{
			deck[i] = -1;  //Initialize array to -1's
		}

        while (index < deck.length)
        {   
            val = rand.nextInt(DECKSIZE);   //Generates a value 0 thru 51
            if (deck[val] == -1)
            {          					 //If the card place referred to in the deck is 
                deck[val] = index;   	 //unused/not intiated yet, then assign it a card
                index++;            	 //Get next card
            }
        } 
        nextCard = 0;           //Prepare to deal the first card
    }
	
    public String getCard ()
    {
		//Returns the nextCard in the deck 
		String card = "";
        if (nextCard < DECKSIZE)
        {
            card = cards[deck[nextCard]];
			nextCard++;
			return card;
        }
        else
        {
			return "Error. Almost out of cards.";
        }
    }
	
    public int getCardValue(String checkCard)
    {
		//Returns the index+1 of the card passed through for value calculation
		for (int i = 0; i < 52; i++)
		{
			if(cards[i].equals(checkCard))
				return i+1;
		}
		return -1;
    }
	
    public int getCardsLeftInDeck()
    {
        return DECKSIZE - nextCard;
    }


    public String getCard(int index)
    {
		//Gets a certain card based on given index
        if (index >= 0 && index < DECKSIZE)
            return cards[index];
        else
            return " ";    //Error
    }
    
    //Override
	public String toString() 
	{
		//Overriding toString method
		return "CardDeck [DECKSIZE=" + DECKSIZE + ", nextCard=" + nextCard + ", "
				+ (deck != null ? "deck=" + Arrays.toString(deck) : "") + "]";
	}
}
