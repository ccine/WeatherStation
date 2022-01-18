# WeatherStation

## Introduzione
Il progetto di cui tratta questo documento riguarda la creazione di una stazione meteo che tramite dei sensori controlla le condizioni atmosferiche e le comunica ad un server per la raccolta dei dati. Un altro server (chiamato server di manutenzione) si occupa invece di gestire lo stato di tali stazioni. La stazione è inoltre dotata di alcuni generatori che permettono a quest'ultima di rimanere in funzione, caricandone la batteria.
In questo progetto sono state implementate le funzioni, descritte negli scenari, che compie la stazione meteo. I sensori, generatori ed i server non sono stati direttamente implementati, bensì sono delle componenti che simulano il loro comportamento e funzionalità.
E' stata inoltre implementata un'interfaccia grafica per poter osservare lo stato della stazione meteo ed il suo comportamento, permettendo all'utente di modificare gli elementi fittizi sopra citati.
<br/>

## Scenari
### Stazione invia dati meteorologici al server periodicamente
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente, ha una connessione con il server, è trascorso il tempo per cui la stazione deve mandare i dati al server.<br/>
* **Descrizione:** La stazione delega ad una routine specifica la lettura dei dati dei propri sensori, questa compone il messaggio finale da inviare insieme ad un timestamp. La stazione quindi manda al server dedicato alla raccolta dei dati il messaggio e lo salva in un file di log insieme ai precedenti messaggi.<br/>
* **Problemi:** Il messaggio inviato viene perso durante la trasmissione, questi tipi di errore vengono gestiti utilizzando dei protocolli di rete che si occupano di rinviare il messaggio perduto e di verificare che arrivi correttamente a destinazione.<br/>
* **Altre attività:** Il server di manutenzione sta effettuando delle verifiche alla stazione durante la raccolta e l’invio dei dati.
<br/>

### Stazione invia dati meteorologici al server su richiesta
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente, ha una connessione con il server, il server dati ha richiesto una lettura dei sensori.<br/>
* **Descrizione:** La stazione delega ad una routine specifica la lettura dei dati dei propri sensori, questa compone il messaggio finale da inviare insieme ad un timestamp. La stazione quindi manda al server dedicato alla raccolta dei dati il messaggio e lo salva in un file di log insieme ai precedenti messaggi.<br/>
* **Problemi:** Il messaggio inviato viene perso durante la trasmissione, questi tipi di errore vengono gestiti utilizzando dei protocolli di rete che si occupano di rinviare il messaggio perduto e di verificare che arrivi correttamente a destinazione.<br/>
* **Altre attività:** Il server di manutenzione sta effettuando delle verifiche alla stazione durante la raccolta e l’invio dei dati.<br/>
* **Stato finale:** Il server riceve i dati correttamente.
<br/>

### Stazione legge i dati dei propri sensori
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente e necessita di leggere i dati dai propri sensori.<br/>
* **Descrizione:** Viene invocata la routine specifica per la lettura dei dati. La stazione legge i valori di tutti i sensori funzionanti che possiede. Raccolti tutti i dati crea un messaggio aggiungendo il timestamp attuale, l’identificativo della stazione e se la lettura è stata richiesta dal server. Questa funzione ritornerà il messaggio creato in formato JSON.<br/>
* **Problemi:** Uno o più sensori sono danneggiati o non funzionanti, la stazione salva le informazioni che riesce a recuperare. Verrà poi scritto nel file di log degli errori quali sensori non sono funzionanti.<br/>
* **Stato finale:** La stazione possiede un messaggio contenente gli ultimi dati meteorologici. Potrebbe essere aggiornato il file di log degli errori in caso di malfunzionamenti.
<br/>

