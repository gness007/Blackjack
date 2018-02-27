/**
 * Created by Goodness on 2/22/18.
 */
public class Player extends NamedEntity {
    public Player(String name) {
        this.setName(name);
    }
    private boolean dealer;

    public boolean isDealer() {
        return dealer;
    }

    public void setDealer(boolean dealer) {
        this.dealer = dealer;
    }
    private boolean beatsDealer;

    public boolean isBeatsDealer() {
        return beatsDealer;
    }

    public void setBeatsDealer(boolean beatsDealer) {
        this.beatsDealer = beatsDealer;
    }

    //to deal card to a hand
    public void dealCard(Hand hand, Deck deck) {
        if(deck.getCards().isEmpty()) {
            deck.makeFullDeck();
            deck.shuffleCards();
        }
        //deal top card
        Card card = deck.getCards().get(0);
        hand.addCard(card);
        deck.getCards().remove(card);
    }
}

