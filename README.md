

**PROGETTO DI**

**“INGEGNERIA DEL SOFTWARE”**

**SAILING CLUB - FUNZIONAMENTO**

*Andrea Bertogalli – Edoardo Sichelli*

*2021/2022*

**SOMMARIO**

[funzionamento](#br2)[ ](#br2)[-](#br2)[ ](#br2)[server........................................................................................................................2](#br2)

[funzionamento](#br2)[ ](#br2)[–](#br2)[ ](#br2)[client](#br2)[ ](#br2)[........................................................................................................................2](#br2)

[client](#br3)[ ](#br3)[–](#br3)[ ](#br3)[login.....................................................................................................................................3](#br3)

[client](#br3)[ ](#br3)[–](#br3)[ ](#br3)[gui](#br3)[ ](#br3)[membro](#br3)[ ](#br3)[del](#br3)[ ](#br3)[club](#br3)[ ](#br3)[...........................................................................................................3](#br3)

[boats](#br3)[ ](#br3)[management](#br3)[ ](#br3)[menu............................................................................................................3](#br3)

[profile](#br5)[ ](#br5)[management](#br5)[ ](#br5)[menu](#br5)[ ](#br5)[..........................................................................................................5](#br5)

[race](#br5)[ ](#br5)[management](#br5)[ ](#br5)[menu..............................................................................................................5](#br5)

[pannello](#br6)[ ](#br6)[notifiche](#br6)[ ](#br6)[........................................................................................................................6](#br6)

[client](#br6)[ ](#br6)[–](#br6)[ ](#br6)[gui](#br6)[ ](#br6)[impiegato](#br6)[ ](#br6)[del](#br6)[ ](#br6)[club](#br6)[ ](#br6)[.........................................................................................................6](#br6)

[races](#br6)[ ](#br6)[management](#br6)[ ](#br6)[menu](#br6)[ ](#br6)[............................................................................................................6](#br6)

[members](#br7)[ ](#br7)[management](#br7)[ ](#br7)[menu](#br7)[ ](#br7)[.....................................................................................................7](#br7)

[boats](#br8)[ ](#br8)[management](#br8)[ ](#br8)[menu............................................................................................................8](#br8)

[payments](#br8)[ ](#br8)[tracking](#br8)[ ](#br8)[menu](#br8)[ ](#br8)[.............................................................................................................8](#br8)





Progetto di Ingegneria del software

**CREAZIONE PROGETTI:** per creare in modo rapido i due progetti e renderli funzionanti la via più

semplice è creare due progetti java, dentro il server fare copia e incolla del contenuto della cartella

“Server” presente nella consegna, per il client il procedimento è lo stesso utilizzando la cartella

“Client” presente nella consegna. Fatto ciò basterà aggiungere le varie librerie come specificato nei

paragrafi client e server qui sotto.

**NOTA:** per entrambi i progetti dato che contengono test è necessario includere JUnit5 nei due

progetti.

**FUNZIONAMENTO - SERVER**

L’esecuzione del server parte dalla classe *Server.java (sailingclub.server.Server.java)* che contiene il

main, l’applicazione server non necessita di argomenti da linea di comando ma necessita del suo file

di configurazione che si trova in *config/srv\_config.json*. in questo file è possibile specificare le

credenziali del database, e la porta sulla quale il server sarà eseguito. Il server non ha nessuna

operazione da parte dell’utente che determina il funzionamento, è un programma da linea di

comando che mostra le richieste fatte dai vari client e le varie connessioni.

**NOTA**: vista la presenza di un file json il server necessita della libreria GSON per funzionare, di

conseguenza basta aggiungere il file gson-2.8.2.jar (fornito con il codice) al build path del progetto

server, inoltre visto che il server si connette ad un database relazionale (il quale schema è fornito

con il codice) sarà necessario aggiungere al build path del server anche il connector per mysql

(mysql-connector-java-8.0.27.jar) sempre fornito insieme al codice, entrambi i jar si trovano nella

cartella libs.

**NOTA:** il server contiene una cartella images volutamente accessibile dal sistema operativo in

quanto il programma proposto ha la possibilità di avere per ogni barca una immagine diversa, di

conseguenza le immagini sono salvate lato server con una reference nel database.

**FUNZIONAMENTO – CLIENT**

L’esecuzione del client parte dalla classe *Client.java* (*sailingclub/client/Client.java)* questa classe

contiene il main e contiene il metodo che lancia inoltre la gui di JavaFX. Il Client necessita di essere

lanciato con 2 argomenti da linea di comando, gli argomenti sono argomenti named di conseguenza

bisogna specificare a quale argomento si assegna quale vaore. Gli argomenti in questione sono l’ip

del server e la porta sulla quale esso ascolta, un esempio di argomenti potrebbe quindi essere

questo: --ip=127.0.0.1 --port=5555. Se il client non riesce a stabilire una connessione con il server

mostrerà un messaggio dove sarà possibile riprovare a connettersi.

2





Progetto di Ingegneria del software

**CLIENT – LOGIN**

Una volta connesso con il server sarà possibile effettuare il login da questa schermata fornendo

username e password (le password sono salvate in hash sul db).

**CLIENT – GUI MEMBRO DEL CLUB**

Una volta loggato come membro del club l’utente si troverà in una gui che riguarda solamente il

membro del club, è possibile accedere ai menu attraverso un menu laterale a comparsa.

**BOATS MANAGEMENT MENU**

In questo menu si possono vedere tutte le barche possedute dall’utente loggato, cliccando su una

barca si accede al pannello che riguarda nello specifico la barca, quindi si può pagare la tassa di

rimessaggio, mentre cliccando il bottone [+] si può aggiungere una barca.

3





Progetto di Ingegneria del software

4





Progetto di Ingegneria del software

**PROFILE MANAGEMENT MENU**

In questo menu è possibile effettuare il logout, aggiungere metodi di pagamento e pagare la tassa

di iscrizione al club.

**RACE MANAGEMENT MENU**

In questo menu è possibile iscrivere o disiscrivere le proprie barche ad una gara, pagando con il

metodo di pagamento selezionato.

5





Progetto di Ingegneria del software

**PANNELLO NOTIFICHE**

In questo pannello, al quale è possibile accedere qualora vi siano notifiche premendo la campanella

nel menu laterale, è possibile vedere le notifiche inviate dagli impiegati per il pagamento delle varie

tasse.

**CLIENT – GUI IMPIEGATO DEL CLUB**

Una volta loggato come impiegato sarà possibile selezionare il menu desiderato attraverso i vari tab

presenti, inoltre sarà possibile fare logout con il bottone in alto a sinistra.

**RACES MANAGEMENT MENU**

Da questo menu è possibile aggiungere e rimuovere gare, ed è possibile modificarne di esistenti.

Cliccando su una riga della tabella si seleziona la gara che si vuole modificare, se invece si usa clear

selection e quindi non si seleziona nulla è possibile aggiungerne di nuove.

6





Progetto di Ingegneria del software

**MEMBERS MANAGEMENT MENU**

In questo tab è possibile modificare e aggiungere membri al club, inoltre è possibile inviare notifiche

per le varie tasse da pagare. Selezionando un utente dal dropdown in alto è possibile aggiornare o

notificare, mentre usando clear selection è possibile aggiungere un nuovo utente. Inoltre premendo

sul bottone a forma di tromba è possibile inviare la notifica per la tassa della barca corrispondente,

metre per notificare il pagamento della tassa annuale del membro è possibile usare il bottone in

basso a destra.

7





Progetto di Ingegneria del software

**BOATS MANAGEMENT MENU**

In questo menu è possibile modificare barche esistenti, selezionando dalla tabella a sinistra la barca

che si desidera modificare.

**PAYMENTS TRACKING MENU**

In questo menu si possono vedere i pagamenti effettuati dai vari utenti e tutte le informazioni

relative al pagamento.

8





Progetto di Ingegneria del software

**UTENTI**

Gli impiegati vengono definiti in fase di installazione del sistema, nella configurazione attuale

l’impiegato è username: emp password: x

Mentre gli utenti possono essere aggiunti, un utente di esempio già presente è username: edo

password: x

9

