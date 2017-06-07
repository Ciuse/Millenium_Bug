package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.client.view.stateView.StateViewBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPersonalBoard;
import it.polimi.ingsw.ps31.client.view.stateView.StateViewPlayer;
import it.polimi.ingsw.ps31.model.constants.PlayerColor;
import it.polimi.ingsw.ps31.model.constants.PlayerId;

import java.util.List;
import java.util.Scanner;

/**
 * Created by Giuseppe on 07/06/2017.
 */
public class TerminalView extends  View {

    public TerminalView(PlayerId viewId, StateViewBoard stateViewBoard, List<StateViewPersonalBoard> stateViewPersonalBoard, List<StateViewPlayer> stateViewPlayer) {
        super(viewId, stateViewBoard, stateViewPersonalBoard, stateViewPlayer);
    }

    @Override
    public String inserisciNome() {
        System.out.println("Inserisci Nome: ");
        Scanner scanner = new Scanner(System.in);
        String in = scanner.nextLine();
        return in;
    }


    @Override
    public PlayerColor inserisciColore() {
        System.out.println("Inserisci Colore");
        PlayerColor playerColor = null;
        Boolean ok = false;
        while (ok == false) {
            Scanner scanner = new Scanner(System.in);
            String in = scanner.nextLine();
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
}
