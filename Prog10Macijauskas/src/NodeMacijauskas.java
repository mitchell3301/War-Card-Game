public class NodeMacijauskas 
{
	/**
	 * Instance variable for myCard
	 */
	private CardMacijauskas myCard;
	
	/**
	 * Instance variable for myNext
	 */
	private NodeMacijauskas myNext;
	
	/**
	 * Creates empty object NodeMacijauskas
	 */
	public NodeMacijauskas()
	{
		CardMacijauskas myCard = null;
		NodeMacijauskas myNext = null;
	} // NodeMacijauskas
	
	/**
	 * Creates partial object NodeMacijauskas
	 * @param newCard accepts a card to store in node
	 */
	public NodeMacijauskas(CardMacijauskas newCard)
	{
		myCard = newCard;
		myNext = null;
	} // NodeMacijauskas
	
	/**
	 * Gets data
	 * @return returns CardMacijauskas myCard
	 */
	public CardMacijauskas getCard()
	{return myCard;} // getData
	
	/**
	 * Sets data
	 * @param newData sets CardMacijauskas myCard
	 */
	public void setCard(CardMacijauskas newCard)
	{myCard = newCard;} // setData
	
	/**
	 * Gets next node
	 * @return returns NodeMacijauskas myNext
	 */
	public NodeMacijauskas getNext()
	{return myNext;} // getNext
	
	/**
	 * Sets next node
	 * @param newNext sets NodeMacijauskas myNext
	 */
	public void setNext(NodeMacijauskas newNext)
	{myNext = newNext;} // setNext
	
} // NodeMacijauskas