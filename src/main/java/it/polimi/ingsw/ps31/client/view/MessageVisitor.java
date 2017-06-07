package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.server.message.*;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MessageVisitor implements MexVisitor {
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

    public void visit(MexStateInfo mexStateInfo) {
        MesStateInfoVisitor mesStateInfoVisitor=new MesStateInfoVisitor();
        mexStateInfo.getStateInfo().acceptState(mesStateInfoVisitor);
    }

    @Override
    public void visit(MexToPrint mexToPrint) {

    }
}