### Programma del controllo degli errori
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente. Il file di log degli errori contiene delle informazioni relative a malfunzionamenti che non sono ancora state segnalate.<br/>
* **Descrizione:** Il programma tiene costantemente monitorato il file di log degli errori. Quando questo presenta dei nuovi malfunzionamenti da segnalare si occupa di inviare queste informazioni al server di manutenzione.<br/>
* **Problemi:** Il server di manutenzione non è raggiungibile, il programma aspetta che il server sia nuovamente disponibile per inviare il messaggio.
Il messaggio inviato viene perso durante la trasmissione, questi tipi di errore vengono gestiti utilizzando dei protocolli di rete che si occupano di rinviare il messaggio perduto e di verificare che arrivi correttamente a destinazione.<br/>
* **Altre attività:** Il server di manutenzione chiede esplicitamente il log degli errori, avviene un malfunzionamento appena prima dell’invio dei dati che verrà rilevato successivamente.<br/>
* **Stato finale:** Il file di log degli errori  verrà aggiornato rimuovendo i malfunzionamenti inviati, il server di manutenzione ha ricevuto il messaggio.
<br/>

### Controllo condizioni meteo avverse
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente, i sensori sono funzionanti e hanno rilevato delle condizioni meteo avverse.<br/>
* **Descrizione:** La stazione spegne e ritrae i propri generatori per evitare di danneggiarli. Viene invocata la funzione che permette di inviare lo stato della stazione al server di manutenzione. La stazione a intervalli regolari controlla le condizioni climatiche attraverso i sensori adibiti a tale scopo. In caso di miglioramento rimette in funzione i generatori.<br/>
* **Problemi:** La batteria scende sotto una soglia critica a causa delle continue o ripetute condizioni meteo avverse, viene segnalato questo stato critico al server di manutenzione.<br/>
* **Altre attività:** Il server dati richiede una lettura dei sensori.<br/>
* **Stato finale:** I sensori sono riparati da eventuali danni causati dalle condizioni meteo avverse, il server di manutenzione è a conoscenza dello stato della stazione meteo.
<br/>

### Gestione livello batteria
* **Assunzione iniziale:** La stazione meteo è accesa.<br/>
* **Descrizione:** Questa funzione controlla periodicamente il livello della batteria. Se la batteria scende sotto ad una soglia minima la stazione meteo va in uno stato di risparmio energetico, questo viene segnalato al server di manutenzione tramite apposita funzione. Durante questo stato vengono mantenute attive solamente le funzioni “Controllo condizioni meteo avverse” e “Gestione livello batteria” affinché la stazione meteo si occupi solo di caricare la batteria. Quando la batteria supera la soglia viene ripristinato il normale funzionamento della stazione.<br/>
* **Problemi:** La stazione meteo non riesce a ricaricarsi, scendendo sotto ad un’altra soglia minima critica. La stazione ritrae i propri generatori, invia il nuovo stato al server e si spegne.<br/>
* **Stato finale:** La stazione meteo si trova in uno stato di quelli descritti in base alla carica residua della batteria.<br/>
<br/>

### Funzione invio stato
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente e si trova in uno stato che richiede l’invio al server di manutenzione.<br/>
* **Descrizione:** La stazione invia al server di manutenzione un messaggio che indica il nuovo stato in cui si trova. Il messaggio contiene l’identificativo della stazione, il livello della batteria, se i generatori sono attivi e stanno ricaricando la stazione e se la stazione è in risparmio energetico.<br/>
* **Problemi:** Il server di manutenzione non è raggiungibile, il programma aspetta che il server sia nuovamente disponibile per inviare il messaggio.<br/>
Il messaggio inviato viene perso durante la trasmissione, questi tipi di errore vengono gestiti utilizzando dei protocolli di rete che si occupano di rinviare il messaggio perduto e di verificare che arrivi correttamente a destinazione.<br/>
* **Stato finale:** Il server di manutenzione è a conoscenza dello nuovo stato in cui si trova la stazione meteo.
<br/>

