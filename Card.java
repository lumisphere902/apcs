/**
 * Card.java
 *
 * <code>Card</code> represents a basic playing card.
 */
public class Card implements Comparable<Card>
{
    /** String value that holds the symbol of the card.
    Examples: "A", "Ace", "10", "Ten", "Wild", "Pikachu"
     */
    private String symbol;

    /** int value that holds the value this card is worth */
    private int value;

    /** boolean value that determines whether this card is face up or down */
    private boolean isFaceUp;
    
    /** 0 = spade, 1 = heart, 2 = diamonds, 3 = clubs */
    private int suit;

    /**
     * Creates a new <code>Card</code> instance.
     *
     * @param symbol  a <code>String</code> value representing the symbol of the card
     * @param value an <code>int</code> value containing the point value of the card
     */    
    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
        this.isFaceUp = true;
    }

	/**
	 * Getter method to access this <code>Card's</code> symbol.
	 * 
	 * @return this <code>Card's</code> symbol.
	 */
    public String getSymbol() {
    	return symbol;
    }
    
    /**
     * Getter method to access this <code>Card's</code> symbol.
     * 
     * @return this <code>Card's</code> value.
     */
    public int getValue() {
		return value;
	}

	/**
	 * Getter method to access whether this <code>Card</code> is face up.
	 * 
	 * @return whether this <code>Card</code> is face up.
	 */
	public boolean isFaceUp() {
        return isFaceUp;
    }

	/**
	 * Setter method to set whether this <code>Card</code> is face up.
	 * 
	 * @param state whether to set this <code>Card</code> face up or down.
	 */
    public void setFaceUp(boolean state) {
        isFaceUp = state;
    }
    
	/**
	 * Getter method to access this <code>Card's</code> suit.
	 * 
	 * @return this <code>Card's</code> suit.
	 */
	public int getSuit() {
		return suit;
	}

	/**
	 * Setter method to set this <code>Card's</code> suit.
	 * 
	 * @param suit suit to set this <code>Card</code> to.
	 */
	public void setSuit(int suit) {
		this.suit = suit;
	}
    
    /**
     * Returns whether or not this <code>Card</code> is equal to another
     *  
     *  @param other <code>Card</code> to compare with
     *  @return whether or not this <code>Card</code> is equal to other.
     */
    public boolean equals(Card other) {
        return value == other.getValue();
    }

	/**
	 * Returns this card as a String.  If the card is face down, "X"
	 * is returned.  Otherwise the symbol of the card is returned.
	 *
	 * @return a <code>String</code> containing the rank, suit,
	 *         and point value of the card.
	 */
	@Override
    public String toString() {
        if (!isFaceUp()) {
    	     return "X";
        }
        else	 {
    	    return symbol;
        }
     }

	/**
	 * Compare the value of two <code>Cards</code>
	 * 
	 * @param o <code>Card</code> to compare with
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	@Override
	public int compareTo(Card o) {
		return value - o.getValue();
	}
}
