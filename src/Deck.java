import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    ArrayList<Card> cards;

    public Deck() {
        this.cards = create();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards2) {
        this.cards.addAll(cards2);
    }

    public ArrayList<Card> create () {
        ArrayList<Card> newDeck = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        colors.add("prasino");
        colors.add("kitrino");
        colors.add("mple");
        colors.add("kokkino");
        numbers.add("0");
        numbers.add("1");
        numbers.add("2");
        numbers.add("3");
        numbers.add("4");
        numbers.add("5");
        numbers.add("6");
        numbers.add("7");
        numbers.add("8");
        numbers.add("9");
        numbers.add("o epomenos xanei seira");
        numbers.add("o epomenos pairnei 2");
        numbers.add("allagi foras");
        for (String color : colors)
            for (String number : numbers)
                newDeck.add(new Card(color, number));

        for (int k = 0 ; k < 4 ; k++) {
            newDeck.add(new Card("mayro", "o epomenos pairnei 4"));
            newDeck.add(new Card("mayro", "balander"));
        }

        return newDeck;
    }

    public void shuffle () {
        Collections.shuffle(cards);
    }

    public void printDeck (ArrayList<Card> cards) {
        for (Card c : cards)
            System.out.println(c.getColor() + " " + c.getNumber());
    }

    public Card drawCard () {
        return cards.get(0);
    }

    public void removeUpperCard () {
        cards.remove(0);
    }

    public int getDeckAvailableCards (){
        return cards.size();
    }

    public void emptyDeck () {
        for (int i=0; i<cards.size() - 1; i++){
            cards.remove(i);
        }
    }


}
