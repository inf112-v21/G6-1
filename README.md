# INF112 GameStoppers' RoboRally

This project is built with:

* Java 15.02
* JUnit 4.11
* Maven


## Installing and using the project

* Clone the project from this repo
* Installing Maven:
    * Open computer's consol
    * Run the command "mvn clean install"
    * Run the command "mvn -Dexec.mainClass=inf112.skeleton.app.Main
    
* In the project overview, go to src/main/java/inf112/skeleton/app and run Main.java

* If you use a Mac, go to "Run" -> "Edit configuration" -> "VM Options" and enter "-XstartOnFirstThread" in that field


## Playing online

Current solution is using the Hamachi software for online play.
Hamachi can be downloaded from https://www.vpn.net , and is a software
to create a LAN between networks connected

When downloaded, you need to either log in, or create a user.
Open the sofware and choose "Network" -> "Create network".
Choose an IP to use, starting with 5.x.x.x, and a password to connect to
the network you created. The other players goes to "Network" -> "Join an existing network",
entering the same IP you chose when creating the network.

If done correctly, you should be able to see each others connection in the application.

The IP used to connect to the server will be the same as the host's IP given from Hamachi
## Description

GameStoppers' project is a simple board-game interface with working movement using arrow-keys on a computer
to navigate a figure around a 12x14 grid. The board-game will stop, and tell the localHumanPlayer they won, if
you navigate the figure on top of a flag.

![Overview](https://github.com/inf112-v21/GameStoppers/blob/master/classDiagram/UML-diagram_oblig2.png?raw=true)



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



## Credits

#### This is the list of people who can contribute (and who have contributed) code to the GameStoppers' project repository.

erlend223 https://github.com/erlend223

thomasRimmereide https://github.com/thomasRimmereide

num014 https://github.com/VildeHaugstad

sharifi98 https://github.com/sharifi98

Yassym06 https://github.com/Yassym06

Jethuestad https://github.com/Jethuestad


## Known bugs

* Currently throws "WARNING: An illegal reflective access operation has occurred", 
however the project still runs and it does not affect performance.

* When selecting cards, if you attempt selecting an empty slot of an already used card the program will think there is a card there. 
  Then move the card already in the playing
 slots one to the right, leaving an empty spot where it was moved from.
  
* When playing single player you have to be patient when selecting cards, because of the error mentioned above.
 This is caused by poor optimization, and if cards are selected too quickly the graphics struggle to keep up, 
 and the wrong card will be selected.
  
* When one person hosts a game, two game-pieces appear on the board. When a new player joins, one more appears.
Only one game-piece should appear per player joined.
  
* Sometimes, when selecting host game, cards will appear in the slots when selected.
Ignore this.

* Server is not currently stopped propperly when winning a game. This will be fixed ASAP.
  
