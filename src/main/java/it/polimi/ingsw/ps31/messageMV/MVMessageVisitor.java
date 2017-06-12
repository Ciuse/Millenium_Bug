package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.model.StateModel.MVStateInfoVisitor;
import it.polimi.ingsw.ps31.client.view.View;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MVMessageVisitor implements MVVisitor {
    private View view;

    public void setView(View view){
        this.view=view;
    }

    @Override
    public void visit(MVAskColor mvAskColor) {
        view.inserisciColore();
    }

    @Override
    public void visit(MVAskName mvAskName){
        view.inserisciNome();
    }

    @Override
    public void visit(MVToPrint mvToPrint) {

    }

    @Override
    public void visit(MVStateInfo mvStateInfo) {
        MVStateInfoVisitor mvStateInfoVisitor =new MVStateInfoVisitor();
        mvStateInfoVisitor.setView(view);
        mvStateInfo.getStateInfo().acceptState(mvStateInfoVisitor);
    }

}
