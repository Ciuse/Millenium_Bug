package it.polimi.ingsw.ps31.server.serverNetworking;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.messages.messageNetworking.ConnectionMessage;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.Match;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Francesco on 08/06/2017.
 *
 * Insieme di classi che gestiscono le varie partite in corso sul server, mettendo in relazione ogni client connesso
 * al relativo match a cui è collegato. Inoltre tiene traccia dei client disconnessi, in modo da poterli riassociare
 * al match corretti una volta riconnessi
 *
 * @see Match
 */

/**
 * Classe che contiene le informazioni relative ad una singola partita, ossia la classe Match che la contiene e le
 * classi PlayerCommunicationInterface relative ai client ad essa collegati
 *
 * @see PlayerCommunicationInterface;
 */
class MatchRow {
    /** Riferimento al match */
    private Match match;

    /** Lista di riferimenti all'interfaccia di comunicaizone con il client */
    private List<PlayerCommunicationInterface> clientList;

    /** Booleano che indica se la partita è iniziata. Se non lo è, allora è ancora possibile aggiungere altri goicatori */
    private boolean started = false;

    public MatchRow(Match match, PlayerCommunicationInterface hostConnection)
    {
        this.match = match;
        this.clientList = new ArrayList<>();
        clientList.add(hostConnection);
    }

    public boolean isStarted()
    {
        return this.started;
    }

    /** aggiunge un player alla partita
     * @param client PlayerCommunicationInterface relativa alla connessione con il player che si sta agigungendo*/
    public void addPlayer(PlayerCommunicationInterface client)
    {
        //aggiungo la connessione alla lista di connessioni della row
        this.clientList.add(client);

        //passo il socket alla partita
        this.match.addConnection(client);
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public Match getMatch()
    {
        return this.match;
    }

    public ArrayList<PlayerCommunicationInterface> getClientList()
    {
        return new ArrayList<>(this.clientList);
    }

    public String printRow()
    {
        String res = "-----------------------------------\n";
        boolean start = true;
        for( PlayerCommunicationInterface playerCommunicationInterface : clientList ) {
            if (start) {
                res += match.getMatchId();
                start = false;
            }
            res += "\t|\t CI: "+playerCommunicationInterface+"\n";
        }

        return res;
    }
}

/** Indica un client disconnesso. Contiene il match da cui è avvenuta la disconnessione, il playerId
 * e il ConnectionMessage del player. Quest'ultimo attributo permette di eseguire confronti tra l'utente
 * che si sta riconnettendo e l'utente che si è disconnesso, in modo da accettare la riconnessione solo
 * se le password coincidono*/
class DisconnectedClient{

    /** ConnectionMessage dell'utente al momento della dsiconnessione */
    private ConnectionMessage connectionMessage;

    /** Riferimento al match a cui riconnettere l'utente*/
    private Match match;

    /** PlayerId del giocatore al momento della disconnessione */
    private PlayerId playerId;

    /* Constructor */
    public DisconnectedClient(ConnectionMessage connectionMessage, Match match, PlayerId playerId)
    {
        this.connectionMessage = connectionMessage;
        this.match = match;
        this.playerId = playerId;
    }

    public ConnectionMessage getConnectionMessage()
    {
        return connectionMessage;
    }

    public Match getMatch()
    {
        return match;
    }

    public PlayerId getPlayerId()
    {
        return this.playerId;
    }

}

/**
 * Classe che rappresenta la tabella di tutte le partite attualmente attive sul server.
 * Può essere instanziata una sola volta.
 */
public class MatchTable {
    /* Singleton */

    /** tabella vera e propria, modellata come una lista di righe */
    private List<MatchRow> matchTable = new ArrayList<>();

    /** Identificativo della prossima partita che verrà instanziata */
    private int nextMatchId;

    /** Lista dei player disconnessi */
    private List<DisconnectedClient> disconnections = new ArrayList<>();

    /* Singleton Methods */
    private static MatchTable ourInstance = new MatchTable();
    public static MatchTable getInstance() {
        return ourInstance;
    }

    /* Constructor */
    private MatchTable() {
        this.nextMatchId = 1;
    }

    /* Class Methods */

    /**
     * Crea un nuovo match e lo agiunge alla partita. Obbligatorio indicare la connessione al primo client della partita.
     *
     * @param playerCommunicationInterface connessione al primo client della partita (host)
     */
    private void newMatch(PlayerCommunicationInterface playerCommunicationInterface)
    {
        //Creo il match
        Match match = new Match(playerCommunicationInterface, nextMatchId, this);
        nextMatchId++;

        //Aggiungo il match alla tabella
        this.matchTable.add(new MatchRow(match, playerCommunicationInterface));

        //TODO: istruzione di test da cancellare
        DebugUtility.simpleUserMessage(/*"MatchTable:newMatch>*/" Creata nuova partita #"+match.getMatchId()+". Client associato.");

    }

