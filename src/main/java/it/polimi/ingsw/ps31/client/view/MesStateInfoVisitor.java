package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.model.StateModel.*;
import it.polimi.ingsw.ps31.server.message.MesVisitor;
import it.polimi.ingsw.ps31.server.message.StateVisitor;
import sun.misc.resources.Messages_zh_CN;

/**
 * Created by Giuseppe on 06/06/2017.
 */
public class MesStateInfoVisitor implements StateVisitor {
    @Override
    public void visit(InfoPlayer infoPlayer) {
        System.out.println("INFO PLAYER: "+infoPlayer.getNickname()+infoPlayer.getPlayerColor());
    }

    @Override
    public void visit(StateCardBox stateCardBox) {
        System.out.println("INFO CARTA: "+stateCardBox.getName()+stateCardBox.getCardId()+stateCardBox.getCardColor());
    }

    @Override
    public void visit(StateFamilyMember stateFamilyMember) {

    }

    @Override
    public void visit(StateGame stateGame) {

    }

    @Override
    public void visit(StatePersonalBoard statePersonalBoard) {

    }

    @Override
    public void visit(StatePlayerAction statePlayerAction) {

    }

    @Override
    public void visit(StateResources stateResources) {

    }
}
