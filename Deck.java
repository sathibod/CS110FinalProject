import java.util.Random;
public class Deck 
{
   final int CARDS_IN_DECK = 52;

   Card [] deck;
   int ct;
   public Deck()
   {
      freshDeck();
   }
   public void freshDeck()
   {
      deck = new Card[CARDS_IN_DECK];
      for (int r = Card.ACE; r<=Card.KING;r++)
      {
         for (int s=Card.SPADES;s<=Card.CLUBS;s++)
         {
            deck[ct]=new Card(r,s);
            ct = ct + 1;
         }
      }
     
   
   }
   public Card dealCard()
   {
      ct--;
      return deck[ct];
   }
   public Card addCard(Card aCard)
   {
      ct++;
      return deck[ct];
   }
   public Card removeAll(){
   return deck[0];
   }
   public int cardsRemaining()
   {  
      return ct;
   }
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < ct; i++)
      {
         randNum = r.nextInt(ct);
         temp = deck[i];
         deck[i]=deck[randNum];
         deck[randNum]=temp;
      }
   }
   
   public boolean isEmpty()
   {
      return (cardsRemaining() == 0);
   }
   public static void main(String [] args) 
   {
      Deck deck = new Deck();
      deck.shuffle();
      int i = 0;
    while (deck.cardsRemaining() > 0)
      while (!(deck.isEmpty()))
         System.out.println(i++ + " : " + deck.dealCard().toString());
      deck.freshDeck();
      while (!(deck.isEmpty()))
         System.out.println(i++ + " : " + deck.dealCard().toString());

   }
}
