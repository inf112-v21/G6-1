# Obligatorisk oppgave 4

## Team og prosjekt

Vi startet arbeidet med obligen med sprint planlegging. Vi lagde en liste med funksjonalitet som vi mente var nødvendig å få med, i tillegg til funksjonalitet som vi ønsket å forsøke å implementere hvis vi hadde tid før innlevering. Dette inkluderte blant annet flere skjermer slik at spilleren kunne velge mellom spilltyper, nettverk, implementere vegger på spillbrettet, checkpoints og få GUI til å vise liv og skade. Vi fortsatte med å definere brukerhistoriene og issues på projectboardet og fordele ut arbeidsoppgavene.

Etter forrige innlevering har vi ikke endret mer på rollefordeling. Vi har også hatt samme fordeling i forhold til hvilken del av koden vi har jobbet med. Hossein og Thomas har fokusert på nettverk, Erlend har fokusert på implementering av spill logikk og Yasmin og Vilde har fortsatt på GUI og forskjellige screens.

Som arbeidsmetodikk har Scrum fungert veldig godt under hele arbeidet med prosjektet. I starten hadde vi konsekvente daily scrums, mens mot slutten av prosjektet hvor arbeidsmengden også økte i andre fag oppdaget vi at å ha daily scrums var et ambisiøst krav. Hyppighet av møter avtok også gradvis da vi ble mer komfortabel med programmeringen og libgdx og ikke hadde like mange code with me økter.  Vi har istedenfor satt mer bestemte tidsfrister for når forskjellige deler av koden skal være ferdig, og oppdatert hverandre kontinuerlig på fremgangen. Vi har hatt felles møter hvor vi har merget kode og gått gjennom stegene videre når disse tidsfristene har blitt nådd.

Gruppedynamikken og kommunikasjonen startet veldig bra, men har også forbedret seg gradvis da vi alle har blitt tryggere på hverandre og på selve prosjektet. Vi synes vi har fungert veldig godt sammen som team da alle har forskjellige styrker som utfyller hverandre. Under hele prosjektet har også alle teammedlemmene deltatt på møter og bidratt. 

## Sprint retrospective

Under forrige sprint definerte vi følgende områder med forbedringspotensiale:
- Bedre planlegging og mindre spontane møter.
- Bedre kommentering av kode
- Slette ubrukt kode underveis.

Disse punktene har vi klart å forbedre under denne sprinten. Vi startet sprinten med en planleggingsøkt hvor vi klart definerte hva vi ønsket å implementere og få på plass. Vi fordelte ut oppgavene og satte klare tidsfrister for når de forskjellige oppgavene burde være ferdig til. Videre bestemte vi oss også for møtetidspunkt. Dette gjorde at vi har forhindret spontane møter, men heller hatt en tydelig oversikt over hva som skal gjøres til når.

Vi har blitt bedre på å kommentere kode og slette ubrukt kode underveis, men vi ser at det fortsatt er et forbedringspotensiale her som vi kan jobbe med.

Da vi under denne sprinten ikke har hatt like mange møter og code with me økter, har vi også oppdaget at vi har brukt litt lengre tid på deler av funksjonaliteten enn hva vi gjerne hadde gjort hvis vi hadde jobbet og samarbeidet mer på tvers. For eksempel viste det seg veldig godt i arbeidet med nettverk. De som har jobbet med nettverk har brukt tid på å lese og forstå spill logikk istedenfor å samarbeide med teammedlemmene som har skrevet den delen av koden. Nettverksimplementasjonen løste seg og fungerte da vi startet å arbeide på tvers. Dette oppdaget vi heldigvis i god tid før innlevering da vi har kommunisert godt og oppdatert hverandre på framgang.

Vi har fortsatt litt forskjell i commits da vi har parprogrammert og git fortsatt ikke viser commitsene til en av teammedlemmene. Istedenfor blir disse commitsene fordelt på to brukere. 

## Krav

Vi har fått implementert all funksjonalitet som vi hadde prioritert i sprint planleggingen. Ny funksjonalitet i spillet er:
- Multiplayer over nettverk.
- Vegger som spiller tar skade av hvis de prøver å gå gjennom.
- Koble opp liv til skade.
- Koble opp GUI illustrasjoner til liv og skade.
- Checkpoints som spiller blir flyttet til om de dør.

Skjermbilde av en del av projectboard er lagt ved i deliverables, det komplette projectboard finnes her: https://github.com/inf112-v21/GameStoppers/projects/5

## Brukerhistorier

**1)** As a user I want to navigate through multiple menu screens, so that I can find the gamemode I want to play or the information I seek.

Acceptance criteria: 
- All desired screens is working

Work tasks: 
- Create desired screens 

**2)** As a user I want my board piece to collide in walls, so that the game works as intended.

Acceptance criteria:
- Player can’t move if a wall is in its path.

Work tasks:
- Create wall logic and implement it in player move logic.

**3)** As a user I want to be able to visit checkpoints, so that the game works as intended.

Acceptance criteria:
- Checkpoint is registered if a players turn ends on a checkpoint.

Work tasks:
- Create logic for checkpoint.

**4)** As a user I want my board piece to have a checkpoint or start position registered, so that if my board piece dies, it returns to the checkpoint instead of the start position.

Acceptance criteria: 
- If a player dies, the piece is moved to the last checkpoint or start position.

Work tasks: 
- Add function to move player to checkpoint or start position in case of death.

**5)** As a user I want to be able to read of my health and damage from the screen, so that I know
how close I am to losing one health point, or dying from losing all of them.

Acceptance criteria: 
- Current damage and health is indicated on the play screen.

Work tasks: 
- Implement logic to connect backend player health and damage with gui representation.

**6)** As a user I want to be able to choose between different screens, so that it is easy to choose between singler player, host or join game. 

Acceptance criteria: 
- A screen for host game before starting the game where number of players are showed. 
- A screen for join game where the player can input the IP address in order to join the game. 

Work task:
- Implement a host screen where the host can see the number of players connected to the game. 
- Implement a join screen where the player can input the IP address and press ready.





