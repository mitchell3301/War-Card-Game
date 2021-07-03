import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Mitchell Macijauskas <br>
 * 
 * Prog 10 <br>
 * Due Date and Time: 5/17/21 before 9:00 AM <br>
 * 
 * Purpose: Build a program that allows file input for the game 'War' <br>
 * 			Run rounds of the game until there is either a winner of 1000 rounds have gone by <br>
 * 
 * Input: A file imitating a deck of cards<br>
 * 
 * Output: A summary of the game, including winner, number of cards, and number of rounds <br>
 * 
 * Certification of Authenticity: <br>
 * 
 * I certify that this is entirely my own work.
 */
public class BattleDemoMacijauskas 
{
	static Scanner keyboard = new Scanner(System.in);
	
	/**
	 * The main function
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// Create stacks
		StackMacijauskas player1PlayStack = new StackMacijauskas();
		StackMacijauskas player2PlayStack = new StackMacijauskas();

		StackMacijauskas player1DiscardStack = new StackMacijauskas();
		StackMacijauskas player2DiscardStack = new StackMacijauskas();
		
		int plays = 0;
		
		System.out.println("Welcome to the War Card Game Program!");
		System.out.println("This program simulates rounds of War given an inputted deck of cards.");
		
		int cardsDealt = deal(player1PlayStack, player2PlayStack);
		
		int p1cards = countCards(player1PlayStack) + countCards(player1DiscardStack);
		int p2cards = countCards(player2PlayStack) + countCards(player2DiscardStack);
		
		// Check if game has ended
		while(p1cards > 0 && p2cards > 0 && plays < 1000)
		{
			play(player1PlayStack, player2PlayStack, player1DiscardStack, player2DiscardStack);
			plays++;
			
			// Update player's card counts
			p1cards = countCards(player1PlayStack) + countCards(player1DiscardStack);
			p2cards = countCards(player2PlayStack) + countCards(player2DiscardStack);
		} // While
		
		printResults(player1DiscardStack,
					 player2DiscardStack,
					 player1PlayStack,
					 player2PlayStack,
					 plays,
					 cardsDealt);
	
	} // Main

	/**
	 * Deals inputed cards to users' stacks
	 * @param playStack1 Player 1's play stack
	 * @param playStack2 Player 2's play stack
	 * @return returns number of cards dealt
	 */
	public static int deal(StackMacijauskas playStack1, StackMacijauskas playStack2) 
	{
		boolean p1Card = true;
		int numCardsInputted = 0;
		String fileName = "";
		int value = 0;
		String suit = "none";
		
		// Prompt
		System.out.print("Enter a file name: ");
	    fileName = keyboard.next();
	    File theFile = new File(fileName);  
	    
		try
	    {  
	      Scanner input = new Scanner(theFile);
	      
	      while(input.hasNext())
	      {
	    	  value = input.nextInt();
	    	  suit = input.next();
	    	  if(p1Card)
	    		  playStack1.push(new CardMacijauskas(value, suit));
	    	  else
	    		  playStack2.push(new CardMacijauskas(value, suit));
	    	  p1Card = !p1Card; // Alternate which stack sent to
	    	  numCardsInputted++;
	      } // While
	              
	      System.out.println("Cards inputted. \n");
	      input.close();
	      
	    } // Try
		
		catch(FileNotFoundException ex)
	    {
	      System.out.println("Failed to find file: " + fileName); 
	    } // Catch
		
	    catch(InputMismatchException ex)
	    {
	    	System.out.println("Type mismatch for the number I just tried to read.");
	        System.out.println(ex.getMessage());
	    } // Catch
		
	    catch(NumberFormatException ex)
	    {
	      System.out.println("Failed to convert String text into an integer value.");
	      System.out.println(ex.getMessage());
	    } // Catch
		
	    catch(NullPointerException ex)
	    {
	      System.out.println("Null pointer exception.");
	      System.out.println(ex.getMessage());
	    } // Catch
		
	    catch(Exception ex)
	    {
	    	System.out.println("Something went wrong");
	      ex.printStackTrace();
	    } // Catch
		
		return numCardsInputted;
	} // deal

	/**
	 * Play round
	 * @param player1PlayStack accepts Player 1's play stack
	 * @param player2PlayStack accepts Player 2's play stack
	 * @param player1DiscardStack accepts Player 1's discard stack
	 * @param player2DiscardStack accepts Player 2's discard stack
	 */
	public static void play (StackMacijauskas player1PlayStack,
							 StackMacijauskas player2PlayStack,
							 StackMacijauskas player1DiscardStack,
							 StackMacijauskas player2DiscardStack)
	{
		CardMacijauskas player1Card = null;
		CardMacijauskas player2Card = null;
		
		// Copy from discard to play if empty
		if(player1PlayStack.isEmpty())
			copy(player1DiscardStack, player1PlayStack);
		if(player2PlayStack.isEmpty())
			copy(player2DiscardStack, player2PlayStack);
		
		player1Card = player1PlayStack.pop();
		player2Card = player2PlayStack.pop();
		
		int comparedIndex = compare(player1Card, player2Card);
		
		winPlay(player1Card,
				player2Card,
				player1DiscardStack,
				player2DiscardStack,
				comparedIndex);
	} // play

