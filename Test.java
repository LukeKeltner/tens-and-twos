import java.util.Random;
import java.util.*; 

public class Test
{
	static HashMap<Integer,Integer> hand = new HashMap<Integer,Integer>();

	public static void main(String[] args)
	{
		System.out.println("Hi1");

		for (int i=0; i<10; i++)
		{
			hand.put(i, 2*i);
		}

		System.out.println(hand);
		System.out.println(hand.get(1));
		hand.put(3, 90);
		System.out.println(hand);

		int sum = 0;
		for (int i=0; i<hand.size(); i++)
		{
			sum = sum + hand.get(i);
		}

		System.out.println(sum);

	}
}