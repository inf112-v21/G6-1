#Obligatorisk oppgave 3

##Team og prosjekt

Slik som i oblig 2 startet vi denne sprinten med sprint planlegging. Vi lagde en TODO liste med funksjonalitet som trengte forbedring i koden fra forrige innlevering. Denne inkluderte i hovedsak å få nettverk til å fungere optimalt, så det har vært en prioritet under denne innleveringen.

Deretter definerte vi brukerhistorier for krav som vi ønsket å implementere. Vi fikk fullført MVP kravene (unntatt nettverk), ved forrige innlevering, så vi diskuterte hvilke funksjonaliteter vi ønsket å få på plass utover disse til oblig 3 og 4 og hvilke vi ønsket å starte først med.

Vi endret også litt på rollene forrige innlevering. Disse har fungert fint så vi har ikke sett behovet for å endre mer på rollefordelingen. Hossein og Thomas har fortsatt på nettverk, Erlend har jobbet med implementering av spill logikk og Yasmin og Vilde har jobbet med GUI.

Prosjektmetodikken fungerer fortsatt godt. Under denne innleveringen har flere av gruppemedlemmene hatt større prosjekt i andre fag, så vi har avtalt dager hvor vi ikke har gjort daily scrum. Grunnen til dette er at ikke alle har hatt mulighet til å jobbe litt med oppgaven hver dag, så det hadde ikke vært noe nytt å gjøre rede for i daily scrum disse dagene. De dagene vi har hatt daily scrum og møter så har alle deltatt. Fortsatt fungerer også slack bra som kommunikasjon og alle gruppemedlemmene er flinke til å være aktive og svare på henvendelser.

Under denne innleveringen har vi som tidligere jobbet mye med parprogrammering og mobprogrammering. Vi har fortsatt med arbeidsøkter over zoom hvor vi går i breakoutrooms etter hvilke deler av koden vi jobber med. Kommunikasjonen mellom gruppemedlemmene er fortsatt veldig god. Om noen av oss har problemer med den delen av oppgaven vi jobber med, sender vi en melding i slack og gjerne en zoom link og som oftest hopper hele gruppen på for å finne ut av problemet i fellesskap. 

###Sprint retrospect

Under denne sprinten har vi ikke hatt mulighet til å planlegge like godt som i de andre sprintene, da andre fag har krevd en større arbeidsmengde. Vårt forbedringspotensiale til neste sprint er å planlegge og ha en bedre oversikt over møtetider. Denne sprinten har vært preget av flere mer impulsive møter, og dette vil vi prøve å unngå til neste sprint. 

Videre må vi bli flinkere på å kommentere kode når vi er ferdig med å implementere en metode slik at det er lettere for andre teammedlemmer å jobbe med koden. I sammenheng med dette må vi også forbedre oss på å slette ubrukt kode fortløpende. Vi oppdaget at vi satt med en del ubrukt kode på slutten av sprinten. 

I forhold til koden har vi fått implementert mye av funksjonaliteten vi planlagte i sprintplanning. Vi har implementert life og damagetoken, her har vi fått inn GUI delen, men backend gjenstår. I terminalen får spilleren opp om den har tatt skade, men det vises ikke på selve brettet. Vi ønsket også å få til en tekstboks i menyskjermen hvor man kan skrive inn IP adresse når man vil joine et spill, men dette må også foreløpig gjøres i terminal. 

Ellers synes vi at samarbeidet og kommunikasjonen fungerer bra, og dette vil vi opprettholde til neste sprint. 

Vi ser også at det er en forskjell i commits blant teammedlemmene. Årsaken til dette er at git ikke registrerer og linker commitsene til to av teammedlemmene. Vi bruker også en del mobprogrammering som gjør at fordelingen av commits er litt ujevn. Alle gruppemedlemmene bidrar med planlegging, kode og gjennomføring.  
##Krav

Til obligatorisk oppgave 2 fikk vi implementert alle MVP kravene, unntatt nettverk. Dette var et mål å få til under denne sprinten. I tillegg til MVP kravene ønsket vi å implementere følgende:

- GUI startskjerm.
  
- Laser
- Tannhjul
- Hull
- To forskjellige typer samlebånd
- At en spiller må besøke flaggene i rett rekkefølge for å vinne
- Skade
- Lifetokens
- Knapp for å bekrefte valg
- Knapp for å resette valg

Skade og lifetokens har vi fått implementert i GUI, men mangler å knytte backend opp mot GUI skjerm. Foreløpig kan en spiller se skade og liv i konsollen.

Skjermbilde av en del av projectboard er lagt ved i deliverables, det komplette projectboard finnes her: https://github.com/inf112-v21/GameStoppers/projects/4?fullscreen=true

###Brukerhistorier

**1) As a user, I want the networking and multiplayer function to work so that it is possible to have several players.**

    Acceptance criteria:
- I can see all players on the board
- I can see the other players executed moves on the board.

    
    Work tasks:
- Render the different players on the board.
- Send the different players' moves to all players.

**2) As a user, I want to have a start screen where I can choose between single player and multiplayer, and if I want to host or join a game, so that I can choose what type of game I want to play.**

    Acceptance criteria:
- I can choose between singleplayer, host or join.
- I can decide when I am ready to play with a start game button

    
    Work tasks:
