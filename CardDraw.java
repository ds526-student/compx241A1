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
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; // array of suits
        for (int i = 0; i < numOfSuits; i++) {
            for (int j = 0; j < numOfCards; j++) {
                Card card = new Card(j + 1, suits[i]);
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
                countCard(cardsCounterArray, card);

                // stores the players hand in the array
                playerHands[i - 1][0] = "Player " + i;
                if (playerHands[i - 1][1] == null) {
                    playerHands[i - 1][1] = card.getNumber() + " of " + card.getSuit() + ", ";
                } else
                playerHands[i - 1][1] += card.getNumber() + " of " + card.getSuit() + ", ";
            }
            points = calculatePoints(cardsCounterArray);
            pointsArray[i - 1][0] = i;
            pointsArray[i - 1][1] = points;

            System.out.println("Player " + i + " hand: ");
            player.print();
            System.out.println("Player " + pointsArray[i-1][0] + " has " + pointsArray[i-1][1] + " points");
        }

        sortPlayers(pointsArray);
        int numberOfWinners = multipleWinnersCheck(pointsArray);

        // prints the winner/s and their winning hand
        if (numberOfWinners > 1){
            System.out.println("Winners:");
            for (int i = 0; i < numberOfWinners; i++){
                System.out.println("Player " + pointsArray[i][0] + " with a score of " + pointsArray[i][1] + ", with the hand " + playerHands[pointsArray[i][0] - 1][1]);
            }
        }
        else
            System.out.println("The winner is Player " + pointsArray[0][0] + " with a score of " + pointsArray[0][1] + ", with the hand " + playerHands[pointsArray[0][0] - 1][1]);

        System.out.println("Player points: ");
        for (int[] arr : pointsArray) {
            System.out.println("Player " + arr[0] + " has " + arr[1] + " points");
        }
    }

    /**
     * Counts the number of each card that a player has
     * @param cardsCounterArray
     * @param card
     */
    private static void countCard(int[][] cardsCounterArray, Card card) {
        boolean cardExists = false;
        for (int[] arr : cardsCounterArray) {
            if (arr[0] == card.getNumber()) {
                arr[1]++;
                cardExists = true;
            }
        }
        if (!cardExists) {
            for (int[] arr : cardsCounterArray) {
                if (arr[0] == 0) {
                    arr[0] = card.getNumber();
                    arr[1] = 1;
                    break;
                }
            }
        }
    }

    /**
     * Checks the points array to see if there are multiple winners
     * @param pointsArray
     * @return returns the number of winners
     */
    private static int multipleWinnersCheck(int[][] pointsArray){
        int winnersPoints = pointsArray[0][1];
        boolean multWinners = true;
        int counter = 1;
        int numberOfWinners = 1;

        while (multWinners) {
            if (counter >= pointsArray.length) {
            break;
            }
            int points = pointsArray[counter][1];
            if (points == winnersPoints) {
            numberOfWinners++;
            counter++;
            } else {
            multWinners = false;
            }
        }

        return numberOfWinners;
    }

    /**
     * Calculates the points acquired
     * @param cardsCounterArray
     * @return returns the points acquired
     */
    private static int calculatePoints(int[][] cardsCounterArray) {
        int points = 0;
        for (int[] arr : cardsCounterArray) {
            int currPoints = arr[0] * (int)Math.pow(10, arr[1] - 1);
            if (currPoints > points)
                points = currPoints;
        }
        return points;
    }

    /**
     * Sorts the players by score using bubble sort
     * @param pointsArray
     */
    private static void sortPlayers(int[][] pointsArray) {
        for (int i = 0; i < pointsArray.length; i++) {
            for (int j = 0; j < pointsArray.length - 1; j++) {
                if (pointsArray[j][1] < pointsArray[j + 1][1]) {
                    int[] temp = pointsArray[j];
                    pointsArray[j] = pointsArray[j + 1];
                    pointsArray[j + 1] = temp;
                }
            }
        }
    }
}
