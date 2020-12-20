import java.util.ArrayList;

public class Player {
    private final String name;
    private final int position;
    private final ArrayList<Card> playerCards = new ArrayList<>();

    public Player(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void addACard (Card card) {
        playerCards.add(card);
    }

    public ArrayList<Card> getPlayerCards() {
        return playerCards;
    }

    public ArrayList<String> getPlayerCardsToString () {
        ArrayList<String> kartes = new ArrayList<>();
        for (Card card : playerCards)
            kartes.add(card.getColor() + " " + card.getNumber());
        return kartes;
    }

    public int getPlayerNumberOfCards () {
        return playerCards.size();
    }



}
