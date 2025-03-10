/**
 * Card class that represents a card with a number and a suit
 */
public class Card{

    private int number; // the number of the card
    private String suit; // the suit of the card
    
    /** 
     * constructor for the Card class
     * @param number - the number of the card
     * @param suit - the suit of the card
     */
    public Card(int number, String suit){
        this.number = number;
        this.suit = suit;
    }

    /**
     * returns the number of the card
     * @return
     */
    public int getNumber() {
        return number;
    }

    /**
     * returns the suit of the card
     * @return
     */
    public String getSuit() {
        return suit;
    }

    /**
     * returns a string representation of the card
     * @return 
     */
    public String print(){
        return (number + " of " + suit);
    }

    /**
     * returns true if the card is equal to the card passed in
     * @param card - contains the card with it's number and suit
     * @return
     */
    public boolean equals(Card card){
        return (this.number == card.getNumber() && this.suit == card.getSuit());
    }


}