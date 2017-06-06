package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.server.message.*;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MessageVisitor extends View implements Visitor {

    @Override
    public void visit(Messaggio1 messaggio1){
        System.out.println("RICHIESTA NOME:");
        super.inserisciNome();

    }

    @Override
    public void visit(Messaggio2 messaggio1) {

        System.out.println("RICHIESTA COLORE:");
        super.inserisciColore();

    }
    @Override
    public void visit(MessageToString messaggioToString){
        super.objectToString(messaggioToString.getObjectToString());
    }
    @Override
    public void visit(MesNewPlayer mesNewPlayer){

    }

}
