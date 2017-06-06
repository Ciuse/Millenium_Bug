package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.server.message.Messaggio1;
import it.polimi.ingsw.ps31.server.message.Messaggio2;
import it.polimi.ingsw.ps31.server.message.Visitor;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MessageVisitor extends View implements Visitor {


    public void visit(Messaggio1 messaggio1){
        System.out.println("RICHIESTA NOME:");
        super.inserisciNome();

    }

    @Override
    public void visit(Messaggio2 messaggio1) {

        System.out.println("RICHIESTA COLORE:");
        super.inserisciColore();

    }
}
