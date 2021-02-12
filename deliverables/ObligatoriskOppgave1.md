# Obligatorisk oppgave 1
##Teamet

**Gruppenavn:** GameStoppers


Erlend Osland Nytveit:
- Går studieretning IKT og har erfaring med python og java. Har hatt fagene INF101 og INF102. Har fra tidligere mesterbrev og fagingeniør utdanning innenfor automatisering.

Thomas Rimmereide:
- Går studieretning Informasjonsvitenskap og har erfaring i python og tar java dette semesteret. Har fra tidligere fagingeniør utdanning innenfor automatisering.

Yasmin Mohamoud:
- Går studieretning IKT og har erfaring i design (ui) og litt HTML. Har hatt INF100 og tar INF101 dette semesteret, så har ikke mye erfaring med koding i Java.

Hossein Sharifi:
- Går studieretning Datateknologi og har erfaring i python og java. Har hatt fagene INF101 og INF102. Jobber i tillegg i IT avdelingen til UiB. 

Vilde Haugstad:
- Går studieretning IKT og har erfaring i python og java. Har tatt INF101 og INF102. Har fra tidligere bachelor i økonomi og administrasjon og master i PR og kommunikasjon. 

### Roller

**Team leader/Kundekontakt:** Vilde  
Team leader: Overordnet ansvar for prosjektet, at det blir levert i tide og at oppgavene er blitt gjennomført.
Kundekontakt: Ansvar for å ha kontakt med produkteier. Unngår at produkteier blir spurt de samme spørsmålene flere ganger, og fører til bedre kommunikasjon innad i teamet.

**Scrum master:** Thomas  
Ansvar for at Scrum gjennomføres rett.

**Testansvarlig (kvalitetsansvarlig):** Erlend, Hossein  
Overordnet ansvar for at vi har god testdekning. Ikke ansvarlig for å skrive testene, men for å ha et overblikk over om testene er gode og dekkende nok.

**GUI-ansvarlig:** Yasmin  
Hovedansvarlig for at GUI blir implementert riktig og fungerer med resten av koden.

**Utviklingsteam:**  
Som del av scrum rammeverket er utviklingsteamet en rolle, hvor alle medlemmene av gruppen har en rolle.
- Erlend Osland Nytveit
- Thomas Rimmereide
- Yasmin Mohamoud
- Hossein Sharifi
- Vilde Haugstad



##Arbeidsmetodikk
Den obligatoriske innleveringen har en avgrenset tidsramme med et definert scope som skal leveres på slutten. Dette passer veldig godt med Scrum rammeverket, hvor du definerer sprinter på en til flere uker. Ved slutten av hver sprint skal et produkt leveres, noe som passer perfekt da vi skal ha en MVP klart både ved oblig 1 og 2.

