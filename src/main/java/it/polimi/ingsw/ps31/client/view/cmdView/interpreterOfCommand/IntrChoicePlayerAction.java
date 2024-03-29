package it.polimi.ingsw.ps31.client.view.cmdView.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.cmdView.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCPlayerAction;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 18/06/2017.
 *
 *  Interprete dei comandi relativo alla scelta di quale azione si vuole eseguire
 *
 * @see VCPlayerAction
 */
public class IntrChoicePlayerAction implements CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            for (Integer i = 1; i < terminalView.getMyStateViewPlayer().getStringPlayerAction().size() + 1; i++) {
                if (in.compareTo(i.toString().charAt(0))==0) {
                    terminalView.printLastEvent("Comando OK");
                    terminalView.notifyController(new VCPlayerAction(terminalView.getViewId(),terminalView.getMyStateViewPlayer().getStringPlayerAction().get(i - 1)));
                    return true;
                }
            }
            terminalView.printLastEvent("Comando Non Riconusciuto");
            return false;
        } else {
            terminalView.printLastEvent("Comando Non Rilevato");
            return false;
        }
    }

    @Override
    public boolean messageInterpreter2(CmdLineView terminalView, ChoiceType choiceType, Character in1, Character in2) {
        return false;
    }
}
