#Obligatorisk oppgave 2

##Teamet 

**Gamestoppers**

**Team leader:** Vilde  
Overordnet ansvar for prosjektet, at det blir levert i tide og at oppgavene er blitt gjennomført.

**Scrum master/Kundekontakt:** Thomas  
Scrum master: Ansvar for at Scrum gjennomføres rett.  
Kundekontakt: Ansvar for å ha kontakt med produkteier. Unngår at produkteier blir spurt de samme spørsmålene flere ganger, og fører til bedre kommunikasjon innad i teamet.

**Nettverksansvarlig:** Thomas, Hossein  
Ansvar for å implementere multiplayer hvor flere spillere kan spille fra egne maskiner.

**Testansvarlig (kvalitetsansvarlig):** Erlend, Hossein  
Overordnet ansvar for at vi har god testdekning. Ikke ansvarlig for å skrive testene, men for å ha et overblikk over om testene er gode og dekkende nok.

**GUI-ansvarlig:** Yasmin  
Hovedansvarlig for at GUI blir implementert riktig og fungerer med resten av koden.

**Utviklingsteam:**  
Som del av scrum rammeverket er utviklingsteamet en rolle, hvor alle medlemmene av gruppen har en rolle.  
Erlend Osland Nytveit  
Thomas Rimmereide  
Yasmin Mohamoud  
Hossein Sharifi  
Vilde Haugstad

##Prosjekt og prosjektstruktur

Vi startet obligatorisk innlevering 2 med sprint planlegging. Vi lagde en oversikt over hvilke oppgaver som vi ønsket å få fullført før vi startet på MVP kravene for oblig 2. Vi ønsket å rydde i koden, få testene til å kjøre og implementere en GUI avslutning. Vi definerte også nye brukerhistorier for oblig 2, og endret på eksisterende brukerhistorier slik at de fulgte riktig format.

Så gikk vi igjennom hvordan rollene fungerte i teamet. Vi syntes de eksisterende rollene fungerte fint, men fant ut at det kunne være hensiktsmessig å gi rollen som kundekontakt til Thomas, da han har hatt mest kontakt med seminarleder grunnet kjennskap til discord. Vi ønsket også å opprette en rolle som nettverksansvarlig, da det er en omfattende implementeringsjobb. Vi fordelte ansvaret til Hossein og Thomas. Yasmin fortsetter som GUI ansvarlig, og Erlend og Vilde tok på seg ansvaret for kort og spill logikk.

Prosjektmetodikken synes vi fungerer godt, vi er veldig fornøyd med valg av Scrum som arbeidsmetodikk. Det fungerer fint siden alle medlemmene av teamet tar ansvar og gjør arbeidsoppgavene som er tildelt. Alle deltar på daily scrums og digitale møter og svarer fortløpende hvis noen sender spørsmål i Slack eller ønsker hjelp. Vi ønsker å forbedre oss på å bruke prosjektboard i git.  

Gruppedynamikken er også god. I tillegg til godt samarbeid om oppgaven har vi har hatt digital spillkveld og deltatt på live gameshow på fritiden. Dette synes vi bedrer kommunikasjonen og gjør at alle føler at det er lavterskel å ta kontakt med hverandre. Vi ønsker å fortsette å jobbe med mobprogrammering og code with me, i tillegg til arbeidsøkter over zoom hvor vi alle enten sitter sammen eller i forskjellige breakoutrooms slik at vi får et bedre samhold og kan lettere samarbeide. Vi har også daglig kommunikasjon over Slack. Vi er enig om at å bruke en kommunikasjonskanal slik som messenger hadde vært å foretrekke da de fleste teammedlemmene bruker denne aktivt, men da ikke alle er på facebook er Slack et veldig godt alternativ.

Vi ønsker å fortsette med samme rutiner som vi har hatt så langt i prosjektarbeidet. Vi mener at vi har klart å ha jevnlige og effektive møter hvor alle har møtt og bidratt og dette ønsker vi å fortsette med. Det vi ønsker å forbedre er å jobbe mer jevnlig med oppgaven slik at vi har det meste gjort i god tid før innlevering. Dette var en utfordring til første innlevering da vi hadde relativt dårlig tid, men for de resterende innleveringene har vi mulighet til å planlegge bedre. 

##Krav og brukerhistorier

**1) As a user, I want to be able to host a game, so that I can invite others to play**   

    Acceptance criteria:
- I can host a server 

  
    Work task:
- Create a class to host a server

**2) As a user, I want to be able to connect to the game over the internet, so that I can join a game I was invited to**

    Acceptance criteria:
- I can connect to the game server


    Work task:
- Set up client connection

**3) As a user, I need cards, to be able to move my robot**

    Acceptance criteria:
- CardObject is created


    Work task:
- Create card objects 

**4) As a user, I want cards to be distributed to me, because I don’t want to pull the cards myself**

    Acceptance criteria:
- Cards is connected to the player object

    
    Work task:
- Distribute cards to players

**5) As a user, I want to be able to see see my cards, so that I know what options I have for movement**


    Acceptance criteria:
- Cards are shown on the screen


    Work task: 
- Create method to show cards on the screen 

**6) As a user, I want my cards to be private for me, so that the other users do not know what options I have**

    Acceptance criteria:
- My cards are only shown to me on my own screen


    Work task: 
- Create method for showing cards to one player only 

**7) As a user, I want to be able to interact with my card, so that I can choose what cards I want to play**

    Acceptance criteria:
- I can move the cards on the screen


    Work task:
- Create method to interact with the cards

**8) As a user, I want to be able to put my card into the slots, so that I know what cards I have chosen, and in which order they are being played**

    Acceptance criteria:
- Cards can be moved into slots


    Work task:
- Create a method for putting the cards in the slots

**9) As a user, I want my piece to act according to the card, so that the chosen move happens as planned**

    Acceptance criteria:
- My piece moves according to the card in the slots


    Work task:  
- Create methods that use cards from the slot

**10) As a user, I want the turns to play out according to the priority of the cards so that the order of moves is correct.**

    Acceptance criteria:
- The piece for different players moves according to the priority on the cards


    Work task:
- Create method for using the cards in the right order