Illustrasjon av en scrum sprint: [Scrum](https://github.com/inf112-v21/G6-1/blob/master/deliverables/scrum.png)



På bakgrunn av illustrasjonen:

Vi definerer en sprint til å være fra utdeling av obligatorisk oppgave til innlevering.

Sprint planlegging innebærer å identifisere oppgaver og brukerhistorier relatert til aktuell sprint. Disse må legges inn i prosjektbrettet. Vi bruker prosjektbrettet til github som alle gruppemedlemmene har tilgang til og kan redigere. På denne måten kan alle teammedlemmene til enhver tid være oppdatert på hvilke saker som er påbegynt, ferdig eller som trenger mer arbeid.

Refinement innebærer å spesifisere saker. Sprinten er såpass kort at det blir nødvendig å ta oppklaringer fortløpende i løpet av sprinten, så dette møtet velger vi å fjerne fra vår arbeidsmetodikk for første innlevering. Vi ønsker å ta en vurdering senere om refinement vil gi oss verdi.

Daily scrum innebærer standup, hvor teamet forteller om hva de har gjort, planlegger å gjøre og eventuelle hindre. Formålet er å oppdage og løse hindringer raskest mulig. Grunnet kort sprint og tidsfrist for oblig og behov for fortløpende avklaringer har vi bestemt at daily scrum kan være hensiktsmessig å gjennomføre. Vi har bestemt at disse kan gjennomføres både over meldinger eller video etter hva som er mest effektivt.

Sprint backlog er en oversikt over saker som skal være med i denne sprinten. Vi har valgt å bruke prosjekt funksjonen til github hvor arbeidsoppgaver blir lagt inn som issues og man registrerer hvem som jobber med hvilke saker, hvilke som er i prosess og hvilke som er ferdig.

Sprint review innebærer et møte hvor teamet viser frem hva vi har oppnådd i løpet av sprinten. Da vi ikke har en gruppetime etter innleveringsfristen for oblig 1, men et par timer før, må sprint review gjennomføres på samme dag som innlevering. På bakgrunn av dette må innleveringen være så fullstendig som mulig slik at vi kan presentere til produkteier, men med forbehold om at endringer kan skje etter møtet også.

Sprint retrospective er hvor teamet tar en egenvurdering på hva som gikk bra og hva som gikk dårlig under sprinten. Her kan vi lage en plan for forbedringer til neste sprint.  Dette vil vi gjennomføre før innleveringsfristen av de obligatoriske oppgavene da dette er en del av vurderingskriteriene.

I tillegg til scrum vil vi benytte oss av metoden for parprogrammering og mob programmering da medlemmene av teamet har forskjellig bakgrunn og kompetanse. Vi ser derfor på parprogrammering som en mulighet til å dele kunnskap og få bedre innsikt i forskjellige deler av prosjektet. 

##UML:
Class Diagram: [her](https://github.com/inf112-v21/GameStoppers/blob/master/classDiagram/Updated_ClassDiagram.png?raw=true)

##Beskrivelse av applikasjonen
Gruppen skal implementere en digital versjon av brettspillet RoboRally hvor det skal være mulig å spille alene, med AI eller multiplayer fra flere maskiner. Spilleren skal utføre trekk ved å få utdelt 9 kort og deretter trekke 5 av disse som skal bestemme hvordan spilleren skal kunne bevege seg på brettet. Spilleren må unngå vegger, lasere og hull i brettet, og kan bli påført skade hvis dette skjer. Om spilleren blir påført nok skade vil roboten dø, og spillet er over om roboten har mistet livet 3 ganger. Spiller vinner spillet ved å besøke flaggene på brettet i riktig rekkefølge. Applikasjonen skal vise brett, spiller, hindringer og flagg og skal bevege roboten på bakgrunn av spillerens valg, samt trekke tilfeldige kort som spiller kan velge fra hver runde. Applikasjonen skal også holde rede på hvor mye skade spilleren påfører seg selv eller andre hver runde.

##Brukerhistorier
Vi har valgt å skrive brukerhistorier med akseptansekriterier og arbeidsoppgaver på engelsk da disse også er lagt inn som issues på github.

**1) As a user, I would like to see the game board.**


    Acceptance criteria:
- Run the program - the game board should be rendered (but rendering of game pieces on the board is irrelevant).


    Work task:
- Generate a game board.
- Create a method that shows the game board.

**2) As a user, I want to be able to see the game pieces on the game board.**

    Acceptance criteria:
- The game board is rendered with game pieces that are visually different depending on the type of game piece.


    Work task:
- Create a player
- Add images for floor, flag and player.
- Render images on to game board.

**3) As a user, I want to be able to move my robot on the game board with arrow keys on the keyboard.**


    Acceptance criteria:
- The robot should move left, right, up, down depending on which key arrows are pressed.


    Work task:
- Define start-point for robot on board.
- Create method to make robot move left when left key arrow is pressed.
- Create method to make robot move right when right key arrow is pressed.
- Create method to make robot move up when up key arrow is pressed.
- Create method to make robot move down when down key arrow is pressed.

**4) As a user, I do not want to be able to move outside the grid of the board.**

    Acceptance criteria:
- The robot will not move if the player tries to move it off grid.


    Work task:
