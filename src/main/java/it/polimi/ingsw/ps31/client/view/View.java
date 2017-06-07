package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.constants.PlayerId;
import it.polimi.ingsw.ps31.server.message.MexVisitable;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * Created by giulia in 01/06/2017.
 */
public class View implements Observer{
    private final PlayerId viewId;
    private final StateViewBoard stateViewBoard;
    private final List<StateViewPersonalBoard> stateViewPersonalBoard;
    private final List<StateViewPlayer> stateViewPlayer;

    public View(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer) {
        this.viewId = viewId;
        this.stateViewBoard = stateViewBoard;
        this.stateViewPersonalBoard = stateViewPersonalBoard;
        this.stateViewPlayer = stateViewPlayer;
    }

    @Override
    public void update(Observable o, Object arg) {
        MessageVisitor messageVisitor = new MessageVisitor();
        MexVisitable message = (MexVisitable) arg;
        message.accept(messageVisitor);
    }


    public String inserisciNome(){
        Scanner scanner =new Scanner(System.in);
        String in= scanner.nextLine();
        return in;
    }


    public PlayerColor inserisciColore(){
        PlayerColor playerColor=null;
        Boolean ok=false;
        while(ok==false) {
            Scanner scanner =new Scanner(System.in);
            String in= scanner.nextLine();
            if (in.equals(PlayerColor.BLUE.name())) {
                playerColor = PlayerColor.BLUE;
                ok = true;
            } else {
                if (in.equals(PlayerColor.GREEN.name())) {
                    playerColor = PlayerColor.GREEN;
                    ok = true;
                } else {
                    if (in.equals(PlayerColor.YELLOW.name())) {
                        playerColor = PlayerColor.YELLOW;
                        ok = true;
                    } else {
                        if (in.equals(PlayerColor.RED.name())) {
                            playerColor = PlayerColor.RED;
                            ok = true;
                        } else {
                            System.out.println("Hai sbagliato a inserire, reinserisci colore:");
                        }
                    }
                }
            }
        }
        return playerColor;
    }

    public void objectToString (Object o){
        System.out.println(o.toString());
    }
    public void printNewPlayer(){

    }

}