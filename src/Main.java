import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
        System.out.println("How many bots? (Enter 2-3)");
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
        do {
            usedCards.addUsedCard(deck.drawCard());
            deck.removeUpperCard();
        } while (usedCards.getUpperUsedCard().getColor().equals("mayro"));
        System.err.println("<--- Game starts --->");

        Card thrownCard;
        boolean forward = true;
        boolean winner = false;
        int position = Players.getNextPlayerPosition(forward) - 1; // 0 position
        int cardsToGet = 0;
        boolean nextOneLosesTurn = false;
        String playerSelectColor = null;
        //game loop
        while (!winner) { ////// ////// ////// ////// ////// ///// //// ///// ///// ////
            System.out.println("                " + "Table card: " + usedCards.getUpperUsedCard().getNumber() + " " + usedCards.getUpperUsedCard().getColor());
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
                for (int i = cardsToGet; i > 0; i--) {
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
                    if (playerSelectColor == null)
                        thrownCard = currentPlayer.chooseCardBot(usedCards.getUpperUsedCard().getNumber(), usedCards.getUpperUsedCard().getColor());
                    else
                        thrownCard = currentPlayer.chooseCardBot(playerSelectColor);
                    if (thrownCard == null) { // den exei karta na petaksei
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
                        playerSelectColor=null;
                        System.out.println("                " +currentPlayer.getName() + " throws " + thrownCard.getNumber() + " " + thrownCard.getColor());

                        usedCards.addUsedCard(thrownCard);
                        // svinw tin karta pou petakse to bot
                        currentPlayer.deleteSpecifiedCard(thrownCard.getNumber(), thrownCard.getColor());
                        if (thrownCard.getNumber().equals("allagi foras")) {
                            forward = !forward;
                        }
                        if (thrownCard.getNumber().equals("o epomenos pairnei 2")) {
                            cardsToGet = 2;
                        }
                        if (thrownCard.getNumber().equals("o epomenos xanei seira")) {
                            nextOneLosesTurn = true;
                        }
                        if (thrownCard.getNumber().equals("balander")) {
                            //playerSelectColor = "kokkino";
                            int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
                            switch (randomNum) {
                                case 1: {playerSelectColor = "kokkino"; break;}
                                case 2: {playerSelectColor = "prasino"; break;}
                                case 3: {playerSelectColor = "kitrino"; break;}
                                case 4: {playerSelectColor = "mple"; break;}
                            }
                            System.out.println("Bot " + currentPlayer.getName() + " chooses " + playerSelectColor);
                        }
                        if (thrownCard.getNumber().equals("o epomenos pairnei 4")) {
                           // playerSelectColor = "kokkino";
                            int randomNum = ThreadLocalRandom.current().nextInt(1, 4 + 1);
                            switch (randomNum) {
                                case 1: {playerSelectColor = "kokkino"; break;}
                                case 2: {playerSelectColor = "prasino"; break;}
                                case 3: {playerSelectColor = "kitrino"; break;}
                                case 4: {playerSelectColor = "mple"; break;}
                            }
                            System.out.println("Bot " + currentPlayer.getName() + " chooses " + playerSelectColor);
                            cardsToGet=4;
                        }

                    }
                    // IF NOT A BOT ||||                while (!valid)
                } else {
                    ArrayList<String> oikartesmou;
                    oikartesmou = currentPlayer.getPlayerCardsToString();
                    int counter = 1;
                    for (String i : oikartesmou) {
                        System.out.println(counter + ". " + i);
                        counter++;
                    }
                    System.out.println(counter + ". " + "no have");
                    Scanner input2 = new Scanner(System.in);
                    //
                    System.out.println("\tDeck: " + deck.getDeckAvailableCards() + "\tTable: " + usedCards.getUsedCards().size());
                    System.out.println();
                    for (Player player : players.getPlayers())
                        System.out.print(player.getName() + ": " + player.getPlayerNumberOfCards() + "\t");
                    System.out.println();
                    //
                    System.out.println("Choose your card");
                    int option = input2.nextInt() - 1;
                    if (option < currentPlayer.getPlayerCards().size()) {
                        thrownCard = currentPlayer.getPlayerCards().get(option);
                        // an petakses lathos karta ---- petaw swsti karta
                        if (playerSelectColor == null) {
                            if (currentPlayer.cardValidation(usedCards.getUpperUsedCard().getNumber(), usedCards.getUpperUsedCard().getColor(), thrownCard)) {
                                valid = true;
                                System.out.println("Good choice, you threw " + thrownCard.getColor() + " " + thrownCard.getNumber());

                                usedCards.addUsedCard(thrownCard);
                                // svinw tin karta pou petaksa
                                currentPlayer.deleteSpecifiedCard(thrownCard.getNumber(), thrownCard.getColor());
                                if (thrownCard.getNumber().equals("allagi foras")) {
                                    forward = !forward;
                                }
                                if (thrownCard.getNumber().equals("o epomenos pairnei 2")) {
                                    cardsToGet = 2;
                                }
                                if (thrownCard.getNumber().equals("o epomenos xanei seira")) {
                                    nextOneLosesTurn = true;
                                }
                                if (thrownCard.getNumber().equals("balander")) {
                                    //playerSelectColor = "kokkino";
                                    System.out.printf("\nSelect the desired color\n1. kokkino\n2. prasino\n3. kitrino\n4. mple\n");
                                    int color = input.nextInt();
                                    switch (color) {
                                        case 1: {playerSelectColor = "kokkino"; break;}
                                        case 2: {playerSelectColor = "prasino"; break;}
                                        case 3: {playerSelectColor = "kitrino"; break;}
                                        case 4: {playerSelectColor = "mple"; break;}
                                    }
                                    System.out.println(currentPlayer.getName() + " chooses " + playerSelectColor);
                                }
                                if (thrownCard.getNumber().equals("o epomenos pairnei 4")) {
                                   // playerSelectColor = "kokkino";
                                    System.out.printf("\nSelect the desired color\n1. kokkino\n2. prasino\n3. kitrino\n4. mple\n");
                                    int color = input.nextInt();
                                    switch (color) {
                                        case 1: {playerSelectColor = "kokkino"; break;}
                                        case 2: {playerSelectColor = "prasino"; break;}
                                        case 3: {playerSelectColor = "kitrino"; break;}
                                        case 4: {playerSelectColor = "mple"; break;}
                                    }
                                    System.out.println(currentPlayer.getName() + " chooses " + playerSelectColor);
                                    cardsToGet = 4;
                                }
                            }
                        }else {
                            if (currentPlayer.cardValidation(thrownCard.getColor(), playerSelectColor)) {
                                valid = true;
                                playerSelectColor = null;
                                System.out.println("Good choice, you threw " + thrownCard.getColor() + " " + thrownCard.getNumber());
                                if (thrownCard.getNumber().equals("allagi foras")) {
                                    forward = !forward;
                                }
                                if (thrownCard.getNumber().equals("o epomenos pairnei 2")) {
                                    cardsToGet = 2;
                                }
                                if (thrownCard.getNumber().equals("o epomenos xanei seira")) {
                                    nextOneLosesTurn = true;
                                }
                                if (thrownCard.getNumber().equals("balander")) {
                                   // playerSelectColor = "kokkino";
                                    System.out.printf("\nSelect the desired color\n1. kokkino\n2. prasino\n3. kitrino\n4. mple\n");
                                    int color = input.nextInt();
                                    switch (color) {
                                        case 1: {playerSelectColor = "kokkino"; break;}
                                        case 2: {playerSelectColor = "prasino"; break;}
                                        case 3: {playerSelectColor = "kitrino"; break;}
                                        case 4: {playerSelectColor = "mple"; break;}
                                    }
                                    System.out.println(currentPlayer.getName() + " chooses " + playerSelectColor);
                                }
                                if (thrownCard.getNumber().equals("o epomenos pairnei 4")) {
                                   // playerSelectColor = "kokkino";
                                    System.out.printf("\nSelect the desired color\n1. kokkino\n2. prasino\n3. kitrino\n4. mple\n");
                                    int color = input.nextInt();
                                    switch (color) {
                                        case 1: {playerSelectColor = "kokkino"; break;}
                                        case 2: {playerSelectColor = "prasino"; break;}
                                        case 3: {playerSelectColor = "kitrino"; break;}
                                        case 4: {playerSelectColor = "mple"; break;}
                                    }
                                    System.out.println(currentPlayer.getName() + " chooses " + playerSelectColor);
                                    cardsToGet=4;
                                }
                                usedCards.addUsedCard(thrownCard);
                                // svinw tin karta pou petaksa
                                currentPlayer.deleteSpecifiedCard(thrownCard.getNumber(), thrownCard.getColor());
                            }
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
                if (currentPlayer.getPlayerCards().size() == 1)
                    System.out.println("Player: " + currentPlayer.getName() + " says  ------>>> UNO <<<-------    !!!!");
                if (currentPlayer.getPlayerCards().size() == 0) {
                    winner = true;
                    System.out.println("Player: " + currentPlayer.getName() + " WON !!!!!!!!!!!!!!!!!");
                }
            } // telos gyrou paixti
            if (deck.getDeckAvailableCards()<6) {
                for (int i=0; i<deck.getDeckAvailableCards() - 1; i++) {
                    usedCards.addToUsedCardsTo0Position(deck.drawCard());
                    deck.removeUpperCard();
                }
                usedCards.shuffleUsedCardsWithoutUpper();
                System.out.println("    ---> Caution: table is empty, RESHUFFLING ...  <<--- ................................................................");
                deck.setCards(usedCards.getUsedCards());
                deck.removeUpperCard();
                usedCards.deleteAllUsedExceptUpper();
            }
            if (forward)
                position++;
            else position--;
        } // telos paixnidiou


    }

}
