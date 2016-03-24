import java.util.*;

public class blackJackBetting
{
	//--------------------------------------Constants--------------------------------------
	final private int PUSH = 0;
	final private int PLAYERWINS = 1;
	final private int DEALERWINS = 2;
	final private int BLACKJACK = 21;

	//--------------------------------------Instance Members-------------------------------
	private int playerIndex;	
	private int dealerIndex;
	private int balance;
	private int wager;
	boolean hiddenCard;
	boolean stay;
	
	private cardDeck myDeck;
	private String[] dealerHand;
	private String[] playerHand;
	Scanner reader;

	//--------------------------------------Constructor-------------------------------------
	public blackJackBetting()
	{
		playerIndex = 0;
		dealerIndex = 0;
		hiddenCard = true;
		stay = false;
		myDeck = new cardDeck();
		dealerHand = new String[11];	//Initialized to 11 cards at max because that's 
		playerHand = new String[11];	//the most cards possible for a player to get in any given hand
		reader = new Scanner(System.in);
		balance = 100;
		wager = 0;
	}
	
	//------------------------------Accessor and Mutator Methods----------------------------
	public int getWager()
	{
		return this.wager;
	}
	
	public void setWager(int amount)
	{
		this.wager = amount;
	}
	
	public int getBalance()
	{
		return this.balance;
	}
	
	public void setBalance(int amount)
	{
		this.balance = amount;
	}
	
	//--------------------------------------Print Methods----------------------------------
	public void printDeck()
	{
		//Used to test the shuffle method
		myDeck.printDeck();
	}
	
	public void printPlayerCards(int numCards)
	{
		//Prints players cards 
		for(int i =0; i <= numCards; i++)
		{
			System.out.print(playerHand[i] + " ");		
		}
	}
	
	public void printDealerCards(int numCards)
	{
		//Prints dealers cards 
		for(int i =0; i <= numCards; i++)
		{
			if(hiddenCard && i == 1)
			{
				System.out.println("*Hidden*");
			}
			else
			{
				System.out.print(dealerHand[i] + " ");
			}			
		}
	}
	
	//--------------------------------------Hand Totals------------------------------------
	public int playerTotal()
	{
		//Returns the total that the player's hand is worth
		int total = 0;
		int card;
		for(int i = 0; i < playerIndex; i++)
		{
			total += getValue(playerHand[i]);
		}
		return total;
	}
	
	public int dealerTotal()
	{
		//Returns the total that the dealer's hand is worth
		int total = 0;
		for(int i = 0; i < dealerIndex; i++)
		{
			total += getValue(dealerHand[i]);
		}
		return total;
	}
	
	
	//--------------------------------------Other Methods----------------------------------
			
