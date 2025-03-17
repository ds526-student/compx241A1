/**
 * CardLinkedList class that represents a linked list of cards
 */
public class CardLinkedList {

    Node head = null; // the head of the linked list

    /**
     * Node class for the linked list
     */
    private class Node {
        Card value;
        Node next;

        /**
         * constructor for the Node class
         * @param value - the value of the node
         * @param next - the next node in the list
         */
        private Node(Card value, Node next) { 
            this.value = value;
            this.next = next;
        }
    }

    /**
     * returns true if the list is empty
     * @return
     */
    public boolean isEmpty(){
        return (head == null);
    }

    /**
     * returns the length of the list
     * @return
     */
    public int getLength(){
        int length = 0;
  
        for(Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
           ++length;
        }
  
        return length;
    }

    /**
     * returns true if the list contains the card passed in
     * @param c - the card to check for
     * @return
     */
    public boolean hasCard(Card c){
        for(Node currentNode = this.head; currentNode != null; currentNode = currentNode.next) {
           if (currentNode.value.equals(c)) {
              return true;
           }
        }
  
        return false;
    }

    /**
     * returns the card at the position passed in
     * @param i - the position of the card to return
     * @return
     */
    public Card getCardAt(int i){
        if (i > getLength() - 1) {
           return null;
        } else {
           Node currentNode = this.head;
  
           for(int j = 0; j < i; j++) {
              currentNode = currentNode.next;
           }
  
           return currentNode.value;
        }
    }

    /**
     * adds a card to the list
     * @param c - the card to add
     */
    public void add(Card c){
        Node currentNode = new Node(c, (Node)null);    
        currentNode.value = c;
        currentNode.next = this.head;
        this.head = currentNode;
    }

    /**
     * removes a card from the list
     * @param c - the card to remove
     */
    public void remove(Card c){
        if (this.head != null) {
           if (this.head.value.equals(c)) {
              this.head = this.head.next;
            } 
            else {
              Node currentNode = this.head;
  
                for(Node i = this.head.next; i != null; i = i.next) {
                    if (i.value.equals(c)) {
                        currentNode.next = i.next;
                        break;
                }

                currentNode = i;
                }
            }
        }
    }

    /**
     * prints the list/card in entered in a string format
     */
    public void print(){
        Node currentNode = this.head;
        StringBuilder result = new StringBuilder();
        while (currentNode != null) {
            result.append(currentNode.value.print());
            if (currentNode.next != null) {
                result.append(", ");
            }
            currentNode = currentNode.next;
        }
        System.out.println(result.toString());
    }
}
