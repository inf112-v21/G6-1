package inf112.skeleton.app.graphics;

import inf112.skeleton.app.card.CardMoveLogic;
import inf112.skeleton.app.player.HumanPlayer;
import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Color;
import inf112.skeleton.app.shared.Direction;

import java.util.ArrayList;

public class testClass {
    private CardMoveLogic cardMoveLogic = new CardMoveLogic();
    public HumanPlayer humanPlayer1;
    public HumanPlayer humanPlayer2;

    public ArrayList<Player> createhuman(){
        ArrayList<Player>players = new ArrayList<>();
        humanPlayer1 = new HumanPlayer(Direction.NORTH, 6969,Color.ORANGE);
        humanPlayer1.playerDeck = cardMoveLogic.playerDeck();
        humanPlayer2 = new HumanPlayer(Direction.NORTH, 6969,Color.GREEN);
        humanPlayer2.playerDeck = cardMoveLogic.playerDeck();
        players.add(humanPlayer1);
        players.add(humanPlayer2);
        return players;
    }

}