- Create a method that checks if the player tries to move to a tile outside the grid.
- Add that the player stays in the same position if it tries to move to a invalid location.

**5) As a user, I want the robot to be able to visit flags.**

    Acceptance criteria:
- When a robot moves to a tile with a flag it must be registered that the robot has collected a flag.


    Work task:
- Create a method that registers if a player has visited a flag.

**6) As a user, I want the game to end when I have visited a flag.**

    Acceptance criteria:
- When a robot moves to a tile with a flag it must be registered that the player has won. The play window must then be removed.


    Work task:
- Create a method for ending the game when a player has visited a flag.
- Create a method for exiting the play window when a player has visited a flag.

**7) As a user, I would like documentation on how to run the program.**

    Acceptance criteria:
- The player should be able to read the documentation that states how the program works and runs.


    Work task:
- Create user manual.

##Sprint review

I følge Scrum skal en sprint ha en sprint review. Dette fikk vi dessverre ikke tid til for denne aktuelle sprinten. Vi planlegger å presentere til produkteier/gruppeleder neste seminar.

##Sprint retrospective

Som team har vi jobbet godt sammen, vi har hatt jevnlig møter hvor alle har deltatt og bidratt. Kommunikasjonsflyten har vært god og Slack og Zoom har fungert bra som kommunikasjonskanaler. Vi har diskutert mye i fellesskap slik at alle kunne komme med sin mening og bidra til kunnskapsdeling.

Forberedelse til neste sprint er å bli flinkere til å bruke project board. Da fristen for denne innleveringen var relativt kort jobbet vi mye i fellesskap og med CodeWithMe med flere arbeidsoppgaver parallelt. Dermed klarte vi ikke helt å holde oversikten på project board og tydelig markere hvem som jobbet med hva da vi alle bidro på de fleste arbeidsoppgavene.

Vi endret også arkitektur på oppgaven fra opprinnelig plan. Da ingen hadde tidligere erfaring med Tiled og Libgdx visste vi ikke hvilke funksjonaliteter vi kunne hente inn. Dermed ble UML diagrammet vi utarbeidet som utgangspunkt til arkitektur noe endret og vi måtte oppdatere det underveis.

Vi har også hatt litt utfordringer med git, da vi ikke har brukt dette til en gruppeoppgave tidligere. Disse har vi klart å løse fortløpende, men brukt mer tid enn ønsket.

Testene får vi ikke kjørt da vi har problemer med å finne sti til assets mappe. Dette har vi brukt mye tid på å fikse og testdekningen ble dermed ikke så god som vi hadde planlagt. Til neste sprint må vi finne ut av hvordan vi fikser dette problemet og øke testdekningen.

Vi er fornøyd med å ha klart å produsere et kjørende RoboRally som dekker MVP krav 1-5. Til neste innlevering ønsker vi å fortsette på MVP kravene.

##Møtereferat
###**Fredag 05.02.2021**

Som forberedelse til møte hadde teamleder utarbeidet et forslag til prosjektmetodikk og begynt på brukerhistorier som dekket MVP krav 1-5 slik at diskusjonen av metoder skulle gå mer effektivt. Under møtet gikk vi i fellesskap gjennom forslaget til arbeidsmetodikk og gjorde tilpasninger slik at alle i teamet var enige om metoder som passet gruppen som helhet. Vi endte med å hente elementer fra Scrum, i tillegg til å drive med mob programmering i starten av prosjektet når vi fortsatt ikke har en komplett oversikt over arkitekturen til koden. Dette ønsker vi for å opprettholde en god kommunikasjon, og for å sikre at vi har en felles forståelse for abstraksjonene vi har valgt.

Vi utarbeidet også flere brukerhistorier, men var litt usikker på hvordan akseptansekriterier og arbeidsoppgaver skulle formuleres. Vi ble enige om at til neste møte skal vi sette oss mer inn i dette slik at brukerhistoriene kan blir komplette.

