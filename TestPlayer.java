import java.util.Random;
import java.util.*; 


public class TestPlayer
{
	String name;
	HashMap<Integer,Integer> hand = new HashMap<Integer,Integer>();
	ArrayList<Integer> cardsFaceUp = new ArrayList<Integer>();
	ArrayList<Integer> cardsFaceDown = new ArrayList<Integer>();

	public TestPlayer(String name)
	{
		this.name = name;
	}

	public static void main(String[] args)
	{
		System.out.println("Hi!");
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
		hand.put(card, hand.get(card)+1);
	}

	public void placeCardOnPile(int card, ArrayList<Integer> pile)
	{
		int numberOfCardsPlaced = hand.get(card);

		for (int i=0; i<numberOfCardsPlaced; i++)
		{
			pile.add(card);
		}

		hand.put(card, 0);
	}

	public int numberOfCardsInHand()
	{
		int sum = 0;

		for (int i=0; i<hand.size(); i++)
		{
			sum = sum + hand.get(i);
		}

		return sum;
	}

	public void playFromHand(ArrayList<Integer> deck, ArrayList<Integer> pile)
	{
		if (pile.size() == 0)
		{
			for (int i=0; i<hand.size()-2; i++)
			{
				if (hand.get(i) != 0)
				{
					System.out.println(name+" decides to play their "+hand.get(i)+" "+i+"'s");
					int numberOfCardsPlaced = hand.get(i);
					hand.put(i, 0);

					for (int j=0; j<numberOfCardsPlaced; j++)
					{
						pile.add(i);
					}

					break;
				}
			}
		}

		else if (pile.size() != 0) 
		{
			int topCard = pile.get(pile.size() - 1);
			System.out.println(name+" must play against a "+topCard);
			Boolean hasTwoOrTen = false;
			Boolean playedNormalCard = false;

			for (int i=0; i<hand.size()-2; i++)
			{
				//If the player can play a card that isn't a two or a ten...
				if ((i >= topCard || topCard == 11) && hand.get(i) > 0)
				{
					System.out.println(name+" can play a card! He has "+hand.get(i)+" of the card "+i);
					int numberOfCardsPlaced = hand.get(i);
					hand.put(i, 0);

					for (int j=0; j<numberOfCardsPlaced; j++)
					{
						pile.add(i);
					}

					playedNormalCard = true;
					System.out.println("Played Normal Card: "+playedNormalCard);

					break;
				}
			}

			//Checking for a two or a ten...
			if (hand.get(11) !=0 || hand.get(12) !=0)
			{
				hasTwoOrTen = true;
			}

			//If player can't play a normal card but has a two or a ten...
			if (!playedNormalCard && hasTwoOrTen)
			{
				if (hand.get(11) != 0)
				{
					System.out.println(name+" plays a 2!  SHOOM!");
					hand.put(11, hand.get(11)-1);
					pile.add(11);
					//PLAYER NEEDS TO TAKE ANOTHER TURN!
					takeTurn(deck, pile);
				}

				else if(hand.get(12) != 0)
				{
					System.out.println(name+" plays a 10!  BOOM!");
					hand.put(12, hand.get(12)-1);
					pile.clear();
					//PLAYER NEEDS TO TAKE ANOTHER TURN!
					takeTurn(deck, pile);
				}
			}

			else if (!playedNormalCard && !hasTwoOrTen)
			{
				System.out.println(name+" can't play a normal card and doesn't have a ten or two and must pick up the pile!");

				for (int j=0; j<pile.size(); j++)
				{	
					int card = pile.get(j);
					hand.put(card, hand.get(card)+1);
				}

				pile.clear();

				//PLAYER NEEDS TO TAKE ANOTHER TURN!
				takeTurn(deck, pile);
			}	
		}
	}

	public void takeTurn(ArrayList<Integer> deck, ArrayList<Integer> pile)
	{
		int numberOfCardsInHand = numberOfCardsInHand();
		System.out.println("-------------------------------- "+name+"'s Turn -------------------------------- ");
		System.out.println("Their hand "+hand);
		System.out.println("They have "+numberOfCardsInHand+" cards in their hand.");
		System.out.println("The pile is "+pile);


		playFromHand(deck, pile);

		System.out.println("Their hand "+hand);
		System.out.println("The pile is "+pile);
	}
}