    /**
     * Aggiunge un player alla prima partita libera della tabella o alla partita da cui si era disconnesso in precedenza.
     * Si scorre dapprima la lista di disconnessioni, controllando per ogni elemento se i ConnectionMessage del player
     * disconnesso e di quello che si sta aggiungendo coincidono. In caso si trovi una corrispondenza, il player verrà
     * aggiunto a quel match. In caso contrario si scorrerà la tabella finchè non si trova una partita con flag "strated"
     * a true e vi si aggiungerà il player. Se tutte le partite attive sono già iniziate, se ne crea una nuova.
     *
     * @param connection connessione al client che si sta aggiungendo
     */
    public void addPlayer(PlayerCommunicationInterface connection)
    {
        DebugUtility.simpleUserMessage(/*"MatchTable:addPlayer>*/" CM=" + connection.getConnectionMessage().toString());

        //Controllo se la connessione corrisponde a un client disconnesso in precedenza
        boolean found = false;
        int i = 0;
        DisconnectedClient currentDisconnection = null;
        while( i < disconnections.size() && !found)
        {
            currentDisconnection = disconnections.get(i);
            if (currentDisconnection.getConnectionMessage().equals(connection.getConnectionMessage()))
            {
                found = true;
                disconnections.remove(i);
            }
            else
                i++;
        }
        if( found )
        {
            DebugUtility.simpleUserMessage(/*"MatchTable" : addPlayer>*/"riconnessione in corso al match "+currentDisconnection.getMatch().getMatchId());
            PlayerId disconnectedPlayerId = currentDisconnection.getPlayerId();
            currentDisconnection.getMatch().reconnectPlayer(connection, disconnectedPlayerId, isMatchStarted(currentDisconnection.getMatch()));
            currentDisconnection.getMatch().printPlayerTable();

            return;
        }

        //Iteratore che scorre la lista di partite
        Iterator<MatchRow> matchItr = matchTable.iterator();

        //Se non ci sono partite nella lista, ne creo una nuova e la ritorno
        if( !matchItr.hasNext() ){
            newMatch(connection);
            return;
        }

        MatchRow currentMatch;
        do
        {
            currentMatch = matchItr.next();
        } while ( currentMatch.isStarted() && matchItr.hasNext() );

        //se il match puntato all'uscita dal ciclo è già iniziato, allora ne creo uno nuovo e lo ritorno
        if ( currentMatch.isStarted() ){
            newMatch(connection);
            return;
        }

        currentMatch.addPlayer(connection);

        DebugUtility.simpleUserMessage("Client connesso alla partita #"+currentMatch.getMatch().getMatchId());
    }

    /**
     * Data una connessione a un client, restituisce il match al quale il client è connesso
     * @param communicationInterface connessione della quale si vuole sapere il match a cui è collegato
     * @return il Match al quale il client è connesso, null se non è stato trovato alcun client
     */
    private Match communicationInterfaceToMatch(PlayerCommunicationInterface communicationInterface)
    {
        if (communicationInterface == null)
            return null;

        printTable();
        for(MatchRow currentRow : matchTable)
        {
            for( PlayerCommunicationInterface currentInterface : currentRow.getClientList() )

            if ( currentInterface == communicationInterface ) {  //Mi interessa che siano esattamente lo stesso oggetto
                return currentRow.getMatch();
            }
        }

        return null;
    }

    /**
     * Rimuove un client dal suo Match e aggiunge una nuova entry alla lista di disconnessioni
     * @param connection la connessione del player che si vuole disconnettere
     */
    public void disconnectClient (PlayerCommunicationInterface connection)//, Match match, PlayerId playerId)
    {
        DebugUtility.simpleDebugMessage("Avvio disconnessione player "+connection.getConnectionMessage().getUsername());
        DebugUtility.simpleDebugMessage("Invocato da "+DebugUtility.getCaller());

        Match match = communicationInterfaceToMatch(connection);
        PlayerId playerId = match.connectionToPlayerId(connection);

        DebugUtility.simpleDebugMessage(/*"MatchTable":disconnectClient()>*/"disconnetto client "+connection.toString()+
                          " dal match "+match.getMatchId());
        this.disconnections.add(new DisconnectedClient(connection.getConnectionMessage(), match, playerId));
        match.disconnectPlayer(playerId);

    }

    /**
     * Invocato dal Match, imposta a true il flag Started nella riga del match che lo ha invocato
     * @param match il match che è iniziato
     */
    public void notifyMatchStarted(Match match)
    {
        int i = 0;
        boolean trovato = false;
        while ( !trovato  &&  i < matchTable.size() )
        {
            MatchRow currentRow = matchTable.get(i);
            if( currentRow.getMatch() == match ) {
                trovato = true;
                currentRow.setStarted(true);
            }
            i++;
        }

    }

    /**
     * Ritorna se il match indicato è iniziato (true) o meno (false). Se il match non esiste ritorna true di
     * default, in modo che si interagisca il meno possibile con il match.
     * @param match il match di cui si vuole conoscere lo stato
     * @return lo stato di esecuzione del match (iniziato - non iniziato)
     */
    public boolean isMatchStarted(Match match) {
        int i = 0;
        boolean trovato = false;
        while (!trovato && i < matchTable.size()) {
            MatchRow currentRow = matchTable.get(i);
            if (currentRow.getMatch() == match) {
                return currentRow.isStarted();
            }
        }
        //TODO eccezione
        return true;
    }

    public void printTable()
    {
        String tableStr="===========[MATCH TABLE]===========\n";
        for ( MatchRow row : matchTable ){
            tableStr += row.printRow();
        }

        System.out.println(tableStr);
    }
    //global = 3;
}