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

Under denne innleveringen har vi prioritert å få ferdig MVP kravene som omhandler å spille fra flere maskiner, dele ut kort, velge 5 kort og bevege robot ut fra valgte kort. Vi har ikke gjort noen endringer på MVP kravene Før vi startet på MVP kravene ønsket vi å rydde i eksisterende kode for å gjøre den mer oversiktlig og lettere å jobbe videre på. Mye av spill logikken var bundet opp i graphics og GUI, og vi ønsket å refaktorere slik at dette ikke lengre var tilfelle.

###Klassediagram

Klassediagram finner du: [her](https://github.com/inf112-v21/GameStoppers/blob/master/classDiagram/UML-diagram_oblig2.png).

###Brukerhistorier

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

**4) As a user, I want 9 random cards to be distributed to me, because I don’t want to pull the cards myself**

    Acceptance criteria:
- Cards is connected to the player object

    
    Work task:
- Distribute cards to players

**5) As a user, I want to be able to see my cards, so that I know what options I have for movement**


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

###Kjente bugs

Vi har oppdaget et par bugs i koden vår.
1) De som har mac får feilkode når man kjører main, men denne blir fikset ved å edit configurations og legge til “-XstartOnFirstThread” i VM options for at koden skal kjøre.
2) Det er mulig å velge samme spillkort flere ganger.
3) For å få kjørt testene for playerLogic må man i edit configurations for hver test legger inn “-ea -XstartOnFirstThread” i VM options. I testene for playerLogic må et spillbrett opprettes og lukkes for at testene kan kjøres. Testene for playerLogic må også kjøres individuelt. 
4) Det blir registrert en spiller for mye når du velger multiplayer.

##Sprint retrospective
Under denne sprinten har teamarbeidet fungert veldig godt. Daily scrum har fungert godt, og alle har bidratt. Vi har jobbet jevnt med koden siden innlevering av oppgave 1, men innså dessverre litt for sent at nettverksfunksjonaliteten var mer utfordrende å implementere enn forventet. Derfor fikk vi ikke til å få multiplayer til å fungere optimalt. Vi lå derfor inn et alternativ for single player hvor kortlogikken fungerer og vi kan bevege spiller. 

Vi var klar over at nettverksdelen av koden var det som var mest tidkrevende denne innleveringen her og derfor startet vi tidlig på denne. Likevel ble den ikke korrekt implementert i tide til innlevering, så her burde vi ha satt inn flere ressurser. I retrospekt burde vi ha fått implementert nettverk først før vi begynte på de andre kravene istedenfor å jobbe parallelt. 

Vi har vært flinkere på å bruke prosjektbrettet under denne sprinten, men vi har fortsatt forbedringspotensiale her for at det skal bli enda mer effektivt.

Det er et par bugs i koden vi ikke har fått fikset før innlevering grunnet tidsbruk på å få nettverk til å fungere optimalt. Disse er nevnt kort over og i readme. 

Vi har også litt forskjell i commits da vi har brukt mye tid på å mobprogrammere og på codewithme. 



##Møtereferat

###Tirsdag 16.02.21

Tilstede på møte: Alle  

Vi bestemte oss for å ha et kort møte for å planlegge neste sprint. Vi lagde en TODO liste over arbeidsoppgaver som gjenstår før vi starter på de neste MVP kravene. Først på prioriteringslisten stod det å fikse testene som vi ikke fikk til å passere før første innlevering, også ønsket vi å dele opp og rydde koden slik at den er oversiktlig og dermed lettere å jobbe videre med. Vi fordelte ut arbeidsoppgavene og bestemte oss for å fortsette med korte daily scrum og å ha et nytt møte torsdag kl. 16.00

###Torsdag 18.02.21  kl 16.00

Tilstede på møte: Erlend, Thomas, Hossein