Da seminaret var over ble vi enige at de av oss som hadde tid skulle fortsette møtet for å utarbeide arkitekturen til interfacet. Dette skulle vi ettertid sende på slack-kanalen til teamet slik at de som måtte gå pga andre forpliktelser var oppdatert og kunne komme med input.

Vi avtalte også at neste møtet skulle bli mandag 08.02 kl 15.00, hvor vi i forberedelse til møtet skulle sette oss inn i Libgdx and Tiled Tutorial, i tillegg til akseptansekriterier og arbeidsoppgaver til brukerhistorier som tidligere nevnt. Vi ønsker å få en god start på kode delen av prosjektet i fellesskap under neste møtet. Deretter skal vi innføre daily scrum slik at vi får en god kontroll på prosjektet og hva alle jobber med fram mot innlevering.

Vi som ble igjen ble ferdig med en foreløpig utarbeidelse av arkitekturen til interfacet.

###**Mandag 08.02.2021**         

**Agenda: **
- Gruppen må godkjenne arkitektur til interface.
- Begynne på koden

I starten av møtet tok vi opp arkitekturen til interface som ble laget forrige møtet. Denne hadde fått noen forbedringer i løpet av helgen og vi hadde utformet et tilhørende klassediagram. Da ikke alle teammedlemmene hadde anledning til å ha digitale møter i helgen, ble oppdateringene postet på slack kanalen slik at alle var oppdatert.
Vi ble alle enige om hvordan arkitekturen skulle utformes, og vi brukte en del tid på møtet i dag til å forklare og endre.

I tillegg tok vi opp Tiled og forsøkte i fellesskap å forstå litt bedre hvordan programmet fungerte.

Vi avsluttet møtet med å fordele oppgaver på bakgrunn av klassediagrammet vi utformet slik at vi alle kan jobbe med forskjellige deler av koden uavhengig av hverandre og med å planlegge et nytt møte onsdag 10.02. Tirsdag 09.02 skal vi ha daily scrum og ta et møte om vi ser at det blir behov for det.

###**Onsdag 10.02.2021**

**Agenda:**
- Forsøke å fullføre metodene som trengs i GameLogic til MVP
- Opprette player som Sprite i Player.java
- Sette opp kode som trengs for å kunne vise GUI som er laget til i dag


På dette møtet gikk vi igjennom hva vi hadde gjort siden sist. Erlend og Thomas hadde forsøkt å kartlegge hvilke funksjoner som var tilgjengelig for både brett og spiller og satt seg mer inn i metoder til Libgdx and Tiled. Vilde hadde skrevet ferdig arbeidsmetodikk, forbedret brukerhistorier og satt seg inn i hvordan markdown fungerer. Hossein skrev metoder til player og Yasmin hadde lagd maps i Tiled.

Vi mob programmerte sammen under dette møtet og kartla hva veien var videre. Da ingen av oss før dette prosjektet hadde hatt erfaring med Libgdx og Tiled brukte vi mer tid enn forventet til å sette oss inn i dette, og vår opprinnelige plan for koden ble endret en del. Dermed ble mange av de opprinnelige metodene overflødig da Tiled dekket disse. Vi delegerte arbeid på nytt og bestemte oss for å dele teamet opp i ansvarsområder og kjøre CodeWithMe.

Vi avtalte å ha en mob programmering og CodeWithMe økt torsdag 11.02, og møtes på nytt på seminar fredag 12.02 for å ha sprint review og retrospective før oppgaven skal leveres. 

###**Fredag 11.02.2021**

**Agenda:**
- Få ferdig testene.
- Sjekke at applikasjonen oppfyller alle krav.
- Ha sprint review og retrospect.

Etter mob programmeringen og CodeWithMe igår var alle ganske oppdatert på hva som gjenstod for å fullføre obligatorisk oppgave 1. Vi gikk først gjennom det som vi ønsket å fikse i fellesskap, før vi opprettet breakout rooms på Zoom hvor vi fordelte oppgavene som gjenstod for at vi skulle bli ferdig. Gikk også gjennom sprint review og sprint retrospective.









