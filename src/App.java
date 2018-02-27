import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Created by Goodness on 2/21/18.
 */
public class App {
    static Scanner sc = new Scanner(System.in);
    static List<Player> players = new LinkedList<>();

    public static void main(String[] args) {

        System.out.println("Welcome to BlackJack Game");

        //create player
        Player dealer = new Player("Dealer");
        //set player as the dealer
        dealer.setDealer(true);

        System.out.println("How many players would play this game.");

        int numOfPlayers = 0;
        try {
            numOfPlayers = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("only positive whole numbers without space is allowed here.");
        }

        for (int i = 1; i <= numOfPlayers; i++) {
            //default names
            players.add(new Player("player " + i));
        }
        //Customize Player names
        System.out.println("Enter player names ");
        int index = 0;
        while (index < numOfPlayers) {
            String name = sc.next();
            players.get(index).setName(name);
            System.out.println("------------");
            index++;
        }
        players.add(dealer);

        //create playing deck
        Deck deck = new Deck();
        //create table deck of cards
        deck.makeFullDeck();
        //shuffling the deck of cards.
        deck.shuffleCards();

        //Dealer deals 2 cards each to self and players from the deck of cards
        System.out.println("Dealer deals 2 cards each to the players.");
        for (int i = 1; i <= 2; i++) {
            players.forEach(player -> {
                dealer.dealCard(player, deck);
            });
        }

        //prompt players to hit or stand after initial round of cards served to them.
        players.forEach(player -> {

            while (player.getAction() != Hand.Action.STAND) {
                if (!player.isDealer() && player.getCards().size() == 5) {
                    player.stand();
                    player.showHand();
                    if (player.getTotalCardsPoint() <= 21) {
                        //player wins dealer
                        System.out.println(player.getName() + " beats Dealer\n");
                    } else {
                        //dealer wins player
                        System.out.println(player.getName() + " Loses\n");
                    }
                } else {
                    if (player.isDealer() && player.getCards().size() == 2) {
                        player.showHand();
                        hitOrStand(dealer, dealer, deck);
                    } else {
                        hitOrStand(dealer, player, deck);
                    }
                }
            }
        });

    }

    public static void hitOrStand(Player dealer, Player hand, Deck deck) {
        System.out.println(hand.getName() + ": Hit or Stand? (h or s)");
        String action = sc.next();
        if (action.equalsIgnoreCase("h")) {
            hand.hit();
            //Dealer deals card to player hand.
            dealer.dealCard(hand, deck);
            System.out.println("dealer deals another card to " + hand.getName());
        } else if (action.equalsIgnoreCase("s")) {
            hand.stand();
            hand.showHand();
            blackJackSummary(players);
        }
    }

    public static void blackJackSummary(List<Player> players) {
        Player dealer = players.stream().filter(player -> player.isDealer()).collect(Collectors.toList()).get(0);
        if (dealer.getAction() == Hand.Action.STAND) {
            System.out.println("BlackJack Game Summary");
            players.remove(dealer);
            for (Player player : players) {
                int totalHandValue = player.getTotalCardsPoint();
                player.showHand();
                if (totalHandValue <= 21 && totalHandValue > dealer.getTotalCardsPoint()) {
                    player.setBeatsDealer(true);
                }
                String report = player.isBeatsDealer() ? player.getName() + " wins Dealer\n" : player.getName() + " Loses\n";
                System.out.println(report);
            }
            long winningPlayers = players.stream().filter(player -> player.isBeatsDealer()).count();
            System.out.println(winningPlayers == 0 ? "Dealer Wins" : "Dealer Loses");
            sc.close();
        }
    }

}
