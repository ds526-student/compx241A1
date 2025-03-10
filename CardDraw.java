/**
 * COMPX241 Assignment 1
 */
public class CardDraw {
    /**
     * Main method, creates a deck of cards, deals that deck to the players, calculates the points earnt by each player and determines the winner
     * @param args
     */
    public static void main(String[] args) {
        CardLinkedList cardsList = new CardLinkedList(); // creates a new linked list of cards
        int numOfSuits = 4; // number of suits in the deck
        int numOfCards = 13; // number of cards in each suit
        int players = 10; // number of players
        int handSize = (numOfSuits * numOfCards) / players; // handsize for each player based on the total number of cards and players
        
        // creates a deck of cards
        for (int i = 0; i < numOfSuits; i++) {
            for (int j = 0; j < numOfCards; j++) {
                Card card;
                if  (i == 0) {
                    card = new Card(j + 1, "Hearts");
                } else if (i == 1) {
                    card = new Card(j + 1, "Diamonds");
                } else if (i == 2) {
                    card = new Card(j + 1, "Clubs");
                } else {
                    card = new Card(j + 1, "Spades");
                }
                cardsList.add(card);

            }
        }

        int[][] pointsArray = new int[players][2]; // array to store the points of each player
        String[][] playerHands = new String[players][handSize]; // array to store the hands of each player
        int points; // integer to store the points earnt 

        // deals the deck to the players
        for (int i = 1; i <= players; i++) {
            CardLinkedList player = new CardLinkedList(); // creates a new linked list for the player
            int[][] cardsCounterArray = new int[handSize][2]; // array to store the number of each card in the players hand

            // deals the cards to the player
            for (int j = 1; j <= handSize; j++) {
                int length = cardsList.getLength(); // gets the length of the deck
                int rand = (int)(Math.random() * length); // generates a random position in the deck to give a player a card

                Card card = cardsList.getCardAt(rand); // gets the card at the random position
                player.add(card);
                cardsList.remove(card);

                // counts the number of each card in the players hand
                boolean cardExists = false;
                for (int[] arr : cardsCounterArray) {
                    if(arr[0] == card.getNumber()){
                        arr[1]++;
                        cardExists = true;
                    }
                }
                // if the card does not exist in the players hand, adds it to the array
                if (!cardExists) {
                    for (int[] arr : cardsCounterArray) {
                        if (arr[0] == 0) {
                            arr[0] = card.getNumber();
                            arr[1] = 1;
                            break;
                        }
                    }
                }

                // stores the players hand in the array
                playerHands[i - 1][0] = "Player " + i;
                if (playerHands[i - 1][1] == null) {
                    playerHands[i - 1][1] = card.getNumber() + " of " + card.getSuit() + ", ";
                } else
                playerHands[i - 1][1] += card.getNumber() + " of " + card.getSuit() + ", ";
            }
            points = 0;

            // calculates the points earnt by the player
            for (int[] arr : cardsCounterArray) {
                if (arr[1] == 1) {
                    points += arr[0];
                } else if (arr[1] == 2) {
                    points += arr[0] * 10;
                } else if (arr[1] == 3) {
                    points += arr[0] * 100;
                } else if (arr[1] == 4) {
                    points += arr[0] * 1000;
                }
                pointsArray[i - 1][0] = i;
                pointsArray[i - 1][1] = points;
            }

            System.out.println("Player " + i + " hand: ");
            player.print();
        }
        System.out.println("Remaining cards: ");
        cardsList.print();

        // uses bubble sort to sort the players by points in descending order
        for (int i = 0; i < pointsArray.length; i++) {
            for (int j = 0; j < pointsArray.length - 1; j++) {
                if (pointsArray[j][1] < pointsArray[j + 1][1]) {
                    int[] temp = pointsArray[j];
                    pointsArray[j] = pointsArray[j + 1];
                    pointsArray[j + 1] = temp;
                }
            }
        }

        // prints the points of each player
        System.out.println("Player points: ");
        for (int[] arr : pointsArray) {
            System.out.println("Player " + arr[0] + " has " + arr[1] + " points");
        }

        // prints the winner and their winning hand
        System.out.println("Winner is player " + pointsArray[0][0] + " with " + pointsArray[0][1] + " points");
        System.out.println("Their winning hand was " + playerHands[pointsArray[0][0] - 1][1]);
    }
}
