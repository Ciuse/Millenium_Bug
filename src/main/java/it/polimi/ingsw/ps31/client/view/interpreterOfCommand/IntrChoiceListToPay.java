package it.polimi.ingsw.ps31.client.view.interpreterOfCommand;

import it.polimi.ingsw.ps31.client.view.CmdLineView;
import it.polimi.ingsw.ps31.messages.messageVC.VCActiveEffectChoice;
import it.polimi.ingsw.ps31.messages.messageVC.VCListToPayChoice;
import it.polimi.ingsw.ps31.model.choiceType.ChoiceType;

/**
 * Created by Giuseppe on 24/06/2017.
 */
public class IntrChoiceListToPay implements  CmdInterpreterView {
    @Override
    public void notGameMessageInterpreter(CmdLineView terminalView, Character in) {

    }

    @Override
    public boolean messageInterpreter(CmdLineView terminalView, ChoiceType choiceType, Character in) {
        if (in != null) {
            if (in == 1) {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCListToPayChoice(terminalView.getViewId(),in));
                return true;
            }
            if (in == 2) {
                terminalView.printLastEvent("Comando OK");
                terminalView.notifyController(new VCListToPayChoice(terminalView.getViewId(),in));
                return true;
            }
            terminalView.printLastEvent("Comando Non Riconusciuto");
            return false;

        } else {
            terminalView.printLastEvent("Comando Non Rilevato");
            return false;
        }    }
}
