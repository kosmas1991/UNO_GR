import java.util.ArrayList;
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

        // drop upper deck card to used cards field and print it...
        usedCards.addUsedCard(deck.drawCard());
        deck.removeUpperCard();
        System.err.println("<--- Game starts --->");

        Card thrownCard;
        boolean forward = true;
        boolean winner = false;
        int position = Players.getNextPlayerPosition(forward) - 1; // 0 position
        int cardsToGet = 0;
        boolean nextOneLosesTurn = false;
        //game loop
        while (!winner) { ////// ////// ////// ////// ////// ///// //// ///// ///// ////
            System.out.println("Table card: " + usedCards.getUpperUsedCard().getNumber() + " " + usedCards.getUpperUsedCard().getColor());
            // check if position out of bound either forwards or backwards
            // also selects current player
            if (position < 0)
                position = players.getPlayers().size() - 1;
            else if (position > players.getPlayers().size() - 1)
                position = 0;
            if (nextOneLosesTurn) {
                if (forward)
                    position++;
                else
                    position--;
                nextOneLosesTurn = false;
                if (position < 0)
                    position = players.getPlayers().size() - 1;
                else if (position > players.getPlayers().size() - 1)
                    position = 0;
            }
            System.out.println("Current player: " + players.getPlayers().get(position).getName());
            Player currentPlayer = players.getPlayers().get(position);
            if (cardsToGet > 0) {
                for (int i = cardsToGet; i <= 0; i--) {
                    currentPlayer.addACard(deck.drawCard());
                    deck.removeUpperCard();
                }
                System.out.println("Player: " + currentPlayer.getName() + " drew " + cardsToGet + " cards");
                cardsToGet = 0;
            }

            // choose n check card validation
            boolean valid = false;
            int playerTry = 1;
            while (!valid) { ////// ////// ////// ////// ////// ///// //// ///// ///// ////
                if (currentPlayer.isBot()) {
                    System.out.println("Bot " + currentPlayer.getName() + " is playing now");
                    thrownCard = currentPlayer.chooseCardBot(usedCards.getUpperUsedCard().getNumber(), usedCards.getUpperUsedCard().getColor());
                    if (thrownCard == null) {
                        if (playerTry < 2) {
                            System.out.println("Bot: " + currentPlayer.getName() + " takes a card from the table.");
                            playerTry++;
                            currentPlayer.addACard(deck.drawCard());
                            deck.removeUpperCard();
                        } else {
                            System.out.println(currentPlayer.getName() + " paei paso");
                            valid = true;
                        }
                    } else { // to bot petaei swsti karta
                        valid = true;
                        System.out.println(currentPlayer.getName() + " throws " + thrownCard.getNumber() + " " + thrownCard.getColor());
                        if (thrownCard.getNumber().equals("allagi foras")) {
                            if (forward)
                                forward = false;
                            else
                                forward = true;
                        }
                        if (thrownCard.getNumber().equals("o epomenos pairnei 2")) {
                            cardsToGet = 2;
                        }
                        if (thrownCard.getNumber().equals("o epomenos xanei seira")) {
                            nextOneLosesTurn = true;
                        }
                        usedCards.addUsedCard(thrownCard);
                        // svinw tin karta pou petakse to bot
                        currentPlayer.deleteSpecifiedCard(thrownCard.getNumber(), thrownCard.getColor());

                    }
                    // IF NOT A BOT ||||                while (!valid || playerTry <2)
                } else {
                    ArrayList<String> oikartesmou = new ArrayList<>();
                    oikartesmou = currentPlayer.getPlayerCardsToString();
                    int counter = 1;
                    for (String i : oikartesmou) {
                        System.out.println(counter + ". " + i);
                        counter++;
                    }
                    System.out.println(counter + ". " + "no have");
                    Scanner input2 = new Scanner(System.in);
                    System.out.println("Choose your card");
                    int option = input2.nextInt() - 1;
                    if (option < currentPlayer.getPlayerCards().size()) {
                        thrownCard = currentPlayer.getPlayerCards().get(option);
                        // an petakses lathos karta ---- petaw swsti karta
                        if (currentPlayer.cardValidation(usedCards.getUpperUsedCard().getNumber(), usedCards.getUpperUsedCard().getColor(), thrownCard)) {
                            valid = true;
                            System.out.println("Good choice, you threw " + thrownCard.getColor() + " " + thrownCard.getNumber());
                            if (thrownCard.getNumber().equals("allagi foras")) {
                                if (forward)
                                    forward = false;
                                else
                                    forward = true;
                            }
                            if (thrownCard.getNumber().equals("o epomenos pairnei 2")) {
                                cardsToGet = 2;
                            }
                            if (thrownCard.getNumber().equals("o epomenos xanei seira")) {
                                nextOneLosesTurn = true;
                            }
                            usedCards.addUsedCard(thrownCard);
                            // svinw tin karta pou petaksa
                            currentPlayer.deleteSpecifiedCard(thrownCard.getNumber(), thrownCard.getColor());
                        }
                    } else { // EPELEKSA NO HAVE
                        System.out.println("No have pressed! ");
                        playerTry++;
                        if (playerTry > 2) {
                            valid = true;
                            System.out.println("Paw PASO");
                            break;
                        }
                        currentPlayer.addACard(deck.drawCard());
                        deck.removeUpperCard();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } // telos gyrou paixti
            if (forward)
                position++;
            else position--;
        } // telos paixnidiou


    }

}
