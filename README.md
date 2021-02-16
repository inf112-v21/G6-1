# INF112 Maven template 
Simple skeleton with libgdx. 

## Description

GameStoppers' project is a simple board-game interface with working movement using arrow-keys on a computer
to navigate a figure around a 12x14 grid. The board-game will stop, and tell the localHumanPlayer they won, if
you navigate the figure on top of a flag.

![Overview](https://github.com/inf112-v21/GameStoppers/blob/master/classDiagram/Updated_ClassDiagram.png?raw=true)

## How code runs 

**Game-class:**
* Creates new game, and is used in Main to run the game.
  
* More will be implemented in this class in the future

**GameLogic-class:**
* Used to update, move, and check if localHumanPlayer can move. 
  
* Method to check if localHumanPlayer reaches flag
    
* Method to update position of localHumanPlayer and draw localHumanPlayer
    
* Method to normalize from pixels to tiles for movement and checking certain tiles. Will be used in other classes

**Graphics-class:**
    
* Initializes GUI settings
    
* Shows the board and localHumanPlayer
    
* Is in control of the game's graphics


**Shared-package**
* Currently unused, will be used for future development

**Main-class**
* Configures gdx
  
* Chooses how big the interface window will be, and title of the window.
    
* Runs Game()


**Summary**

The code runs using mainly libgdx and tiledmap to both generate the graphics of the board, and implement
    moving-methods. Within TiledMap there are methods to recognize what properties certain tiles has, given
    a coordinate(x,y) on the grid. Using this, we can use getX and getY methods from the localHumanPlayer to check if
    they match the coordinates of the flag. When this is the case, a method isGameOver() will become true,
    and tell graphics to end the game.

## Configuration instructions

## Operating instructions

* Run the Main-class from inf112.skeleton.app package, and a GUI will open.

* Move the character by using your arrow-keys (UP, DOWN, LEFT, RIGHT)

* Win the game by navigating the displayed character to one of the three black flags on the board.


## Credits

#### This is the list of people who can contribute (and who have contributed) code to the GameStoppers' project repository.

erlend223 https://github.com/erlend223

thomasRimmereide https://github.com/thomasRimmereide

num014 https://github.com/VildeHaugstad

sharifi98 https://github.com/sharifi98

Yassym06 https://github.com/Yassym06

Jethuestad https://github.com/Jethuestad

## Changelog ( Usually for programmers )