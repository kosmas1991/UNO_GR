import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // create n shuffle the deck
        Deck deck = new Deck();
        deck.shuffle();
        // create the used cards item
        UsedCards usedCards = new UsedCards();
        // get players info
        Scanner input = new Scanner(System.in);
        System.out.println("Whats your name?");
        String playerName = input.nextLine();
        System.out.println("How many bots? (Enter 2-4)");
        int noOfBots = input.nextInt();
        input.nextLine();
        Players players = new Players(playerName, noOfBots);
        // share 7 cards for each player and optional(commented) printing
        for (Player p : players.getPlayers())
            for (int i = 0; i < 7; i++) {
                p.addACard(deck.drawCard());
                deck.removeUpperCard();
            }
//        for (Player p : players.getPlayers()) {
//            System.out.println(p.getName());
//            for (int i=0 ; i<p.getPlayerCardsToString().size(); i++)
//            System.out.println(i+1 + ". " + p.getPlayerCardsToString().get(i));
//        }

        // drop upper deck card to used cards field
        usedCards.addUsedCard(deck.drawCard());
        deck.removeUpperCard();

        













    }
}