### Richiesta stato della stazione meteo
* **Assunzione iniziale:** La stazione meteo è accesa con batteria sufficiente, ha una connessione con il server di manutenzione ed esso richiede di sapere lo stato in cui si trova la stazione.<br/>
* **Descrizione:** La stazione effettua dei controlli per preparare il messaggio da inviare. Lo stato comprende l’identificativo della stazione, il livello della batteria, se i generatori sono attivi e stanno ricaricando la stazione e se la stazione è in risparmio energetico. Questo verrà poi inviato al server di manutenzione.<br/>
* **Problemi:** Il messaggio inviato viene perso durante la trasmissione, questi tipi di errore vengono gestiti utilizzando dei protocolli di rete che si occupano di rinviare il messaggio perduto e di verificare che arrivi correttamente a destinazione.<br/>
* **Stato finale:** Il server di manutenzione riceve le informazioni riguardanti la stazione meteo.
<br/>

## Quality assurance

### Test selection

* **Stazione invia dati meteorologici al server periodicamente:** Per questo scenario è stato realizzato con selenium il file di test *TestPeriodicMessage*. Dopo 60 secondi (quale intervallo di tempo necessario alla stazione per inviare autonomamente un messaggio al server dei dati) il test ricarica la pagina ed effettua un controllo sulla textArea del server dei dati. Tale controllo prevede la presenza di un solo messaggio contenete il dato che indentifica che quest'ultimo sia stato inviato automaticamente dalla stazione, ovvero si controlla la presenza di *manualRequest:false*.
* **Stazione invia dati meteorologici al server su richiesta:** Per questo scenario è stato realizzato con selenium il file di test *TestRequestedData*. In questo test viene premuto il pulsante dedito a porre il server dei dati in attesa delle ultime letture della stazione. Per verificare la corretta ricezione di tali informazioni viene letta la textArea del server dei dati, si controlla che vi sia presente un solo messaggio e che questo contenga la presenza di *manualRequest:true*.
* **Stazione legge i dati dei propri sensori:** Per questo scenario non è stato realizzato un file di test che lo controlli direttamente, tuttavia la sua correttezza è verificata e garantita dai due scenari precedenti, in quanto questi necessitano l'utilizzo di questa funzione per operare adeguatamente.
* **Programma del controllo degli errori:** Per questo scenrio sono stati realizzati con selenium i file di test *TestBreakTemperatureAbove*, *TestBreakTemperatureUnder*, *TestBreakWindAbove*, *TestBreakWindUnder*. Ognuno di questi test prevede di premere i pulsanti che incrementano o diminuiscono la temperatura o velocità del vento il numero di volte necessario a simulare la rottura del sensore stesso. In base al sensore ora difettoso si procede a controllare se l'interfaccia dedita al controllo dei dati dei mock presenta come dato del sensore in questione *Unknown*. Viene inoltre premuto il pulsante che richiede i dati dei sensori dalla stazione. Si procede perciò a controllare che le textAreaDS ed textAreaMS contengano entrambe un solo messaggio. Sulla prima viene controllato che il messaggio ricevuto non riporti alcun dato del sensore rotto, sulla seconda viene invece controllato la presenza di un elemento in più, quale *sensorsBroken* che conterrà il nome del sensore che non funziona (wind: sensore del vento, temperature: sensore della temperatura).
* **Controllo condizioni meteo avverse:** Per questo scenario sono stati realizzati in selenium i file di test *TestIsNotChargingTemperature* e *TestIsNotChargingWind*. In questi test vengono simulate delle condizioni meteo avverse, quali rispettivamente la presenza di un clima troppo freddo o vento troppo veloce. Tali situazioni vengono realizzate premendo più volte i pulsanti per incrementare la velocità del vento o diminuire la temperatura fino al raggiungimento della condizione climatica pericolosa. A questo punto viene controllata la textAreaMS che presenti un solo messaggio contenente il dato *isCharging:false*.
* **Gestione livello batteria:** 
