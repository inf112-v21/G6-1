# Manual Tests

## Player test

### Testing that the player does not go outside off the map:

How: Run the application running Main class. Proceed by choosing either host or join in terminal, then starts the game. A GUI will open which shows the board and the player, as well as the cards and the card-bar.

Move player position at the bottom left corner:

- Clicking on cards that moves the player down and left, will result in keeping the player in the same position, and no movement will occur due to the edge of the map.
- The player will remain on the same position.
  
Move player position at the top right corner:

- Clicking on cards that moves the player up and right, will result in keeping the player in the same position, and no movement will occur due to the edge of the map.
- The player will remain on the same position.

Move player position to the far left of the map:

- Clicking on cards that moves the player to the left when the player is on the far left of the map, will result in keeping the player in the same position, and no movement will occur due to the edge of the map.
- The player will remain on the same position.

Move player position all the way to the right of the map:

- Clicking on cards that moves the player to the right when the player is on the far right of the map, will result in keeping the player in the same position, and no movement will occur due to the edge of the map.
- The player will remain on the same position.

### Testing which direction the robot/player are facing:

How: Run the application running Main class. Proceed by choosing either host or join in terminal, then starts the game. A GUI will open which shows the board and the player, as well as the cards and the card-bar.

Player direction:

- The player should face in one of these directions after selected cards: north, south, west or east.
- After the round has started, the player is faced in the correct direction based on the sequence of chosen cards.
- Example: The player is facing north before the chosen cards leads to action. The chosen cards: Move 1, Move 2, Rotate Right, Move 3, Move 1. The player is now facing east.

## Card test

### Testing the card functionality:

(also covered by automatic tests)

How: Run the application running Main class. Proceed by choosing either host or join in terminal, then starts the game. A GUI will open which shows the board and the player, as well as the cards and the card-bar.

Select the cards you want to start the move-action of player:

- Each round the player get randomly selected deck of 9 cards. The cards that the player can randomly get, are: Move 1, Move 2, Move 3, Back up, U-turn, Rotate right, Rotate left.
- When a card is selected by click, then the card will be moved down to the chosen card-bar which determines the move-action of the player.

Action order of selected cards:

- The cards are placed in chronological order of click from left to right in the chosen card-bar.
- After selecting five cards, the player will perform the move-action in the exact order the cards were selected.

When player can perform move-action:

- Only after all of the 5 cards are selected, the player can perform the moves.
- When less than 5 cards is chosen, it should result in no movement.
- Cant attempt to choose more than five cards because the round is rendered on new, and the player has already performed its move-action.

## Menu screen test 

### Testing that the menu screen works as intended.

How: Run the application running Main class.

Select either single player, host game or join game.

- When single player is selected a game starts where there is only one player, and the game logic implemented for one player works.
- When host game is selected, the server is connected.
- Before clicking join game the player must type the IP adress to the host in the terminal. Then, when join game is then selected, the player gets connected to the server.