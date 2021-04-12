# INF112 GameStoppers' RoboRally

This project is built with:

* Java 15.02
* JUnit 4.12
* Maven


## Installing and using the project

* Clone the project from this repo: 
  `git clone git@github.com:inf112-v21/GameStoppers.git`
* Installing Maven:
    * Open computer's console
    * Run the command `mvn clean install`
    * Run the command `mvn -Dexec.mainClass=inf112.skeleton.app.Main`
    
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

![Overview](https://github.com/inf112-v21/GameStoppers/blob/master/classDiagram/UML-diagram_oblig3.png?raw=true)



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
  
* When one person hosts a game, two game-pieces appear on the board. When a new player joins, one more appears.
Only one game-piece should appear per player joined.
  
* The online feature is still not fully operative. You can connect to each other, but each player creates it's own list with all the 
players and cards, instead of using a shared list

* When running all tests at once, your computer may run out of memory. If this happens, just
try running less tests at once, or try running all of them again. 

* When standing on the edge of an express conveyor you are moved two positions. This should be only one position.

* Occasionally the ready button fails to work in single player. If you reset and re-chose cards, this should work.

* Currently we display damage token and health in the GUI. These are implemented in the GUI but not back-end yet, as we needed
more time to complete it, but it will be implemented ASAP.
