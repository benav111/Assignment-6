import java.util.*;

public class blackJack
{
	//-------------Constant---------------------
	final public int PUSH = 0;
	final public int PLAYERWINS = 1;
	final public int DEALERWINS = 2;
	final public int BLACKJACK = 21;

	//-------------Instance members----------------
	private int balance;
	private int wager;
	private int outcome;
	private int position;
	private int dealerFirstCard;
	private int dealerSecondCard;
	private int playerFirstCard;
	private int playerSecondCard;
	private int dealtCard;
	private int dealerFirstCardIndex;
	private int dealerSecondCardIndex;
	private int playerFirstCardIndex;
	private int playerSecondCardIndex;
	private int dealtCardIndex;
	private int playerTotal;
	private int dealerTotal;
	private int playerFirstCardValue;
	private int playerSecondCardValue;
	private int dealerFirstCardValue;
	private int dealerSecondCardValue;

	private cardDeck myDeck;

	//-------------Constructor----------------
	public blackJack()
	{
		balance = 100;
		wager = 0;
		outcome = 0;
		position = 0;
		myDeck = new cardDeck();
	}
	
	//-------------Accessor and Mutator Methods----------------
	public int getBalance()
	{
		return balance;
	}

	public void setBalance(int value)
	{
		if (value >= 0)
		{
			balance = value;
		}
	}

	public int getWager()
	{
		return wager;
	}
	
	public void setWager(int value)
	{
		if (value > 0)
		{
			wager = value;
		}
	}

	//-------------Other Methods----------------
	private void setCards(String[ ] hand)
	{
		hand[0] = myDeck.getCard(playerFirstCardIndex);
		hand[1] = myDeck.getCard(dealerFirstCardIndex);
		hand[3] = myDeck.getCard(playerSecondCardIndex);
		hand[4] = myDeck.getCard(dealerSecondCardIndex);
	}

	private void setWinnerAndPosition()
	{
		if (dealerFirstCard + dealerSecondCard == BLACKJACK)
		{
			this.outcome = DEALERWINS;
			return;
		}
		else if (playerFirstCard + playerSecondCard== BLACKJACK)
		{
			this.outcome = PLAYERWINS;
			return;
		}
		else if (playerTotal == dealerTotal)
		{
			this.outcome = PUSH;
			return;             
		}
		else if (dealerTotal > BLACKJACK && playerTotal <= BLACKJACK)
		{
			this.outcome = PLAYERWINS;
			return;           
		}
		else if (playerTotal > BLACKJACK)
		{
			this.outcome = DEALERWINS;
			return;
		}
	}

	public void getPlayerCard()
	{
		playerFirstCardIndex = myDeck.getOneCard();
		if (playerFirstCardIndex % 13 == 0 || playerFirstCardIndex % 13 == 1  || playerFirstCardIndex % 13 == 2 )
		{
			playerFirstCardValue = 10;
		}
		else 
		{
			playerFirstCardValue = playerFirstCardIndex % 13;
		}

	}

	public void getDealerCard()
	{
		dealerFirstCardIndex = myDeck.getOneCard();
		if (dealerFirstCardIndex % 13 == 0 || dealerFirstCardIndex % 13 == 1 || dealerFirstCardIndex % 13 == 2)
		{
			dealerFirstCardValue = 10;
		}
		else
		{
			dealerFirstCardValue = dealerFirstCardIndex % 13;
		}

	}
	public void getPlayerSecondCard()
	{
		playerSecondCardIndex = myDeck.getOneCard();
		if (playerSecondCardIndex % 13 == 0 || playerSecondCardIndex % 13 == 1 || playerSecondCardIndex % 13 == 2)
		{
			playerSecondCardValue = 10;
		}
		else
		{
			playerSecondCardValue = playerSecondCardIndex % 13;
		}
	}

	public void getDealerSecondCard()
	{
		dealerSecondCardIndex = myDeck.getOneCard();
		if (dealerSecondCardIndex % 13 == 0 || dealerSecondCardIndex % 13 == 1 || dealerSecondCardIndex % 13 == 2)
		{
			dealerSecondCardValue = 10;
		}
		else
		{
			dealerSecondCardValue = playerFirstCardIndex % 13;
		}
	}

	public void shuffle()
	{
		myDeck.shuffleDeck();
	}
	
	public int getCardsLeft()
	{
		return myDeck.getCardsLeftInDeck();
	}
	
	public void dealHand(String[] hand)
	{
		getPlayerCard();
		getDealerCard();
		getPlayerSecondCard();
		getDealerSecondCard();

		setWinnerAndPosition();
	}

	public void dealNextCard()
	{

	}
}