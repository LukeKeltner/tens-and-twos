import java.util.Random;
import java.util.*; 

public class tensAndTwosTest
{
	static ArrayList<Integer> cardsUp = new ArrayList<Integer>();
	static ArrayList<Integer> discard = new ArrayList<Integer>();
	static ArrayList<TestPlayer> listOfPlayers = new ArrayList<TestPlayer>();
	static ArrayList<Integer> deck = new ArrayList<Integer>();
	static ArrayList<Integer> pile = new ArrayList<Integer>();

	public static void main(String[] args)
	{
		//Creating Deck
		for (int i=0; i<4; i++)
		{
			for (int j=0; j<13; j++)
			{
				deck.add(j);
			}
		}

		//Shuffling Deck
		Collections.shuffle(deck);
		System.out.println(deck);
		System.out.println("The deck has "+deck.size()+" cards in it.");

		//Adding Players
		TestPlayer player1 = new TestPlayer("Patrick");
		TestPlayer player2 = new TestPlayer("Matt");
		TestPlayer player3 = new TestPlayer("Robert");
		TestPlayer player4 = new TestPlayer("Bill");

		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		listOfPlayers.add(player3);
		listOfPlayers.add(player4);

		//Setting up the intial hand HashMap so that every player has 0 cards.
		for (int i=0; i<listOfPlayers.size(); i++)
		{
			for (int j=0; j<13; j++)
			{
				listOfPlayers.get(i).hand.put(j, 0);
			}
		}

		//Giving each player 3 cards face down
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<listOfPlayers.size(); j++)
			{
				listOfPlayers.get(j).putCardFaceDown(deck.get(0));
				deck.remove(0);
			}
		}

		//Giving each player 3 cards face up
		for (int i=0; i<3; i++)
		{
			for (int j=0; j<listOfPlayers.size(); j++)
			{
				listOfPlayers.get(j).putCardFaceUp(deck.get(0));
				deck.remove(0);
			}
		}

		//Each Player gets 7 cards (max) in their hand to start
		for (int i=0; i<7; i++)
		{
			for (int j=0; j<listOfPlayers.size(); j++)
			{
				listOfPlayers.get(j).putCardInHand(deck.get(0));
				deck.remove(0);

				//If the deck is out of cards in mid deal...
				if (deck.size()==0)
				{
					break;
				}
			}

			//...we must break both for loops
			if (deck.size()==0)
			{
				break;
			}
		}

		for (int i=0; i<listOfPlayers.size(); i++)
		{
			System.out.println(listOfPlayers.get(i).name+"'s hand is...");
			System.out.println(listOfPlayers.get(i).hand);
		}

		System.out.println("current deck is "+deck);
		System.out.println("The pile is "+pile);

		for (int i=0; i<10; i++)
		{
			listOfPlayers.get(0).takeTurn(deck, pile);
			listOfPlayers.get(1).takeTurn(deck, pile);
			listOfPlayers.get(2).takeTurn(deck, pile);
			listOfPlayers.get(3).takeTurn(deck, pile);
		}
	}
}