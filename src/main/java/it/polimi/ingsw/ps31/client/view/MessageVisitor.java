package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.server.message.*;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MessageVisitor implements MesVisitor {
    View view;
    @Override
    public void visit(Messaggio1 messaggio1){
        System.out.println("RICHIESTA NOME:");
        view.inserisciNome();
    }

    @Override
    public void visit(Messaggio2 messaggio1) {

        System.out.println("RICHIESTA COLORE:");
        view.inserisciColore();

    }

    public void visit(MesStateInfo mesStateInfo) {
        MesStateInfoVisitor mesStateInfoVisitor=new MesStateInfoVisitor();
        StateVisitable message = (StateVisitable) mesStateInfo.getStateInfo();

        message.acceptState(mesStateInfoVisitor);
    }
}
