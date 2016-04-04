import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

public class UnitTesting {

	
	@Test 
	public void testSize() {
		//Tests for 52 cards in a new deck
		CardDeck deck = new CardDeck();
		assertEquals(deck.getDeckSize(), 52);
	}
	
	@Test
	public void testDraw() {
		//Tests that drawing card makes deck diminish
		CardDeck deck = new CardDeck();
		deck.shuffleDeck();
		deck.getCard();
		int remainingCards = deck.getCardsLeftInDeck();
		assertEquals(remainingCards, 51);
	}
	
	@Test
	public void testAce() {
		//Tests that first card of unshuffled deck is Ace of Spades
		CardDeck deck = new CardDeck();
	    String card = deck.getCard();
	    assertEquals(card, "AS");
	}
		
	@Test
	public void testUnique()
	{
			//Tests that a randomly drawn card is unique in that deck
			String card, card2;
			boolean unique = true;
			CardDeck deck = new CardDeck(); 
			deck.shuffleDeck();
			card = deck.getCard();
		    int remainingCards = deck.getCardsLeftInDeck();
		    while(remainingCards>0)
		    {
		    	card2 = deck.getCard();
		    	if(card2.equals(card))
		    	{
		    		unique = false;
		    	}
		    	remainingCards = deck.getCardsLeftInDeck();
		    }
		    assertTrue(unique);
	}
		
	@Test
	public void testShuffle() {
		// Checks that shuffling randomizes card order
		int i =0;
		boolean shuffled = false;
		while(i<10)
		{
				CardDeck deck = new CardDeck();
				deck.shuffleDeck();
				String card = deck.getCard();
	    	  	if(card.equals("AS")) {
	    	  		//Allows for ten tries to not have the first card of a shuffled deck to be ace of spades
	    	  		//NOTE: it is possible, though improbable that the ace of spades came up ten times in a shuffled deck
	    	  		i++;
	    	  	} else {
	    	  		shuffled = true;
	    	  		break;
	    	  	}
	    	  	assertTrue(shuffled);
		}
	}

}
