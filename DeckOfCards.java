//Chi Nguyen
//cqnguyen18@ole.augie.edu
//DeckOfCards.java
import java.util.Scanner;
import java.util.Random;
public class DeckOfCards
{
	private int top;
	private String[] rank;
	private String[] suit;
	//Post:	This DeckOfCards initialized to a new deck; the cards are ordered 		//	from AceSpade to KingDiamond as in a new deck; top initialized to 0.
	public DeckOfCards()
	{
		top = 0;
		rank = new String[52];
		suit = new String[52];
		for (int i = 0;  i < 4; ++i)
		{
			rank[i*13+0] = "Ace";
			rank[i*13+1] = "Two";
			rank[i*13+2] = "Three";
			rank[i*13+3] = "Four";
			rank[i*13+4] = "Five";
			rank[i*13+5] = "Six";
			rank[i*13+6] = "Seven";
			rank[i*13+7] = "Eight";
			rank[i*13+8] = "Nine";
			rank[i*13+9] = "Ten";
			rank[i*13+10] = "Jack";
			rank[i*13+11] = "Queen";
			rank[i*13+12] = "King";
		}
		for (int i = 0; i < 13; ++i)
			suit[i] = "Spade";
		for (int i = 13; i < 26; ++i)
			suit[i] = "Heart";
		for (int i = 26; i < 39; ++i)
			suit[i] = "Club";
		for (int i = 39; i < 52; ++i)
			suit[i] = "Diamond";
	}
	//Post:	This DeckOfCards thoroughly shuffled; top set to 0.
	public void shuffle()
	{
		Random r = new Random();
		for (int i = 0; i < 10000; ++i)
		{
			int x = r.nextInt(52);
			int y = r.nextInt(52);
			String temp = rank[x];
			rank[x] = rank[y];
			rank[y] = temp;
			temp = suit[x];
			suit[x] = suit[y];
			suit[y] = temp;
		}
		top = 0;
	}
	//Post:	top incremented by 1.
	//Return: The top card of this DeckOfCards as a String such as "AceHeart", 		//	"TwoSpade", "TenDiamond", "KingClub".  If top >=52, "NoMoreCard" is 		//	returned.
	public String deal()
	{
		String result = "";
		if (top < 52) result = rank[top] + suit[top];
		else result = "NoMoreCard";
		top++;
		return result;
	}
}
