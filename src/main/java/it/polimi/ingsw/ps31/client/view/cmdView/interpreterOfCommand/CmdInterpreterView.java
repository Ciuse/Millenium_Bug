package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 08/06/2017.
 *
 * Classe generica dell'interpretazione di un comando
 * Vi sono vari comandi possibili, con 1 o 2 caratteri in ingresso e/o un messaggio di tipo scelta associato
 * Uun comando dopo essere stato interpretato nel modo giusto, assicurandosi che il player abbia
 * messo solo un valore sensato inerente alla scelta, viene inviato al controller dentro un messaggio di scelta
 * del tipo che si stava controllando
 *
 * @see it.polimi.ingsw.ps31.messages.messageVC.VCVisitable
 */
public interface CmdInterpreterView {
    void notGameMessageInterpreter(CmdLineView terminalView, Character in);

    boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in);

    boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2);

    @Override
    String toString();
}
