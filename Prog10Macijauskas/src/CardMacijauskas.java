public class CardMacijauskas 
{
	/**
	 * Instance variable for myValue
	 */
	private int myValue;
	
	/**
	 * Instance variable for mySuit
	 */
	private String mySuit;
	
	/**
	 * Creates empty object CardMacijauskas
	 */
	public CardMacijauskas()
	{
		myValue = 0;
		mySuit = "none";
	} // CardMacijauskas
	
	/**
	 * Creates full object CardMacijauskas
	 * @param newValue accepts a new card value
	 * @param newSuit accepts a new card suit
	 */
	public CardMacijauskas(int newValue, String newSuit)
	{
		myValue = newValue;
		mySuit = newSuit;
	} // CardMacijauskas
	
	/**
	 * Gets card value
	 * @return returns card value myValue
	 */
	public int getValue()
	{return myValue;} // getValue
	
	/**
	 * Sets card value
	 * @param newValue accepts new card value
	 */
	public void setValue(int newValue)
	{myValue = newValue;} // setValue
	
	/**
	 * Gets card suit
	 * @return returns mySuit
	 */
	public String getSuit()
	{return mySuit;} // getSuit
	
	/**
	 * Sets card suit
	 * @param newSuit accepts new card suit
	 */
	public void setSuit(String newSuit)
	{mySuit = newSuit;} // setSuit
	
	/**
	 * Puts card details into a string
	 */
	public String toString()
	{
		String ans = "My Card Value: " + myValue + "\n";
		ans += "My Card Suit: " + mySuit + "\n";
		return ans;
	} // toString

} // CardMacijauskas
