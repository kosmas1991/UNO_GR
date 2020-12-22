import java.util.ArrayList;
import java.util.Collections;

public class UsedCards {
    private final ArrayList<Card> usedCards = new ArrayList<>();

    public ArrayList<Card> getUsedCards() {
        return usedCards;
    }

    public void shuffleUsedCardsWithoutUpper () {
        Card temp = usedCards.get(usedCards.size() - 1);
        usedCards.remove(usedCards.size() - 1);
        Collections.shuffle(usedCards);
        usedCards.add(temp);
    }

    public void deleteAllUsedExceptUpper () {
        Card tempCard = usedCards.get(usedCards.size() - 1);
        usedCards.clear();
        usedCards.add(tempCard);
    }


    public void addUsedCard (Card card) {
        usedCards.add(card);
    }

    public void addToUsedCardsTo0Position (Card card) {
        usedCards.add(0,card);
    }

    public Card getUpperUsedCard () {
        return usedCards.get(usedCards.size()-1);
    }
}
