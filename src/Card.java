/**
 * Created by Goodness on 2/21/18.
 */
public class Card {
    private Suit suit;
    private Rank rank;
    private boolean cardUp;

    public Card(Suit suit, Rank rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public boolean isCardUp() {
        return cardUp;
    }

    public void setCardUp(boolean cardUp) {
        this.cardUp = cardUp;
    }

    @Override
    public String toString() {
        String cardFace = "";
        if(cardUp) {
            cardFace += rank.name()+" of "+suit.getTitle();
        } else {
            cardFace += "Card face down [Hidden].";
        }
        return cardFace;
    }
}