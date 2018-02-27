import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Goodness on 2/22/18.
 */
public class Deck {
    private List<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void makeFullDeck() {
        for(Suit suit : Suit.values()) {
            for(Rank rank: Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }
}