Yasmin og Vilde kunne dessverre ikke delta grunnet sykdom, så Erlend, Hossein og Thomas startet med å sette opp enkle Users stories for å kunne starte videre programmering slik at sprinten kan komme i gang. I første omgang ble det satt opp user-stories for kort, og online spilling. User stories ble fullført, og lagt inn til Git klar for å velge medlemmer som skal jobbe med forskjellige tasks. Mangler fortsatt godkjenning av user stories angående GUI.

### Fredag 19.02.21  kl 10.15, seminar

Tilstede på møte: Alle

Vi jobbet videre med koden. 

###Mandag 22.02.21 kl 15.00

Tilstede på møte: Alle

Vi gikk igjennom hva vi hadde fått gjort i løpet av helgen, og planlagte hva som trengtes å gjøre videre. 

###Onsdag 24.02.21 kl. 14.30

Tilstede på møte: Alle

Et kort møte hvor vi hadde daily scrum. 

###Fredag 26.02.21 kl. 10.15

Tilstede på møte: Alle

Vi hadde vår daily scrum, også jobbet vi i fellesskap med å merge fra branches til master for å være sikker på at alle endringer kom med. Vi jobbet med skaleringer for å sjekke om det var likt for alle gruppemedlemmene. Dette viste seg å ikke være tilfelle, så vi brukte tid på å finne ut hvordan brettet skulle bli lik størrelse for alle, slik at x og y koordinatene på brettet stemte. Dette for at funksjonen for å klikke på kort skal fungere skikkelig. 

###Tirsdag 02.03.21 12.15

Tilstede på møte: Alle

Vi har delt oss opp i mindre grupper og jobbet i forskjellige branches de siste dagene, så på dette møtet måtte vi oppdatere hverandre på hva som har blitt gjort. Erlend og Vilde har jobbet med kortfunksjonalitet, Thomas og Hossein har jobbet med implementering av nettverk og Yasmin har jobbet med GUI. Vi ble enige om at koden måtte i stor grad bli ferdig i løpet av dagen slik at vi har de neste dagene før innlevering til testskriving og dokumentasjon. På slutten av møtet delte vi oss inn i breakout rooms, hvor Hossein og Thomas jobbet videre med nettverk, og Erlend, Vilde og Yasmin jobbet med skalering av brett slik at kort kan fungere riktig.

Vi avtalte å møtes på nytt onsdag kl 08.00.

###Onsdag 03.03.21 kl 08.00

Tilstede på møte: Alle

Vi startet med å ta en daily scrum, hvor vi så jobbet med å merge de forskjellige branchene til master.
Vi hadde som mål å være ferdig med MVP til idag, men det klarte vi dessverre ikke da det var større utfordringer med den siste implementeringen av nettverk enn hva vi hadde forutsett. Siste del av game logikken er avhengig av nettverk så vi har bestemt oss for å få dette til i løpet av dagen. I tillegg dukket det opp flere problemstillinger i forhold til å vise retning til robot, så dette har vi fokus på å få fikset idag. Vi har alle mulighet til å bruke store deler av dagen til oppgaven, så vi har en zoom med forskjellige breakoutrooms slik at vi kan samarbeide når det er behov for det. 

###Torsdag 04.03.21

Vi mobprogrammerte og parprogrammerte store deler av dagen for å få fullført implementeringen av multiplayer. Vi hadde problemer med at kortfunksjonaliteten fungerte slik den skulle i multiplayer. 

###Fredag 05.03.21 Seminar

Tilstede på møte: Alle

Vi jobbet med å få fullført alle MVP kravene. Vi innså at det ikke var mulig å få kortfunksjonaliteten til å fungere i multiplayer, men vi får opp de forskjellige spillerene. Vi bestemte oss derfor for å legge inn metode for å velge mellom multiplayer og singleplayer. I singleplayer kan vi få spillet og kortene til å fungere etter MVP kravene. Her får vi muligheten til å velge 5 kort av 9 tilfeldige, og roboten beveger seg etter valgte kort. Roboten snur seg også etter hvilken retning den står i. 







