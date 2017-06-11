package it.polimi.ingsw.ps31.client.view;

import it.polimi.ingsw.ps31.server.message.*;

import java.io.IOException;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MessageVisitor implements MexVisitor {
    View view;

    public void setView(View view){
        this.view=view;
    }
    @Override
    public void visit(AskName askName){
        view.inserisciNome();
    }

    @Override
    public void visit(AskColor askColor) throws IOException {
       view.inserisciColore();
    }

    @Override
    public void visit(MexStateInfo mexStateInfo) {
        MesStateInfoVisitor mesStateInfoVisitor=new MesStateInfoVisitor();
        mesStateInfoVisitor.setView(view);
        mexStateInfo.getStateInfo().acceptState(mesStateInfoVisitor);
    }

    @Override
    public void visit(MexToPrint mexToPrint) {

    }
}
