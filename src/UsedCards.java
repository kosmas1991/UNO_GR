import java.util.ArrayList;
import java.util.Collections;

public class UsedCards {
    private ArrayList<Card> usedCards = new ArrayList<>();

    public ArrayList<Card> getUsedCards() {
        return usedCards;
    }

    public void shuffleUsedCards () {
        Collections.shuffle(usedCards);
    }

    public void addUsedCard (Card card) {
        usedCards.add(card);
    }

    public Card getUpperUsedCard () {
        return usedCards.get(usedCards.size()-1);
    }
}