	public boolean checkHands()
	{		
		//Check the hands to see who the winner is, if they push, or if the play should continue
		boolean keepPlaying = false;
		
		if ((getValue(dealerHand[0]) == 10 && getValue(dealerHand[1]) == 1) ||
			(getValue(dealerHand[0]) == 1 && getValue(dealerHand[1]) == 10))
		{
			//Dealer has automatic Blackjack
			
			System.out.println(dealerHand[1]);
			System.out.println("\n\nDealer has Blackjack. You lost :(");
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + BLACKJACK);
			setBalance(balance - wager);
			userLimitedOption();
		}
		else if(dealerTotal() == BLACKJACK)	
		{
			//Dealer has blackjack
			
			System.out.println("\n\nDealer has Blackjack. You lost :(");
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + BLACKJACK);
			setBalance(balance - wager);
			userLimitedOption();
		}
		else if ((getValue(playerHand[0]) == 10 && getValue(playerHand[1]) == 1) ||
				(getValue(playerHand[0]) == 1 && getValue(playerHand[1]) == 10)) 
				
		{
			//Player has automatic blackjack
			
			System.out.println("*Hidden*\nYou got Blackjack! Winner winner chicken dinner!");
			System.out.println("You had: " + BLACKJACK + " and the Dealer had " + dealerTotal());
			setBalance(balance + (int)(1.5 * wager));
			userLimitedOption();
		}
		else if((playerTotal() == BLACKJACK))	
		{
			//Player has blackjack
			
			System.out.println("\nYou got Blackjack! Winner winner chicken dinner!");
			System.out.println("You had: " + BLACKJACK + " and the Dealer had " + dealerTotal());
			setBalance(balance + wager);
			userLimitedOption();
			
		}
		else if (dealerTotal() >= 17 && playerTotal() == dealerTotal() && hiddenCard == false)	
		{
			//Player and dealer push
			
			System.out.println("\n\nPush.You tied.");   
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + dealerTotal());
			userLimitedOption();
		}
		else if (dealerTotal() > BLACKJACK && playerTotal() <= BLACKJACK)	
		{
			//Dealer busts
			
			System.out.println("\n\nDealer busts! Winner winner chicken dinner!");  
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + dealerTotal());
			setBalance(balance + wager);
			userLimitedOption();
		}
		else if (playerTotal() > BLACKJACK)		
		{
			//Player busts
			
			System.out.println("\nSorry you busted! You lost :(");
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + dealerTotal());
			setBalance(balance - wager);
			userLimitedOption();
		}
		else if ((dealerTotal() > playerTotal()) && 
				(dealerTotal() <= BLACKJACK && stay == true) && 
				(hiddenCard == false))	
		{
			//Dealer beats player after player chooses to stay
			
			System.out.println("\n\nDealer beat you. You lost :(");       
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + dealerTotal());
			setBalance(balance - wager);
			userLimitedOption();			
		}
		else if((playerTotal() <= BLACKJACK && playerTotal() > dealerTotal()) && 
				(dealerTotal() < BLACKJACK && stay == true)	&& 
				(hiddenCard == false && dealerTotal() >=17))	
		{
			//Player wins after player chooses to stay
			
			System.out.println("\n\nCongrats. You beat the dealer! Winner winner chicken dinner!");
			System.out.println("You had: " + playerTotal() + " and the Dealer had " + dealerTotal());
			setBalance(balance + wager);
			userLimitedOption();
		}
		else
		{
			keepPlaying = true;		//Continue playing game
		}
		return keepPlaying;
	}

	public int getValue(String card)
	{
		//Returns the number value of the card to be used in calculating the hand total
		int cardValue = myDeck.getCardValue(card);
		if (cardValue % 13 == 0 || cardValue % 13 == 11 || cardValue % 13 == 12)
		{
			//If the card is a Jack, Queen or King, set value to 10
			cardValue = 10;
		}
		else
		{
			//Otherwise set value to card number (Ace-10)
			cardValue = cardValue % 13;
		}
		return cardValue;

	}
	
	public void shuffle()
	{
		//Shuffles 15 times to make sure the deck is mixed up well
		for (int i = 0; i < 15; i++)
		{
			myDeck.shuffleDeck();
		}
	}
		
	public int getCardsLeft()
	{
		//Returns cards left in deck
		return myDeck.getCardsLeftInDeck();
	}
		
	public void dealerPlay()
	{
		//Dealer's turn to play after player either busts, gets Blackjack or stays
		hiddenCard = false;			//Show dealer's other card now
		System.out.println("\nYour hand:		Dealer's Hand:");
		printPlayerCards(playerIndex);
		System.out.print("		");
		printDealerCards(dealerIndex);
		System.out.print("\b\b");
		while(dealerTotal() <17 || checkHands()) //Play until the dealer gets 17 or greater
		{
			dealerHand[dealerIndex] = myDeck.getCard();				//Draws dealer's next card
			System.out.print(dealerHand[dealerIndex] + " ");		//Prints dealer's next card
			dealerIndex++;
		}
		checkHands();			//Check for winner
		userLimitedOption();	//Ask to play again or quit
	}
	
	public void hit()
	{
		System.out.println("\nYour hand:		Dealer's Hand:");	//Set up display labels of hands
		playerHand[playerIndex] = myDeck.getCard(); 			//Draws another card
		printPlayerCards(playerIndex);							//Prints the card along with previous cards
		System.out.print("		");
		printDealerCards(dealerIndex);							//Prints dealer's cards
		playerIndex++;
		if(checkHands())										//Player continues
			userOption();
	}
	
	public void hitDD()											//Double down method
	{
		System.out.println("\nYour hand:		Dealer's Hand:");	//Set up display labels of hands
		playerHand[playerIndex] = myDeck.getCard(); 			//Draws another card
		printPlayerCards(playerIndex);							//Prints the card along with previous cards
		System.out.print("		");
		printDealerCards(dealerIndex);							//Prints dealer's cards
		playerIndex++;
		stay = true;
		if(checkHands())										//Dealer continues
			dealerPlay();
	}
	
	public void initializeHands()
	{
		//Just initializing both hands 
		for (int i = 0; i < 11; i++)
		{
			playerHand[i] = " ";
			dealerHand[i] = " ";
		}
	}
	

	public void initialHand()
	{
		//Sets up the initial hands and checks if 
		//the dealer or player get an automatic Blackjack
		
		if(getCardsLeft() > 10)	//If deck has at least 10 cards continue...
		{
			System.out.println("\nYour hand:		Dealer's Hand:");	//Set up display labels of hands
			playerHand[playerIndex] = myDeck.getCard(); 			//Draws player's first card
			System.out.print(playerHand[playerIndex] + " ");		//Prints player's first card
			playerIndex++;
			playerHand[playerIndex] = myDeck.getCard();				//Draws player's second card
			System.out.print(playerHand[playerIndex]+ "			");			//Prints player's second card
			playerIndex++;
					
			dealerHand[dealerIndex] = myDeck.getCard();				//Draws dealer's first card
			System.out.print(dealerHand[dealerIndex] + " ");		//Prints dealer's first card
			dealerIndex++;
			dealerHand[dealerIndex] = myDeck.getCard();				//Draws dealer's second card
			dealerIndex++;
			if(getValue(dealerHand[0]) == 1)
			{
				//Dealer is showing an Ace so offer insurance
				System.out.println("*Hidden*");	
				boolean goodInput = false;
				while(!goodInput)
				{
					System.out.println("Would you like to buy insurance?");
					System.out.println("This means you can take half your wager now regardless"
										+"\nof if I have Blackjack under the hidden card.");
					System.out.println("Type Y for Yes or N for No."
										+ " If you type no, we will continue playing.");
					try
					{
						char input = reader.next().trim().charAt(0);	
						if(input == 'Y' || input == 'y')			//Player wants insurance
						{
							//Add half of what they bet but typecast to an int so it's a whole amount
							setBalance(balance - (int)(wager/2));	
							System.out.println("You chose yes.");
							goodInput = true;		//Break out of while loop
							userLimitedOption();  	//Start a new game or quit
						}
						else if(input == 'N' || input == 'n')	//Continue playing 
						{
							goodInput = true;		//Break out of while loop
							userOption();
						}
						else 			//Invalid option entered so continue
						{
							System.out.println("Error. Please try again.");
						}
					}
					catch (InputMismatchException e) 
					{
						System.out.println("Invalid value!");
						reader.next(); 					//Consumes the invalid token
					}
				}
			
			}
			else if(checkHands())						//If there is not a decided winner yet, keep playing
			{
				System.out.println("*Hidden*");			//Show that the dealer's second card is hidden
				userOption();							//Ask the player if they want to Hit, Stand or Quit
			}
		}
		else		//Deck has less than 10 cards so reshuffle
		{
			System.out.println("Hold on.. Almost out of cards! Reshuffling."); 	
			shuffle();
			System.out.println("Deck has been shuffled!\n");
			initialHand();	//Continue and deal hand
		}
	}
	
	public void getBet()
	{
		//Gets bet from player in whole dollar amount
		System.out.println("How much would you like to bet?"
						+ " Please enter amount in whole dollars.");
		boolean goodInput = false;
		while (!goodInput)	//Test to make sure they entered a valid amount
		{
			try
			{
				int i = reader.nextInt();
				if(i <= balance && i > 0)
				{
					setWager(i);				
					goodInput = true;
				}
				else
				{
					//They entered an amount greater than their balance
					System.out.println("Error. please enter an "
					+ "amount less than or equal to your balance.");
				}
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Invalid value!");
				reader.next(); 	//Consumes the invalid token
				getBet();		//Calls this function again if error occurs
			}
		}
	}
	
	public void userOption()
	{
		//Called after dealer has dealt cards and it is the player's turn. 
		//Asks if the user wants to hit, stay, double down or quit.
		System.out.println("\nYour total is " + playerTotal() + ".");
		if(playerIndex == 2) 		//For first two cards only offer double down
		{	
			System.out.println("Please press H to Hit, S to stay, D to Double Down or Q to quit.");
			char input;
			try
			{
				input = reader.next().trim().charAt(0);	
				switch (input) 
				{
					case 'H':		//Hit 
					case 'h':
						hit();
						break;
					case 'S':		//Stay
					case 's':
						System.out.println("You chose to stay at " + playerTotal() + ".");
						stay = true;
						dealerPlay();	//Dealer's turn to draw cards
						break;
					case 'D':		//Double down 
					case 'd':
						if((balance - 2*wager) < 0)		//Not enough money to double down so just hit
						{
							System.out.println("Error, you don't have enough to double down."
												+ " So I will just continue and give you a card");
										
						}
						else
						{
							setWager(2*wager);	//Has enough money to double down
						}
						hitDD();			//Since they are doubling down they only get one card 
						break;
					case 'Q':		//Quit
					case 'q':
						System.out.println("Thanks for playing. Goodbye!");
						System.exit(0);
						break;
					default:		//Error. User entered something other than 
									//the options that they were given so start over.
						System.out.println("Error. Please try again");
						userOption();
						break;
				}	
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Invalid value!");
				reader.next(); // this consumes the invalid token
				userOption();
			}	
		}
		else		//Player has more than 2 cards so double down not allowed
		{
			System.out.println("Please press H to Hit, S to stay, or Q to quit.");
			char input;
			try
			{
				input = reader.next().trim().charAt(0);	
				switch (input) 
				{
					case 'H':		//Hit 
					case 'h':
						hit();
						break;
					case 'S':		//Stay
					case 's':
						System.out.println("You chose to stay at " + playerTotal() + ".");
						stay = true;
						dealerPlay();	//Dealer's turn to draw cards
						break;
					case 'Q':		//Quit
					case 'q':
						System.out.println("Thanks for playing. Goodbye!");
						System.exit(0);
						break;
					default:		//Error. User entered something other than 
									//the options that they were given so start over.
						System.out.println("Error. Please try again");
						userOption();
						break;
				}	
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Invalid value!");
				reader.next(); // this consumes the invalid token
				userOption();
			}			
		}
	}
	
	
	public void userLimitedOption()
	{
		//Function that only give the user the option to deal a new hand or quit
		playerIndex = 0;
		dealerIndex = 0;
		hiddenCard = true;
		if(getBalance() == 0)		//Quits if the player's balance drops to $0
		{
			System.out.println("You have no more money left. Game over!");
			System.exit(0);
		}
		else						//Otherwise continue
		{
			System.out.println("Balance: $" + getBalance() + ".");
			System.out.println("\nPress P to play a new hand or Q to quit.");
			char input;
			try
			{
				input = reader.next().trim().charAt(0);	
				switch (input) 
				{
					case 'P':		//Play a new hand
					case 'p':
						getBet();
						initializeHands();	//Initialize player and dealer hands
						initialHand();		//Deal first hand
						break;
					case 'Q':		//Quit
					case 'q':
						System.out.println("Thanks for playing. Goodbye!");
						System.exit(0);
						break;
					default:		//Error. User entered something other than 
									//the options that they were given so start over.
						System.out.println("Error. Please try again");
						userLimitedOption();
						break;
				}
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Invalid value!");
				reader.next(); 		 //Consumes the invalid token
				userLimitedOption(); //Restarts this function if this error is called
			}
		}
	}
	
	public void playBJ()
	{
		//Intro to the game and then asks the user if they want to deal or quit
		System.out.println("\nWelcome to the game of Blackjack!");
		System.out.println("In this game, Aces count as 1's only, not 11, except for\n"
							+ "the first hand (automatic Blackjacks).");
		System.out.println("May the odds be forever in your favor :)\n");
		userLimitedOption();
	}
	
	//-------------------------------Testing Functions-------------------------------------
	
	public void testValueFunction()
	{
		//Used to test the getValue() function
		for (int i = 1; i <= myDeck.getDeckSize(); i++)
		{
			if (i % 9 == 0)
			{
				System.out.println();
			}
			System.out.print(getValue(myDeck.getCard()) + " ");
		}
		System.out.println();
	}
}