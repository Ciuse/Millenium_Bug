package it.polimi.ingsw.ps31.messageMV;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.model.stateChoice.MVChoiceInfoVisitor;
import it.polimi.ingsw.ps31.model.stateModel.MVStateInfoVisitor;

/**
 * Created by Giuseppe on 05/06/2017.
 */
public class MVMessageVisitor implements MVVisitor {
    private View view;

    public void setView(View view){
        this.view=view;
    }

    @Override
    public void visit(MVStringToPrint mvStringToPrint) {
        view.printLastEvent(mvStringToPrint.getStringToPrint());
    }

    @Override
    public void visit(MVUpdateState mvUpdateState) {
        view.printLastEvent(mvUpdateState.getStringToPrint());
        MVStateInfoVisitor mvStateInfoVisitor =new MVStateInfoVisitor();
        mvStateInfoVisitor.setView(view);
        mvUpdateState.getStateType().acceptState(mvStateInfoVisitor);
    }

    @Override
    public void visit(MVAskChoice mvAskChoice) {
        view.printLastEvent(mvAskChoice.getStringToPrint());
        MVChoiceInfoVisitor mvChoiceInfoVisitor=new MVChoiceInfoVisitor();
        mvChoiceInfoVisitor.setView(view);
        mvAskChoice.getChoiceType().acceptChoice(mvChoiceInfoVisitor);
    }

}
