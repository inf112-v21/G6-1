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
    * Run the command `mvn exec:java - Dexec.mainClass=inf112.skeleton.app.Main`
    
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

GameStoppers' project is a simple board-game where the player can choose if they want to play single player or multiplayer. When starting the game, the player will be presented with a menu screen. Here the player have three options:
- Single player 
- Host game
- Join game

If the player chooses single player, the game board will be rendered. Here, the player navigates around a 12x14 grid board. The board have obstacles such as walls, lasers and holes which the player will loose damage or lives when hitting. 
The player moves are decided by game cards showing possible moves. The player must choose 5 cards out of 9 random cards each round. A player can use conveyors to move around more quickly. Furthermore, the player can visit checkpoints so that if the player dies it will not be moved to its start position. In order to win the game, the player must visit all three flags in the correct order. When this happens, a "You won!" screen will show. 

If the player click the host game button, a server will be connected and the player (host) will be shown a new screen where there is information about how many players there are in the game. When all the players have connected, the host can click the start game button and the game will start. Information about your players colour will be shown in the terminal window. Each player chooses their own cards and when all players have clicked that they are ready, the moves to the different players will be shown on the board. 

If the player click the join game button, they will be asked to enter the IP address. This information must be given from the player that is the host. When the player press the ready button, the game screen will be rendered without players og cards. This will first be shown when the host starts the game. 


![Overview](https://github.com/inf112-v21/GameStoppers/blob/master/classDiagram/UML-diagram_oblig3.png?raw=true)



**Main-class**
* Configures gdx
  
* Chooses how big the interface window will be, and title of the window.
    
* Runs Game()




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
    
* When running all tests at once, your computer may run out of memory. If this happens, just
try running less tests at once, or try running all of them again. 

