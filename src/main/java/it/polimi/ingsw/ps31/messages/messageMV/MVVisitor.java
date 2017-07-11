package it.polimi.ingsw.ps31.messages.messageMV;


/**
 * Created by Giuseppe on 05/06/2017.
 *
 * Interfaccia del pattern Visitor (dei messaggi), implementata dall'oggetto visitatore che sa come visitare i vari oggetti diversi
 *
 * @see MVMessageVisitor
 */
public interface MVVisitor {
    public void visit(MVStringToPrint mvStringToPrint);
    public void visit(MVUpdateState mvUpdateState);
    public void visit(MVAskChoice mvAskChoice);
}
