import java.util.*;

public class Hand 
{
ArrayList <Card> cardList = new ArrayList <Card> (53);

//top card
public Card playCard()
{
return cardList.remove(0);
}

//adds card to botton
public void addCard(Card a)
{
cardList.add(a); 
}

//number of cards
public int numCards()
{
int numCards = cardList.size();
return numCards;
}

//prints hand
public void printHand()
{
for(int i=0; i < cardList.size(); i++)
System.out.println(i + ". " + cardList.get(i));
}



}