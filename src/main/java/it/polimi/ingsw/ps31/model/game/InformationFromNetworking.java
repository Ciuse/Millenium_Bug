package it.polimi.ingsw.ps31.model.game;

import it.polimi.ingsw.ps31.DebugUtility;
import it.polimi.ingsw.ps31.client.view.TypeOfView;
import it.polimi.ingsw.ps31.model.ModelChoices;
import java.util.ArrayList;
import java.util.List;
import it.polimi.ingsw.ps31.server.Match;
/**
 * Created by giulia on 21/06/2017.
 *
 * Classe che fa da ponte tra il Model e le informazioni che arrivano dai client prima che la partita sia iniziata
 * Il Match prima di partire raccoglie le informazioni dei client (Nome scelto, Interfaccia grafica scelta) e le setta all interno di questa classe
 *
 * @see GameLogic
 * @see ModelChoices
 * @see Match
 */
public class InformationFromNetworking {
    private List<String> playerNameList;
    private List<TypeOfView> viewChoiceList;
    private int size = 0;

    public InformationFromNetworking()
    {
        this.playerNameList = new ArrayList<>();
        this.viewChoiceList = new ArrayList<>();
    }

    public int addPlayerViewChoice(TypeOfView typeOfView, String username){
        DebugUtility.simpleDebugMessage("Invocato. Username: "+username+"; TOV = "+typeOfView);
        viewChoiceList.add(typeOfView);
        playerNameList.add(username);
        this.size ++;

        return this.size;
    }

    public List<String> getPlayerNameList() {
        return playerNameList;
    }

    public List<TypeOfView> getViewChoiceList() {
        return viewChoiceList;
    }


}
