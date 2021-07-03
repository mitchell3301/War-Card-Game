public class StackMacijauskas 
{
	/**
	 * Instance variable for myTop
	 */
	private NodeMacijauskas myTop;
	
	/**
	 * Creates empty object StackMacijauskas
	 */
	public StackMacijauskas()
	{
		myTop = null;
	} // StackMacijauskas
	
	/**
	 * Pushes card onto stack
	 * @param cardToPush accepts a card to push onto stack
	 * @return returns a boolean, true if pushed, false if not
	 */
	public boolean push(CardMacijauskas cardToPush)
	{
		boolean pushed = false;
		NodeMacijauskas newNode = null;
		
		if(!isFull())
		{
			pushed = true;
			newNode = new NodeMacijauskas(cardToPush);
			newNode.setNext(myTop);
			myTop = newNode;
		} // While
		
		return pushed;
	} // Push
	
	/**
	 * Pops card off of top of stack
	 * @return returns the card that was popped off of stack
	 */
	public CardMacijauskas pop()
	{
		CardMacijauskas cardToPop = null;
		
		if(!isEmpty())
		{
			cardToPop = myTop.getCard();
			myTop = myTop.getNext();
		} // If
		
		return cardToPop;
	} // Pop
	
	/**
	 * Checks if stack is empty
	 * @return returns a boolean, true if empty, false if not empty
	 */
	public boolean isEmpty()
	{return(myTop == null);} // isEmpty
	
	/**
	 * Checks if stack is full
	 * @return returns false, stack can never be full
	 */
	public boolean isFull()
	{return false;} // isFull
	
} // StackMacijauskas
