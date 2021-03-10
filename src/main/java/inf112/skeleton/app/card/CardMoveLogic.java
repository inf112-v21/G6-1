package inf112.skeleton.app.card;

import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.Arrays;

public class CardMoveLogic {
    private final CardDeck cardDeck = new CardDeck();

    /**
     * This method returns the initial card position to where the cards are placed
     * on the game-board when a player gets a new deck of cards
     * @return card coordinates for the board
     */
    public  ArrayList<Float> resetCardCoordinates(){
        return new ArrayList<>(
                Arrays.asList(5555f, 3090f,
                        6080f, 3090f,
                        6605f, 3090f,
                        5555f, 2370f,
                        6080f, 2370f,
                        6605f, 2370f,
                        5555f, 1640f,
                        6080f, 1640f,
                        6605f, 1640f));
    }

    /**
     * Get the first player deck og card. This deck is automatically updated
     * by other methods and needs to be used only when starting the game.
     * @return new sorted player deck
     */
    public ArrayList<Card> playerDeck(){
        return this.cardDeck.dealNineCards();

    }
    /**
     *  Checks if a card is a card that changes the position of a player
     *  and not the direction.
     * @param card : Card of a player
     * @return boolean
     */
    public boolean moveTypeCard(Card card){
        return card.action == Action.MOVE_ONE || card.action == Action.MOVE_TWO|| card.action == Action.MOVE_THREE||card.action== Action.BACK_UP;
    }

    /**
     * Sets new position for the given card.
     * This function is used to move cards the player is holding
     * to the programming slots on the board in the correct order.
     * @param cardXPositionIndex X index of the card from cardCoordinates list
     * @param cardYPositionIndex Y index of the card from cardCoordinates list
     */
    public void updateCardPosition(int cardXPositionIndex, int cardYPositionIndex, Player player){
        int chosenCardListSize = player.chosenCards.size();
        if (chosenCardListSize < 6){
            player.cardCoordinates.set(cardYPositionIndex, 520f);
            if (chosenCardListSize == 1) player.cardCoordinates.set(cardXPositionIndex, 3945f);
            if (chosenCardListSize == 2) player.cardCoordinates.set(cardXPositionIndex, 4490f);
            if (chosenCardListSize == 3) player.cardCoordinates.set(cardXPositionIndex, 5020f);
            if (chosenCardListSize == 4) player.cardCoordinates.set(cardXPositionIndex, 5550f);
            if (chosenCardListSize == 5) player.cardCoordinates.set(cardXPositionIndex, 6090f);
        }else{
            player.cardCoordinates.set(cardXPositionIndex,player.cardCoordinates.get(cardXPositionIndex));
            player.cardCoordinates.set(cardYPositionIndex,player.cardCoordinates.get(cardYPositionIndex));
        }
    }

    /**
     * This method:
     * Moves card sprite to the programming slots on the board
     * Add the card in question to players chosenCard list.
     * The dummyPlayerDeck get one smaller
     * @param playerCardDeckIndex the index of the card in players cards on hand
     * @param cardXPositionIndex the index of the given card coordinate  X
     * @param cardYPositionIndex the index of the given card coordinate  Y
     * @param player the given player
     */

    public void moveCardWhenClicked(int playerCardDeckIndex, int cardXPositionIndex, int cardYPositionIndex, Player player){
        if (!player.movedCards.contains(playerCardDeckIndex)){
            player.chosenCards.add(player.playerDeck.get(playerCardDeckIndex));
            player.movedCards.add(playerCardDeckIndex);
            updateCardPosition(cardXPositionIndex, cardYPositionIndex, player);
        }
    }
}
