/**
 * Created by Goodness on 2/21/18.
 */
public enum Suit {
    HEARTS("Hearts"),SPADES("Spades"),CLUBS("Clubs"),DIAMONDS("Diamonds");
    private final String title;

    private Suit(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
