package it.polimi.ingsw.ps31.messages.messageMV;

import it.polimi.ingsw.ps31.client.view.View;
import it.polimi.ingsw.ps31.model.choiceType.MVChoiceInfoVisitor;
import it.polimi.ingsw.ps31.model.stateModel.MVStateInfoVisitor;

/**
 * Created by Giuseppe on 05/06/2017.
 *
 * Implementazione del pattern visitor all'interno della logica dei messaggi.
 * I messaggi sapranno una volta visitati sapranno che metodi invocare sulla view.
 * A volte è neccessarrio che i messaggi a loro volta visitino il loro contenuto
 */
public class MVMessageVisitor  implements MVVisitor {
    private View view;

    public void setView(View view){
        this.view=view;
    }

    /**
     * I messaggi di tipo "stampa semplice" una volta accettati invocheranno sulla view un metodo di stampa
     * @param mvStringToPrint messaggio di tipo stampa
     */
    @Override
    public void visit(MVStringToPrint mvStringToPrint) {
        view.printLastEvent(mvStringToPrint.getStringToPrint()+" ");
    }

    /**
     * I messaggi di tipo "aggiornamento stato" una volta ricevuti stampano una stringa, poi analizzano sempre attraverso un altro
     * pattern visitor il contenut del proprio messaggio.
     * @param mvUpdateState messaggio di tipo stato
     */
    @Override
    public void visit(MVUpdateState mvUpdateState) {
        if(mvUpdateState.getStringToPrint()!=null) {
            view.printLastState(mvUpdateState.getStringToPrint() + " ");
        }
        MVStateInfoVisitor mvStateInfoVisitor =new MVStateInfoVisitor();
        mvStateInfoVisitor.setView(view);
        mvUpdateState.getStateType().acceptState(mvStateInfoVisitor);
    }
    /**
     * I messaggi di tipo "scelta" una volta ricevuti stampano una stringa, poi analizzano sempre attraverso un altro
     * pattern visitor il contenuto del proprio messaggio.
     * @param mvAskChoice messaggio di tipo scelta
     */
    @Override
    public void visit(MVAskChoice mvAskChoice) {
        view.printLastEvent(mvAskChoice.getStringToPrint()+" ");
        MVChoiceInfoVisitor mvChoiceInfoVisitor=new MVChoiceInfoVisitor();
        mvChoiceInfoVisitor.setView(view);
        mvAskChoice.getChoiceType().acceptChoice(mvChoiceInfoVisitor);
    }
}
