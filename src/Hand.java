import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Goodness on 2/21/18.
 */
public abstract class Hand {
    private List<Card> cards;
    private Action action;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void stand() {
        this.action = Action.STAND;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void flipCard(Card card) {
        card.setCardUp(true);
    }

    public final String getHandName() {
        Player p = (Player) this;
        return p.getName();
    }

    public void showHand() {
        Player p = (Player) this;
        String cardsInHand = "";
        int index = 0;
        for (Card card : cards) {
            if (index == 0 && p.isDealer() && p.getAction() == null) {
                //Don't flip the dealers first card in the first round.
            } else {
                flipCard(card);
            }
            index++;
            cardsInHand += card.toString() + "\n";
        }
        System.out.println(getHandName() + "'s Cards");
        cardsInHand += "Total Value: " + getTotalCardsPoint() + "\n";
        System.out.println(cardsInHand);
    }

    /**
     * The total cards value in hand
     *
     * @return
     */
    public int getTotalCardsPoint() {
        long totalAceCards = getCards().stream().filter(card -> card.getRank() == Rank.ACE).count();
        List<Card> nonAceCards = getCards().stream().filter(card -> card.getRank() != Rank.ACE).collect(Collectors.toList());
        int total = 0;
        for (Card card : nonAceCards) {
            if (card.isCardUp()) {
                //total of cards facing up.
                total += card.getRank().getValue();
            }
        }
        //Scoring Ace cards with either a 1 or 11 points
        if (totalAceCards > 0) {
            for (int i = 0; i < totalAceCards; i++) {
                //total of Ace cards facing up.
                total += ((total + 11) <= 21 ? (total + 11) : (total + 1));
            }
        }
        return total;
    }

    public void hit() {
        //request more card from dealer.
        this.action = Action.HIT;
    }

    enum Action {
        HIT, STAND;
    }
}