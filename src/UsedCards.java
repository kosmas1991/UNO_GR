import java.util.ArrayList;
import java.util.Collections;

public class UsedCards {
    private ArrayList<Card> usedCards = new ArrayList<>();

    public ArrayList<Card> getUsedCards() {
        return usedCards;
    }

    public void shuffleUsedCardsWithoutUpper () {
        Card temp = usedCards.get(usedCards.size() - 1);
        usedCards.remove(usedCards.size() - 1);
        Collections.shuffle(usedCards);
        usedCards.add(temp);
    }

    public void addUsedCard (Card card) {
        usedCards.add(card);
    }

    public Card getUpperUsedCard () {
        return usedCards.get(usedCards.size()-1);
    }
}
