import java.util.Random;
import java.util.*; 

public class tensAndTwos
{
	static ArrayList<Integer> cardsUp = new ArrayList<Integer>();
	static ArrayList<Integer> discard = new ArrayList<Integer>();
	static ArrayList<Player> listOfPlayers = new ArrayList<Player>();
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
		Player player1 = new Player("Patrick");
		Player player2 = new Player("Matt");
		Player player3 = new Player("Robert");

		listOfPlayers.add(player1);
		listOfPlayers.add(player2);
		listOfPlayers.add(player3);

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

		//Each player sorts their hand from smallest to largest
		for (int i=0; i<listOfPlayers.size(); i++)
		{
			listOfPlayers.get(i).sortHand();
		}

		System.out.println("current deck is "+deck);
		System.out.println("The pile is "+pile);

/*		for (int i=0; i<10; i++)
		{
			listOfPlayers.get(0).takeTurn(deck, pile);
			listOfPlayers.get(1).takeTurn(deck, pile);
			listOfPlayers.get(2).takeTurn(deck, pile);
		}*/
	}
}