public class Program {
    public static void main(String[] args){
        System.out.println("Linked Lists!");
        CardLinkedList cardsList = new CardLinkedList();

        System.out.println("Empty list printed below: ");
        cardsList.print();
        System.out.println("Is Empty = " + cardsList.isEmpty());
        System.out.println("Length of list = " + cardsList.getLength());

        Card card1 = new Card(1, "Hearts");
        System.out.println("Has 1 of Hearts = " + cardsList.hasCard(card1));

        System.out.println("Getting card at position 0: " + cardsList.getCardAt(0));

        System.out.println("\n");
        System.out.println("Adding cards to the list.....");
 
        Card card2 = new Card(2, "Diamonds");
        Card card3 = new Card(3, "Clubs");
        Card card4 = new Card(4, "Spades");

        cardsList.add(card1);
        cardsList.add(card2);
        cardsList.add(card3);
        cardsList.add(card4);

        cardsList.print();
        System.out.println("Is Empty = " + cardsList.isEmpty());
        System.out.println("Has 1 of Hearts = " + cardsList.hasCard(card1));

        Card card5 = new Card(cardsList.getCardAt(0).getNumber(), cardsList.getCardAt(0).getSuit());

        System.out.println("Getting card at position 0: " + card5.print());

        System.out.println("Removing " + card3.print() + " from the list");
        cardsList.remove(card3);
        cardsList.print();
    }
}
