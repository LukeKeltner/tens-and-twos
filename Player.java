import java.util.Random;
import java.util.*; 

public class Player
{
	String name;
	ArrayList<Integer> hand = new ArrayList<Integer>();
	ArrayList<Integer> cardsFaceUp = new ArrayList<Integer>();
	ArrayList<Integer> cardsFaceDown = new ArrayList<Integer>();
	Boolean hasTakenTurn = false;

	public Player(String name)
	{
		this.name = name;
	}

	public static void main (String[] args)
	{
		System.out.println("Hey!");
	}

	public void putCardFaceDown(int card)
	{
		cardsFaceDown.add(card);
	}

	public void putCardFaceUp(int card)
	{
		cardsFaceUp.add(card);
	}

	public void putCardInHand(int card)
	{
		hand.add(card);
	}

	public ArrayList<Integer> sortHand()
	{
		int count = 0;

		while (count < hand.size())
		{
			for (int i=0; i<hand.size()-1; i++)
			{
				if (hand.get(i) > hand.get(i+1))
				{
					int bigger = hand.get(i);
					int smaller = hand.get(i+1);
					hand.set(i, smaller);
					hand.set(i+1, bigger);
				}
			}

			count = count + 1;
		}

		return hand;
	}

	public void placeCardOnPile(int index, int cardPlaced, ArrayList<Integer> pile)
	{
		hand.remove(index);
		pile.add(cardPlaced);
		sortHand();
	}

	public void lookThroughDeckAndPlay(ArrayList<Integer> deck, ArrayList<Integer> pile)
	{
		System.out.println(name+"'s current hand is "+hand);
		int cardPlayed = -1;
		int cardPlaced = -1;
		int howManyCardsPlayed = 0;
		Boolean playedCard = false;

		//The player looks through their hand
		for (int i=0; i<hand.size(); i++)
		{
			//If they have not played a card before...

				//...and if there is no card in the pile....
				if (pile.size() == 0)
				{
					System.out.println("The pile is empty");

					//If the card they run across is not a ten or a two...
					if (hand.get(i) != 11 && hand.get(i) != 12)
					{
						System.out.println("The card "+name+" is focused on is "+hand.get(i));


							for (int k=i; k<hand.size(); k++)
							{
								if (!playedCard)
								{
									if (hand.get(k) == hand.get(i))
									{
										//The player places down this card.  Since the deck is sorted, this will be their lowest non-ten or non-two card
										System.out.println(name+" decides to play "+hand.get(k));
										int index = k;
										cardPlaced = hand.get(k);
										placeCardOnPile(index, cardPlaced, pile);
										howManyCardsPlayed = howManyCardsPlayed + 1;
										//The player has played a card
										playedCard = true;
										//We keep track of what card the player played in order to see if they have more than one of this card
										cardPlayed = cardPlaced;
										System.out.println(name+"'s hand after playing is "+hand);
										System.out.println("the pile is "+pile);
										k = k - 1;
									}
								}
							}
						

						break;
					}
				}

				else
				{
					int topCard = pile.get(pile.size() - 1);
					System.out.println("The top card "+name+" must play against is "+topCard);

					//If the player hasn't played a card by the end of this for loop, then they must either play a 2, 10, or pick up the pile.
					for (int j=0; j<hand.size(); j++)
					{
						if (!playedCard)
						{
							if (hand.get(j) >= topCard && hand.get(j) != 10)
							{
								//The player places down this card.  Since the deck is sorted, this will be their lowest non-ten or non-two card
								System.out.println(name+" decides to play "+hand.get(j));
								int index = j;
								cardPlaced = hand.get(j);
								placeCardOnPile(index, cardPlaced, pile);
								howManyCardsPlayed = howManyCardsPlayed + 1;
								//The player has played a card
								playedCard = true;
								//We keep track of what card the player played in order to see if they have more than one of this card
								cardPlayed = cardPlaced;
								System.out.println(name+"'s hand after playing is "+hand);
								System.out.println("the pile is "+pile);
								break;
							}
						}
					}

					//If a player can't play a card that isn't a ten or two...
					if (!playedCard)
					{
						int index = 0;
						Boolean twoSeen = false;
						Boolean tenSeen = false;
						System.out.println(name+" must play a 2, a 10, or pick up the pile");

						for (int j=0; j<hand.size(); j++)
						{
							if (hand.get(j) == 2)
							{
								System.out.println(name+" has a 2!!");
								twoSeen = true;
								index = j;
								break;
							}

							else if (hand.get(j) == 10)
							{
								System.out.println(name+" has a 10!!");
								tenSeen = true;
								index = j;
								break;
							}
						}

						if (twoSeen || tenSeen)
						{
							if (twoSeen)
							{
								System.out.println(name+" has a 2 at index "+index);
								hand.remove(index);
								pile.add(2);
								System.out.println(name+" added a 2 to the pile.  their hand is "+hand);
								hasTakenTurn = true;
								System.out.println(name+" is about to take a second turn to hasTakenTurn is set to "+hasTakenTurn);
								takeTurn(deck, pile);
							}

							else if (tenSeen)
							{
								System.out.println(name+" has a 10 at index "+index);
								hand.remove(index);
								pile.clear();
								System.out.println(name+" played a 10.  Their hand is "+hand);
								System.out.println("A 10 was played! The pile now is "+pile);
								hasTakenTurn = true;
								System.out.println(name+" is about to take a second turn to hasTakenTurn is set to "+hasTakenTurn);
								takeTurn(deck, pile);							
							}
						}

						//The player must pick up the pile!
						else
						{
							System.out.println(name+" must pick up the pile!!------------------------------------------------------");

							for (int q=0; q<pile.size(); q++)
							{
								hand.add(pile.get(q));
							}

							pile.clear();
							sortHand();
							System.out.println(name+"'s hand is now "+hand);
							System.out.println("The pile is "+pile);
							hasTakenTurn = true;
							System.out.println(name+" is about to take a second turn to hasTakenTurn is set to "+hasTakenTurn);
							takeTurn(deck, pile);
							break;
						}
					}
				}
		

			//If the player has ANOTHER card in their hand that is the same value as the card they just played, they also play that card
/*			System.out.println("We are right before putting down a duplicate.");
			System.out.println("The card being looked at is "+hand.get(i));
			System.out.println("The card that was just played is "+cardPlayed);
			if (hand.get(i) == cardPlayed)
			{
				System.out.println(name+" has another "+hand.get(i)+" and plays it.");
				int index = i;
				cardPlaced = hand.get(i);
				placeCardOnPile(index, cardPlaced, pile);
				howManyCardsPlayed = howManyCardsPlayed + 1;
				System.out.println(name+"'s hand after playing is "+hand);
				System.out.println("the pile is "+pile);
			}*/
		}

		//The player must pick up the amount of cards she/he played if the deck is not empty.
		if (deck.size() != 0)
		{
			for (int j=0; j<howManyCardsPlayed; j++)
			{
				if (deck.size() != 0)
				{
					System.out.println("The deck is not empty and "+name+" must pick "+howManyCardsPlayed+" from the deck");
					hand.add(deck.get(0));
					deck.remove(0);
					sortHand();
					System.out.println("Now, "+name+"'s hand looks like "+hand);
					System.out.println("and the deck looks like "+deck);
				}
			}
		}
	}

	public void takeTurn(ArrayList<Integer> deck, ArrayList<Integer> pile)
	{
		System.out.println("-------------------------------- "+name+"'s TURN ------------------------------------------");
		if (!hasTakenTurn)
		{
			lookThroughDeckAndPlay(deck, pile);
		}
	}
}