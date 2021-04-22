package inf112.skeleton.app.card;

import inf112.skeleton.app.player.Player;
import inf112.skeleton.app.shared.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CardMoveLogic {
    private final CardDeck cardDeck = new CardDeck();
    ArrayList<Card> deck = cardDeck.getCardDeck();
    /**
     * This method returns the initial card position to where the cards are placed
     * on the game-board when a player gets a new deck of cards
     * The cardCoordinates is indexed as follows
     * 0 is x pos and 1 is y pos for the first card in the playerDeck and so on.
     *
     * @return card coordinates for the board
     */
    public ArrayList<Float> resetCardCoordinates(){
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
     * This method gives a randomized deck of nine cards.
     * @return new randomized playerDeck
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
     * This function is used to move cards the player is holding,
     * to the programming slots on the board in the correct order.
     * The list movedCards holds the position of the moved cards from the player deck.
     * The card coordinates for the card sprites in the list cardCoordinates is indexed as follows
     * 0 is x pos and 1 is y pos for the first card in the playerDeck and so on.
     * Therefore the x coordinate for a card in the a position n in playerDeck
     * will be playerDeck index*2 and the y coordinate the same +1.
     * @param player the player
     */
    public void updateCardPosition(Player player){
        int chosenCardListSize = player.chosenCards.size();
        ArrayList<Float> cardYPos = new ArrayList<>(Arrays.asList(3945f,4490f,5020f,5550f,6090f));
        if (chosenCardListSize < 6){
            int i = 0;
            for(int playerCard : player.movedCards){
                player.cardCoordinates.set(playerCard*2+1, 520f);
                player.cardCoordinates.set(playerCard*2, cardYPos.get(i));
                i++;
            }
        }
    }

    /**
     * This method is used to give å player a way to regret the choice of cards to the programming slots.
     * It reset the cards from the programming slots on the board to the initial card position on the board.
     * It does not give the player a new deck of card but keeps the old one.
     *
     * @param player the player who want to reset the card.
     */
    public void resetCard(Player player){
        player.movedCards = new ArrayList<>();
        player.chosenCards = new ArrayList<>();
        player.cardCoordinates = resetCardCoordinates();
    }


    /**
     * This method:
     * Moves card sprite when clicked to the programming slots on the board.
     * Add the card in question to players chosenCard list.
     * Add the playerCardDeck index to the movedCards list.
     * @param playerCardDeckIndex the index of the card in players cards on hand
     * @param player the given player
     */
    public void moveCardWhenClicked(int playerCardDeckIndex, Player player){
        if (!player.movedCards.contains(playerCardDeckIndex) && player.movedCards.size() <= 4 ){
            player.chosenCards.add(player.playerDeck.get(playerCardDeckIndex));
            player.movedCards.add(playerCardDeckIndex);
            updateCardPosition(player);
        }
    }

    /**
     * This method convert ArrayList<Card> chosenCard into ArrayList<HashMap<Integer(Priority), Action>>
     * to make CardList possible to send on network.
     * @param chosenCard list of players chosen cards for one round
     * @return ArrayList<HashMap<Integer, Action>>
     */
    public ArrayList<HashMap<Integer, Action>> convertToSendAbleCard(ArrayList<Card> chosenCard){
        ArrayList<HashMap<Integer, Action>> senAbleChosenCard = new ArrayList<>();
        for(Card card: chosenCard){
            HashMap<Integer, Action> sendAbleCard = new HashMap<>();
            sendAbleCard.put(card.priority,card.action);
            senAbleChosenCard.add(sendAbleCard);
        }
        return senAbleChosenCard;
    }

    /**
     * This method convert ArrayList<HashMap<Integer(Priority), Action>> to ArrayList<Card>
     * to make cards from CardList possible to render in graphics
     * @param sendAbleCards
     * @return
     */
    public ArrayList<Card> convertToCardObject(ArrayList<HashMap<Integer, Action>> sendAbleCards){
        ArrayList<Card> cardsAsObject = new ArrayList<>();
        for(HashMap<Integer, Action> sendAbleCard: sendAbleCards){
            int pri = (int) sendAbleCard.keySet().toArray()[0];
            Action action = sendAbleCard.get(pri);
            for(Card card : deck){
                if (card.priority == pri && card.action == action){
                    cardsAsObject.add(card);

                }
            }
        }
        return cardsAsObject;

    }
}
