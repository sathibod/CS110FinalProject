/*Sierra Thibodeau
CS 110 Final Project*/

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections; 


public class WarGameAndGUI extends JFrame
{
   private JPanel panel,player1Panel, player2Panel, gamePanel;
   private JButton playHand, compareHand, startGame;
   private JButton startNewGame;
   private final int WINDOW_WIDTH = 350;
   private final int WINDOW_HEIGHT = 250;
   public static Deck warDeck;
	public Deck player1Deck;
	public Deck player2Deck;
	public Card p1Card, p2Card;
	public Hand player1Hand, player2Hand;
	public boolean done;
   public ImageIcon player1Front, player1Back, player2Front, player2Back;
   public JLabel pic, player1PicFront, player2PicFront,player1PicBack,player2PicBack, winner;
   
  

   public WarGameAndGUI()
   {
      //build windown
      setTitle("WAR");
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setLayout(new BorderLayout());
      
      
      
      buildPanel();
      add(panel);
      
      
      setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
      
      setVisible(true);
      
      //create game
      warDeck = new Deck(); //Creating the Deck of Cards
      warDeck.shuffle();//shuffle deck
      player1Hand = new Hand(); //create player 1 hand
      player2Hand = new Hand(); //create player 2 hand
      done = false;
      split(warDeck);//split the deck
 
   }
   
   //build panel
   private void buildPanel()
   {
      //start game button
      startGame = new JButton("Start Game");
      startGame.addActionListener(new StartGameListener());
      panel = new JPanel();
      panel.add(startGame, BorderLayout.NORTH);
      
      //play hand button
      playHand = new JButton("Play Hand");
      playHand.addActionListener(new PlayHandListener());
      panel.add(playHand, BorderLayout.CENTER);
      
      //compare hand button
      compareHand = new JButton("Compare Cards");
      compareHand.addActionListener( new CompareHandListener());
      panel.add(compareHand, BorderLayout.SOUTH);
      
      
   
   }
   //button will start the game and display the back side of the cards
   private class StartGameListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        createBackIcons();
         pack();
         }
         }
   
   //button will flip the card and show each player's card
   private class PlayHandListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
         
         createFrontIcons();
         panel.remove(winner);
         pack();
	   
      }
   }
   
   //button will compare the two cards and determine the winner
   private class CompareHandListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e)
      {
      compareCards();
      pack();
      }
      }
   
   //split the deck in half for each player   
   public void split(Deck deck){
	   while(!done) //Dealing the Cards
		{
      //player1
		if(!(deck.isEmpty()))
		player1Hand.addCard(deck.dealCard());
		else
		done = true;

      //player2
		if(!(deck.isEmpty()))
		player2Hand.addCard(deck.dealCard());
		else
		done = true;
		}
   }
   
   //if there are two similar cards then there will be a war
   public void war(Hand player1, Hand player2){
   
   //create labels
   player1PicBack = new JLabel();
   player2PicBack = new JLabel();
   player1PicFront = new JLabel();
	player2PicFront = new JLabel();
   
	Card p1Card1 = player1.playCard();//face down
   player1Back = new ImageIcon("back.jpg");
	Card p1Card2 = player1.playCard();//face up
   player1Front = p1Card2.cardImage();

	Card p2Card1 = player2.playCard();//face down
   player2Back = new ImageIcon("back.jpg");
	Card p2Card2 = player2.playCard();//face up
   player2Front = p2Card2.cardImage();
   
   //add pictures
   panel.add(player1PicBack);
   panel.add(player1PicFront);
   panel.add(player2PicBack);
   panel.add(player2PicFront);
	

	if (p1Card2.getRank()>p2Card2.getRank()) //if player1 wins
	{
	player1.addCard(p1Card1);
	player1.addCard(p1Card2);
	player1.addCard(p2Card1);
	player1.addCard(p2Card2);
   winner = new JLabel("Player 1 Wins");
   panel.add(winner);
	warDeck.shuffle();
	}

	else if (p1Card2.getRank()<p2Card2.getRank())//if player2 wins
	{
	player2.addCard(p1Card1);
	player2.addCard(p1Card2);
	player2.addCard(p2Card1);
	player2.addCard(p2Card2);
   winner = new JLabel("Player 2 Wins");
   panel.add(winner);
	warDeck.shuffle();
	}
	else //if a war is declared
	war(player1 , player2);
	warDeck.shuffle();
	
	}
   
   //creates the back side of the cards to display
   public void createBackIcons()
   {
      //create image icons
		player1Back = new ImageIcon("back.jpg");
		player2Back = new ImageIcon ("back.jpg");
      
      //create labels for image icons
		player1PicBack = new JLabel(player1Back);
		player2PicBack = new JLabel(player2Back);
      
      //add pictures to panels
		panel.add(player1PicBack);
		panel.add(player2PicBack);
      
   }
   
   //creates the front side of the cards to display
   public void createFrontIcons(){
      //deal card and create image icon
      p1Card = player1Hand.playCard();
      player1Front = p1Card.cardImage();
      
      p2Card = player2Hand.playCard();
      player2Front = p2Card.cardImage();
      
      //create labels for image icons
      player1PicFront = new JLabel(player1Front);
	   player2PicFront = new JLabel(player2Front);
	

 
      //remove back pictures and add pictures to panels
      panel.remove(player1PicBack);
      panel.remove(player2PicBack);
	   panel.add(player1PicFront,BorderLayout.CENTER);
	   panel.add(player2PicFront,BorderLayout.CENTER);
      }
      
   //compare the two cards that were drawn   
   public void compareCards(){
      //remove cards from panel
      panel.remove(player1PicFront);
      panel.remove(player2PicFront);
      //panel.remove(winner);
   
	   if (p1Card.getRank()>p2Card.getRank()) //if player1 wins
	      {  
      
		      player1Hand.addCard(p1Card);
		      player1Hand.addCard(p2Card);
            winner = new JLabel("Player 1 Wins");
            panel.add(winner);
	      }

	   else if (p1Card.getRank()<p2Card.getRank()) //if player2 wins
	      {
      
		      player2Hand.addCard(p1Card);
		      player2Hand.addCard(p2Card);
            winner = new JLabel("Player 2 Wins");
            panel.add(winner);
	      }
	   else //if equal, then there is a war
	      {
	         war(player1Hand , player2Hand);

	        }
	}

   //driver for the program
   public static void main(String [] args)
   {
      WarGameAndGUI window = new WarGameAndGUI();
   }
              
   }
