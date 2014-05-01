
import java.util.Scanner;
	public class WarGame 
	{

	public static void main(String[] args) 
	{

	Deck warDeck = new Deck(); //Creating the Deck of Cards

	Hand player1 = new Hand(); //Creating player 1's hand
	Hand player2 = new Hand(); //Creating player 2's hand

	warDeck.shuffle(); //Shuffling the Deck

	String again ="y";

	boolean done = false;

	while(!done) //Dealing the Cards
	{
	if(!(warDeck.isEmpty()))
	player1.addCard(warDeck.dealCard());
	else
	done = true;


	if(!(warDeck.isEmpty()))
	player2.addCard(warDeck.dealCard());
	else
	done = true;
	}

	Scanner keyboard = new Scanner(System.in);
	System.out.println(player1.numCards());
	System.out.println(player2.numCards());

	while(again.equalsIgnoreCase("y")&&player1.numCards() != 0 && player2.numCards() != 0)
	{

	//Playing the Game
	Card p1Card = player1.playCard();
	Card p2Card = player2.playCard();

	if (p1Card.getRank()>p2Card.getRank()) //if player1 wins
	{
	System.out.println("Player 1 Card: "+p1Card);
	System.out.println("Player 2 Card: "+p2Card);
	System.out.println("Player 1 Wins!");
	player1.addCard(p1Card);
	player1.addCard(p2Card);
   System.out.println(player1.numCards());
   System.out.println(player2.numCards());
	}

	else if (p1Card.getRank()<p2Card.getRank()) //if player2 wins
	{
	System.out.println("Player 1 Card: "+p1Card);
	System.out.println("Player 2 Card: "+p2Card);
	System.out.println("Player 2 Wins!");
	player2.addCard(p1Card);
	player2.addCard(p2Card);
   System.out.println(player1.numCards());
   System.out.println(player2.numCards());
	}
	else //if equal, then there is a war
	{
	war(player1 , player2);
   System.out.println(player1.numCards());
   System.out.println(player2.numCards());

	}
	again=keyboard.nextLine();
	}
	//Winner
	if(player2.numCards() == 0)
	System.out.println("PLAYER 1 WINS THE GAME!");
	else
	System.out.println("PLAYER 2 WINS THE GAME!");
	}



	

	//war: if they are equal
	public static void war(Hand player1, Hand player2)
	{
	System.out.println("It's a war!");

	
	Card p1Card1 = player1.playCard();//face down
	Card p1Card2 = player1.playCard();//face up

	Card p2Card1 = player2.playCard();//face down
	Card p2Card2 = player2.playCard();//face up
	
	System.out.println("Player 1 Face Down: "+p1Card1);
	System.out.println("Player 1 Face Up: "+p1Card2);
	System.out.println("Player 2 Face Down: "+p2Card1);
	System.out.println("Player 2 Face Up: "+p2Card1);
	

	if (p1Card2.getRank()>p2Card2.getRank()) //if player1 wins
	{
	System.out.println("Player 1 Wins!");
	player1.addCard(p1Card1);
	player1.addCard(p1Card2);
	player1.addCard(p2Card1);
	player1.addCard(p2Card2);
	}

	else if (p1Card2.getRank()<p2Card2.getRank())//if player2 wins
	{
	System.out.println("Player 2 Wins!");
	player2.addCard(p1Card1);
	player2.addCard(p1Card2);
	player2.addCard(p2Card1);
	player2.addCard(p2Card2);
	}
	else //if a war is declared
	war(player1 , player2);
	
	}

	}

	
	

	