- Create GUI start screen.
- Implement a function where the right type of game starts depending on the user's choice.
- Create a start game button. 

**3) As a user, I want the player to move one position in the right direction if the player is standing on a yellow conveyer, so that the conveyor works according to game rules.**

    Acceptance criteria:
- The board piece moves one position in the conveyors direction.


    Work tasks:  
- Implement method for moving board piece one position when standing on conveyor.

**4) As a user, I want the player to move two positions in the right direction if the player is standing on a blue conveyer, so that the conveyor works according to game rules.**

    Acceptance criteria:
- The board piece moves two positions in the conveyors direction.


    Work tasks:  
- Implement method for moving board piece two positions when standing on conveyor.

**5) As a user, I want the board to have a laser function, so that if a player stands where a laser shoots, it loses a damage token.**
    Acceptance criteria:
- Board piece loses a life token when standing in front of an activated laser


    Work task:
- Implement function for lasers.
- Reduce life tokens by one if the player is hit by a laser. 


**6) As a user, I want the game to end when I win.**

    Acceptance criteria:
- The player gets notified when all flags have been visited and the player has won.


    Work task:
- Create method for counting flags for player.
- Check if player has collected all flags.

**7) As a user, I want a button to confirm my choice of cards, so that the game does not perform moves before I am ready.**
   
    Acceptance criteria:
- After all 5 cards are chosen, confirm the choices before performing moves.


    Work tasks:
- Create a button for confirming card choices. 

**8) As a user, I want a button to reset my choice of cards, so that if I choose a wrong card I can undo my choice.**

    Acceptance criteria:
- If the player has chosen a wrong card, it is possible to undo.


    Work tasks:
- Create a button for resetting card choices. 

**9) As a user, I want to lose a life and be moved to start if I move to a place on the board where there is a hole, so that I have to plan my moves accordingly.**

    Acceptance criteria:
- If the player moves to a hole, the player loses a life and gets moved to its starting position


    Work tasks:
- Create a method for checking if there is a player on a hole.
- Create a method for losing life and moving the player if the player is on a hole. 

**10) As a user, I want my piece to be rotated in the direction the gear is indicating, so that the game rules are followed.**

    Acceptance criteria:
- If the player moves to a gear, the player gets rotated in the direction the gear is indicating. 
  
  
    Work tasks:
- Create a method for checking if there is a player on a gear.
- Create a method for rotating the player the direction the gear is indicating. 


## Møtereferat

###Onsdag 10.03.21 kl 12.00

Tilstede: Alle

Vi gikk gjennom hva som må på plass før vi kan implementere flere funksjoner. Vi laget en TODO liste:
- Nettverk må fungere skikkelig
- Kortlogikk må fungere med nettverk
- Mulitplayer må fungere som tenkt
- Bugs må fjernes
- Kjøretid må ned i logikk 

###Torsdag 11.03.21 kl 15.00
Tilstede: Alle

Vi mobprogrammerte og hadde en arbeidsøkt i fellesskap. Jobbet videre med TODO listen som ble definert under forrige økt. 

###Mandag 15.03.21 kl 15.00
Tilstede Alle

Vi startet møtet med å se på nettverk sammen. Deretter lagde vi en liste over funksjonalitet som vi ønsker utover MVP krav. Vi har fortsatt en del jobb med å få implementert MVP kravene skikkelig slik at multiplayer fungerer sammen med kortlogikk og at alle spillerne kan se hverandre og trekkene motspillerne gjør, men ønsket en oversikt over hva annet som bør implementeres. Vi bestemte oss for å teste ny funksjonalitet med single player framover slik at vi kan få det implementert og koble opp mot multiplayer når det er på plass.
Ny funksjonalitet:
- Laser
- Rullebånd
- Skade/liv
  
- Vinner når du treffer flagg 3 ganger
- Hull
- Checkpoint

Vi avtalte nytt møte tirsdag 16.03.21 kl 16.00

###Tirsdag 16.03.21
Tilstede: Alle

Vi ble enige om å pause daily scrum da vi hadde en viktig oppgave i et annet fag. 

###Fredag 19.03.21
Tilstede: Yasmin, Erlend, Hossein, Thomas

Vilde var ikke tilstede da hun var på jobb. Det var seminar og vi jobbet videre med koden. 

###Mandag 22.03.21
Tilstede: Alle

Vi fullførte brukerhistoriene, og ble enige om hva som må på plass før innlevering:
- Nettverk
- GUI startskjerm
- Laser
- Rullebånd
- Damage

###Onsdag 24.03.21
Tilstede: Alle

Vi diskuterte hva som gjenstår. Vi jobbet i fellesskap med nettverk før vi gikk i breakoutrooms for å jobbe i mindre grupper. Thomas og Hossein fortsatte med nettverksimplementeringen og Erlend, Yasmin og Vilde jobbet med GUI og spill-logikk.
Nettverk er fortsatt ikke helt implementert og derfor må spill-logikken foreløpig gjøres med single player. 

###Torsdag 25.03.21
Tilstede: Alle

Vi mobprogrammerte og forsøkte å fullføre implementasjonen som manglet. GUI skjerm må fullføres, vi må merge fra branchene og sjekke at nettverk fungerer. 