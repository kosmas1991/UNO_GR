import java.util.ArrayList;

public class Player {
    private final String name;
    private final int position;
    private final boolean isBot;
    private final ArrayList<Card> playerCards = new ArrayList<>();

    public Player(String name, int position, boolean isBot) {
        this.name = name;
        this.position = position;
        this.isBot = isBot;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public boolean isBot() {
        return isBot;
    }

    public void addACard(Card card) {
        playerCards.add(card);
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public ArrayList<String> getPlayerCardsToString() {
        ArrayList<String> kartes = new ArrayList<>();
        for (Card card : playerCards)
            kartes.add(card.getColor() + " " + card.getNumber());
        return kartes;
    }

    public int getPlayerNumberOfCards() {
        return playerCards.size();
    }

    public Card chooseCardBot(String number, String color) {
        Card theChosenOne = null;
        for (Card card : playerCards) {
            if (card.getColor().equals(color) || card.getNumber().equals(number) || card.getColor().equals("mayro"))
                theChosenOne = card;
        }
        return theChosenOne;
    }

    public Card chooseCardBot (String color) { ////// PAIZEI MPALANTER, METHOD OVERLOADING
        Card theChosenOne = null;
        for (Card card : playerCards) {
            if (card.getColor().equals(color))
                theChosenOne = card;
        }
        return theChosenOne;
    }

    public boolean cardValidation(String number, String color, Card thrownCard) {
            if (thrownCard.getColor().equals(color) || thrownCard.getNumber().equals(number) || thrownCard.getColor().equals("mayro"))
                return true;
            else
                return false;
    }

    public boolean cardValidation(String color, String playerSelectColor) {
        if (color == playerSelectColor)
            return true;
        else
            return false;
    }

    public void deleteSpecifiedCard (String number, String color) {
        int counter = 0;
        for (Card card : playerCards) {
            if (card.getNumber().equals(number) && card.getColor().equals(color)) {
                playerCards.remove(counter);
                break;
            }
            counter++;
        }
    }
}