	/**
	 * Compares the popped cards
	 * @param firstCard accepts first card that is popped
	 * @param secondCard accepts second card that is popped
	 * @return returns compared index
	 */
	public static int compare (CardMacijauskas firstCard, CardMacijauskas secondCard) 
	{
		int compared = 0;

		if (firstCard.getValue() > secondCard.getValue())
			compared = 1;
		
		else if (firstCard.getValue() < secondCard.getValue())
			compared = 2;
		
		else
			compared = compareSuits(firstCard, secondCard);
		
		return compared;
	} // compare
	
	/**
	 * Compares suits if card values are equal
	 * @param firstCard accepts card from P1
	 * @param secondCard accepts card from P2
	 * @return return compared index
	 */
	private static int compareSuits (CardMacijauskas firstCard, CardMacijauskas secondCard)
	{
		int suitIndex = Integer.MIN_VALUE;
		
		if(firstCard.getSuit().compareToIgnoreCase(secondCard.getSuit()) < 0)
			suitIndex = 1;
		else if(firstCard.getSuit().compareToIgnoreCase(secondCard.getSuit()) == 0)
			suitIndex = 0;
		else
			suitIndex = 2;
		
		return suitIndex;
	} // compareSuits

	/**
	 * Calculates the winner of a given play
	 * @param card1 accepts first card
	 * @param card2 accepts second card
	 * @param discard1 accepts p1 discard stack
	 * @param discard2 accepts p2 discard stack
	 * @param winner accepts winner index
	 */
	public static void winPlay (CardMacijauskas card1, 
								CardMacijauskas card2,
								StackMacijauskas discard1, 
								StackMacijauskas discard2, 
								int winner) 
	{
		if (winner == 1)
		{
			discard1.push(card1);
			discard1.push(card2);
		} // If

		else if (winner == 0)
		{
			discard1.push(card1);
			discard2.push(card2);
		} // Else If
		
		else if (winner == 2) 
		{
			discard2.push(card2);
			discard2.push(card1);
		} // Else If
	} // winPlay
	
	/**
	 * Copies stacks
	 * @param discardStack accepts discard stack
	 * @param playStack accepts play stack
	 * @return returns copied stack
	 */
	public static StackMacijauskas copy (StackMacijauskas discardStack, StackMacijauskas playStack)
	{
		StackMacijauskas temp = new StackMacijauskas();
		
		
		while(!discardStack.isEmpty())
		{temp.push(discardStack.pop());} // While
		
		while(!temp.isEmpty())
		{playStack.push(temp.pop());} // While
		
		return playStack;
	} // copy
	
	/**
	 * Counds cards in stack
	 * @param stackToCount accepts stack to count
	 * @return returns card count
	 */
	public static int countCards (StackMacijauskas stackToCount)
	{
		int count = 0;
		StackMacijauskas temp = new StackMacijauskas();
		
		while(!stackToCount.isEmpty())
		{
			temp.push(stackToCount.pop());
			count++;
		} // While
		
		while(!temp.isEmpty())
		{stackToCount.push(temp.pop());} // While
		
		return count;
	} // countCards
	
	/**
	 * Prints results
	 * @param discard1 accepts p1 discard stack
	 * @param discard2 accepts p2 discard stack
	 * @param play1 accepts p1 play stack
	 * @param play2 accepts p2 play stack
	 * @param numPlays accepts number of plays
	 * @param numCards accepts number of cards
	 */
	public static void printResults (StackMacijauskas discard1,
									 StackMacijauskas discard2,
									 StackMacijauskas play1,
									 StackMacijauskas play2,
									 int numPlays,
									 int numCards)
	{
		int totalCardsP1 = countCards(play1) + countCards(discard1);
		int totalCardsP2 = countCards(play2) + countCards(discard2);
		String winner = "none";
		String outcome = "NA";
		
		System.out.println("Battle Card Game Summary");
		System.out.println("========================");
		System.out.println("The game started with " + numCards + ".");
		System.out.println("There were " + numPlays + " plays in the game.");
		
		if(numPlays < 1000)
		{
			outcome = "The game ended with a clear winner.";
			if(totalCardsP1 < totalCardsP2)
				winner = "Player 2.";
			else if(totalCardsP2 < totalCardsP1)
				winner = "Player 1.";
			else if(totalCardsP1 == totalCardsP2)
			{
				winner = "no one."; // For an empty input
				outcome = "The game did not end with a winner.";
			} // Else If
		} // If
		
		else
		{
			outcome = "The game took too long.";
			if(totalCardsP1 > totalCardsP2)
				winner = "Player 1";
			else if(totalCardsP1 < totalCardsP2)
				winner = "Player 2";
			else
				winner = "no one";
		} // Else
		
		System.out.println(outcome);
		System.out.println("Player 1 ended up with " + totalCardsP1 + " cards.");
		System.out.println("Player 2 ended up with " + totalCardsP2 + " cards.");
		System.out.println("The winner was " + winner + "\n");
	
	} // printResults

} // BattleDemoMacijauskas
